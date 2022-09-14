package main.server.model;

import java.util.Date;

public class SearchResponseAccountDTO {
    private Long id;
    private String email;
    private String displayName;
    private String firstName;
    private String lastName;
    private Short status;
    private Date createdAt;
    private Date updatedAt;

    public Boolean getSentRequest() {
        return sentRequest;
    }

    public void setSentRequest(Boolean sentRequest) {
        this.sentRequest = sentRequest;
    }

    private Boolean sentRequest;

    public SearchResponseAccountDTO(Long id, String email, String displayName, String firstName, String lastName, Short status, Date createdAt, Date updatedAt, Boolean sentRequest) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sentRequest = sentRequest;
    }

    public SearchResponseAccountDTO() {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SearchResponseAccountDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sentRequest=" + sentRequest +
                '}';
    }
}
