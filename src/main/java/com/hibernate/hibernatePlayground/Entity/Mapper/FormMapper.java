package com.hibernate.hibernatePlayground.Entity.Mapper;

import com.hibernate.hibernatePlayground.Entity.Dto.FormDto;
import com.hibernate.hibernatePlayground.Entity.Form;
import com.hibernate.hibernatePlayground.common.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormMapper extends AbstractMapper<Form, FormDto> {

}
