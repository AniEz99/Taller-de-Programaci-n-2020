
package webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegistroNoExisteException_QNAME = new QName("http://webservices/", "RegistroNoExisteException");
    private final static QName _CategoriaNoExisteException_QNAME = new QName("http://webservices/", "CategoriaNoExisteException");
    private final static QName _EspectaculoNoExisteException_QNAME = new QName("http://webservices/", "EspectaculoNoExisteException");
    private final static QName _FaltanRegistroException_QNAME = new QName("http://webservices/", "FaltanRegistroException");
    private final static QName _FuncionNoExisteException_QNAME = new QName("http://webservices/", "FuncionNoExisteException");
    private final static QName _MinEspMayorAMaxEspException_QNAME = new QName("http://webservices/", "MinEspMayorAMaxEspException");
    private final static QName _YaExisteCategoriaException_QNAME = new QName("http://webservices/", "YaExisteCategoriaException");
    private final static QName _PaqueteYaExisteException_QNAME = new QName("http://webservices/", "PaqueteYaExisteException");
    private final static QName _EspectaculoYaExisteException_QNAME = new QName("http://webservices/", "EspectaculoYaExisteException");
    private final static QName _FuncionYaExisteException_QNAME = new QName("http://webservices/", "FuncionYaExisteException");
    private final static QName _Exception_QNAME = new QName("http://webservices/", "Exception");
    private final static QName _ValoracionNoValidaException_QNAME = new QName("http://webservices/", "ValoracionNoValidaException");
    private final static QName _IOException_QNAME = new QName("http://webservices/", "IOException");
    private final static QName _NoExistePaqueteException_QNAME = new QName("http://webservices/", "NoExistePaqueteException");
    private final static QName _PlataformaYaExisteException_QNAME = new QName("http://webservices/", "PlataformaYaExisteException");
    private final static QName _EspectaculoNoAceptadoException_QNAME = new QName("http://webservices/", "EspectaculoNoAceptadoException");
    private final static QName _PlataformaNoExisteException_QNAME = new QName("http://webservices/", "PlataformaNoExisteException");
    private final static QName _CamposIncompletosException_QNAME = new QName("http://webservices/", "CamposIncompletosException");
    private final static QName _AutenticacionException_QNAME = new QName("http://webservices/", "AutenticacionException");
    private final static QName _YaExisteRegistroException_QNAME = new QName("http://webservices/", "YaExisteRegistroException");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://webservices/", "UsuarioNoExisteException");
    private final static QName _EspectadorNoExisteException_QNAME = new QName("http://webservices/", "EspectadorNoExisteException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EspectadorNoExisteException }
     * 
     */
    public EspectadorNoExisteException createEspectadorNoExisteException() {
        return new EspectadorNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
    }

    /**
     * Create an instance of {@link AutenticacionException }
     * 
     */
    public AutenticacionException createAutenticacionException() {
        return new AutenticacionException();
    }

    /**
     * Create an instance of {@link YaExisteRegistroException }
     * 
     */
    public YaExisteRegistroException createYaExisteRegistroException() {
        return new YaExisteRegistroException();
    }

    /**
     * Create an instance of {@link CamposIncompletosException }
     * 
     */
    public CamposIncompletosException createCamposIncompletosException() {
        return new CamposIncompletosException();
    }

    /**
     * Create an instance of {@link PlataformaNoExisteException }
     * 
     */
    public PlataformaNoExisteException createPlataformaNoExisteException() {
        return new PlataformaNoExisteException();
    }

    /**
     * Create an instance of {@link EspectaculoNoAceptadoException }
     * 
     */
    public EspectaculoNoAceptadoException createEspectaculoNoAceptadoException() {
        return new EspectaculoNoAceptadoException();
    }

    /**
     * Create an instance of {@link PlataformaYaExisteException }
     * 
     */
    public PlataformaYaExisteException createPlataformaYaExisteException() {
        return new PlataformaYaExisteException();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link NoExistePaqueteException }
     * 
     */
    public NoExistePaqueteException createNoExistePaqueteException() {
        return new NoExistePaqueteException();
    }

    /**
     * Create an instance of {@link ValoracionNoValidaException }
     * 
     */
    public ValoracionNoValidaException createValoracionNoValidaException() {
        return new ValoracionNoValidaException();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link EspectaculoYaExisteException }
     * 
     */
    public EspectaculoYaExisteException createEspectaculoYaExisteException() {
        return new EspectaculoYaExisteException();
    }

    /**
     * Create an instance of {@link FuncionYaExisteException }
     * 
     */
    public FuncionYaExisteException createFuncionYaExisteException() {
        return new FuncionYaExisteException();
    }

    /**
     * Create an instance of {@link PaqueteYaExisteException }
     * 
     */
    public PaqueteYaExisteException createPaqueteYaExisteException() {
        return new PaqueteYaExisteException();
    }

    /**
     * Create an instance of {@link YaExisteCategoriaException }
     * 
     */
    public YaExisteCategoriaException createYaExisteCategoriaException() {
        return new YaExisteCategoriaException();
    }

    /**
     * Create an instance of {@link MinEspMayorAMaxEspException }
     * 
     */
    public MinEspMayorAMaxEspException createMinEspMayorAMaxEspException() {
        return new MinEspMayorAMaxEspException();
    }

    /**
     * Create an instance of {@link FaltanRegistroException }
     * 
     */
    public FaltanRegistroException createFaltanRegistroException() {
        return new FaltanRegistroException();
    }

    /**
     * Create an instance of {@link FuncionNoExisteException }
     * 
     */
    public FuncionNoExisteException createFuncionNoExisteException() {
        return new FuncionNoExisteException();
    }

    /**
     * Create an instance of {@link EspectaculoNoExisteException }
     * 
     */
    public EspectaculoNoExisteException createEspectaculoNoExisteException() {
        return new EspectaculoNoExisteException();
    }

    /**
     * Create an instance of {@link CategoriaNoExisteException }
     * 
     */
    public CategoriaNoExisteException createCategoriaNoExisteException() {
        return new CategoriaNoExisteException();
    }

    /**
     * Create an instance of {@link RegistroNoExisteException }
     * 
     */
    public RegistroNoExisteException createRegistroNoExisteException() {
        return new RegistroNoExisteException();
    }

    /**
     * Create an instance of {@link FuncionMinDto }
     * 
     */
    public FuncionMinDto createFuncionMinDto() {
        return new FuncionMinDto();
    }

    /**
     * Create an instance of {@link ValoracionDto }
     * 
     */
    public ValoracionDto createValoracionDto() {
        return new ValoracionDto();
    }

    /**
     * Create an instance of {@link PlataformaDtoList }
     * 
     */
    public PlataformaDtoList createPlataformaDtoList() {
        return new PlataformaDtoList();
    }

    /**
     * Create an instance of {@link ArtistaDto }
     * 
     */
    public ArtistaDto createArtistaDto() {
        return new ArtistaDto();
    }

    /**
     * Create an instance of {@link FuncionDtoList }
     * 
     */
    public FuncionDtoList createFuncionDtoList() {
        return new FuncionDtoList();
    }

    /**
     * Create an instance of {@link ArtistaDtoList }
     * 
     */
    public ArtistaDtoList createArtistaDtoList() {
        return new ArtistaDtoList();
    }

    /**
     * Create an instance of {@link EspectadorDto }
     * 
     */
    public EspectadorDto createEspectadorDto() {
        return new EspectadorDto();
    }

    /**
     * Create an instance of {@link ImagenDto }
     * 
     */
    public ImagenDto createImagenDto() {
        return new ImagenDto();
    }

    /**
     * Create an instance of {@link EspectaculoDto }
     * 
     */
    public EspectaculoDto createEspectaculoDto() {
        return new EspectaculoDto();
    }

    /**
     * Create an instance of {@link RegistroDto }
     * 
     */
    public RegistroDto createRegistroDto() {
        return new RegistroDto();
    }

    /**
     * Create an instance of {@link PaqueteDto }
     * 
     */
    public PaqueteDto createPaqueteDto() {
        return new PaqueteDto();
    }

    /**
     * Create an instance of {@link CategoriaDto }
     * 
     */
    public CategoriaDto createCategoriaDto() {
        return new CategoriaDto();
    }

    /**
     * Create an instance of {@link PremioDto }
     * 
     */
    public PremioDto createPremioDto() {
        return new PremioDto();
    }

    /**
     * Create an instance of {@link UsuarioDto }
     * 
     */
    public UsuarioDto createUsuarioDto() {
        return new UsuarioDto();
    }

    /**
     * Create an instance of {@link RegistroDtoList }
     * 
     */
    public RegistroDtoList createRegistroDtoList() {
        return new RegistroDtoList();
    }

    /**
     * Create an instance of {@link FuncionDto }
     * 
     */
    public FuncionDto createFuncionDto() {
        return new FuncionDto();
    }

    /**
     * Create an instance of {@link EspectaculoMinDto }
     * 
     */
    public EspectaculoMinDto createEspectaculoMinDto() {
        return new EspectaculoMinDto();
    }

    /**
     * Create an instance of {@link EspectaculoDtoList }
     * 
     */
    public EspectaculoDtoList createEspectaculoDtoList() {
        return new EspectaculoDtoList();
    }

    /**
     * Create an instance of {@link PaqueteDtoList }
     * 
     */
    public PaqueteDtoList createPaqueteDtoList() {
        return new PaqueteDtoList();
    }

    /**
     * Create an instance of {@link StringList }
     * 
     */
    public StringList createStringList() {
        return new StringList();
    }

    /**
     * Create an instance of {@link PlataformaDto }
     * 
     */
    public PlataformaDto createPlataformaDto() {
        return new PlataformaDto();
    }

    /**
     * Create an instance of {@link CategoriaDtoList }
     * 
     */
    public CategoriaDtoList createCategoriaDtoList() {
        return new CategoriaDtoList();
    }

    /**
     * Create an instance of {@link EspectadorDtoList }
     * 
     */
    public EspectadorDtoList createEspectadorDtoList() {
        return new EspectadorDtoList();
    }

    /**
     * Create an instance of {@link LogActividadDtoList }
     * 
     */
    public LogActividadDtoList createLogActividadDtoList() {
        return new LogActividadDtoList();
    }

    /**
     * Create an instance of {@link LogActividadDto }
     * 
     */
    public LogActividadDto createLogActividadDto() {
        return new LogActividadDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "RegistroNoExisteException")
    public JAXBElement<RegistroNoExisteException> createRegistroNoExisteException(RegistroNoExisteException value) {
        return new JAXBElement<RegistroNoExisteException>(_RegistroNoExisteException_QNAME, RegistroNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "CategoriaNoExisteException")
    public JAXBElement<CategoriaNoExisteException> createCategoriaNoExisteException(CategoriaNoExisteException value) {
        return new JAXBElement<CategoriaNoExisteException>(_CategoriaNoExisteException_QNAME, CategoriaNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectaculoNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EspectaculoNoExisteException")
    public JAXBElement<EspectaculoNoExisteException> createEspectaculoNoExisteException(EspectaculoNoExisteException value) {
        return new JAXBElement<EspectaculoNoExisteException>(_EspectaculoNoExisteException_QNAME, EspectaculoNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaltanRegistroException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "FaltanRegistroException")
    public JAXBElement<FaltanRegistroException> createFaltanRegistroException(FaltanRegistroException value) {
        return new JAXBElement<FaltanRegistroException>(_FaltanRegistroException_QNAME, FaltanRegistroException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuncionNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "FuncionNoExisteException")
    public JAXBElement<FuncionNoExisteException> createFuncionNoExisteException(FuncionNoExisteException value) {
        return new JAXBElement<FuncionNoExisteException>(_FuncionNoExisteException_QNAME, FuncionNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MinEspMayorAMaxEspException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "MinEspMayorAMaxEspException")
    public JAXBElement<MinEspMayorAMaxEspException> createMinEspMayorAMaxEspException(MinEspMayorAMaxEspException value) {
        return new JAXBElement<MinEspMayorAMaxEspException>(_MinEspMayorAMaxEspException_QNAME, MinEspMayorAMaxEspException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YaExisteCategoriaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "YaExisteCategoriaException")
    public JAXBElement<YaExisteCategoriaException> createYaExisteCategoriaException(YaExisteCategoriaException value) {
        return new JAXBElement<YaExisteCategoriaException>(_YaExisteCategoriaException_QNAME, YaExisteCategoriaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteYaExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "PaqueteYaExisteException")
    public JAXBElement<PaqueteYaExisteException> createPaqueteYaExisteException(PaqueteYaExisteException value) {
        return new JAXBElement<PaqueteYaExisteException>(_PaqueteYaExisteException_QNAME, PaqueteYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectaculoYaExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EspectaculoYaExisteException")
    public JAXBElement<EspectaculoYaExisteException> createEspectaculoYaExisteException(EspectaculoYaExisteException value) {
        return new JAXBElement<EspectaculoYaExisteException>(_EspectaculoYaExisteException_QNAME, EspectaculoYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuncionYaExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "FuncionYaExisteException")
    public JAXBElement<FuncionYaExisteException> createFuncionYaExisteException(FuncionYaExisteException value) {
        return new JAXBElement<FuncionYaExisteException>(_FuncionYaExisteException_QNAME, FuncionYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValoracionNoValidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "ValoracionNoValidaException")
    public JAXBElement<ValoracionNoValidaException> createValoracionNoValidaException(ValoracionNoValidaException value) {
        return new JAXBElement<ValoracionNoValidaException>(_ValoracionNoValidaException_QNAME, ValoracionNoValidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistePaqueteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "NoExistePaqueteException")
    public JAXBElement<NoExistePaqueteException> createNoExistePaqueteException(NoExistePaqueteException value) {
        return new JAXBElement<NoExistePaqueteException>(_NoExistePaqueteException_QNAME, NoExistePaqueteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlataformaYaExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "PlataformaYaExisteException")
    public JAXBElement<PlataformaYaExisteException> createPlataformaYaExisteException(PlataformaYaExisteException value) {
        return new JAXBElement<PlataformaYaExisteException>(_PlataformaYaExisteException_QNAME, PlataformaYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectaculoNoAceptadoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EspectaculoNoAceptadoException")
    public JAXBElement<EspectaculoNoAceptadoException> createEspectaculoNoAceptadoException(EspectaculoNoAceptadoException value) {
        return new JAXBElement<EspectaculoNoAceptadoException>(_EspectaculoNoAceptadoException_QNAME, EspectaculoNoAceptadoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlataformaNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "PlataformaNoExisteException")
    public JAXBElement<PlataformaNoExisteException> createPlataformaNoExisteException(PlataformaNoExisteException value) {
        return new JAXBElement<PlataformaNoExisteException>(_PlataformaNoExisteException_QNAME, PlataformaNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CamposIncompletosException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "CamposIncompletosException")
    public JAXBElement<CamposIncompletosException> createCamposIncompletosException(CamposIncompletosException value) {
        return new JAXBElement<CamposIncompletosException>(_CamposIncompletosException_QNAME, CamposIncompletosException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutenticacionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "AutenticacionException")
    public JAXBElement<AutenticacionException> createAutenticacionException(AutenticacionException value) {
        return new JAXBElement<AutenticacionException>(_AutenticacionException_QNAME, AutenticacionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YaExisteRegistroException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "YaExisteRegistroException")
    public JAXBElement<YaExisteRegistroException> createYaExisteRegistroException(YaExisteRegistroException value) {
        return new JAXBElement<YaExisteRegistroException>(_YaExisteRegistroException_QNAME, YaExisteRegistroException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "UsuarioNoExisteException")
    public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<UsuarioNoExisteException>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectadorNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "EspectadorNoExisteException")
    public JAXBElement<EspectadorNoExisteException> createEspectadorNoExisteException(EspectadorNoExisteException value) {
        return new JAXBElement<EspectadorNoExisteException>(_EspectadorNoExisteException_QNAME, EspectadorNoExisteException.class, null, value);
    }

}
