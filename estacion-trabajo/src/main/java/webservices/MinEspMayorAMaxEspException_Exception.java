
package webservices;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "MinEspMayorAMaxEspException", targetNamespace = "http://webservices/")
public class MinEspMayorAMaxEspException_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private MinEspMayorAMaxEspException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public MinEspMayorAMaxEspException_Exception(String message, MinEspMayorAMaxEspException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public MinEspMayorAMaxEspException_Exception(String message, MinEspMayorAMaxEspException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: webservices.MinEspMayorAMaxEspException
     */
    public MinEspMayorAMaxEspException getFaultInfo() {
        return faultInfo;
    }

}
