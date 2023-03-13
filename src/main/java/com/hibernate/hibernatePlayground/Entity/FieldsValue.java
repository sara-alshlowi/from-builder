package com.hibernate.hibernatePlayground.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class FieldsValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name="field_id")
    private Field field;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;
}
