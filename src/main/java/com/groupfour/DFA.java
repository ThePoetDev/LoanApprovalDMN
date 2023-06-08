package com.groupfour;

/**
 * q0: Application status
 * q1: Consistency of information
 * q2: Eligibility of loans
 * q3: Informing the user in Camunda
 * q4: Transmission of credits by e-mail
 * q5: Application completed
 */
public class DFA {
    private static boolean applyStatus;
    private static boolean consistencyOfInfos;
    private static boolean eligibilityLoans;
    private static boolean informingTheUser;
    private static boolean sendEmail;
    private static boolean applicationCompleted;

    public static boolean isApplyStatus() {
        return applyStatus;
    }

    public static void setApplyStatus(boolean applyStatus) {
        DFA.applyStatus = applyStatus;
    }

    public static boolean isConsistencyOfInfos() {
        return consistencyOfInfos;
    }

    public static void setConsistencyOfInfos(boolean consistencyOfInfos) {
        DFA.consistencyOfInfos = consistencyOfInfos;
    }

    public static boolean isEligibilityLoans() {
        return eligibilityLoans;
    }

    public static void setEligibilityLoans(boolean eligibilityLoans) {
        DFA.eligibilityLoans = eligibilityLoans;
    }

    public static boolean isInformingTheUser() {
        return informingTheUser;
    }

    public static void setInformingTheUser(boolean informingTheUser) {
        DFA.informingTheUser = informingTheUser;
    }

    public static boolean isSendEmail() {
        return sendEmail;
    }

    public static void setSendEmail(boolean sendEmail) {
        DFA.sendEmail = sendEmail;
    }

    public static void setApplicationCompleted(boolean applicationCompleted) {
        DFA.applicationCompleted = applicationCompleted;
    }

    public static boolean isApplicationCompleted() {
        return applicationCompleted;
    }
}
