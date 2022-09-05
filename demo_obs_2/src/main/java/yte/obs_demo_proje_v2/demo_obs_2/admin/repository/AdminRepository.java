package yte.obs_demo_proje_v2.demo_obs_2.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.obs_demo_proje_v2.demo_obs_2.admin.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminById(Long id);

    List<Admin> findAll();
}
