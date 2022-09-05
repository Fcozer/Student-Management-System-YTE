package yte.obs_demo_proje_v2.demo_obs_2.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Authority;
import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Users;
import yte.obs_demo_proje_v2.demo_obs_2.security.repository.AuthorityRepository;
import yte.obs_demo_proje_v2.demo_obs_2.security.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class  UserTablePopulator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;


    @PostConstruct
    public void populateDatabase() {
        if (!authorityRepository.existsByAuthority("STUDENT")) {
            authorityRepository.save(new Authority("STUDENT"));
        }

        if (!authorityRepository.existsByAuthority("ASSISTANT")) {
            authorityRepository.save(new Authority("ASSISTANT"));
        }
        if (!authorityRepository.existsByAuthority("ACADEMICIAN")) {
            authorityRepository.save(new Authority("ACADEMICIAN"));
        }
        if (!authorityRepository.existsByAuthority("ADMIN")) {
            authorityRepository.save(new Authority("ADMIN"));
        }
        Authority admin = authorityRepository.findByAuthority("ADMIN")
                .orElseThrow(() -> new EntityNotFoundException("Authority:ADMIN not found!"));

        if (!userRepository.existsByUsername("admin")) {
            Users user = new Users(List.of(admin), "admin", passwordEncoder.encode("admin"));
            userRepository.save(user);

        }


    }
}

