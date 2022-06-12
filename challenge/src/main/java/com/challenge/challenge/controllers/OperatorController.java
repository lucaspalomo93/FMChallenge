package com.challenge.challenge.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.challenge.models.Operator;
import com.challenge.challenge.pojos.OperatorPOJO;
import com.challenge.challenge.services.OperatorService;

@RestController
@RequestMapping("/operator")
public class OperatorController {
    
    @Autowired
    private OperatorService oS;

    @GetMapping("/list")
    public ResponseEntity<List<OperatorPOJO>> getAll(){
        List<Operator> operatorsList = oS.findAll();
        List<OperatorPOJO> opPojoList = new ArrayList<>();

        operatorsList.forEach(op ->{
            OperatorPOJO opojo = new OperatorPOJO();
            opojo.setName(op.getName());
            opojo.setSurname(op.getSurname());
            opojo.setStatus(op.getStatus());
            opojo.setCreationDate(op.getCreationDate());
            opojo.setLastLoginDate(op.getLastLoginDate());
            opPojoList.add(opojo);
        });

        return ResponseEntity.ok().body(opPojoList);
    } 

    @GetMapping("/list/{id}")
    public ResponseEntity<Operator> getById(@PathVariable Long id){
        Operator op = null;
        if(oS.findById(id) != null){
            op = oS.findById(id).get();
        }

        if(op != null){
            return ResponseEntity.ok().body(op);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/create")
    public ResponseEntity<Operator> createOperator(@RequestBody Operator operator){
        if(oS.save(operator) != null){
            oS.save(operator);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Operator> updateOperator(@PathVariable Long id, @RequestBody Operator operator){
        if(operator != null){
            oS.update(id, operator);
            return ResponseEntity.ok().body(operator);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOperator(@PathVariable Long id){
        System.out.println(id);
        if(oS.deleteById(id)){
            oS.deleteById(id);

            return ResponseEntity.ok().body("Operator removed");
        }
        return ResponseEntity.notFound().build();
    }
}
