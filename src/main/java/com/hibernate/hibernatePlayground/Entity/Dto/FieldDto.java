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
public class FieldDto {

    private Long id;
    private String fieldName;
    private String fieldType;
    private Boolean required;
    private int min;
    private int max;
    private List<OptionDto> option;


}
