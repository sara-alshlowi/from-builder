package com.hibernate.hibernatePlayground.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormDto {
    private Long id;
    private String name;
    private List<FieldDto> fields;


}
