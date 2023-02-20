package com.hibernate.hibernatePlayground.Controller;

import com.hibernate.hibernatePlayground.Entity.Dto.FormDto;
import com.hibernate.hibernatePlayground.Services.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/form")
public class FormController {

    private final FormService formService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createForm(@RequestBody FormDto formDto){
        formService.createForm(formDto);
    }
}
