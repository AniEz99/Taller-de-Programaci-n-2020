
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for funcionMinDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="funcionMinDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombrePlat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreEsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreFunc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funcionMinDto", propOrder = {
    "nombrePlat",
    "nombreEsp",
    "nombreFunc"
})
public class FuncionMinDto {

    protected String nombrePlat;
    protected String nombreEsp;
    protected String nombreFunc;

    /**
     * Gets the value of the nombrePlat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePlat() {
        return nombrePlat;
    }

    /**
     * Sets the value of the nombrePlat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePlat(String value) {
        this.nombrePlat = value;
    }

    /**
     * Gets the value of the nombreEsp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEsp() {
        return nombreEsp;
    }

    /**
     * Sets the value of the nombreEsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEsp(String value) {
        this.nombreEsp = value;
    }

    /**
     * Gets the value of the nombreFunc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreFunc() {
        return nombreFunc;
    }

    /**
     * Sets the value of the nombreFunc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreFunc(String value) {
        this.nombreFunc = value;
    }

}
