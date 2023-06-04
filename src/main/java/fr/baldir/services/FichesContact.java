package fr.baldir.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FichesContact {
    private final Logger mermaidLog = LoggerFactory.getLogger("mermaid");

    public void updateContactIban(String contactId, String iban) {
        mermaidLog.trace("MyApi -->> ContactsService : PUT /{}/fiche-contact", contactId);
        // fictive call to some other external service
        mermaidLog.trace("ContactsService -->> MyApi : 200 OK");
    }
}