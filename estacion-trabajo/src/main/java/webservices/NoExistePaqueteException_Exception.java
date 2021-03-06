
package webservices;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "NoExistePaqueteException", targetNamespace = "http://webservices/")
public class NoExistePaqueteException_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private NoExistePaqueteException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public NoExistePaqueteException_Exception(String message, NoExistePaqueteException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public NoExistePaqueteException_Exception(String message, NoExistePaqueteException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: webservices.NoExistePaqueteException
     */
    public NoExistePaqueteException getFaultInfo() {
        return faultInfo;
    }

}
