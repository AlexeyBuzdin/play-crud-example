package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

import static play.data.validation.Constraints.Required;
import static play.db.ebean.Model.Finder;

@Entity
@Table(name = "HOTEL_SERVICE")
public class HotelService extends Model {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Required
    @Column(name = "NAME")
    public String name;

    @Required
    @Column(name = "ALIAS")
    public String alias;

    @Required
    @Column(name = "PRICE")
    public Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }


    public static Finder<Long, HotelService> find = new Finder<Long, HotelService>(Long.class, HotelService.class);

    public static List<HotelService> all() {
        return find.all();
    }

    public static HotelService get(Long id) {
        return find.byId(id);
    }

    public static void save(HotelService hotelService) {
        hotelService.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static void update(HotelService hotelService, Long id) {
        hotelService.update(id);
    }
}
