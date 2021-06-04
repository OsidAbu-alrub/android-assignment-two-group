package com.abualrub.androidassignmenttwogroup.domain;

public class Payment {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expireYear;
    private String expireMonth;

    public Payment(){

    }

    public Payment(String cardNumber, String cardHolderName, String cvv, String expireYear, String expireMonth) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expireYear = expireYear;
        this.expireMonth = expireMonth;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(String expireMonth) {
        this.expireMonth = expireMonth;
    }
}
