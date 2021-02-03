
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for espectadorDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="espectadorDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices/}usuarioDto">
 *       &lt;sequence>
 *         &lt;element name="funciones" type="{http://webservices/}funcionMinDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paquetes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="espectaculosFavoritos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="valoraciones" type="{http://webservices/}valoracionDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="premiosGanados" type="{http://webservices/}premioDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "espectadorDto", propOrder = {
    "funciones",
    "paquetes",
    "espectaculosFavoritos",
    "valoraciones",
    "premiosGanados"
})
public class EspectadorDto
    extends UsuarioDto
{

    @XmlElement(nillable = true)
    protected List<FuncionMinDto> funciones;
    @XmlElement(nillable = true)
    protected List<String> paquetes;
    @XmlElement(nillable = true)
    protected List<String> espectaculosFavoritos;
    @XmlElement(nillable = true)
    protected List<ValoracionDto> valoraciones;
    @XmlElement(nillable = true)
    protected List<PremioDto> premiosGanados;

    /**
     * Gets the value of the funciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the funciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFunciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FuncionMinDto }
     * 
     * 
     */
    public List<FuncionMinDto> getFunciones() {
        if (funciones == null) {
            funciones = new ArrayList<FuncionMinDto>();
        }
        return this.funciones;
    }

    /**
     * Gets the value of the paquetes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paquetes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaquetes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPaquetes() {
        if (paquetes == null) {
            paquetes = new ArrayList<String>();
        }
        return this.paquetes;
    }

    /**
     * Gets the value of the espectaculosFavoritos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the espectaculosFavoritos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEspectaculosFavoritos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEspectaculosFavoritos() {
        if (espectaculosFavoritos == null) {
            espectaculosFavoritos = new ArrayList<String>();
        }
        return this.espectaculosFavoritos;
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
     * Gets the value of the premiosGanados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the premiosGanados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPremiosGanados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PremioDto }
     * 
     * 
     */
    public List<PremioDto> getPremiosGanados() {
        if (premiosGanados == null) {
            premiosGanados = new ArrayList<PremioDto>();
        }
        return this.premiosGanados;
    }

}
