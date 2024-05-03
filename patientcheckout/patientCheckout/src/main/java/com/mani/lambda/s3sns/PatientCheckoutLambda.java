package com.mani.lambda.s3sns;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PatientCheckoutLambda {
    AmazonS3 s3= AmazonS3ClientBuilder.defaultClient();
    ObjectMapper mapper= new ObjectMapper();

    AmazonSNS sns= AmazonSNSClientBuilder.defaultClient();

    public void handler(S3Event event){
        event.getRecords().forEach(record-> {
                    S3ObjectInputStream s3ObjectInputStream= s3.getObject(record.getS3().getBucket().getName(),
                            record.getS3().getObject().getKey()).getObjectContent();
                    try {
                        List<PatientCheckoutEvent> checkoutEvents= Arrays.asList(mapper.readValue(s3ObjectInputStream,PatientCheckoutEvent[].class));
                        checkoutEvents.forEach(
                                patientCheckoutEvent -> {
                                    try {
                                        sns.publish(System.getenv("PATIENT_CHECKOUT_TOPIC"),mapper.writeValueAsString(patientCheckoutEvent));
                                    } catch (JsonProcessingException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                );
    }
}
