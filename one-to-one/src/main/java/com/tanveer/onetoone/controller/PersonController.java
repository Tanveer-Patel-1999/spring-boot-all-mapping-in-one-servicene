package com.tanveer.onetoone.controller;

import com.tanveer.onetoone.model.PersonRequest;
import com.tanveer.onetoone.model.PersonResponse;
import com.tanveer.onetoone.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/one-entity/")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonRequest personRequest)
    {
     PersonResponse response= personService.createPerson(personRequest);
     return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getPerson/{id}")
    public ResponseEntity<PersonRequest> getPerson(@PathVariable Long id){
        PersonRequest request = personService.getPerson(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PersonRequest>> getAll(){
        List<PersonRequest> request = personService.getAll();
        return ResponseEntity.ok(request);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<PersonRequest> updatePerson(@RequestBody PersonRequest request, @PathVariable Long id){
        PersonRequest personRequest = personService.updatePerson(request,id);
        return ResponseEntity.ok(personRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
