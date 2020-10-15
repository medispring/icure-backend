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
 * <p>Java class for ExportVirtualMedicinesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExportVirtualMedicinesType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}VersionedExportType">
 *       &lt;sequence>
 *         &lt;element name="Vtm" type="{urn:be:fgov:ehealth:samws:v2:export}VtmFullDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="VmpGroup" type="{urn:be:fgov:ehealth:samws:v2:export}VmpGroupFullDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CommentedClassification" type="{urn:be:fgov:ehealth:samws:v2:export}CommentedClassificationFullDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Vmp" type="{urn:be:fgov:ehealth:samws:v2:export}VmpFullDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExportVirtualMedicinesType", propOrder = {
    "vtm",
    "vmpGroup",
    "commentedClassification",
    "vmp"
})
public class ExportVirtualMedicinesType
    extends VersionedExportType
{

    @XmlElement(name = "Vtm")
    protected List<VtmFullDataType> vtm;
    @XmlElement(name = "VmpGroup")
    protected List<VmpGroupFullDataType> vmpGroup;
    @XmlElement(name = "CommentedClassification")
    protected List<CommentedClassificationFullDataType> commentedClassification;
    @XmlElement(name = "Vmp")
    protected List<VmpFullDataType> vmp;

    /**
     * Gets the value of the vtm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vtm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVtm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VtmFullDataType }
     * 
     * 
     */
    public List<VtmFullDataType> getVtm() {
        if (vtm == null) {
            vtm = new ArrayList<VtmFullDataType>();
        }
        return this.vtm;
    }

    /**
     * Gets the value of the vmpGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vmpGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVmpGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VmpGroupFullDataType }
     * 
     * 
     */
    public List<VmpGroupFullDataType> getVmpGroup() {
        if (vmpGroup == null) {
            vmpGroup = new ArrayList<VmpGroupFullDataType>();
        }
        return this.vmpGroup;
    }

    /**
     * Gets the value of the commentedClassification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commentedClassification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommentedClassification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommentedClassificationFullDataType }
     * 
     * 
     */
    public List<CommentedClassificationFullDataType> getCommentedClassification() {
        if (commentedClassification == null) {
            commentedClassification = new ArrayList<CommentedClassificationFullDataType>();
        }
        return this.commentedClassification;
    }

    /**
     * Gets the value of the vmp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vmp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVmp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VmpFullDataType }
     * 
     * 
     */
    public List<VmpFullDataType> getVmp() {
        if (vmp == null) {
            vmp = new ArrayList<VmpFullDataType>();
        }
        return this.vmp;
    }

}
