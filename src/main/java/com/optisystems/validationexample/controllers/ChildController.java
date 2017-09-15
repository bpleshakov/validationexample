package com.optisystems.validationexample.controllers;

import com.optisystems.validationexample.dto.ChildDTO;
import com.optisystems.validationexample.entity.Child;
import com.optisystems.validationexample.repository.ChildRepository;
import com.optisystems.validationexample.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/child")
public class ChildController {

    private ChildRepository repository;
    private ParentRepository parentRepository;

    @Autowired
    public ChildController(ChildRepository repository, ParentRepository parentRepository) {
        this.repository = repository;
        this.parentRepository = parentRepository;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ChildDTO> read(@PathVariable long id) {
        Child child = repository.findOne(id);
        ChildDTO dto = new ChildDTO();
        dto.setId(child.getId());
        dto.setParentId(child.getParent().getId());
        dto.setName(child.getName());
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Long> create(@RequestBody @Valid ChildDTO dto) {
        Child child = new Child();
        child.setParent(parentRepository.findOne(dto.getParentId()));
        child.setName(dto.getName());
        child = repository.save(child);
        return ResponseEntity.ok(child.getId());
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@RequestBody @Valid ChildDTO dto) {
        Child child = repository.findOne(dto.getId());
        child.setParent(parentRepository.findOne(dto.getParentId()));
        child.setName(dto.getName());
        repository.save(child);
        return ResponseEntity.ok(null);
    }
}
