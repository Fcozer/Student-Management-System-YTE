package yte.obs_demo_proje_v2.demo_obs_2.academician.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.obs_demo_proje_v2.demo_obs_2.academician.controller.request.AddAcademicianRequest;
import yte.obs_demo_proje_v2.demo_obs_2.academician.controller.request.UpdateAcademicianRequest;
import yte.obs_demo_proje_v2.demo_obs_2.academician.controller.responses.AcademicianQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.academician.service.AcademicianService;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;
import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Authority;
import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Users;
import yte.obs_demo_proje_v2.demo_obs_2.security.repository.AuthorityRepository;
import yte.obs_demo_proje_v2.demo_obs_2.security.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/academicians")
@RequiredArgsConstructor
@Validated
public class AcademicianController {


    private final AcademicianService academicianService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;


    //@RequestBody HTTP isteği gönderiyor.
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addAcademician(@Valid @RequestBody AddAcademicianRequest addAcademicianRequest) {
        Users a = new Users();
        Authority academician = authorityRepository.findByAuthority("ACADEMICIAN")
                .orElseThrow(() -> new EntityNotFoundException("Authority:ACADEMICIAN not found!"));
        a.setPassword(passwordEncoder.encode(addAcademicianRequest.password()));

        a.setUsername(addAcademicianRequest.username());

        a.setAuthorities(List.of(academician));

        userRepository.save(a);
        return academicianService.addAcademician(addAcademicianRequest.toDomainEntity());
    }

    @PutMapping("{id}")

    public MessageResponse updateAcademician(@Valid @RequestBody UpdateAcademicianRequest updateAcademicianRequest, @PathVariable Long id) {
        return academicianService.updateAcademician(id, updateAcademicianRequest.toDomainEntity());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteAcademician(@PathVariable Long id) {
        return academicianService.deleteAcademician(id);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AcademicianQueryModel> getAllAcademician() {
        return academicianService.getAllAcademician()
                .stream()
                .map(AcademicianQueryModel::new)
                .toList();
    }

    @GetMapping("{id}")

    public AcademicianQueryModel getAcademicianById(@PathVariable Long id) {
        return new AcademicianQueryModel(academicianService.getById(id));
    }


}
