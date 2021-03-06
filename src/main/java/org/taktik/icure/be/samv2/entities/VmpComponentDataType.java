//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.samv2.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.samws.v2.core.Text255Type;


/**
 * <p>Java class for VmpComponentDataType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="VmpComponentDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}DataPeriodType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:virtual:common}VmpComponentFields"/>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:export}VmpComponentReferenceFields"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VmpComponentDataType", propOrder = {
    "phaseNumber",
    "name",
    "virtualForm",
    "routeOfAdministrations"
})
public class VmpComponentDataType
    extends DataPeriodType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "PhaseNumber", namespace = "urn:be:fgov:ehealth:samws:v2:virtual:common")
    protected Short phaseNumber;
    @XmlElement(name = "Name", namespace = "urn:be:fgov:ehealth:samws:v2:virtual:common", required = true)
    protected Text255Type name;
    @XmlElement(name = "VirtualForm", required = true)
    protected VirtualFormWithStandardsType virtualForm;
    @XmlElement(name = "RouteOfAdministration", required = true)
    protected List<RouteOfAdministrationWithStandardsType> routeOfAdministrations;

    /**
     * Gets the value of the phaseNumber property.
     *
     * @return
     *     possible object is
     *     {@link Short }
     *
     */
    public Short getPhaseNumber() {
        return phaseNumber;
    }

    /**
     * Sets the value of the phaseNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Short }
     *
     */
    public void setPhaseNumber(Short value) {
        this.phaseNumber = value;
    }

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
     * Gets the value of the virtualForm property.
     *
     * @return
     *     possible object is
     *     {@link VirtualFormWithStandardsType }
     *
     */
    public VirtualFormWithStandardsType getVirtualForm() {
        return virtualForm;
    }

    /**
     * Sets the value of the virtualForm property.
     *
     * @param value
     *     allowed object is
     *     {@link VirtualFormWithStandardsType }
     *
     */
    public void setVirtualForm(VirtualFormWithStandardsType value) {
        this.virtualForm = value;
    }

    /**
     * Gets the value of the routeOfAdministrations property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routeOfAdministrations property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRouteOfAdministrations().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RouteOfAdministrationWithStandardsType }
     *
     *
     */
    public List<RouteOfAdministrationWithStandardsType> getRouteOfAdministrations() {
        if (routeOfAdministrations == null) {
            routeOfAdministrations = new ArrayList<RouteOfAdministrationWithStandardsType>();
        }
        return this.routeOfAdministrations;
    }

}
