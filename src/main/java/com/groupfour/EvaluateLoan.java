package com.groupfour;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.ArrayList;

@Named
public class EvaluateLoan implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        User user = new User();
        user.setName((String) delegateExecution.getVariable("name"));
        user.setSurname((String) delegateExecution.getVariable("surname"));
        user.setIdentity((String) delegateExecution.getVariable("identity"));
        user.setEmail((String) delegateExecution.getVariable("email"));
        user.setCreditType((String) delegateExecution.getVariable("creditType"));
        user.setCreditNote((String) delegateExecution.getVariable("creditNote"));
        user.setRequestedCreditAmount(Integer.parseInt((String) delegateExecution.getVariable("requestedCreditAmount")));
        user.setSalary(Integer.parseInt((String) delegateExecution.getVariable("salary")));
        user.setTerm((String) delegateExecution.getVariable("term"));
        DFA.setApplyStatus(true);

        Loans loans = new Loans();
        Evaluation evaluation = new Evaluation(user);
        if(evaluation.creditNoteCheck(loans)){
            DFA.setConsistencyOfInfos(true);
            evaluation.termCheck(loans);
            evaluation.creditTypeCheck(loans);
            evaluation.amountCheck(loans);
        } else {
            DFA.setConsistencyOfInfos(false);
            DFA.setInformingTheUser(true);
            loans.setLoans(new ArrayList<>());
        }

        if(DFA.isApplyStatus() && DFA.isConsistencyOfInfos()){
            delegateExecution.setVariable("successMessage", "Bilgiler başarı ile email adresine gönderilmiştir!");
            DFA.setInformingTheUser(true);
        }else{
            delegateExecution.setVariable("successMessage", "Size uygun kredi bulunmamaktadır veya girmiş olduğunu bilgiler yeterli değil-hatalı.");
            DFA.setInformingTheUser(true);
        }

        if(DFA.isInformingTheUser()){
            new GMailer().sendMail("We have suitable Offers For You!",
                    user.getEmail(), //TODO: Bu kısımda email değiştirilecek
                    evaluation.generateEvaluateMessage(loans));
            if(DFA.isSendEmail()){
                DFA.setApplicationCompleted(true);
            }else{
                DFA.setApplicationCompleted(true);
            }
        }


    }
}
