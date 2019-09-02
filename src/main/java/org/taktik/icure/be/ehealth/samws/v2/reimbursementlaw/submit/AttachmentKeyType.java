//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.reimbursementlaw.submit;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.samws.v2.consultation.ConsultAttachmentType;
import org.taktik.icure.be.samv2.entities.AttachmentFullDataType;


/**
 * <p>Java class for AttachmentKeyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AttachmentKeyType">
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
@XmlType(name = "AttachmentKeyType")
@XmlSeeAlso({
    AttachmentFullDataType.class,
    AttachmentType.class,
    ConsultAttachmentType.class
})
public class AttachmentKeyType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
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
