package com.challenge.challenge.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.challenge.models.Operator;
import com.challenge.challenge.models.Role;
import com.challenge.challenge.repositories.IOperatorRepository;
import com.challenge.challenge.repositories.IRoleRepository;

@Service
public class OperatorService {
    
    @Autowired
    private PasswordEncoder pE;

    @Autowired
    private IRoleRepository rR;

    @Autowired
    private IOperatorRepository oR;

    public List<Operator> findAll(){
        return oR.findAll();
    }

    public Optional<Operator> findById(Long id){
        if(oR.existsById(id)){
            return oR.findById(id);
        }
        return null;
    }

    public Operator save(Operator operator){

        Role role = rR.findByName("ROLE_USER");

        if(operator != null){
            if(!oR.existsByUserName(operator.getUserName())){
                operator.setCreationDate(new Date(System.currentTimeMillis()));
                String newPass = pE.encode(operator.getPassword());
                operator.setPassword(newPass);
                operator.setRole(role);
                operator.setStatus(1);
                return oR.save(operator);
            }
        }
        return null;
    }

    public Operator update(Long id, Operator operator){
        if(oR.existsById(id)){
            Operator oldOp = oR.findById(id).get();

            oldOp.setName(operator.getName());
            oldOp.setSurname(operator.getSurname());
            oldOp.setUserName(operator.getUserName());
            String newPass = pE.encode(operator.getPassword());
            oldOp.setPassword(newPass);
            oldOp.setStatus(operator.getStatus());

            oR.save(oldOp);
            return oldOp;
        }
        
        return null;
    }

    public boolean deleteById(Long id){
        if(oR.existsById(id)){
            oR.deleteById(id);
            return true;
        }
        return false;
    }

    public Operator findByusername(String username){
        if(oR.existsByUserName(username)){
            return oR.findByUserName(username);
        }
        return null;
    }
    
}
