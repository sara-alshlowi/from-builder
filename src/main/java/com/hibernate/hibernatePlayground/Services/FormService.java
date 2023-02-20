package com.hibernate.hibernatePlayground.Services;

import com.hibernate.hibernatePlayground.Entity.Dto.FormDto;
import com.hibernate.hibernatePlayground.Entity.Form;
import com.hibernate.hibernatePlayground.Entity.Mapper.FormMapper;
import com.hibernate.hibernatePlayground.Repo.FormRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FormService {

    private final FormMapper formMapper;
    private final FormRepo formRepo;

    @Transactional(rollbackFor = Exception.class)
    public void createForm(FormDto formDto){
        Form form = formMapper.toEntity(formDto);
        Form newForm = formRepo.save(form);
        newForm.getFields().forEach(field -> field.setForm(newForm));

    }

    public List<FormDto> listForm (){
        List<Form> form =formRepo.findAll();
        return formMapper.toDTO(form);
    }

}
