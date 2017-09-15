package com.optisystems.validationexample.controllers;

import com.optisystems.validationexample.dto.ParentDTO;
import com.optisystems.validationexample.entity.Parent;
import com.optisystems.validationexample.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "parent")
public class ParentController {

    ParentRepository repository;

    @Autowired
    public ParentController(ParentRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ParentDTO> read(@PathVariable long id) {
        Parent parent = repository.findOne(id);
        ParentDTO dto = new ParentDTO();
        dto.setId(parent.getId());
        dto.setName(parent.getName());
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Long> create(@RequestBody @Valid ParentDTO dto) {
        Parent parent = new Parent();
        parent.setName(dto.getName());
        parent = repository.save(parent);
        return ResponseEntity.ok(parent.getId());
    }
}
