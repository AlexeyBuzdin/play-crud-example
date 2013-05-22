package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static play.data.validation.Constraints.Required;

@Entity
@Table(name = "APARTMENT")
public class Apartment extends Model {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="HOTEL_ID", nullable=false)
    public Hotel hotel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="APARTMENT_TYPE_ID", nullable=false)
    public ApartmentType apartmentType;

    @OneToMany(cascade = CascadeType.ALL)
    public List<ApartmentProposal> proposals = new ArrayList<ApartmentProposal>();

    @Required
    @Column(name = "TITLE")
    public String title;

    @Required
    @Column(name = "CAPACITY")
    public Integer capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<ApartmentProposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<ApartmentProposal> proposals) {
        this.proposals = proposals;
    }





    public static Finder<Long,Apartment> find = new Finder<Long, Apartment>(Long.class, Apartment.class);

    public static List<Apartment> all() {
        return find.all();
    }

    public static Apartment get(Long id) {
        return find.byId(id);
    }

    public static void save(Apartment apartment) {
        apartment.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static void update(Apartment apartment, Long id) {
        apartment.update(id);
    }
}
