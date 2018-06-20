package org.notifier.sender_management.entity;

import org.hibernate.annotations.Cascade;
import org.notifier.sender_management.service.type.SenderStateType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sender")
public class Sender {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "guid")
    private String guid;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private SenderStateType state;

    @OneToMany(targetEntity = SenderChannel.class, mappedBy = "sender", fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<SenderChannel> senderChannels = new HashSet<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SenderStateType getState() {
        return state;
    }

    public void setState(SenderStateType state) {
        this.state = state;
    }

    public Set<SenderChannel> getSenderChannels() {
        return senderChannels;
    }

    public void setSenderChannels(Set<SenderChannel> senderChannels) {
        this.senderChannels = senderChannels;
    }
}
