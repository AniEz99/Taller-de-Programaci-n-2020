
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for funcionDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="funcionDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="horaComienzoHs" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="horaComienzoMin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="invitados" type="{http://webservices/}usuarioDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sorteado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="registros" type="{http://webservices/}registroDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ganadores" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funcionDto", propOrder = {
    "nombre",
    "fechaInicio",
    "horaComienzoHs",
    "horaComienzoMin",
    "fechaRegistro",
    "invitados",
    "imagen",
    "sorteado",
    "registros",
    "ganadores"
})
public class FuncionDto {

    protected String nombre;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicio;
    protected int horaComienzoHs;
    protected int horaComienzoMin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlElement(nillable = true)
    protected List<UsuarioDto> invitados;
    protected String imagen;
    protected boolean sorteado;
    @XmlElement(nillable = true)
    protected List<RegistroDto> registros;
    @XmlElement(nillable = true)
    protected List<String> ganadores;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the horaComienzoHs property.
     * 
     */
    public int getHoraComienzoHs() {
        return horaComienzoHs;
    }

    /**
     * Sets the value of the horaComienzoHs property.
     * 
     */
    public void setHoraComienzoHs(int value) {
        this.horaComienzoHs = value;
    }

    /**
     * Gets the value of the horaComienzoMin property.
     * 
     */
    public int getHoraComienzoMin() {
        return horaComienzoMin;
    }

    /**
     * Sets the value of the horaComienzoMin property.
     * 
     */
    public void setHoraComienzoMin(int value) {
        this.horaComienzoMin = value;
    }

    /**
     * Gets the value of the fechaRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Sets the value of the fechaRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRegistro(XMLGregorianCalendar value) {
        this.fechaRegistro = value;
    }

    /**
     * Gets the value of the invitados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invitados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvitados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UsuarioDto }
     * 
     * 
     */
    public List<UsuarioDto> getInvitados() {
        if (invitados == null) {
            invitados = new ArrayList<UsuarioDto>();
        }
        return this.invitados;
    }

    /**
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Gets the value of the sorteado property.
     * 
     */
    public boolean isSorteado() {
        return sorteado;
    }

    /**
     * Sets the value of the sorteado property.
     * 
     */
    public void setSorteado(boolean value) {
        this.sorteado = value;
    }

    /**
     * Gets the value of the registros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegistros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegistroDto }
     * 
     * 
     */
    public List<RegistroDto> getRegistros() {
        if (registros == null) {
            registros = new ArrayList<RegistroDto>();
        }
        return this.registros;
    }

    /**
     * Gets the value of the ganadores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ganadores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGanadores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGanadores() {
        if (ganadores == null) {
            ganadores = new ArrayList<String>();
        }
        return this.ganadores;
    }

}
