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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SubmissionService {

    private final SubmissionMapper submissionMapper;
    private final SubmissionRepository submissionRepository;
    private final FormRepo formRepo;
    private final FieldRepository fieldRepository;

//    TODO:add all validation methods in sperate class
    public  static boolean isEmail(String email){
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(email).matches();
    }

    public static boolean isValidDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException ps){
            return false;
        }
        return true;
    }

    public static boolean isNumber(String number){
        try {
            Float.parseFloat(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void FormSubmission (SubmissionDto submissionDto) throws Exception {
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
        //TODO: should add validation related to each field
        for(int i=0; i < fields.size(); i++){
            boolean fieldSubmited = false;
           for(int j=0; j <submission.getFieldsValues().size(); j ++){
               if(submission.getFieldsValues().get(j).getField().getId().equals(fields.get(i).getId())) {
                   fieldSubmited = true;
                   if(fields.get(i).getFieldType().equals(EInputType.EMAIL) && !isEmail(submission.getFieldsValues()
                           .get(j).getValue())){
                       throw new Exception("please provide valid email with question number "
                               + submission.getFieldsValues().get(j).getField().getId());
                   }
                   if(fields.get(i).getFieldType().equals(EInputType.DATE) && !isValidDate(submission.getFieldsValues()
                           .get(j).getValue())){
                       throw new Exception("please provide valid Date with question number "
                               + submission.getFieldsValues().get(j).getField().getId());
                   }
                   if(fields.get(i).getFieldType().equals(EInputType.NUMBER) && !isNumber(submission.getFieldsValues()
                           .get(j).getValue())){
                       throw new Exception("please provide valid Number with question number "
                               + submission.getFieldsValues().get(j).getField().getId());
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
