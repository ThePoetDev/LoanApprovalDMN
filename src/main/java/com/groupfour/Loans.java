package com.groupfour;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loans {
    private List<Loan> loans;

    public Loans() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/loans.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray loansList = (JSONArray) obj;
            loans = new ArrayList<Loan>();
            loansList.forEach(loans -> parseLoanObject((JSONObject) loans));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseLoanObject(JSONObject loan) {
        String bankname = (String) loan.get("bankname");
        int amount = Integer.parseInt((String) loan.get("amount"));
        String term = (String) loan.get("term");
        double interest = Double.parseDouble((String) loan.get("interest"));
        double mountlyPayment = Double.parseDouble((String) loan.get("mountlyPayment"));
        double totalPayment = Double.parseDouble((String) loan.get("totalPayment"));
        int creditNote = Integer.parseInt((String) loan.get("creditNote"));
        String creditType = (String) loan.get("creditType");

        Loan currentLoan = new Loan();
        currentLoan.setBankName(bankname);
        currentLoan.setAmount(amount);
        currentLoan.setTerm(term);
        currentLoan.setInterest(interest);
        currentLoan.setMonthlyPayment(mountlyPayment);
        currentLoan.setTotalPayment(totalPayment);
        currentLoan.setCreditNote(creditNote);
        currentLoan.setCreditType(creditType);
        this.loans.add(currentLoan);
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
