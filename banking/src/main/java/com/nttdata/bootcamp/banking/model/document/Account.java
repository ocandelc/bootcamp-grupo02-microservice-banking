package com.nttdata.bootcamp.banking.model.document;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Document(collection="account")
public class Account {

    @Id
    private String id;
    private String accountNumber;
    private String accountInterbankNumber;
    private String codeClient;
    private String codeProduct;
    private Date dateRegister;
    private double creditLine;
    private double availableAmount;
    private String codeAccountState;

}
