package main.server.friends;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import main.server.chat.ChatLog;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Room {
    @Id
    @SequenceGenerator(name ="room_sequence", sequenceName = "room_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<ChatLog> log;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<UserRelationship> relation;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;


    public Room(Long id, Set<ChatLog> log, Date createdAt, Date updatedAt) {
        this.id = id;
        this.log = log;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Room(Set<ChatLog> log) {
        this.log = log;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Set<ChatLog> getLog() {
        return log;
    }

    public void setLog(java.util.Set<ChatLog> log) {
        this.log = log;
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

    public Set<UserRelationship> getRelation() {
        return relation;
    }

    public void setRelation(Set<UserRelationship> relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", log=" + log +
                ", relation=" + relation +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
