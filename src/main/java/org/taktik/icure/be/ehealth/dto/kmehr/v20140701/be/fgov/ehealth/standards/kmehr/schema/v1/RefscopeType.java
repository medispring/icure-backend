//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:49:11 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20140701.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.dto.kmehr.v20140701.be.fgov.ehealth.standards.kmehr.cd.v1.CDREFSCOPE;
import org.taktik.icure.be.ehealth.dto.kmehr.v20140701.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;


/**
 * to specify if the minref and maxref references are adjusted to the patient's age, sex, ..., 
 * 
 * <p>Java class for refscopeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refscopeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-REFSCOPE"/>
 *         &lt;element name="refvalue" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="minref" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}minrefType" minOccurs="0"/>
 *                   &lt;element name="maxref" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}maxrefType" minOccurs="0"/>
 *                   &lt;element name="context" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refscopeType", propOrder = {
    "cd",
    "refvalues"
})
public class RefscopeType
    implements Serializable
{

    private final static long serialVersionUID = 20140701L;
    @XmlElement(required = true)
    protected CDREFSCOPE cd;
    @XmlElement(name = "refvalue")
    protected List<RefscopeType.Refvalue> refvalues;

    /**
     * Gets the value of the cd property.
     * 
     * @return
     *     possible object is
     *     {@link CDREFSCOPE }
     *     
     */
    public CDREFSCOPE getCd() {
        return cd;
    }

    /**
     * Sets the value of the cd property.
     * 
     * @param value
     *     allowed object is
     *     {@link CDREFSCOPE }
     *     
     */
    public void setCd(CDREFSCOPE value) {
        this.cd = value;
    }

    /**
     * Gets the value of the refvalues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refvalues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefvalues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefscopeType.Refvalue }
     * 
     * 
     */
    public List<RefscopeType.Refvalue> getRefvalues() {
        if (refvalues == null) {
            refvalues = new ArrayList<RefscopeType.Refvalue>();
        }
        return this.refvalues;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="minref" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}minrefType" minOccurs="0"/>
     *         &lt;element name="maxref" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}maxrefType" minOccurs="0"/>
     *         &lt;element name="context" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "minref",
        "maxref",
        "context"
    })
    public static class Refvalue
        implements Serializable
    {

        private final static long serialVersionUID = 20140701L;
        protected MinrefType minref;
        protected MaxrefType maxref;
        @XmlElement(required = true)
        protected TextType context;

        /**
         * Gets the value of the minref property.
         * 
         * @return
         *     possible object is
         *     {@link MinrefType }
         *     
         */
        public MinrefType getMinref() {
            return minref;
        }

        /**
         * Sets the value of the minref property.
         * 
         * @param value
         *     allowed object is
         *     {@link MinrefType }
         *     
         */
        public void setMinref(MinrefType value) {
            this.minref = value;
        }

        /**
         * Gets the value of the maxref property.
         * 
         * @return
         *     possible object is
         *     {@link MaxrefType }
         *     
         */
        public MaxrefType getMaxref() {
            return maxref;
        }

        /**
         * Sets the value of the maxref property.
         * 
         * @param value
         *     allowed object is
         *     {@link MaxrefType }
         *     
         */
        public void setMaxref(MaxrefType value) {
            this.maxref = value;
        }

        /**
         * Gets the value of the context property.
         * 
         * @return
         *     possible object is
         *     {@link TextType }
         *     
         */
        public TextType getContext() {
            return context;
        }

        /**
         * Sets the value of the context property.
         * 
         * @param value
         *     allowed object is
         *     {@link TextType }
         *     
         */
        public void setContext(TextType value) {
            this.context = value;
        }

    }

}
