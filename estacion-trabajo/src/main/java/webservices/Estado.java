
package webservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estado.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="estado">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INGRESADO"/>
 *     &lt;enumeration value="RECHAZADO"/>
 *     &lt;enumeration value="ACEPTADO"/>
 *     &lt;enumeration value="FINALIZADO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estado")
@XmlEnum
public enum Estado {

    INGRESADO,
    RECHAZADO,
    ACEPTADO,
    FINALIZADO;

    public String value() {
        return name();
    }

    public static Estado fromValue(String v) {
        return valueOf(v);
    }

}
