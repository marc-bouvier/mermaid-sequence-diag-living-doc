package fr.baldir;

import fr.baldir.services.FichesContact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FicheContactController {

    private final    Logger mermaidLog = LoggerFactory.getLogger("mermaid");

    private final FichesContact fichesContact;
    private final IbanValidation ibanValidation;

    public FicheContactController(FichesContact fichesContact, IbanValidation ibanValidation) {
        this.fichesContact = fichesContact;
        this.ibanValidation = ibanValidation;
    }

    public void patchFicheContact(FicheContactPatch ficheContactPatch) {
        var contactId = ficheContactPatch.contactId();
        mermaidLog.trace("Front -->> MyApi : PATCH /{}/fiche-contact, <br/> {}", contactId, ficheContactPatch.render());

        if (ficheContactPatch.hasIban()) {
            var iban = ficheContactPatch.iban();
            if (!ibanValidation.validateIban(contactId, iban)) {
                mermaidLog.trace("MyApi -->> Front : 400 Bad Request");
                return;
                // -> HTTP 400 error
            } else {
                fichesContact.updateContactIban(contactId, iban);
            }
        }

        mermaidLog.trace("MyApi -->> Front : 200 OK");
    }

    public record FicheContactPatch(String contactId, String iban) {
        boolean hasIban() {
            return iban != null;
        }

        public String render() {
            return "contact id : " + contactId + ", iban : " + iban;
        }
    }
}