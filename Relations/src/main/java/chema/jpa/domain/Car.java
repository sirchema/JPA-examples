package chema.jpa.domain;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    //(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToOne(cascade = CascadeType.PERSIST)//Save People when save car
    private People owner;

    public Car(){
        this(null);
    }

    public Car(People owner) {
        this(null, owner);
    }
    public Car(String model, People owner) {
        this.model = model;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public People getOwner() {
        return owner;
    }

    public void setOwner(People owner) {
        this.owner = owner;
    }
}
