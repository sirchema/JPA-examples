package chema.jpa.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class People {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;

    @ManyToOne
    private People father;

    @OneToMany (mappedBy = "owner")
    private Set<Car> carsSet = new HashSet<>();

    public People() {}

    public People(String name) {
        this.name = name;
    }

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

    public Set<Car> getCarsSet() {
        return carsSet;
    }

    public void setCarsSet(Set<Car> carsSet) {
        this.carsSet = carsSet;
    }

    public People getFather() {
        return father;
    }

    public void setFather(People father) {
        this.father = father;
    }
}
