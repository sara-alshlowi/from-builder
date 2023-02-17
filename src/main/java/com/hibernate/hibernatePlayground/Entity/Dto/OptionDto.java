package com.hibernate.hibernatePlayground.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OptionDto {
    private Long id;
    private String key;
    private  String value;
    private String lable;

}
