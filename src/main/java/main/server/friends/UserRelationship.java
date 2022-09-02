package main.server.friends;

import main.server.account.Account;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class UserRelationship {
    @Id
    @SequenceGenerator(name ="user_relationship_sequence", sequenceName = "user_relationship_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_relationship_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "friend")
    private Account friend;
    @ManyToOne
    @JoinColumn(name ="room")
    private Room room;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserRelationship(Long id, Account account, Account friend, Room room, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.account = account;
        this.friend = friend;
        this.room = room;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserRelationship(Account account, Account friend, Room room, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.account = account;
        this.friend = friend;
        this.room = room;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserRelationship() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getFriend() {
        return friend;
    }

    public void setFriend(Account friend) {
        this.friend = friend;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
}