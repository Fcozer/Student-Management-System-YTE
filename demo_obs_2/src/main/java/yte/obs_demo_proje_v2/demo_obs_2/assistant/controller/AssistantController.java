package yte.obs_demo_proje_v2.demo_obs_2.assistant.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.request.AddAssistantRequest;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.request.UpdateAssistantRequest;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.response.AssistantQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.service.AssistantService;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;

import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Authority;
import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Users;
import yte.obs_demo_proje_v2.demo_obs_2.security.repository.AuthorityRepository;
import yte.obs_demo_proje_v2.demo_obs_2.security.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assistant")
@Validated
public class AssistantController {

    private final AssistantService assistantService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addAssistant(@Valid @RequestBody AddAssistantRequest addAssistantRequest) {
        Users a = new Users();
        Authority assistant = authorityRepository.findByAuthority("ASSISTANT")
                .orElseThrow(() -> new EntityNotFoundException("Authority:ASSISTANT not found!"));
        a.setPassword(passwordEncoder.encode(addAssistantRequest.password()));

        a.setUsername(addAssistantRequest.username());

        a.setAuthorities(List.of(assistant));

        userRepository.save(a);
        return assistantService.addAssistant(addAssistantRequest.toDomainEntity());
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
            public MessageResponse updateAssistant(@Valid @RequestBody UpdateAssistantRequest updateAssistantRequest, @PathVariable Long id) {
        return assistantService.updateAssistant(id, updateAssistantRequest.toDomainEntity());

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse getAssistantById(@PathVariable Long id) {
        return assistantService.deleteAssistantById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AssistantQueryModel> getAllAssistants() {
        return assistantService.getAllAssistant()
                .stream()
                .map(assistant -> new AssistantQueryModel(assistant))
                .toList();
    }

    @GetMapping("{id}")

    public AssistantQueryModel getById(@PathVariable Long id) {
        return new AssistantQueryModel(assistantService.getById(id));
    }


}
