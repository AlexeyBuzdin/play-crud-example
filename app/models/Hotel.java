package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import static play.data.validation.Constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel extends Model {

    @Id
    public Long id;
    @Required
    public String name;
    @Required
    public String address;

    public static Finder<Long,Hotel> find = new Finder(
            Long.class, Hotel.class
    );

    public static List<Hotel> all() {
        return find.all();
    }

    public static void create(Hotel hotel) {
        hotel.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static void modify(Long id, Hotel hotel) {

    }
}
