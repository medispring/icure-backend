//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.12 at 04:36:37 PM CEST 
//


package org.taktik.icure.be.samv2v4.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReimbursementTermKeyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReimbursementTermKeyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="sequenceNr" use="required" type="{urn:be:fgov:ehealth:samws:v2:core}PositiveShortType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReimbursementTermKeyType", namespace = "urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit")
@XmlSeeAlso({
    ReimbursementTermFullDataType.class,
    ReimbursementTermType.class
})
public class ReimbursementTermKeyType {

    @XmlAttribute(name = "sequenceNr", required = true)
    protected short sequenceNr;

    /**
     * Gets the value of the sequenceNr property.
     * 
     */
    public short getSequenceNr() {
        return sequenceNr;
    }

    /**
     * Sets the value of the sequenceNr property.
     * 
     */
    public void setSequenceNr(short value) {
        this.sequenceNr = value;
    }

}
