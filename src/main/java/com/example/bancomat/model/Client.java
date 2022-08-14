package com.example.bancomat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "Client")
@Entity
public class Client {
    @Id
    private Integer cnp;
    private String cardNumber;
    private String name;
    private String familyName;
    private String phoneNumber;
    private String emailAddress;
    private Integer pin;
    private Integer amount;

    public Client(Integer cnp, String name, String familyName, String phoneNumber, String emailAddress, Integer pin, Integer amount) {
        this.cnp = cnp;
        this.name = name;
        this.familyName = familyName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.pin = pin;
        this.amount = amount;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String generateCardNumber() {
        Random rand = new Random();
        return this.getCnp() + String.valueOf(rand.nextInt(9));
    }
}
