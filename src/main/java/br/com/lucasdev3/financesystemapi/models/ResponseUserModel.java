package br.com.lucasdev3.financesystemapi.models;

import br.com.lucasdev3.financesystemapi.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ResponseUserModel {

    private UUID id;

    private String username;

    private Collection<Role> roles;

}
