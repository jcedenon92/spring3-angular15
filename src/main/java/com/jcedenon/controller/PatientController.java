package com.jcedenon.controller;

import com.jcedenon.dto.PatientDTO;
import com.jcedenon.exception.ModelNotFoundException;
import com.jcedenon.exception.NewModelNotFoundException;
import com.jcedenon.model.Patient;
import com.jcedenon.service.impl.PatientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientServiceImpl service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll(){
        /*List<Patient> list = service.findAll();
        return new ResponseEntity<>(list, OK);*/
        List<PatientDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);

        if (obj == null){
            throw new ModelNotFoundException("ID: " + id + " NOT FOUND");
            //throw new NewModelNotFoundException("ID: " + id + " NOT FOUND");
        }

        return new ResponseEntity<>(this.convertToDto(obj), OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);

        service.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private PatientDTO convertToDto(Patient obj){
        return mapper.map(obj, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO dto){
        return mapper.map(dto, Patient.class);
    }

}
