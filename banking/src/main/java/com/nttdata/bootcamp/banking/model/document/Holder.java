package com.nttdata.bootcamp.banking.model.document;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@ToString
@Document(collection="holder")
public class Holder {

    @Id
    private String id;
    private String codePerson;
    private String accountNumber;
    private Date dateRegister;
    private boolean state;

}
