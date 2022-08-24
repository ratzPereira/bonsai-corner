package com.ratz.bonsaicorner.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCredentialsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5761938655044589959L;


    private String username;
    private String password;
}
