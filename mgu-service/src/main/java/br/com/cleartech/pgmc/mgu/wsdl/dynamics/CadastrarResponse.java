
package br.com.cleartech.pgmc.mgu.wsdl.dynamics;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CadastrarResult" type="{http://schemas.datacontract.org/2004/07/Cleartech.XrmServices}DadosRetorno" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cadastrarResult"
})
@XmlRootElement(name = "CadastrarResponse")
public class CadastrarResponse {

    @XmlElementRef(name = "CadastrarResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<DadosRetorno> cadastrarResult;

    /**
     * Gets the value of the cadastrarResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DadosRetorno }{@code >}
     *     
     */
    public JAXBElement<DadosRetorno> getCadastrarResult() {
        return cadastrarResult;
    }

    /**
     * Sets the value of the cadastrarResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DadosRetorno }{@code >}
     *     
     */
    public void setCadastrarResult(JAXBElement<DadosRetorno> value) {
        this.cadastrarResult = value;
    }

}
