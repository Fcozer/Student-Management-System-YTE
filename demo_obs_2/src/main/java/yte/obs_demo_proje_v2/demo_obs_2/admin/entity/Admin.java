package yte.obs_demo_proje_v2.demo_obs_2.admin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yte.obs_demo_proje_v2.demo_obs_2.common.entity.BaseEntity;
import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Admin extends BaseEntity {


    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void update(Admin udpateAdmin) {
        this.username = udpateAdmin.username;
        this.password = udpateAdmin.password;

    }

}
