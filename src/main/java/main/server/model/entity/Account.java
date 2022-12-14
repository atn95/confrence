package main.server.model.entity;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account{
    @Id
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private Long id;
    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    @JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String displayName;
    private String firstName;
    private String lastName;
    @Column(columnDefinition = "smallint default 0")
    private Short status = 0;


    @JsonManagedReference
    @OneToMany(mappedBy = "account")
    private Set<UserRelationship> friends;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;


    @UpdateTimestamp
    private Date updatedAt;

    public Account(Long id, String email, String password, String displayName, String firstName, String lastName){
        this.id = id;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account(String email, String password, String displayName, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<UserRelationship> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserRelationship> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
