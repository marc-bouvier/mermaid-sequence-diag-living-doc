package fr.baldir.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// # tag::example_of_mermaid_log[]
public class Insurances {
    private final Logger mermaidLog = LoggerFactory.getLogger("mermaid");

    public InsuredDetails loadInsuredDetails(String contactId) {
        var insurersUrl = "https://insurersUrl/contacts/" + contactId;

        // Trace before remote call
        mermaidLog.trace("MyApi -->> Insurers : GET {}", insurersUrl);
        // simulate some API call to Insurers service
        var insuredDetails = new InsuredDetails(contactId);
        // Trace after remote call
        mermaidLog.trace("Insurers -->> MyApi : {}", insuredDetails.render());

        return insuredDetails;
    }
    // # end::example_of_mermaid_log[]

    public record InsuredDetails(String contactId) {
        public String render() {
            return "Informations assuré : " + contactId;
        }
    }
}