package com.hibernate.hibernatePlayground.Entity.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SubmissionDto {
    private Long id;
    private FormDto form;
    private List<FieldsValueDto> fieldsValues;
}
