package yte.obs_demo_proje_v2.demo_obs_2.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.obs_demo_proje_v2.demo_obs_2.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
