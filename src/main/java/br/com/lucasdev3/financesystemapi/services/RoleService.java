package br.com.lucasdev3.financesystemapi.services;

import br.com.lucasdev3.financesystemapi.entities.Role;
import br.com.lucasdev3.financesystemapi.entities.UserDetailsImpl;
import br.com.lucasdev3.financesystemapi.enumerate.ERole;
import br.com.lucasdev3.financesystemapi.models.ResponseModel;
import br.com.lucasdev3.financesystemapi.models.ResponseUserModel;
import br.com.lucasdev3.financesystemapi.models.UserRegistryModel;
import br.com.lucasdev3.financesystemapi.repositories.RoleRepository;
import br.com.lucasdev3.financesystemapi.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    private static final Logger LOGGER = Logger.getLogger(RoleService.class);
    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Role getById(UUID id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<ResponseModel> save() {
        try {
            Role roleUser = new Role(ERole.ROLE_USER);
            Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);
            return ResponseEntity.ok(new ResponseModel("Roles cadastradas!"));
        } catch (
                Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseModel("Falha ao cadastrar roles!"));
        }
    }

}
