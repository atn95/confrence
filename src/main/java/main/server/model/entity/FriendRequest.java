package main.server.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class FriendRequest {
    @Id
    @SequenceGenerator(name = "friend_request_sequence", sequenceName = "friend_request_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_request_sequence")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requester_id", referencedColumnName = "id")
    private Account requester;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Account receiver;
    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public FriendRequest(Account requester, Account receiver) {
        this.requester = requester;
        this.receiver = receiver;
    }

    public FriendRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequester() {
        return requester.getId();
    }

    public void setRequester(Long requesterID) {
        Account requester = new Account();
                requester.setId(requesterID);
        this.requester = requester;
    }

    public Long getReceiver() {
        return receiver.getId();
    }

    public void setReceiver(Long receiverID) {
        Account receiver = new Account();
        receiver.setId(receiverID);
        this.requester = receiver;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", requester=" + requester +
                ", receiver=" + receiver +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
