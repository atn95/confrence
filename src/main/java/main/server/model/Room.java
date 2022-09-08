package main.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table
public class Room {
    @Id
    @SequenceGenerator(name ="room_sequence", sequenceName = "room_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
    private Long id;


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonBackReference
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<ChatLog> messages;


    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
    private Set<UserRelationship> relation;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;


    public Room(Long id, Set<ChatLog> log, Date createdAt, Date updatedAt) {
        this.id = id;
        this.messages = log;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Room(Set<ChatLog> log) {
        this.messages = log;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stream<ChatLog> getLog() {
        return messages.stream().sorted((log1, log2) -> log2.getCreatedAt().compareTo(log1.getCreatedAt())).limit(20);
    }

    public void setLog(java.util.Set<ChatLog> log) {
        this.messages = log;
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
                ", log=" + messages +
                ", relation=" + relation +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
