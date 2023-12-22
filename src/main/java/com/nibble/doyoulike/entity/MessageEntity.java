package com.nibble.doyoulike.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class MessageEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "memberId", nullable = false)
    private MemberEntity sender;

    @Id
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "memberId", nullable = false)
    private MemberEntity receiver;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime timestamp;

}