package com.hibernate.hibernatePlayground.Services;

import com.hibernate.hibernatePlayground.Entity.Dto.SubmissionDto;
import com.hibernate.hibernatePlayground.Entity.Enum.EInputType;
import com.hibernate.hibernatePlayground.Entity.Field;
import com.hibernate.hibernatePlayground.Entity.FieldsValue;
import com.hibernate.hibernatePlayground.Entity.Form;
import com.hibernate.hibernatePlayground.Entity.Mapper.SubmissionMapper;
import com.hibernate.hibernatePlayground.Entity.Submission;
import com.hibernate.hibernatePlayground.Repo.FieldRepository;
import com.hibernate.hibernatePlayground.Repo.FormRepo;
import com.hibernate.hibernatePlayground.Repo.SubmissionRepository;
import com.hibernate.hibernatePlayground.exception.ExceptionHandler;
import com.hibernate.hibernatePlayground.utils.InputValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class SubmissionService {

    private final SubmissionMapper submissionMapper;
    private final SubmissionRepository submissionRepository;
    private final FormRepo formRepo;
    private final FieldRepository fieldRepository;

    @Transactional(rollbackFor = Exception.class)
    public void FormSubmission (SubmissionDto submissionDto) throws Exception, ExceptionHandler {
//        TODO: add exception handler

        Submission submission = submissionMapper.toEntity(submissionDto);
        Optional<Form> form = formRepo.findById(submission.getForm().getId());
//      List<Field> requiredFields = fields.stream()
//              .filter(field -> field.getRequired().equals(Boolean.TRUE)).collect(Collectors.toList());

        // check if the form exist
        if(form.isEmpty()){
            throw new Exception("there is no form");
        }

        List<Field> fields = fieldRepository.findAllByForm(form.get());

        //the field submitted should be in the fields related to the form
        for (FieldsValue value: submission.getFieldsValues()){
            if (fields.stream().noneMatch(field -> Objects.equals(value.getField().getId(), field.getId()))){
                throw new Exception("not related to the form "+ value.getField().getId());
            }
        }
        //add validation related to each field
        for(int i=0; i < fields.size(); i++){
            boolean fieldSubmited = false;
           for(int j=0; j <submission.getFieldsValues().size(); j ++){
               if(submission.getFieldsValues().get(j).getField().getId().equals(fields.get(i).getId())) {
                   fieldSubmited = true;
                   if(fields.get(i).getFieldType().equals(EInputType.EMAIL) && !InputValidation.isEmail(submission.getFieldsValues()
                           .get(j).getValue())){
                       throw new Exception("please provide valid email with question number "
                               + submission.getFieldsValues().get(j).getField().getId());
                   }
                   if(fields.get(i).getFieldType().equals(EInputType.DATE) && !InputValidation.isValidDate(submission.getFieldsValues()
                           .get(j).getValue())){
                       throw new Exception("please provide valid Date with question number "
                               + submission.getFieldsValues().get(j).getField().getId());
                   }
                   if(fields.get(i).getFieldType().equals(EInputType.NUMBER) && !InputValidation.isNumber(submission.getFieldsValues()
                           .get(j).getValue())){
                       throw new ExceptionHandler("please provide valid Number with question number "
                               + submission.getFieldsValues().get(j).getField().getId());
                   }
                   if(submission.getFieldsValues().get(j).getValue().length() < fields.get(i).getMin()){
                       throw new Exception("the minimum character is " + fields.get(i).getMin());
                   }
                   if(submission.getFieldsValues().get(j).getValue().length() > fields.get(i).getMax()){
                       throw new Exception("the Maximum character is " + fields.get(i).getMax());
                   }
                   // TODO: check the option here - check if the answer is related to the fields options
                   if(fields.get(i).getFieldType().equals(EInputType.CHECKBOX)
                            || fields.get(i).getFieldType().equals(EInputType.RADIO)
                           || fields.get(i).getFieldType().equals(EInputType.SELECT)){
                       System.out.print("check the option here ");
                   }

               }
           }
           if(!fieldSubmited &&  fields.get(i).getRequired()){
               throw new Exception("the field "+ fields.get(i).getId() + "is required");
           }
        }
        Submission newSubmission = submissionRepository.save(submission);
        newSubmission.getFieldsValues().forEach(fieldsValue -> fieldsValue.setSubmission(newSubmission));

    }
}
