package com.challenge.challenge.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.challenge.challenge.models.Operator;
import com.challenge.challenge.repositories.IOperatorRepository;

@Service("userDetailsService")
public class OperatorDetailsService implements UserDetailsService{

    @Autowired
    private IOperatorRepository oR;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Operator operator = oR.findByUserName(username);

        if(operator == null){
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> set = new HashSet<>();
        set.add(new SimpleGrantedAuthority(operator.getRole().getName()));

        return new User(operator.getUserName(), operator.getPassword(), set);
    }
    
}
