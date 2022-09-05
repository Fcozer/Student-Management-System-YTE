package yte.obs_demo_proje_v2.demo_obs_2.security.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import yte.obs_demo_proje_v2.demo_obs_2.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Authority extends BaseEntity implements GrantedAuthority {

    private String authority;



    public Authority(String authority) {
        this.authority = authority;
    }
}
