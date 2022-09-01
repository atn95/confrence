package main.server.friends;

import main.server.chat.ChatLog;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
public class Room {
    @Id
    @SequenceGenerator(name ="room_sequence", sequenceName = "room_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
    private Long id;

    @OneToMany(mappedBy = "room")
    private Set<ChatLog> log;
    @OneToMany(mappedBy = "room")
    private Set<UserRelationship> relation;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Room(Long id, Set<ChatLog> log, Set<UserRelationship> relation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.log = log;
        this.relation = relation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Room(Set<ChatLog> log, Set<UserRelationship> relation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.log = log;
        this.relation = relation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<UserRelationship> getRelation() {
        return relation;
    }

    public void setRelation(Set<UserRelationship> relation) {
        this.relation = relation;
    }
}
