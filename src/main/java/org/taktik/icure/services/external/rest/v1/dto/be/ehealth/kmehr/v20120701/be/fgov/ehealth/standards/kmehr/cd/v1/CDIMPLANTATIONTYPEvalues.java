//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:48:49 PM CEST 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-IMPLANTATION-TYPEvalues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-IMPLANTATION-TYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="unimedial"/>
 *     &lt;enumeration value="unilateral"/>
 *     &lt;enumeration value="bicompartimental"/>
 *     &lt;enumeration value="femoropatellar"/>
 *     &lt;enumeration value="totalcr"/>
 *     &lt;enumeration value="totalps"/>
 *     &lt;enumeration value="totalcck"/>
 *     &lt;enumeration value="hinge"/>
 *     &lt;enumeration value="totaluc"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-IMPLANTATION-TYPEvalues")
@XmlEnum
public enum CDIMPLANTATIONTYPEvalues {

    @XmlEnumValue("unimedial")
    UNIMEDIAL("unimedial"),
    @XmlEnumValue("unilateral")
    UNILATERAL("unilateral"),
    @XmlEnumValue("bicompartimental")
    BICOMPARTIMENTAL("bicompartimental"),
    @XmlEnumValue("femoropatellar")
    FEMOROPATELLAR("femoropatellar"),
    @XmlEnumValue("totalcr")
    TOTALCR("totalcr"),
    @XmlEnumValue("totalps")
    TOTALPS("totalps"),
    @XmlEnumValue("totalcck")
    TOTALCCK("totalcck"),
    @XmlEnumValue("hinge")
    HINGE("hinge"),
    @XmlEnumValue("totaluc")
    TOTALUC("totaluc"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    CDIMPLANTATIONTYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDIMPLANTATIONTYPEvalues fromValue(String v) {
        for (CDIMPLANTATIONTYPEvalues c: CDIMPLANTATIONTYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
