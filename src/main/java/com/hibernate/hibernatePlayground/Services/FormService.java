package com.hibernate.hibernatePlayground.Services;

import com.hibernate.hibernatePlayground.Entity.Dto.FormDto;
import com.hibernate.hibernatePlayground.Entity.Enum.EInputType;
import com.hibernate.hibernatePlayground.Entity.Form;
import com.hibernate.hibernatePlayground.Entity.Mapper.FormMapper;
import com.hibernate.hibernatePlayground.Repo.FormRepo;
import com.hibernate.hibernatePlayground.exception.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FormService {

    private final FormMapper formMapper;
    private final FormRepo formRepo;

    public void formValidate(Form form) throws ExceptionHandler {
        for(int i=0; i < form.getFields().size(); i++){
            if((form.getFields().get(i).getFieldType().equals(EInputType.CHECKBOX)
                    || form.getFields().get(i).getFieldType().equals(EInputType.RADIO)
                    || form.getFields().get(i).getFieldType().equals(EInputType.SELECT))
                    && (form.getFields().get(i).getOptions() == null || form.getFields().get(i).getOptions().size() < 2)){
                throw new ExceptionHandler("you have to add at least 2 option for field ("
                        + form.getFields().get(i).getFieldName() + ")" );
            }
        }
    }

    public Form formIsPresent(Long id) throws ExceptionHandler{
        Optional<Form> form = formRepo.findById(id);
        return form.orElseThrow(()-> new ExceptionHandler("form not found"));
    }

    public void setFormRelationship(Form form){
        form.getFields().forEach(field -> {
            field.setForm(form);
            if(field.getFieldType().equals(EInputType.CHECKBOX)|| field.getFieldType().equals(EInputType.RADIO)
                    || field.getFieldType().equals(EInputType.SELECT)){
                field.getOptions().forEach(options -> options.setField(field));
            }
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void createForm(FormDto formDto) throws ExceptionHandler {
        Form form = formMapper.toEntity(formDto);
        // add validation for the option - should at least have two option if the field type is CHECKBOX | RADIO | SELECT
        // TODO: (SUGGESTION) add fixed options like list of CITY - YES&NO
        formValidate(form);
        Form newForm = formRepo.save(form);
        setFormRelationship(newForm);
    }

    public List<FormDto> listForm (){
        List<Form> form =formRepo.findAll();
        return formMapper.toDTO(form);
    }



    @Transactional(rollbackFor = Exception.class)
    public void updateForm(Long id, FormDto formDto) throws ExceptionHandler {
        // check if form exists
        formIsPresent(id);

        Form form = formMapper.toEntity(formDto);
        formValidate(form);
        Form updatedForm =formRepo.save(form);
        setFormRelationship(updatedForm);

    }

}
