package yte.obs_demo_proje_v2.demo_obs_2.assistant.controller;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.QueryResultsRegionTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.request.AddAssistantRequest;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.request.UpdateAssistantRequest;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.response.AssistantQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.service.AssistantService;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assistant")
@Validated
public class AssistantController {

    private final AssistantService assistantService;

    @PostMapping
    public MessageResponse addAssistant(@Valid @RequestBody AddAssistantRequest addAssistantRequest) {
        return assistantService.addAssistant(addAssistantRequest.toDomainEntity());
    }

    @PutMapping("{id}")
            public MessageResponse updateAssistant(@Valid @RequestBody UpdateAssistantRequest updateAssistantRequest, @PathVariable Long id) {
        return assistantService.updateAssistant(id, updateAssistantRequest.toDomainEntity());

    }

    @DeleteMapping("{id}")
    public MessageResponse getAssistantById(@PathVariable Long id) {
        return assistantService.deleteAssistantById(id);
    }

    @GetMapping
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
