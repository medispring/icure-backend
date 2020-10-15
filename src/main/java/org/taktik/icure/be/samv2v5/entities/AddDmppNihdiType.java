//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.15 at 03:32:18 PM CEST 
//


package org.taktik.icure.be.samv2v5.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddDmppNihdiType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddDmppNihdiType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:actual:common}AddDmppType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:actual:common}DmppReimbursementFields"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ctiExtended" use="required" type="{urn:be:fgov:ehealth:samws:v2:core}CtiExtendedType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddDmppNihdiType", namespace = "urn:be:fgov:ehealth:samws:v2:actual:common", propOrder = {
    "reimbursable"
})
public class AddDmppNihdiType
    extends AddDmppType
{

    @XmlElement(name = "Reimbursable")
    protected boolean reimbursable;
    @XmlAttribute(name = "ctiExtended", required = true)
    protected String ctiExtended;

    /**
     * Gets the value of the reimbursable property.
     * 
     */
    public boolean isReimbursable() {
        return reimbursable;
    }

    /**
     * Sets the value of the reimbursable property.
     * 
     */
    public void setReimbursable(boolean value) {
        this.reimbursable = value;
    }

    /**
     * Gets the value of the ctiExtended property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtiExtended() {
        return ctiExtended;
    }

    /**
     * Sets the value of the ctiExtended property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtiExtended(String value) {
        this.ctiExtended = value;
    }

}
