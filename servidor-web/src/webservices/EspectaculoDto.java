
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
 * <p>Java class for espectaculoDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="espectaculoDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duracionHoras" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="duracionMinutos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minEspectadores" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxEspectadores" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaFinalizado" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://webservices/}estado" minOccurs="0"/>
 *         &lt;element name="descPremio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantPremio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="organizador" type="{http://webservices/}artistaDto" minOccurs="0"/>
 *         &lt;element name="funcionesAsociadas" type="{http://webservices/}funcionDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paquetesAsociados" type="{http://webservices/}paqueteDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="categoriasAsociadas" type="{http://webservices/}categoriaDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombrePlataforma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlImagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantFavoritos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="valoraciones" type="{http://webservices/}valoracionDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="urlVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "espectaculoDto", propOrder = {
    "nombre",
    "descripcion",
    "duracionHoras",
    "duracionMinutos",
    "minEspectadores",
    "maxEspectadores",
    "url",
    "costo",
    "fechaRegistro",
    "fechaFinalizado",
    "estado",
    "descPremio",
    "cantPremio",
    "organizador",
    "funcionesAsociadas",
    "paquetesAsociados",
    "categoriasAsociadas",
    "nombrePlataforma",
    "urlImagen",
    "cantFavoritos",
    "valoraciones",
    "urlVideo"
})
public class EspectaculoDto {

    protected String nombre;
    protected String descripcion;
    protected int duracionHoras;
    protected int duracionMinutos;
    protected int minEspectadores;
    protected int maxEspectadores;
    protected String url;
    protected float costo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFinalizado;
    @XmlSchemaType(name = "string")
    protected Estado estado;
    protected String descPremio;
    protected int cantPremio;
    protected ArtistaDto organizador;
    @XmlElement(nillable = true)
    protected List<FuncionDto> funcionesAsociadas;
    @XmlElement(nillable = true)
    protected List<PaqueteDto> paquetesAsociados;
    @XmlElement(nillable = true)
    protected List<CategoriaDto> categoriasAsociadas;
    protected String nombrePlataforma;
    protected String urlImagen;
    protected int cantFavoritos;
    @XmlElement(nillable = true)
    protected List<ValoracionDto> valoraciones;
    protected String urlVideo;

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
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the duracionHoras property.
     * 
     */
    public int getDuracionHoras() {
        return duracionHoras;
    }

    /**
     * Sets the value of the duracionHoras property.
     * 
     */
    public void setDuracionHoras(int value) {
        this.duracionHoras = value;
    }

    /**
     * Gets the value of the duracionMinutos property.
     * 
     */
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     * Sets the value of the duracionMinutos property.
     * 
     */
    public void setDuracionMinutos(int value) {
        this.duracionMinutos = value;
    }

    /**
     * Gets the value of the minEspectadores property.
     * 
     */
    public int getMinEspectadores() {
        return minEspectadores;
    }

    /**
     * Sets the value of the minEspectadores property.
     * 
     */
    public void setMinEspectadores(int value) {
        this.minEspectadores = value;
    }

    /**
     * Gets the value of the maxEspectadores property.
     * 
     */
    public int getMaxEspectadores() {
        return maxEspectadores;
    }

    /**
     * Sets the value of the maxEspectadores property.
     * 
     */
    public void setMaxEspectadores(int value) {
        this.maxEspectadores = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
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
     * Gets the value of the fechaFinalizado property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinalizado() {
        return fechaFinalizado;
    }

    /**
     * Sets the value of the fechaFinalizado property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinalizado(XMLGregorianCalendar value) {
        this.fechaFinalizado = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link Estado }
     *     
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Estado }
     *     
     */
    public void setEstado(Estado value) {
        this.estado = value;
    }

    /**
     * Gets the value of the descPremio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescPremio() {
        return descPremio;
    }

    /**
     * Sets the value of the descPremio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescPremio(String value) {
        this.descPremio = value;
    }

    /**
     * Gets the value of the cantPremio property.
     * 
     */
    public int getCantPremio() {
        return cantPremio;
    }

    /**
     * Sets the value of the cantPremio property.
     * 
     */
    public void setCantPremio(int value) {
        this.cantPremio = value;
    }

    /**
     * Gets the value of the organizador property.
     * 
     * @return
     *     possible object is
     *     {@link ArtistaDto }
     *     
     */
    public ArtistaDto getOrganizador() {
        return organizador;
    }

    /**
     * Sets the value of the organizador property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArtistaDto }
     *     
     */
    public void setOrganizador(ArtistaDto value) {
        this.organizador = value;
    }

    /**
     * Gets the value of the funcionesAsociadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the funcionesAsociadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFuncionesAsociadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FuncionDto }
     * 
     * 
     */
    public List<FuncionDto> getFuncionesAsociadas() {
        if (funcionesAsociadas == null) {
            funcionesAsociadas = new ArrayList<FuncionDto>();
        }
        return this.funcionesAsociadas;
    }

    /**
     * Gets the value of the paquetesAsociados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paquetesAsociados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaquetesAsociados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaqueteDto }
     * 
     * 
     */
    public List<PaqueteDto> getPaquetesAsociados() {
        if (paquetesAsociados == null) {
            paquetesAsociados = new ArrayList<PaqueteDto>();
        }
        return this.paquetesAsociados;
    }

    /**
     * Gets the value of the categoriasAsociadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoriasAsociadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoriasAsociadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaDto }
     * 
     * 
     */
    public List<CategoriaDto> getCategoriasAsociadas() {
        if (categoriasAsociadas == null) {
            categoriasAsociadas = new ArrayList<CategoriaDto>();
        }
        return this.categoriasAsociadas;
    }

    /**
     * Gets the value of the nombrePlataforma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePlataforma() {
        return nombrePlataforma;
    }

    /**
     * Sets the value of the nombrePlataforma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePlataforma(String value) {
        this.nombrePlataforma = value;
    }

    /**
     * Gets the value of the urlImagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Sets the value of the urlImagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlImagen(String value) {
        this.urlImagen = value;
    }

    /**
     * Gets the value of the cantFavoritos property.
     * 
     */
    public int getCantFavoritos() {
        return cantFavoritos;
    }

    /**
     * Sets the value of the cantFavoritos property.
     * 
     */
    public void setCantFavoritos(int value) {
        this.cantFavoritos = value;
    }

    /**
     * Gets the value of the valoraciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valoraciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValoraciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValoracionDto }
     * 
     * 
     */
    public List<ValoracionDto> getValoraciones() {
        if (valoraciones == null) {
            valoraciones = new ArrayList<ValoracionDto>();
        }
        return this.valoraciones;
    }

    /**
     * Gets the value of the urlVideo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Sets the value of the urlVideo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlVideo(String value) {
        this.urlVideo = value;
    }

}
