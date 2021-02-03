
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for espectaculoMinDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="espectaculoMinDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomEsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomPlat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "espectaculoMinDto", propOrder = {
    "nomEsp",
    "nomPlat",
    "estado"
})
public class EspectaculoMinDto {

    protected String nomEsp;
    protected String nomPlat;
    protected String estado;

    /**
     * Gets the value of the nomEsp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomEsp() {
        return nomEsp;
    }

    /**
     * Sets the value of the nomEsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomEsp(String value) {
        this.nomEsp = value;
    }

    /**
     * Gets the value of the nomPlat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomPlat() {
        return nomPlat;
    }

    /**
     * Sets the value of the nomPlat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomPlat(String value) {
        this.nomPlat = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

}
