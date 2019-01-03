package pl.sdacademy.dbConnecton.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Embedded
    private PersonalData personalData;
    private String phoneNumber;
    @Column(name = "address")
    private String homeAddress;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<UserRole> privileges;
    private boolean removed;

    public LibraryUser() {
        privileges = new ArrayList<>();
        removed = false;
    }

    public LibraryUser(Long id, String username, String password, PersonalData personalData, String phoneNumber, String homeAddress, List<UserRole> privileges) {
        this();
        this.id = id;
        this.username = username;
        this.password = password;
        this.personalData = personalData;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.privileges = privileges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String address) {
        this.homeAddress = address;
    }

    public List<UserRole> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<UserRole> privileges) {
        this.privileges = privileges;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public boolean isAdmin() {
        return privileges.stream().map(UserRole::getRoleName).anyMatch(roleName -> roleName.equalsIgnoreCase("admin"));

    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData( String firstName, String lastName) {
        PersonalData personalData = new PersonalData();
        personalData.setFirstName(firstName);
        personalData.setLastName(lastName);
        this.personalData = personalData;
    }
}
