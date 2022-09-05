package yte.obs_demo_proje_v2.demo_obs_2.assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;

import java.util.List;
import java.util.Optional;

public interface AssistantRepository extends JpaRepository<Assistant, Long> {

    Optional<Assistant> findAssistantById(Long id);

    List<Assistant> findAll();
}
