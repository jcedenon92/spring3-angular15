package com.jcedenon.service.impl;

import com.jcedenon.model.Patient;
import com.jcedenon.repo.IGenericRepo;
import com.jcedenon.repo.IPatientRepo;
import com.jcedenon.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl extends CRUDImpl<Patient,Integer> implements IPatientService {

    private final IPatientRepo repo;

    @Override
    protected IGenericRepo<Patient, Integer> getRepo() {
        return repo;
    }
}
