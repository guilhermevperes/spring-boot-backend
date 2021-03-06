package com.guilherme.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.guilherme.cursomc.DTO.ClientNewDTO;
import com.guilherme.cursomc.domain.enums.ClientType;
import com.guilherme.cursomc.repositories.ClientRepository;
import com.guilherme.cursomc.resources.exceptions.FieldMessage;
import com.guilherme.cursomc.services.validation.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class InsertClientValidator implements ConstraintValidator<InsertClient, ClientNewDTO> {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void initialize(InsertClient ann) {

    }

    @Override
    public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDTO.getType().equals(ClientType.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfCnpj())) {
            FieldMessage fieldMessage = new FieldMessage("cpfCnpj", "CPF inválido.");
            list.add(fieldMessage);
        }

        if (objDTO.getType().equals(ClientType.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfCnpj())) {
            FieldMessage fieldMessage = new FieldMessage("cpfCnpj", "CNPJ inválido.");
            list.add(fieldMessage);
        }

        if (!Objects.isNull(clientRepository.findByEmail(objDTO.getEmail()))) {
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
