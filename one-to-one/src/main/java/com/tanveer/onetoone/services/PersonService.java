package com.tanveer.onetoone.services;

import com.tanveer.onetoone.entity.PersonEntity;
import com.tanveer.onetoone.model.PersonRequest;
import com.tanveer.onetoone.model.PersonResponse;
import com.tanveer.onetoone.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse createPerson(PersonRequest personRequest) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(personRequest.getFirstName());
        personEntity.setLastName(personRequest.getFirstName());
        personEntity.setAge(personRequest.getAge());
        personRepository.save(personEntity);

        PersonResponse response = new PersonResponse();
        response.setId(personEntity.getId());
        return response;
    }

    public List<PersonRequest> getAll() {
        return personRepository.findAll().stream()
                .map(personEntity -> {
                    PersonRequest personRequest = new PersonRequest();
                    personRequest.setFirstName(personEntity.getFirstName());
                    personRequest.setLastName(personEntity.getLastName());
                    personRequest.setAge(personEntity.getAge());
                    return personRequest;
                })
                .collect(Collectors.toList());
    }

    public PersonRequest getPerson(Long id) {
        PersonRequest personRequest = new PersonRequest();
        Optional<PersonEntity> personOption = personRepository.findById(id);
        if (personOption.isPresent()){
            personRequest.setFirstName(personOption.get().getFirstName());
            personRequest.setLastName(personOption.get().getLastName());
            personRequest.setAge(personOption.get().getAge());
        }
        else {
            throw new EntityNotFoundException("PersonEntity with ID " + id + " not found");
        }
        return personRequest;
    }

    public void deleteById(Long id) {
        Optional<PersonEntity> entity = personRepository.findById(id);
        if (entity.isPresent()){
                personRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("PersonEntity with ID " + id + " not found");
        }
    }

    public PersonRequest updatePerson(PersonRequest request, Long id) {
        Optional<PersonEntity> optionalPersonEntity = personRepository.findById(id);
        if (optionalPersonEntity.isPresent()) {
                PersonEntity personEntity = optionalPersonEntity.get();
                personEntity.setFirstName(request.getFirstName());
                personEntity.setAge(request.getAge());
                personEntity.setLastName(request.getLastName());
                personRepository.save(personEntity);
            }
        else {
            throw new EntityNotFoundException("PersonEntity with ID " + id + " not found");
        }
            return request;
    }
}
