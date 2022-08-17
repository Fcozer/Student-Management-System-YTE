package yte.obs_demo_proje_v2.demo_obs_2.assistant.service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.response.AssistantQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.repository.AssistantRepository;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssistantService {

    private final AssistantRepository assistantRepository;

    public MessageResponse addAssistant(Assistant assistant) {
        assistantRepository.save(assistant);

        return new MessageResponse(ResponseType.SUCCESS, "Assistant  has been added successfully");
    }

    public MessageResponse updateAssistant(Long id, Assistant updateAssistant) {
        Assistant assistant = assistantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assistant not found"));
        assistant.update(updateAssistant);

        assistantRepository.save(assistant);

        return new MessageResponse(ResponseType.SUCCESS, "Assistant has been updated successfully");
    }


    public List<Assistant> getAllAssistant() {
        return assistantRepository.findAll();
    }

    public Assistant getById(Long id) {
        return assistantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assistant not found"));
    }

    public MessageResponse deleteAssistantById(Long id){
        assistantRepository.deleteById(id);
        return  new MessageResponse(ResponseType.SUCCESS, "Assistant has been deleted successfully");
    }
}






















