package pl.sdacademy.dbConnecton.model;

import javax.persistence.*;


@Entity
@Table(name = "Author")
public class Writer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private PersonalData personalData;

    public Writer() {
    }


    public Writer(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
