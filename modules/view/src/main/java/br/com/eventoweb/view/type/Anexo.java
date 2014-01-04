package br.com.eventoweb.view.type;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class Anexo implements Serializable {

    enum SituacaoAnexo {
        ERRO,
        ENVIADO,
        AGUARDANDO;
    }

    private static final long serialVersionUID = 1L;

    private UploadedFile file;
    private SituacaoAnexo situacao;
    private String mensagem;

    public Anexo(UploadedFile file) {
        this.file = file;
        situacao = SituacaoAnexo.AGUARDANDO;
    }

    public UploadedFile getFile() {
        return file;
    }

    public SituacaoAnexo getSituacao() {
        return situacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setEnviado() {
        situacao = SituacaoAnexo.ENVIADO;
    }

    public void setErro(String mensagem) {
        situacao = SituacaoAnexo.ERRO;
        this.mensagem = mensagem;
    }

    public boolean isSituacaoErro() {
        return situacao == SituacaoAnexo.ERRO;
    }

    public boolean isSituacaoAguardando() {
        return situacao == SituacaoAnexo.AGUARDANDO;
    }
	
}
