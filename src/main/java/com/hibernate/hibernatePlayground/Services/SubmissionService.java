package com.hibernate.hibernatePlayground.Services;

import com.hibernate.hibernatePlayground.Entity.Dto.SubmissionDto;
import com.hibernate.hibernatePlayground.Entity.Mapper.SubmissionMapper;
import com.hibernate.hibernatePlayground.Entity.Submission;
import com.hibernate.hibernatePlayground.Repo.SubmissionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class SubmissionService {

    private final SubmissionMapper submissionMapper;
    private final SubmissionRepository submissionRepository;

    @Transactional(rollbackFor = Exception.class)
    public void FormSubmission (SubmissionDto submissionDto) {
        Submission submission = submissionMapper.toEntity(submissionDto);
        Submission newSubmission = submissionRepository.save(submission);
        newSubmission.getFieldsValues().forEach(fieldsValue -> fieldsValue.setSubmission(newSubmission));

    }
}
