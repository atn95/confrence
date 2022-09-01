package main.Server.account;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class Account {
    @Id
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private Long id;
    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;
    private String displayName;
    private String firstName;
    private String lastName;
    private Short status;

    public Account(Long id, String email, String password, String displayName, String firstName, String lastName, Short status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public Account(String email, String password, String displayName, String firstName, String lastName, Short status) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", displayName='" + displayName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                '}';
    }
}
