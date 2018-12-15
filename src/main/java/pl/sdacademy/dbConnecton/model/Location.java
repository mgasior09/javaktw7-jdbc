package pl.sdacademy.dbConnecton.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rackSymbol;
    private String shelfSymbol;
    private Long position;

    public Location() {
    }

    public Location(Long id, String rackSymbol, String shelfSymbol, Long position) {
        this.id = id;
        this.rackSymbol = rackSymbol;
        this.shelfSymbol = shelfSymbol;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRackSymbol() {
        return rackSymbol;
    }

    public void setRackSymbol(String rackSymbol) {
        this.rackSymbol = rackSymbol;
    }

    public String getShelfSymbol() {
        return shelfSymbol;
    }

    public void setShelfSymbol(String shelfSymbol) {
        this.shelfSymbol = shelfSymbol;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
