package org.notifier.sender_management.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscribe_action")
public class SubscribeAction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "subscriber", nullable = false)
    private String subscriber;

    @ManyToOne(targetEntity = Sender.class)
    @JoinColumn(name = "sender_id")
    private Sender sender;

    @ManyToOne(targetEntity = Sender.class)
    @JoinColumn(name = "sender_channel_id")
    private SenderChannel senderChannel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public SenderChannel getSenderChannel() {
        return senderChannel;
    }

    public void setSenderChannel(SenderChannel senderChannel) {
        this.senderChannel = senderChannel;
    }
}
