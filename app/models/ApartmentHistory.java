package models;

import com.avaje.ebean.*;
import com.avaje.ebean.Query;
import play.db.DB;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "APARTMENT_HISTORY")
public class ApartmentHistory extends Model{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "BOOKED_FROM")
    public Date bookedFrom;

    @Column(name = "BOOKED_TO")
    public Date bookedTo;

    @ManyToOne
    @JoinColumn(name = "APARTMENT_PROPOSAL_ID", nullable = false)
    public ApartmentProposal proposal;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    public Client client;

    @Transient
    public List<HotelService> hotelServices = new ArrayList<HotelService>();

    public ApartmentHistory() {
    }

    public ApartmentHistory(Date bookedFrom, Date bookedTo, ApartmentProposal apartmentProposal, Client client) {
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.proposal = apartmentProposal;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(Date bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public Date getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(Date bookedTo) {
        this.bookedTo = bookedTo;
    }

    public ApartmentProposal getProposal() {
        return proposal;
    }

    public void setProposal(ApartmentProposal proposal) {
        this.proposal = proposal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<HotelService> getHotelServices() {
        return hotelServices;
    }

    public void setHotelServices(List<HotelService> hotelServices) {
        this.hotelServices = hotelServices;
    }

    public static Finder<Long,ApartmentHistory> find = new Finder<Long, ApartmentHistory>(Long.class, ApartmentHistory.class);

    public static List<ApartmentHistory> all() {
        return find.all();
    }

    public static ApartmentHistory get(Long id) {
        return find.byId(id);
    }

    public static void save(ApartmentHistory proposal) {
        proposal.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

}
