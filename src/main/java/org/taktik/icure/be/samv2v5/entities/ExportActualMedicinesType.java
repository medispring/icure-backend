//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.15 at 03:32:18 PM CEST 
//


package org.taktik.icure.be.samv2v5.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExportActualMedicinesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExportActualMedicinesType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}VersionedExportType">
 *       &lt;sequence>
 *         &lt;element name="Amp" type="{urn:be:fgov:ehealth:samws:v2:export}AmpFullDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExportActualMedicinesType", propOrder = {
    "amp"
})
public class ExportActualMedicinesType
    extends VersionedExportType
{

    @XmlElement(name = "Amp")
    protected List<AmpFullDataType> amp;

    /**
     * Gets the value of the amp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AmpFullDataType }
     * 
     * 
     */
    public List<AmpFullDataType> getAmp() {
        if (amp == null) {
            amp = new ArrayList<AmpFullDataType>();
        }
        return this.amp;
    }

}