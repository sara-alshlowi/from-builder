package com.hibernate.hibernatePlayground.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="fieldName")
    private String fieldName;

    @Column(name = "fieldType")
    private String fieldType;  // should be enum

    @Column(name="required")
    private Boolean required;

    @Column(name="min")
    private int min;

    @Column(name = "max")
    private int max;

    @OneToMany(mappedBy = "field")
    private List<Options> options;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;


}
