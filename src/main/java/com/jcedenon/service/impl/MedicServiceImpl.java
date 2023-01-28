package com.jcedenon.service.impl;

import com.jcedenon.model.Medic;
import com.jcedenon.repo.IGenericRepo;
import com.jcedenon.repo.IMedicRepo;
import com.jcedenon.service.IMedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDImpl<Medic,Integer> implements IMedicService {

    private final IMedicRepo repo;

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }
}
