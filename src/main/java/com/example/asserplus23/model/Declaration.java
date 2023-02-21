package com.example.asserplus23.model;

public class Declaration {
    private String userId;
    private String contractNumber;


    public Declaration(String user, String contract, String photo, String file) {
        this.userId = user;
        this.contractNumber = contract;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user) {
        this.userId = user;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contract) {
        this.contractNumber = contract;
    }


    @Override
    public String toString() {
        return "Declaration{" +
                "userId=" + userId +
                ", contractNumber=" + contractNumber +
                '}';
    }
}
