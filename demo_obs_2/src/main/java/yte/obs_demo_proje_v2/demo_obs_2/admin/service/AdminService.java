package yte.obs_demo_proje_v2.demo_obs_2.admin.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yte.obs_demo_proje_v2.demo_obs_2.admin.controller.response.AdminQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.admin.entity.Admin;
import yte.obs_demo_proje_v2.demo_obs_2.admin.repository.AdminRepository;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.response.AssistantQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {


    private final AdminRepository adminRepository;

    public MessageResponse addAdmin(Admin admin) {
        adminRepository.save(admin);
        return new MessageResponse(ResponseType.SUCCESS, "Admin başarıyla kaydedildi");
    }

    public MessageResponse updateAdmin(Long id,Admin updateAdmin){
        Admin admin= adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("kayıt bulunamadı"));

    adminRepository.save(updateAdmin);

    updateAdmin.update(admin);
    return new MessageResponse(ResponseType.SUCCESS,"Admin başarıyla güncellendi");
    }

    public MessageResponse deleteAdminById(Long id){
        adminRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS,"Admin başarıyla silindi");
    }

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("kayıt bulunamadı"));
    }
    @GetMapping("{id}")
    public Admin getById(@PathVariable Long id) {
        return adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("kayıt bulunamadı"));

    }

}
