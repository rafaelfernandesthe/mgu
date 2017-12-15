
package br.com.cleartech.pgmc.mgu.wsdl.dynamics;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.cleartech.archetype.ejb.webservices.dynamics2 package. 
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

    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _DadosRetorno_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "DadosRetorno");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _DadosUsuarioPgmc_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "DadosUsuarioPgmc");
    private final static QName _CadastrarResponseCadastrarResult_QNAME = new QName("http://tempuri.org/", "CadastrarResult");
    private final static QName _DadosUsuarioPgmcLogin_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "Login");
    private final static QName _DadosUsuarioPgmcNomePrestadora_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "NomePrestadora");
    private final static QName _DadosUsuarioPgmcEmail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "Email");
    private final static QName _DadosUsuarioPgmcSpidPrestadora_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "SpidPrestadora");
    private final static QName _DadosUsuarioPgmcTelefoneCelular_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "TelefoneCelular");
    private final static QName _DadosUsuarioPgmcTelefoneComercial_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "TelefoneComercial");
    private final static QName _DadosUsuarioPgmcNome_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "Nome");
    private final static QName _DadosUsuarioPgmcCpf_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "Cpf");
    private final static QName _CadastrarDadosUsuarioPgmc_QNAME = new QName("http://tempuri.org/", "dadosUsuarioPgmc");
    private final static QName _DadosRetornoId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "Id");
    private final static QName _DadosRetornoMensagem_QNAME = new QName("http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", "Mensagem");
    private final static QName _DesativarEmail_QNAME = new QName("http://tempuri.org/", "email");
    private final static QName _DesativarLogin_QNAME = new QName("http://tempuri.org/", "login");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.cleartech.archetype.ejb.webservices.dynamics2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DadosUsuarioPgmc }
     * 
     */
    public DadosUsuarioPgmc createDadosUsuarioPgmc() {
        return new DadosUsuarioPgmc();
    }

    /**
     * Create an instance of {@link DadosRetorno }
     * 
     */
    public DadosRetorno createDadosRetorno() {
        return new DadosRetorno();
    }

    /**
     * Create an instance of {@link DesativarResponse }
     * 
     */
    public DesativarResponse createDesativarResponse() {
        return new DesativarResponse();
    }

    /**
     * Create an instance of {@link AlterarResponse }
     * 
     */
    public AlterarResponse createAlterarResponse() {
        return new AlterarResponse();
    }

    /**
     * Create an instance of {@link Cadastrar }
     * 
     */
    public Cadastrar createCadastrar() {
        return new Cadastrar();
    }

    /**
     * Create an instance of {@link Desativar }
     * 
     */
    public Desativar createDesativar() {
        return new Desativar();
    }

    /**
     * Create an instance of {@link CadastrarResponse }
     * 
     */
    public CadastrarResponse createCadastrarResponse() {
        return new CadastrarResponse();
    }

    /**
     * Create an instance of {@link Alterar }
     * 
     */
    public Alterar createAlterar() {
        return new Alterar();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DadosRetorno }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "DadosRetorno")
    public JAXBElement<DadosRetorno> createDadosRetorno(DadosRetorno value) {
        return new JAXBElement<DadosRetorno>(_DadosRetorno_QNAME, DadosRetorno.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, (value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DadosUsuarioPgmc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "DadosUsuarioPgmc")
    public JAXBElement<DadosUsuarioPgmc> createDadosUsuarioPgmc(DadosUsuarioPgmc value) {
        return new JAXBElement<DadosUsuarioPgmc>(_DadosUsuarioPgmc_QNAME, DadosUsuarioPgmc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DadosRetorno }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CadastrarResult", scope = CadastrarResponse.class)
    public JAXBElement<DadosRetorno> createCadastrarResponseCadastrarResult(DadosRetorno value) {
        return new JAXBElement<DadosRetorno>(_CadastrarResponseCadastrarResult_QNAME, DadosRetorno.class, CadastrarResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "Login", scope = DadosUsuarioPgmc.class)
    public JAXBElement<String> createDadosUsuarioPgmcLogin(String value) {
        return new JAXBElement<String>(_DadosUsuarioPgmcLogin_QNAME, String.class, DadosUsuarioPgmc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "NomePrestadora", scope = DadosUsuarioPgmc.class)
    public JAXBElement<String> createDadosUsuarioPgmcNomePrestadora(String value) {
        return new JAXBElement<String>(_DadosUsuarioPgmcNomePrestadora_QNAME, String.class, DadosUsuarioPgmc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "Email", scope = DadosUsuarioPgmc.class)
    public JAXBElement<String> createDadosUsuarioPgmcEmail(String value) {
        return new JAXBElement<String>(_DadosUsuarioPgmcEmail_QNAME, String.class, DadosUsuarioPgmc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "SpidPrestadora", scope = DadosUsuarioPgmc.class)
    public JAXBElement<String> createDadosUsuarioPgmcSpidPrestadora(String value) {
        return new JAXBElement<String>(_DadosUsuarioPgmcSpidPrestadora_QNAME, String.class, DadosUsuarioPgmc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "TelefoneCelular", scope = DadosUsuarioPgmc.class)
    public JAXBElement<String> createDadosUsuarioPgmcTelefoneCelular(String value) {
        return new JAXBElement<String>(_DadosUsuarioPgmcTelefoneCelular_QNAME, String.class, DadosUsuarioPgmc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "TelefoneComercial", scope = DadosUsuarioPgmc.class)
    public JAXBElement<String> createDadosUsuarioPgmcTelefoneComercial(String value) {
        return new JAXBElement<String>(_DadosUsuarioPgmcTelefoneComercial_QNAME, String.class, DadosUsuarioPgmc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "Nome", scope = DadosUsuarioPgmc.class)
    public JAXBElement<String> createDadosUsuarioPgmcNome(String value) {
        return new JAXBElement<String>(_DadosUsuarioPgmcNome_QNAME, String.class, DadosUsuarioPgmc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "Cpf", scope = DadosUsuarioPgmc.class)
    public JAXBElement<String> createDadosUsuarioPgmcCpf(String value) {
        return new JAXBElement<String>(_DadosUsuarioPgmcCpf_QNAME, String.class, DadosUsuarioPgmc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DadosUsuarioPgmc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dadosUsuarioPgmc", scope = Cadastrar.class)
    public JAXBElement<DadosUsuarioPgmc> createCadastrarDadosUsuarioPgmc(DadosUsuarioPgmc value) {
        return new JAXBElement<DadosUsuarioPgmc>(_CadastrarDadosUsuarioPgmc_QNAME, DadosUsuarioPgmc.class, Cadastrar.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "Id", scope = DadosRetorno.class)
    public JAXBElement<String> createDadosRetornoId(String value) {
        return new JAXBElement<String>(_DadosRetornoId_QNAME, String.class, DadosRetorno.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Cleartech.XrmServices", name = "Mensagem", scope = DadosRetorno.class)
    public JAXBElement<String> createDadosRetornoMensagem(String value) {
        return new JAXBElement<String>(_DadosRetornoMensagem_QNAME, String.class, DadosRetorno.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "email", scope = Desativar.class)
    public JAXBElement<String> createDesativarEmail(String value) {
        return new JAXBElement<String>(_DesativarEmail_QNAME, String.class, Desativar.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "login", scope = Desativar.class)
    public JAXBElement<String> createDesativarLogin(String value) {
        return new JAXBElement<String>(_DesativarLogin_QNAME, String.class, Desativar.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DadosUsuarioPgmc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dadosUsuarioPgmc", scope = Alterar.class)
    public JAXBElement<DadosUsuarioPgmc> createAlterarDadosUsuarioPgmc(DadosUsuarioPgmc value) {
        return new JAXBElement<DadosUsuarioPgmc>(_CadastrarDadosUsuarioPgmc_QNAME, DadosUsuarioPgmc.class, Alterar.class, value);
    }

}
