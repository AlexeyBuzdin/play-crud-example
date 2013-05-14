package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "APARTMENT_PROPOSAL")
public class ApartmentProposal extends Model {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "DATE_FROM")
    public Date dateFrom;

    @Column(name = "DATE_TO")
    public Date dateTo;

    @Column(name = "PRICE")
    public Long price;

    @ManyToOne
    @JoinColumn(name = "APARTMENT_ID")
    public Apartment apartment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }


    public static Finder<Long,ApartmentProposal> find = new Finder<Long, ApartmentProposal>(Long.class, ApartmentProposal.class);

    public static List<ApartmentProposal> all() {
        return find.all();
    }

    public static ApartmentProposal get(Long id) {
        return find.byId(id);
    }

    public static void save(ApartmentProposal proposal) {
        proposal.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }
}
