package chema.jpa.demo;

import jakarta.persistence.*;

@Entity
public class Persona {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private int consultas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getConsultas() {
        return consultas;
    }

    public void setConsultas(int consultas) {
        this.consultas = consultas;
    }
}
