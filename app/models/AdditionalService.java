package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "ADDITIONAL_SERVICE")
public class AdditionalService extends Model {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "PRICE")
    public Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
