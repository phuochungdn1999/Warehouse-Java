package com.example.warehouse.model;

import javax.persistence.*;

@Entity
public class UserHistory {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private  History history;


}
