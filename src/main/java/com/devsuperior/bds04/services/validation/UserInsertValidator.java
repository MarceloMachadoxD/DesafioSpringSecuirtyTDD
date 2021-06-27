package com.devsuperior.bds04.services.validation;

import com.devsuperior.bds04.controller.exceptions.FieldMessage;
import com.devsuperior.bds04.dto.UserInsertDTO;
import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.getEmail());
        if (user != null){
            list.add(new FieldMessage("email", "e-mail " + dto.getEmail() + " já cadastrado na base de dados"));
        }



        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                .addConstraintViolation();
        }
        return list.isEmpty();
    }
}