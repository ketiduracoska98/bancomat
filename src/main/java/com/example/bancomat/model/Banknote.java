package com.example.bancomat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "Banknote")
@Entity
public class Banknote implements Comparable<Banknote> {
    @Id
    private Integer type;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private Double percentage;

    public Banknote(Integer type, Integer initialQuantity) {
        this.type = type;
        this.initialQuantity = initialQuantity;
        this.currentQuantity = initialQuantity;
        this.percentage = calculatePercentage();
    }
    public Double calculatePercentage() {
        return (Double.valueOf(this.currentQuantity) / Double.valueOf(this.initialQuantity)) * 100;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public static String aboveTwoHundred(Integer sum) {
        final int twoHundred = 200;
        if(sum >= twoHundred) {
            return "Sum above " + twoHundred;
        }
        return "";
    }
    public String alertMessage(Client client) {
        final int hundred = 100;
        final int fifty = 50;
        final int ten = 10;
        final int twenty = 20;
        final int fifteen = 15;
        if (this.getType() == hundred) {
            if (this.getPercentage() < ten) {
                return "Mobile phone: " + client.getPhoneNumber() + " Critical! Percentage of "+ hundred +" banknotes under " + ten +"%";
            }
            if (this.getPercentage() < twenty) {
                return "Email address: " + client.getEmailAddress() + " Message: Warning! Percentage of "+ hundred +" banknotes under "+ twenty +"%";
            }
        }
        if (this.getType() == fifty) {
            if (this.getPercentage() < fifteen) {
                return "Email address: " + client.getEmailAddress() + " Message: Warning! Percentage of " + fifty + " banknotes under "+ fifteen +"%";
            }
        }
        return "";
    }

    public static boolean isSumCorrect(Integer sum, List<Banknote> banknoteList) {
        int newSum = 0;
        for (Banknote banknote : banknoteList) {
            newSum += banknote.getType();
        }
        return newSum == sum;
    }

    public static List<Banknote> withdrawBanknotes(Integer sum, List<Banknote> allBanknotes) {
        List<Banknote> resultList = new ArrayList<>();
        for (int i = 0; i < allBanknotes.size(); i++) {
            Banknote currentBanknote = allBanknotes.get(i);
            Integer currentQuantity = currentBanknote.getCurrentQuantity();
            Integer currentType = currentBanknote.getType();
            if (currentQuantity == 0) {
                continue;
            }
            if (currentType <= sum) {
                resultList.add(currentBanknote);
                currentBanknote.setCurrentQuantity(currentQuantity - 1);
                currentBanknote.setPercentage(currentBanknote.calculatePercentage());
                sum -= currentType;
                if (sum == 0)
                    break;
                i = 0;
            }
        }
        return resultList;
    }

    public int compareTo(Banknote banknote) {
        return banknote.getType() - this.getType();
    }
}
