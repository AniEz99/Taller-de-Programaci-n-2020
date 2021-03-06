
package webservices;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "YaExisteRegistroException", targetNamespace = "http://webservices/")
public class YaExisteRegistroException_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private YaExisteRegistroException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public YaExisteRegistroException_Exception(String message, YaExisteRegistroException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public YaExisteRegistroException_Exception(String message, YaExisteRegistroException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: webservices.YaExisteRegistroException
     */
    public YaExisteRegistroException getFaultInfo() {
        return faultInfo;
    }

}
