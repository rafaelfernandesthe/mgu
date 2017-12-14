
package br.com.cleartech.pgmc.mgu.wsdl.dynamics;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DadosUsuarioPgmc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DadosUsuarioPgmc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Login" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NomePrestadora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpidPrestadora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefoneCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefoneComercial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DadosUsuarioPgmc", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", propOrder = {
    "cpf",
    "email",
    "login",
    "nome",
    "nomePrestadora",
    "spidPrestadora",
    "telefoneCelular",
    "telefoneComercial"
})
public class DadosUsuarioPgmc {

    @XmlElementRef(name = "Cpf", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cpf;
    @XmlElementRef(name = "Email", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", type = JAXBElement.class, required = false)
    protected JAXBElement<String> email;
    @XmlElementRef(name = "Login", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", type = JAXBElement.class, required = false)
    protected JAXBElement<String> login;
    @XmlElementRef(name = "Nome", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nome;
    @XmlElementRef(name = "NomePrestadora", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nomePrestadora;
    @XmlElementRef(name = "SpidPrestadora", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", type = JAXBElement.class, required = false)
    protected JAXBElement<String> spidPrestadora;
    @XmlElementRef(name = "TelefoneCelular", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", type = JAXBElement.class, required = false)
    protected JAXBElement<String> telefoneCelular;
    @XmlElementRef(name = "TelefoneComercial", namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", type = JAXBElement.class, required = false)
    protected JAXBElement<String> telefoneComercial;

    /**
     * Gets the value of the cpf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCpf() {
        return cpf;
    }

    /**
     * Sets the value of the cpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCpf(JAXBElement<String> value) {
        this.cpf = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEmail(JAXBElement<String> value) {
        this.email = value;
    }

    /**
     * Gets the value of the login property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLogin(JAXBElement<String> value) {
        this.login = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNome(JAXBElement<String> value) {
        this.nome = value;
    }

    /**
     * Gets the value of the nomePrestadora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomePrestadora() {
        return nomePrestadora;
    }

    /**
     * Sets the value of the nomePrestadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomePrestadora(JAXBElement<String> value) {
        this.nomePrestadora = value;
    }

    /**
     * Gets the value of the spidPrestadora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSpidPrestadora() {
        return spidPrestadora;
    }

    /**
     * Sets the value of the spidPrestadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSpidPrestadora(JAXBElement<String> value) {
        this.spidPrestadora = value;
    }

    /**
     * Gets the value of the telefoneCelular property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelefoneCelular() {
        return telefoneCelular;
    }

    /**
     * Sets the value of the telefoneCelular property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelefoneCelular(JAXBElement<String> value) {
        this.telefoneCelular = value;
    }

    /**
     * Gets the value of the telefoneComercial property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelefoneComercial() {
        return telefoneComercial;
    }

    /**
     * Sets the value of the telefoneComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelefoneComercial(JAXBElement<String> value) {
        this.telefoneComercial = value;
    }

}
