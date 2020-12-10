package com.example.amazonSnsG.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.example.amazonSnsG.model.Notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
// Causes Lombok to generate a logger field.
@RestController
public class SnsController {
	// Topic arn. You are free to choose their topic arn.
	@Value("$topic_arn")
    private String TOPIC_ARN ;
    public static final Logger LOG = LoggerFactory.getLogger(SnsController.class);
    @Autowired
    private AmazonSNSClient amazonSNSClient;
 
    // URL - http://localhost:10093/addSubscription/myemail@somecompany.com
    // NOTE - In this tutorial, we are skipping the email validation part. Trusting that you'll add a valid email address.
    @PostMapping(value = "/addSubscription/{protocol}/{value}")
    public ResponseEntity<String> addSubscription(@PathVariable final String protocol, @PathVariable final String value) {
        LOG.info("Adding new email subscription = {} to the topic.", value);
        final SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN, protocol, value);
        amazonSNSClient.subscribe(subscribeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    // URL - http://localhost:10093/sendNotification
    // Sample request body -
    //  {
    //      "subject": "Springboot sns demo email",
    //      "message": "Lorem Ipsum is simply dummied text of the printing and typesetting industry."
    //  }
    @PostMapping(value = "/sendNotification")
    public ResponseEntity<String> publishMessageToTopic(@RequestBody final Notification notification) {
        LOG.info("Publishing the notification = {} to the topic.", notification.toString());
       final PublishRequest publishRequest = new PublishRequest(TOPIC_ARN,notification.getId(), notification.getMessage());
        amazonSNSClient.publish(publishRequest);
        return new ResponseEntity<>("Notification sent successfully!!", HttpStatus.OK);
    }

}
