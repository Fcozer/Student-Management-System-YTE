package yte.obs_demo_proje_v2.demo_obs_2.academician.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.obs_demo_proje_v2.demo_obs_2.academician.controller.request.AddAcademicianRequest;
import yte.obs_demo_proje_v2.demo_obs_2.academician.controller.request.UpdateAcademicianRequest;
import yte.obs_demo_proje_v2.demo_obs_2.academician.controller.responses.AcademicianQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.academician.service.AcademicianService;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/academicians")
@RequiredArgsConstructor
@Validated
public class AcademicianController {


    private final AcademicianService academicianService;


    @PostMapping
    public MessageResponse addAcademician(@Valid @RequestBody AddAcademicianRequest addAcademicianRequest) {
        return academicianService.addAcademician(addAcademicianRequest.toDomainEntity());
    }

    @PutMapping("{id}")
    public MessageResponse updateAcademician(@Valid @RequestBody UpdateAcademicianRequest updateAcademicianRequest, @PathVariable Long id) {
        return academicianService.updateAcademician(id, updateAcademicianRequest.toDomainEntity());
    }

    @DeleteMapping("{id}")
    public MessageResponse deleteAcademician(@PathVariable Long id) {
        return academicianService.deleteAcademician(id);
    }


    @GetMapping
    public List<AcademicianQueryModel> getAllAcademician() {
        return academicianService.getAllAcademician()
                .stream()
                .map(academician -> new AcademicianQueryModel(academician))
                .toList();
    }

    @GetMapping("{id}")
    public AcademicianQueryModel getAcademicianById(@PathVariable Long id) {
        return new AcademicianQueryModel(academicianService.getById(id));
    }


}
