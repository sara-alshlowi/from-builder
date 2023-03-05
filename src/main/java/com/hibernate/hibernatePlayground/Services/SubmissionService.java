package com.hibernate.hibernatePlayground.Services;

import com.hibernate.hibernatePlayground.Entity.Dto.SubmissionDto;
import com.hibernate.hibernatePlayground.Entity.Field;
import com.hibernate.hibernatePlayground.Entity.Form;
import com.hibernate.hibernatePlayground.Entity.Mapper.SubmissionMapper;
import com.hibernate.hibernatePlayground.Entity.Submission;
import com.hibernate.hibernatePlayground.Repo.FieldRepository;
import com.hibernate.hibernatePlayground.Repo.FormRepo;
import com.hibernate.hibernatePlayground.Repo.SubmissionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
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
    public void FormSubmission (SubmissionDto submissionDto) throws Exception {
//        TODO: add exception handler

        Submission submission = submissionMapper.toEntity(submissionDto);
        Optional<Form> form = formRepo.findById(submission.getForm().getId());

        // check if the form exist
        if(form.isEmpty()){
            throw new Exception("there is no form");
        }

        //the field submitted should be in the fields related to the form
        //TODO: should add validation related to each field
        for(int i =0 ; i <submission.getFieldsValues().size(); i++ ){
            if(!fieldRepository.existsByIdAndForm_Id(submission.getFieldsValues().get(i).getField().getId(),
                    submission.getForm().getId())){
                throw new Exception("the field id (" + submission.getFieldsValues().get(i).getField().getId() +
                        ") not related to the form" );
            }
        }

        Submission newSubmission = submissionRepository.save(submission);
        newSubmission.getFieldsValues().forEach(fieldsValue -> fieldsValue.setSubmission(newSubmission));

    }
}
