package com.groupfour;

public class User {
    private String name;
    private String surname;
    private String identity;
    private String email;
    private String creditType;
    private String creditNote;
    private String term;
    private int requestedCreditAmount;
    private int salary;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(String creditNote) {
        this.creditNote = creditNote;
    }

    public int getRequestedCreditAmount() {
        return requestedCreditAmount;
    }

    public void setRequestedCreditAmount(int requestedCreditAmount) {
        this.requestedCreditAmount = requestedCreditAmount;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
