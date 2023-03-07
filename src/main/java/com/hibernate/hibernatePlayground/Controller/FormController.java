package com.hibernate.hibernatePlayground.Controller;

import com.hibernate.hibernatePlayground.Entity.Dto.FormDto;
import com.hibernate.hibernatePlayground.Entity.Form;
import com.hibernate.hibernatePlayground.Services.FormService;
import com.hibernate.hibernatePlayground.exception.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/form")
public class FormController {

    private final FormService formService;

    @GetMapping
    public ResponseEntity<List<FormDto>> listForm(){
        return ResponseEntity.ok(formService.listForm());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createForm(@RequestBody FormDto formDto) throws ExceptionHandler {
        formService.createForm(formDto);
    }
}
