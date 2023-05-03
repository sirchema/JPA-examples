package chema.jpa.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class People {

    @Id
    private String name;
    private String hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
