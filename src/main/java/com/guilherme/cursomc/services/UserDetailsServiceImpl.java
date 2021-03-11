package com.guilherme.cursomc.services;

import com.guilherme.cursomc.domain.Client;
import com.guilherme.cursomc.repositories.ClientRepository;
import com.guilherme.cursomc.security.UserSS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client cli = clientRepository.findByEmail(email);
        if (cli == null)
            throw new UsernameNotFoundException(email);

        return new UserSS(cli.getId(), cli.getEmail(), cli.getPassword(), cli.getPerfils());
    }

}
