
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for registroDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registroDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="esCanjeable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="nomEspectador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreFuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registroDto", propOrder = {
    "fecha",
    "esCanjeable",
    "costo",
    "nomEspectador",
    "nombreFuncion"
})
public class RegistroDto {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    protected boolean esCanjeable;
    protected float costo;
    protected String nomEspectador;
    protected String nombreFuncion;

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the esCanjeable property.
     * 
     */
    public boolean isEsCanjeable() {
        return esCanjeable;
    }

    /**
     * Sets the value of the esCanjeable property.
     * 
     */
    public void setEsCanjeable(boolean value) {
        this.esCanjeable = value;
    }

    /**
     * Gets the value of the costo property.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Gets the value of the nomEspectador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomEspectador() {
        return nomEspectador;
    }

    /**
     * Sets the value of the nomEspectador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomEspectador(String value) {
        this.nomEspectador = value;
    }

    /**
     * Gets the value of the nombreFuncion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreFuncion() {
        return nombreFuncion;
    }

    /**
     * Sets the value of the nombreFuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreFuncion(String value) {
        this.nombreFuncion = value;
    }

}
