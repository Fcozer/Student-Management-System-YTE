package yte.obs_demo_proje_v2.demo_obs_2.academician.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;
import yte.obs_demo_proje_v2.demo_obs_2.academician.repository.AcademicianRepository;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicianService {


    private final AcademicianRepository academicianRepository;



    public MessageResponse addAcademician(Academician academician) {
        academicianRepository.save(academician);

        return new MessageResponse(ResponseType.SUCCESS, "Academician has been added successfully");
    }

    public List<Academician> getAllAcademician() {
        return academicianRepository.findAll();
    }

    public Academician getById(Long id) {

        return academicianRepository.findById(id)
                .orElseThrow(() -> (new EntityNotFoundException("Academician not found")));

    }

    public MessageResponse updateAcademician(Long id, Academician updateAcademician) {

        Academician academician = academicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academician not found"));

        academician.update(updateAcademician);

        academicianRepository.save(academician);

        return new MessageResponse(ResponseType.SUCCESS, "Academician has been updated successfully");

    }

    public MessageResponse deleteAcademician(Long id) {
        academicianRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Academician has been delete successfully");


    }


}
