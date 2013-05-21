package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

import static play.data.validation.Constraints.Required;

@Entity
@Table(name = "APARTMENT_TYPE")
public class ApartmentType extends Model {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Required
    public String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public static Finder<Long,ApartmentType> find = new Finder<Long, ApartmentType>(Long.class, ApartmentType.class);

    public static List<ApartmentType> all() {
        return find.all();
    }

    public static ApartmentType get(Long id) {
        return find.byId(id);
    }

    public static void save(ApartmentType type) {
        type.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

}
