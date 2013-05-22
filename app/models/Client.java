package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

import static play.data.validation.Constraints.Required;
import static play.db.ebean.Model.Finder;

@Entity
@Table(name = "CLIENT")
public class Client extends Model{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Required
    @Column(name = "FIRST_NAME")
    public String firstName;

    @Required
    @Column(name = "LAST_NAME")
    public String lastName;

    @Required
    @Column(name = "EMAIL")
    public String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public static Finder<Long,Client> find = new Finder<Long, Client>(Long.class, Client.class);

    public static List<Client> all() {
        return find.all();
    }

    public static Client get(Long id) {
        return find.byId(id);
    }

    public static void save(Client client) {
        client.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static void update(Client client, Long id) {
        client.update(id);
    }
}
