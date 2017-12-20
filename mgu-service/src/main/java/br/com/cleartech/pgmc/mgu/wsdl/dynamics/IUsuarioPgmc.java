package br.com.cleartech.pgmc.mgu.wsdl.dynamics;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2017-10-05T17:20:45.501-03:00
 * Generated source version: 2.4.6
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "IUsuarioPgmc")
@XmlSeeAlso({ObjectFactory.class})
public interface IUsuarioPgmc {

    @Action(input = "http://tempuri.org/IUsuarioPgmc/Desativar", output = "http://tempuri.org/IUsuarioPgmc/DesativarResponse")
    @RequestWrapper(localName = "Desativar", targetNamespace = "http://tempuri.org/", className = "br.com.cleartech.archetype.ejb.webservices.dynamics2.Desativar")
    @WebMethod(operationName = "Desativar", action = "http://tempuri.org/IUsuarioPgmc/Desativar")
    @ResponseWrapper(localName = "DesativarResponse", targetNamespace = "http://tempuri.org/", className = "br.com.cleartech.archetype.ejb.webservices.dynamics2.DesativarResponse")
    public void desativar(
        @WebParam(name = "login", targetNamespace = "http://tempuri.org/")
        java.lang.String login,
        @WebParam(name = "email", targetNamespace = "http://tempuri.org/")
        java.lang.String email
    );

    @Action(input = "http://tempuri.org/IUsuarioPgmc/Alterar", output = "http://tempuri.org/IUsuarioPgmc/AlterarResponse")
    @RequestWrapper(localName = "Alterar", targetNamespace = "http://tempuri.org/", className = "br.com.cleartech.archetype.ejb.webservices.dynamics2.Alterar")
    @WebMethod(operationName = "Alterar", action = "http://tempuri.org/IUsuarioPgmc/Alterar")
    @ResponseWrapper(localName = "AlterarResponse", targetNamespace = "http://tempuri.org/", className = "br.com.cleartech.archetype.ejb.webservices.dynamics2.AlterarResponse")
    public void alterar(
        @WebParam(name = "dadosUsuarioPgmc", targetNamespace = "http://tempuri.org/")
        DadosUsuarioPgmc dadosUsuarioPgmc,
        @WebParam(name = "acessoRemovido", targetNamespace = "http://tempuri.org/")
        java.lang.Boolean acessoRemovido
    );

    @WebResult(name = "CadastrarResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IUsuarioPgmc/Cadastrar", output = "http://tempuri.org/IUsuarioPgmc/CadastrarResponse")
    @RequestWrapper(localName = "Cadastrar", targetNamespace = "http://tempuri.org/", className = "br.com.cleartech.archetype.ejb.webservices.dynamics2.Cadastrar")
    @WebMethod(operationName = "Cadastrar", action = "http://tempuri.org/IUsuarioPgmc/Cadastrar")
    @ResponseWrapper(localName = "CadastrarResponse", targetNamespace = "http://tempuri.org/", className = "br.com.cleartech.archetype.ejb.webservices.dynamics2.CadastrarResponse")
    public DadosRetorno cadastrar(
        @WebParam(name = "dadosUsuarioPgmc", targetNamespace = "http://tempuri.org/")
        DadosUsuarioPgmc dadosUsuarioPgmc
    );
}