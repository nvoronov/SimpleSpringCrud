package com.example.simple_crud_with_h2.controller;

import com.example.simple_crud_with_h2.model.SimpleEntity;
import com.example.simple_crud_with_h2.repository.SimpleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SimpleController {

  private final SimpleRepository simpleRepository;

  public SimpleController(SimpleRepository simpleRepository) {
    this.simpleRepository = simpleRepository;
  }

  @GetMapping("/entities")
  public ResponseEntity<List<SimpleEntity>> getAllEntities() {
    List<SimpleEntity> entities = simpleRepository.findAll();
    return new ResponseEntity<>(entities, HttpStatus.OK);
  }

  @GetMapping("/entities/{id}")
  public ResponseEntity<SimpleEntity> getEntityById(@PathVariable("id") long id) {
    return simpleRepository.findById(id)
      .map(simpleEntity -> new ResponseEntity<>(simpleEntity, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/entities")
  public ResponseEntity<SimpleEntity> createEntity(@RequestBody SimpleEntity entity) {
    try {
      SimpleEntity createdEntity = simpleRepository
        .save(new SimpleEntity(entity.getName(), entity.getNumber()));
      return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/entities/{id}")
  public ResponseEntity<SimpleEntity> updateEntity(@PathVariable("id") long id, @RequestBody SimpleEntity entity) {
    Optional<SimpleEntity> entityToUpdate = simpleRepository.findById(id);
    if (!entityToUpdate.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    SimpleEntity entityToSave = entityToUpdate.get().setName(entity.getName()).setNumber(entity.getNumber());
    return new ResponseEntity<>(simpleRepository.save(entityToSave), HttpStatus.OK);
  }

  @DeleteMapping("/entities")
  public ResponseEntity<HttpStatus> deleteAllEntities() {
    try {
      simpleRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/entities/{id}")
  public ResponseEntity<HttpStatus> deleteEntityById(@PathVariable("id") long id) {
    try {
      simpleRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
