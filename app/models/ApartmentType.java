package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static play.data.validation.Constraints.Required;

@Entity
@Table(name = "APARTMENT_TYPE")
public class ApartmentType extends Model {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Required
    @Column(name = "TITLE")
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


    public static Finder<Long, ApartmentType> find = new Finder<Long, ApartmentType>(Long.class, ApartmentType.class);

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

    public static List<ApartmentType> availableFor(Long hotelId) {

        //TODO: should be
//        select at.ID, at.TITLE,  from APARTMENT_TYPE at
//        inner join APARTMENT a on a.APARTMENT_TYPE_ID = at.ID
//        where a.HOTEL_ID = 1

        List<Apartment> apartments = Apartment.find.where().eq("HOTEL_ID", hotelId).findList();

        Map<Long, ApartmentType> apartmentTypes = new HashMap<Long, ApartmentType>();
        for (Apartment apartment : apartments) {
            Long typeId = apartment.apartmentType.id;
            if (!apartmentTypes.containsKey(typeId)) {
                apartmentTypes.put(typeId, ApartmentType.get(typeId));
            }
        }

        return new ArrayList<ApartmentType>(apartmentTypes.values());
    }
}
