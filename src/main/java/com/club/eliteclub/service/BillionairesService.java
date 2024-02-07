package com.club.eliteclub.service;

import com.club.eliteclub.entity.Billionaires;
import com.club.eliteclub.repository.BillionairesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillionairesService {
    @Autowired
    BillionairesRepository billionairesRepository;

    public List<Billionaires> listAll(){
        return billionairesRepository.findAll();
    }

    public boolean deleteBillionaire(Long billionaireId ){
        Billionaires billionaire = new Billionaires(billionaireId);
        billionairesRepository.delete(billionaire);
        return true;
    }
    public Billionaires createBillionaire(Billionaires billionaire){
       return billionairesRepository.save(billionaire);
    }

    public Billionaires updateBillionaire(Billionaires billionaire){
        return billionairesRepository.save(billionaire);
    }

    public Optional<Billionaires> getBillionaire(Long billionaireId){
        return billionairesRepository.findById(billionaireId);
    }
}
