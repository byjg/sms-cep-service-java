package com.byjg.services.sms;

import com.byjg.services.ByJGBaseWebService;
import com.byjg.services.ByJGWebServiceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Classe para abstrair as chamadas ao WebService ByJG para SMS
 *
 * @author jg
 */
public class SMSService extends ByJGBaseWebService {

    private static final String SERVICE = "sms";

    public SMSService(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * Retorna a versao do WebService
     *
     * @return Versão Retorna a versão do serviço
     * @throws ByJGWebServiceException Dispara se não conseguir acessar o serviço
     */
    public String obterVersao() throws IOException, ByJGWebServiceException {
        return this.executeWebService(SMSService.SERVICE, "obterVersao", null);
    }

    /**
     * Enviar um SMS
     *
     * @param ddd      DDD do telefone
     * @param celular  Número do telefone
     * @param mensagem Mensagem a ser enviada
     * @return Status do Envio
     * @throws ByJGWebServiceException Dispara se não conseguir acessar o serviço
     */
    public String enviarSMS(String ddd, String celular, String mensagem) throws IOException, ByJGWebServiceException {
        return this.enviarSMS(ddd, celular, mensagem, null);
    }

    /**
     * Enviar SMS com o SenderID (requer cadastro)
     *
     * @param ddd      DDD do telefone
     * @param celular  Número do telefone
     * @param mensagem Mensagem a ser enviada
     * @param senderid SenderID
     * @return Status do Envio
     * @throws ByJGWebServiceException Dispara se não conseguir acessar o serviço
     */
    public String enviarSMS(String ddd, String celular, String mensagem, String senderid) throws IOException, ByJGWebServiceException {
        HashMap<String, String> params = this.getHashMap();
        params.put("ddd", ddd);
        params.put("celular", celular);
        params.put("mensagem", mensagem);
        if (senderid != null) {
            params.put("senderid", senderid);
        }

        return this.executeWebService(SMSService.SERVICE, "enviarsms2", params);
    }

    /**
     * Enviar uma lista de SMS para serem enviados por vez (máximo 50).
     *
     * @param lista    Lista de telefones no formato DDPPPPNNNN
     * @param mensagem Mensagem a ser enviada
     * @return Status do Envio
     * @throws ByJGWebServiceException Dispara se não conseguir acessar o serviço
     */
    public String enviarListaSMS(List<String> lista, String mensagem) throws IOException, ByJGWebServiceException {
        return this.enviarListaSMS(lista, mensagem, null);
    }

    /**
     * Enviar uma lista de SMS para serem enviados por vez (máximo 50) com
     * SenderID (requer cadastro).
     *
     * @param lista    Lista de telefones no formato DDPPPPNNNN
     * @param mensagem Mensagem a ser enviada
     * @param senderid SenderID
     * @return Status do Envio
     * @throws ByJGWebServiceException Dispara se não conseguir acessar o serviço
     */
    public String enviarListaSMS(List<String> lista, String mensagem, String senderid) throws IOException, ByJGWebServiceException {
        HashMap<String, String> params = this.getHashMap();
        StringBuilder strLista = new StringBuilder();
        for (Object telefone : lista) {
            strLista.append(strLista.length() > 0 ? "|" : "").append(telefone.toString());
        }
        params.put("lista", strLista.toString());
        params.put("mensagem", mensagem);
        if (senderid != null) {
            params.put("senderid", senderid);
        }

        return this.executeWebService(SMSService.SERVICE, "enviarlistasms", params);
    }

    /**
     * Consulta Créditos
     *
     * @return Quantidade de Créditos
     * @throws Exception Dispara se não conseguir acessar o serviço
     */
    public String creditos() throws IOException, ByJGWebServiceException {
        HashMap<String, String> params = this.getHashMap();

        return this.executeWebService(SMSService.SERVICE, "creditos", params);
    }
}
