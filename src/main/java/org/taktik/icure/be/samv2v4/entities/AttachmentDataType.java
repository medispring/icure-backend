//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.12 at 04:36:37 PM CEST 
//


package org.taktik.icure.be.samv2v4.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AttachmentDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AttachmentDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}DataPeriodType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit}AttachmentFields"/>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:export}AttachmentReferenceFields"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttachmentDataType", propOrder = {
    "name",
    "templateUrl",
    "mandatory",
    "appendix",
    "formCategory"
})
public class AttachmentDataType
    extends DataPeriodType
{

    @XmlElement(name = "Name", namespace = "urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit", required = true)
    protected Text255Type name;
    @XmlElement(name = "TemplateUrl", namespace = "urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit", required = true)
    protected String templateUrl;
    @XmlElement(name = "Mandatory", namespace = "urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit")
    protected boolean mandatory;
    @XmlElement(name = "Appendix", required = true)
    protected AppendixType appendix;
    @XmlElement(name = "FormCategory", required = true)
    protected FormCategoryType formCategory;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link Text255Type }
     *     
     */
    public Text255Type getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link Text255Type }
     *     
     */
    public void setName(Text255Type value) {
        this.name = value;
    }

    /**
     * Gets the value of the templateUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateUrl() {
        return templateUrl;
    }

    /**
     * Sets the value of the templateUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateUrl(String value) {
        this.templateUrl = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Sets the value of the mandatory property.
     * 
     */
    public void setMandatory(boolean value) {
        this.mandatory = value;
    }

    /**
     * Gets the value of the appendix property.
     * 
     * @return
     *     possible object is
     *     {@link AppendixType }
     *     
     */
    public AppendixType getAppendix() {
        return appendix;
    }

    /**
     * Sets the value of the appendix property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppendixType }
     *     
     */
    public void setAppendix(AppendixType value) {
        this.appendix = value;
    }

    /**
     * Gets the value of the formCategory property.
     * 
     * @return
     *     possible object is
     *     {@link FormCategoryType }
     *     
     */
    public FormCategoryType getFormCategory() {
        return formCategory;
    }

    /**
     * Sets the value of the formCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormCategoryType }
     *     
     */
    public void setFormCategory(FormCategoryType value) {
        this.formCategory = value;
    }

}
