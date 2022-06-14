package com.challenge.challenge.pojos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatorPOJO {

    private String name;

    private String surname;

    private String username;

    private int status;

    private Date CreationDate;
    
    private Date lastLoginDate;
}
