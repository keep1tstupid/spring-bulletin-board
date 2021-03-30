package com.example.bb;

import com.example.bb.domain.Role;
import com.example.bb.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.example.bb.domain.ERole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void findRoleByNameTest() {
        Optional<Role> role = roleRepository.findByName(ROLE_USER);
        assertThat(role.isPresent());
    }

    @Test
    public void findAllRolesTest() {
        List<Role> roles = roleRepository.findAll();
        assertThat(roles.size() != 0);
    }
}