package com.example.warehouse.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class History {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;


    private Date date;

    private String note;

    @OneToMany(mappedBy = "history")
    private Set<UserHistory> userHistories;



    @ManyToOne
    @JoinColumn(name="type_id",nullable = false)
    private HistoryType historyType;

}
