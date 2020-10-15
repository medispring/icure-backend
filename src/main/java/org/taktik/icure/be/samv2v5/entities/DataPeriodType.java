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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DataPeriodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataPeriodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{urn:be:fgov:ehealth:samws:v2:export}validityPeriod"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataPeriodType")
@XmlSeeAlso({
    AmppComponentDataType.class,
    DerogationImportDataType.class,
    CopaymentDataType.class,
    LegalTextDataType.class,
    AmpDataType.class,
    DmppDataType.class,
    CompoundingFormulaDataType.class,
    FormalInterpretationDataType.class,
    VmpDataType.class,
    RealActualIngredientEquivalentDataType.class,
    CompanyDataType.class,
    ReimbursementContextDataType.class,
    VmpGroupDataType.class,
    AmppComponentEquivalentDataType.class,
    RealVirtualIngredientDataType.class,
    LegalBasisDataType.class,
    VirtualIngredientDataType.class,
    NonMedicinalProductDataType.class,
    RealActualIngredientDataType.class,
    ReimbursementConditionDataType.class,
    ReimbursementTermDataType.class,
    AttachmentDataType.class,
    VtmDataType.class,
    AmpComponentDataType.class,
    CommercializationDataType.class,
    RootObjectDataType.class,
    LegalReferenceDataType.class,
    CompoundingIngredientDataType.class,
    SupplyProblemDataType.class,
    AmppDataType.class,
    VmpComponentDataType.class,
    CommentedClassificationDataType.class
})
public class DataPeriodType {

    @XmlAttribute(name = "from", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar from;
    @XmlAttribute(name = "to")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar to;

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrom(XMLGregorianCalendar value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTo(XMLGregorianCalendar value) {
        this.to = value;
    }

}
