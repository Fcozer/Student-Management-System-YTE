package yte.obs_demo_proje_v2.demo_obs_2.academician.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;

import java.util.List;
import java.util.Optional;

public interface AcademicianRepository extends JpaRepository<Academician,Long> {
    Optional<Academician> findAcademicianById(Long id);
    List<Academician> findAll();
}
