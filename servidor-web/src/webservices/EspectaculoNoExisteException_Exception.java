
package webservices;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EspectaculoNoExisteException", targetNamespace = "http://webservices/")
public class EspectaculoNoExisteException_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EspectaculoNoExisteException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public EspectaculoNoExisteException_Exception(String message, EspectaculoNoExisteException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public EspectaculoNoExisteException_Exception(String message, EspectaculoNoExisteException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: webservices.EspectaculoNoExisteException
     */
    public EspectaculoNoExisteException getFaultInfo() {
        return faultInfo;
    }

}
