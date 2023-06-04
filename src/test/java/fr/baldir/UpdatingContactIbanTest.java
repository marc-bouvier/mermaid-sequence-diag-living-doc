package fr.baldir;

import fr.baldir.FicheContactController.FicheContactPatch;
import fr.baldir.services.FichesContact;
import fr.baldir.services.Ficoba;
import fr.baldir.services.Insurances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// # tag::test_setup_for_mermaid_headers[]
@DisplayName("Updating contact's IBAN")
@IndicativeSentencesGeneration(separator = " : ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
public class UpdatingContactIbanTest {
    Logger mermaidLog = LoggerFactory.getLogger("mermaid");

    private Logger log = LoggerFactory.getLogger(UpdatingContactIbanTest.class);
    private FicheContactController ficheContactController;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        ficheContactController = createSut();

        log.info(testInfo.getDisplayName() + ". Log visible in 'normal' test runs but not in 'mermaid' run");

        // Some layout and diagram title as %% mermaid comment
        mermaidLog.trace("");
        mermaidLog.trace("%% " + testInfo.getDisplayName());
        mermaidLog.trace("");

        // Mermaid diagram headers and options
        mermaidLog.trace("sequenceDiagram");
        mermaidLog.trace("autonumber");
    }
    // # end::test_setup_for_mermaid_headers[]

    // # tag::test_run[]
    @Test
    void when_iban_is_valid() {
        var contactId = "00001";
        var iban = "FR7600000000001";

        ficheContactController.patchFicheContact(new FicheContactPatch(contactId, iban));
    }
    // # end::test_run[]

    @Test
    void when_iban_is_not_found() {
        var contactId = "00001";
        var iban = "FR7600000000000";

        ficheContactController.patchFicheContact(new FicheContactPatch(contactId, iban));

    }

    FicheContactController createSut() {
        return new FicheContactController(
                new FichesContact(),
                new IbanValidation(
                        new Ficoba(),
                        new Insurances()));
    }
}
