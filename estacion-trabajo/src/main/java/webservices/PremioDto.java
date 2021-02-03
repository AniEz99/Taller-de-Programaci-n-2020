
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for premioDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="premioDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombrePremio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="espAsociado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="funcAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaPremio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="platAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "premioDto", propOrder = {
    "nombrePremio",
    "espAsociado",
    "funcAsociada",
    "fechaPremio",
    "platAsociada"
})
public class PremioDto {

    protected String nombrePremio;
    protected String espAsociado;
    protected String funcAsociada;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaPremio;
    protected String platAsociada;

    /**
     * Gets the value of the nombrePremio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePremio() {
        return nombrePremio;
    }

    /**
     * Sets the value of the nombrePremio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePremio(String value) {
        this.nombrePremio = value;
    }

    /**
     * Gets the value of the espAsociado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEspAsociado() {
        return espAsociado;
    }

    /**
     * Sets the value of the espAsociado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEspAsociado(String value) {
        this.espAsociado = value;
    }

    /**
     * Gets the value of the funcAsociada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncAsociada() {
        return funcAsociada;
    }

    /**
     * Sets the value of the funcAsociada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncAsociada(String value) {
        this.funcAsociada = value;
    }

    /**
     * Gets the value of the fechaPremio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPremio() {
        return fechaPremio;
    }

    /**
     * Sets the value of the fechaPremio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPremio(XMLGregorianCalendar value) {
        this.fechaPremio = value;
    }

    /**
     * Gets the value of the platAsociada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlatAsociada() {
        return platAsociada;
    }

    /**
     * Sets the value of the platAsociada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlatAsociada(String value) {
        this.platAsociada = value;
    }

}
