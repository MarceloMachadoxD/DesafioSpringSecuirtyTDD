package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {

    private Long id;


    @NotNull
    @Email(message = "Entrar com um e-mail valido")
    private String email;

    private Set<RoleDTO> role = new HashSet<>();


    public UserDTO() {
    }

    public UserDTO(Long id,  String email) {
        this.id = id;

        this.email = email;
    }

    public UserDTO(User entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        entity.getRoles().forEach(role -> this.role.add(new RoleDTO(role)));

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRole() {
        return role;
    }

}
