//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.21 at 02:32:17 AM GMT-08:00 
//


package com.mata.webservice.afe.sms;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mata.webservice.afe.sms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mata.webservice.afe.sms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendMessage }
     * 
     */
    public SendMessage createSendMessage() {
        return new SendMessage();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link SendMessageResponse }
     * 
     */
    public SendMessageResponse createSendMessageResponse() {
        return new SendMessageResponse();
    }

    /**
     * Create an instance of {@link SendBusinessCard }
     * 
     */
    public SendBusinessCard createSendBusinessCard() {
        return new SendBusinessCard();
    }

    /**
     * Create an instance of {@link SendBusinessCardResponse }
     * 
     */
    public SendBusinessCardResponse createSendBusinessCardResponse() {
        return new SendBusinessCardResponse();
    }

    /**
     * Create an instance of {@link SendWappush }
     * 
     */
    public SendWappush createSendWappush() {
        return new SendWappush();
    }

    /**
     * Create an instance of {@link SendWappushResponse }
     * 
     */
    public SendWappushResponse createSendWappushResponse() {
        return new SendWappushResponse();
    }

}
