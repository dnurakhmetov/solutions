/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.webservice1;

import kz.webservice1.ws1.StudentRequestDocument;
import kz.webservice1.ws1.StudentResponseDocument;
import kz.webservice1.ws1.StudentRequestDocument.*;
import kz.webservice1.ws1.StudentResponseDocument.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
/**
 *
 * @author Nurakhmetov
 */

@Endpoint
public class WS1Endpoint {
    private iWS1 ws1;
    
    public static Logger logger = null;
    
    
    @PayloadRoot(localPart = "StudentRequest", namespace = "http://www.webservice1.kz/WS1")
    public @ResponsePayload StudentResponseDocument submitStudent(@RequestPayload StudentRequestDocument rq){ 
        DOMConfigurator.configure(WS1Endpoint.class.getResource("/log4j.xml"));
        logger = Logger.getLogger("WS1_debug");
        logger.debug("Starting");
        ws1 = new WS1();
        StudentResponseDocument response = null;
        StudentRequest req = rq.getStudentRequest();
        response = StudentResponseDocument.Factory.newInstance();
        StudentResponse resp = response.addNewStudentResponse();
        if(req.getSurname()==null) {
        resp.setStatus(ws1.getStatus(req.getId(), req.getName(), req.getSubjectArray()));
        } else {
            resp.setStatus(ws1.getStatus(req.getId(), req.getName(), req.getSurname(), req.getSubjectArray()));
        }
        return response;
    }
}
