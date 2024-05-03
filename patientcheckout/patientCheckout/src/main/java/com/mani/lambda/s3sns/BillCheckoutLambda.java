package com.mani.lambda.s3sns;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BillCheckoutLambda {

    ObjectMapper mapper= new ObjectMapper();

    public void handler(SNSEvent event){
        event.getRecords().forEach(snsRecord -> {
            try {
                PatientCheckoutEvent patientCheckoutEvent=mapper.readValue(snsRecord.getSNS().getMessage(),PatientCheckoutEvent.class);
                System.out.println(patientCheckoutEvent);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
