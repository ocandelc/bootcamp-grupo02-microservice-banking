package com.nttdata.bootcamp.banking.model.document;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection="movement_type")
public class MovementType {

    @Id
    private String id;
    private String code;
    private String name;
    private String operationType;
    private boolean state;

}
