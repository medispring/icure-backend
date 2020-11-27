//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.15 at 03:32:18 PM CEST 
//


package org.taktik.icure.be.samv2v5.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LegalBasisTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LegalBasisTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ROYAL_DECREE"/>
 *     &lt;enumeration value="COORDINATED_LAW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LegalBasisTypeType", namespace = "urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit")
@XmlEnum
public enum LegalBasisTypeType {

    ROYAL_DECREE,
    COORDINATED_LAW;

    public String value() {
        return name();
    }

    public static LegalBasisTypeType fromValue(String v) {
        return valueOf(v);
    }

}