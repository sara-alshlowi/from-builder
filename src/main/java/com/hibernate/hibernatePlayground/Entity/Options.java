package com.hibernate.hibernatePlayground.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Key")
    private String key;

    @Column(name="value")
    private  String value;

    @Column(name = "lable")
    private String lable;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;
}
