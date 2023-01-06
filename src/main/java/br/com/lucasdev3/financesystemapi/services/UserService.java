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
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    @Transactional(readOnly = true)
    public Iterable<ResponseUserModel> getAll() {
        var users = userRepository.findAll();
        Set<ResponseUserModel> userDetailsSet = new HashSet<>();
        users.forEach(userDetails -> userDetailsSet.add(new ResponseUserModel(userDetails.getUserId(), userDetails.getUsername(), userDetails.getRoles())));
        return userDetailsSet;
    }
    @Transactional(readOnly = true)
    public UserDetailsImpl getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    public ResponseEntity<ResponseModel> save(UserRegistryModel model) {
        try {
            List<Role> roles = new ArrayList<>();
            var rolesFound = roleRepository.findAll();
            if (!rolesFound.isEmpty()) {
                for (Role role : rolesFound) {
                    if (role.getRoleName().name().equals("ROLE_USER")) {
                        roles.add(role);
                    }
                }
                if (roles.size() == 0)
                    return ResponseEntity.badRequest().body(new ResponseModel("ROLE_USER não cadastrada!"));
            } else {
                return ResponseEntity.badRequest().body(new ResponseModel("Lista de roles vazia!"));
            }
            if (model.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(new ResponseModel("Senha inválida!"));
            }
            var passwordEncoded = new BCryptPasswordEncoder().encode(model.getPassword());
            userRepository.save(new UserDetailsImpl(model.getUsername(), passwordEncoded, roles));
            return ResponseEntity.ok(new ResponseModel("Usuario cadastrado com sucesso!", model));
        } catch (
                Exception e) {
            LOGGER.error("Message: " + e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseModel("Falha ao cadastrar usuario!"));
        }
    }

}
