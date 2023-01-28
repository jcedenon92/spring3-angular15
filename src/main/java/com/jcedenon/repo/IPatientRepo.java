package com.jcedenon.repo;

import com.jcedenon.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepo extends IGenericRepo<Patient, Integer>{
}
