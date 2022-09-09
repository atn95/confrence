package main.server.model.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@Entity
@Table
public class UserRelationship {
    @Id
    @SequenceGenerator(name ="user_relationship_sequence", sequenceName = "user_relationship_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_relationship_sequence")
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinColumn(name = "account_id")
    private Account account;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Account friend;

    @ManyToOne
    @JoinColumn(name ="room_id")
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

    public HashMap<String, Object> getRoom() {
        HashMap<String, Object> roomMapped = new HashMap<>();
        HashMap<String, Long> relation = new HashMap<>();
        relation.put("user", account.getId());
        relation.put("friend", friend.getId());
        roomMapped.put("id", room.getId());
        roomMapped.put("log", room.getLog());
        roomMapped.put("relation", relation);
        return  roomMapped;
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
