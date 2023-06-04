package fr.baldir.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ficoba est le Fichier des comptes bancaires Français.
 */
public class Ficoba {
    Logger mermaidLog = LoggerFactory.getLogger("mermaid");

    public BankAccountInformations loadBankAccountInformations(String iban) {
        String ficobaUrl = "https://ficobaUrl/validation-route";
        mermaidLog.trace("MyApi -->> Ficoba : POST {} <br/> iban : {}", ficobaUrl, iban);
        // simulate some API call to Ficoba service
        var bankAccountInformations = new BankAccountInformations(iban);
        mermaidLog.trace("Ficoba -->> MyApi : {}", bankAccountInformations.render());
        return bankAccountInformations;
    }


    public record BankAccountInformations(String iban) {
        public boolean accountExists() {
            return "FR7600000000001".equals(iban);
        }

        public String render() {
            return iban + " " + (accountExists() ? "exists" : "does not exist");
        }
    }
}
