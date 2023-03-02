package com.hibernate.hibernatePlayground.Entity.Mapper;

import com.hibernate.hibernatePlayground.Entity.Dto.SubmissionDto;
import com.hibernate.hibernatePlayground.Entity.Submission;
import com.hibernate.hibernatePlayground.common.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubmissionMapper extends AbstractMapper<Submission, SubmissionDto> {
}
