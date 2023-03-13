package com.hibernate.hibernatePlayground.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "form", cascade =CascadeType.ALL)
    private List<Field> fields;

    @OneToMany(mappedBy = "form", cascade =CascadeType.ALL) // need to know the cascade type
    private List<Submission> submission;

//    @CreatedDate
//    @Column(name = "created_by", nullable = false, updatable = false)
//    private Date createdDate;
//
//
//    @LastModifiedDate
//    @Column
//    private Date lastUpdate;

}
