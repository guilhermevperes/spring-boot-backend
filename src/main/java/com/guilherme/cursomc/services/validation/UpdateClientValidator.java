package com.guilherme.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.guilherme.cursomc.DTO.ClientDTO;
import com.guilherme.cursomc.domain.Client;
import com.guilherme.cursomc.repositories.ClientRepository;
import com.guilherme.cursomc.resources.exceptions.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {

    @Autowired
    HttpServletRequest request;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void initialize(UpdateClient ann) {

    }

    @Override
    public boolean isValid(ClientDTO objDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer id = Integer.parseInt(map.get("id"));

        Client aux = clientRepository.findByEmail(objDTO.getEmail());
        if (!Objects.isNull(aux) && !aux.getId().equals(id)) {
            FieldMessage fieldMessage = new FieldMessage("email", "Email já cadastrado.");
            list.add(fieldMessage);
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
