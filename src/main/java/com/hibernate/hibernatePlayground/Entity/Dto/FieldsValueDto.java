package com.hibernate.hibernatePlayground.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FieldsValueDto {
    private Long id;
    private String value;
    private FieldDto field;


}
