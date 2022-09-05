package main.server.friends;

import main.server.account.Account;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public UserRelationship(Long id, Account account, Account friend, Room room, Date createdAt, Date updatedAt) {
        this.id = id;
        this.account = account;
        this.friend = friend;
        this.room = room;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserRelationship(Account account, Account friend, Room room) {
        this.account = account;
        this.friend = friend;
        this.room = room;
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
}
