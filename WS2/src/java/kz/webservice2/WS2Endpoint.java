/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.webservice2;

import kz.webservice2.ws2.StudentRequestDocument;
import kz.webservice2.ws2.StudentResponseDocument;
import kz.webservice2.ws2.StudentRequestDocument.*;
import kz.webservice2.ws2.StudentResponseDocument.*;

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
public class WS2Endpoint {
    private iWS2 ws2;
    
    public static Logger logger = null;
    
    
    @PayloadRoot(localPart = "StudentRequest", namespace = "http://www.webservice2.kz/WS2")
    public @ResponsePayload StudentResponseDocument submitStudent(@RequestPayload StudentRequestDocument rq){ 
        DOMConfigurator.configure("log4j.xml");
        logger = Logger.getLogger("WS2_debug");
        logger.debug("Starting");
        ws2 = new WS2();
        StudentResponseDocument response = null;
        StudentRequest req = rq.getStudentRequest();
        response = StudentResponseDocument.Factory.newInstance();
        StudentResponse resp = response.addNewStudentResponse();
        resp.setStatus(ws2.getStatus(req.getId(), req.getSubjectArray()));
        return response;
    }
}
