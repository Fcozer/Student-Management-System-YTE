package yte.obs_demo_proje_v2.demo_obs_2.academician.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;

public interface AcademicianRepository extends JpaRepository<Academician,Long> {
}
