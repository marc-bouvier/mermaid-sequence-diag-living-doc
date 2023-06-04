package fr.baldir.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Insurances {
    Logger mermaidLog = LoggerFactory.getLogger("mermaid");

    public InsuredDetails loadInsuredDetails(String contactId) {
        var insurersUrl = "https://insurersUrl/contacts/" + contactId;
        mermaidLog.trace("MyApi -->> Insurers : GET {}", insurersUrl);
        // some API call to Insurers service
        var insuredDetails = new InsuredDetails(contactId);
        mermaidLog.trace("Insurers -->> MyApi : {}", insuredDetails.render());
        return insuredDetails;
    }

    public record InsuredDetails(String contactId) {
        public String render() {
            return "Informations assuré : " + contactId;
        }
    }
}