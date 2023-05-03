package chema.spring.boot.jpa.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class People {

    @Id
    private String name;
    private String town;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
