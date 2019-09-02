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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExportReimbursementsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ExportReimbursementsType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}VersionedExportType">
 *       &lt;sequence>
 *         &lt;element name="ReimbursementContext" type="{urn:be:fgov:ehealth:samws:v2:export}ReimbursementContextFullDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExportReimbursementsType", propOrder = {
    "reimbursementContexts"
})
@XmlRootElement(name = "ExportReimbursements")
public class ExportReimbursements
    extends VersionedExportType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "ReimbursementContext")
    protected List<ReimbursementContextFullDataType> reimbursementContexts;

    /**
     * Gets the value of the reimbursementContexts property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reimbursementContexts property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReimbursementContexts().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReimbursementContextFullDataType }
     *
     *
     */
    public List<ReimbursementContextFullDataType> getReimbursementContexts() {
        if (reimbursementContexts == null) {
            reimbursementContexts = new ArrayList<ReimbursementContextFullDataType>();
        }
        return this.reimbursementContexts;
    }

}
