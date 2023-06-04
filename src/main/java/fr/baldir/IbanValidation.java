package fr.baldir;

import fr.baldir.services.Ficoba;
import fr.baldir.services.Insurances;

public class IbanValidation {

    private final Ficoba ficoba;
    private final Insurances insurances;

    public IbanValidation(Ficoba ficoba, Insurances insurances) {
        this.ficoba = ficoba;
        this.insurances = insurances;
    }

    public boolean validateIban(String contactId, String iban) {
        // Abrégé : construction de payload
        var bankAccountInformations = ficoba.loadBankAccountInformations(iban);
        if (!bankAccountInformations.accountExists()) return false;
        var insuredDetails = insurances.loadInsuredDetails(contactId);

        var ibanForInsuredContact = new IbanForInsuredContact(insuredDetails, bankAccountInformations);
        return ibanForInsuredContact.isValid();
    }

    /**
     * Abrégé : logique pour vérifier que le compte bancaire
     * <ul>
     *     <li>correspondant à l'IBAN</li>
     *     <li>existe</li>
     *     <li>est ouvert</li>
     *     <li>correspond à l'identité de l'assuré</li>
     * </ul>
     *
     * @param insuredDetails
     * @param bankAccountInformations
     */
    public record IbanForInsuredContact(Insurances.InsuredDetails insuredDetails,
                                        Ficoba.BankAccountInformations bankAccountInformations) {

        public boolean isValid() {
            // Simplification for pedagogic purpose
            // domain correctness is not our concern here
            return "00001".equals(insuredDetails.contactId()) && "FR7600000000001".equals(bankAccountInformations.iban());
        }
    }

}
