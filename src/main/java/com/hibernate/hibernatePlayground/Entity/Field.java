package com.hibernate.hibernatePlayground.Entity;

import com.hibernate.hibernatePlayground.Entity.Enum.EInputType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fieldName")
    private String fieldName;

    @Column(name = "fieldType")
    @Enumerated(EnumType.STRING)
    private EInputType fieldType;

    @Column(name="required")
    private Boolean required;

    @Column(name="min")
    private int min;

    @Column(name = "max")
    private int max;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Options> options;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @OneToMany(mappedBy = "field")
    private List<FieldsValue> fieldsValues;


}
