package com.groupfour;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluation {
    private User user;

    public Evaluation(User user) {
        this.user = user;
    }

    public String generateEvaluateMessage(Loans loans) {
        StringBuilder returnString = new StringBuilder("Sayın " + user.getName() + " " + user.getSurname() + "\n");
        if (loans.getLoans().isEmpty()) {
            returnString = new StringBuilder("Girdiğiniz bilgilere uygun kredi bulunmamaktadır.");
        } else {
            returnString.append(getBestLoan(loans));
            returnString.append("Size uygun gördüğümüz krediler\n");

            for (int i = 0; i < loans.getLoans().size(); i++) {
                Loan currentLoan = loans.getLoans().get(i);
                System.out.println(currentLoan.getBankName());
                returnString.append("\nBanka: ").append(currentLoan.getBankName())
                        .append("\nKredi miktarı: ").append(currentLoan.getAmount()).append("₺")
                        .append("\nVade: ").append(currentLoan.getTerm())
                        .append("\nFaiz: %").append(currentLoan.getInterest())
                        .append("\nAylık Taksit: ").append(currentLoan.getMonthlyPayment()).append("₺")
                        .append("\nToplam Ödeme: ").append(currentLoan.getTotalPayment()).append("₺").append("\n");
            }


        }
        return returnString.toString();
    }

    public StringBuilder getBestLoan(Loans loans) {
        Loan bestLoan = null;
        double bestScore = Double.MAX_VALUE;
        StringBuilder str = new StringBuilder("Sizin için karar verdiğimiz en uygun kredi;\n");
        Loans tempLoans = loans;

        List<Loan> loanList = tempLoans.getLoans().stream().sorted(Comparator.comparingDouble(Loan::getInterest)).limit(3).collect(Collectors.toList()); // En uygun 3 krediyi sırala intereste göre

        for (Loan loan : loanList) {
            double score = calculateScore(loan, user.getSalary());
            if (score < bestScore) {
                bestLoan = loan;
                bestScore = score;
            }
        }


        str.append("\nBanka: ").append(bestLoan.getBankName())
                .append("\nKredi miktarı: ").append(bestLoan.getAmount()).append("₺")
                .append("\nVade: ").append(bestLoan.getTerm())
                .append("\nFaiz: %").append(bestLoan.getInterest())
                .append("\nAylık Taksit: ").append(bestLoan.getMonthlyPayment()).append("₺")
                .append("\nToplam Ödeme: ").append(bestLoan.getTotalPayment()).append("₺").append("\n\n\n--------------------------------------------------------------\n");

        return str;
    }

    private double calculateScore(Loan loan, double monthlySalary) {
        double interestWeight = 0.3;
        double monthlyPaymentWeight = 0.2;
        double totalPaymentWeight = 0.2;
        double monthlySalaryWeight = 0.3;

        double interestScore = interestWeight * loan.getInterest();
        double monthlyPaymentScore = monthlyPaymentWeight * loan.getMonthlyPayment();
        double totalPaymentScore = totalPaymentWeight * loan.getTotalPayment();

        double monthlySalaryScore = 0.0;
        if (monthlySalary > 0) {
            double maxMonthlySalary = 10000.0;
            double normalizedMonthlySalary = monthlySalary / maxMonthlySalary;
            monthlySalaryScore = monthlySalaryWeight * normalizedMonthlySalary;
        }

        return interestScore + monthlyPaymentScore + totalPaymentScore + monthlySalaryScore;
    }

    public void amountCheck(Loans loans) {
        Iterator<Loan> i = loans.getLoans().iterator();
        while (i.hasNext()) {
            Loan currentLoan = i.next();
            if ((this.user.getRequestedCreditAmount() > currentLoan.getAmount()) || (currentLoan.getAmount() > this.user.getRequestedCreditAmount() + (this.user.getRequestedCreditAmount() / 5))) {
                i.remove();
            }
        }

        if(loans.getLoans().isEmpty()){
            DFA.setEligibilityLoans(false);
        }else{
            DFA.setEligibilityLoans(true);
        }
    }

    public boolean creditNoteCheck(Loans loans) {
        if (checkNote()) {
            Iterator<Loan> i = loans.getLoans().iterator();
            while (i.hasNext()) {
                Loan currentLoan = i.next();
                if (Integer.parseInt(this.user.getCreditNote()) < currentLoan.getCreditNote()) {
                    i.remove();
                }
            }
            return true;
        }
        return false;
}

    public void creditTypeCheck(Loans loans) {
        Iterator<Loan> i = loans.getLoans().iterator();
        while (i.hasNext()) {
            Loan currentLoan = i.next();
            if (!this.user.getCreditType().equalsIgnoreCase(currentLoan.getCreditType())) {
                i.remove();
            }
        }

        if(loans.getLoans().isEmpty()){
            DFA.setEligibilityLoans(false);
        }else{
            DFA.setEligibilityLoans(true);
        }
    }

    private boolean checkNote() {
        if (Integer.parseInt(this.user.getCreditNote()) < 500) {
            return false;
        }
        return true;
    }

    public void termCheck(Loans loans) {
        Iterator<Loan> i = loans.getLoans().iterator();
        while (i.hasNext()) {
            Loan currentLoan = i.next();
            if (!currentLoan.getTerm().equalsIgnoreCase(user.getTerm())) {
                i.remove();
            }
        }

        if(loans.getLoans().isEmpty()){
            DFA.setEligibilityLoans(false);
        }else{
            DFA.setEligibilityLoans(true);
        }
    }
}
