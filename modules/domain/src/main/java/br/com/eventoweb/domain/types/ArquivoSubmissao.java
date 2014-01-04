package br.com.eventoweb.domain.types;

public class ArquivoSubmissao {
	
	private String nomeArquivo;
	
	private byte[] byteArquivo;
	
	private String extensaoArquivo;

	
	public ArquivoSubmissao(String nomeArquivo, byte[] byteArquivo) {
		super();
		this.nomeArquivo = nomeArquivo;
		this.byteArquivo = byteArquivo;
		
		int pos = this.nomeArquivo.lastIndexOf(".");
		this.extensaoArquivo = this.nomeArquivo.substring(pos, this.nomeArquivo.length());
		
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public byte[] getByteArquivo() {
		return byteArquivo;
	}

	public void setByteArquivo(byte[] byteArquivo) {
		this.byteArquivo = byteArquivo;
	}

	public String getExtensaoArquivo() {
		return extensaoArquivo;
	}

	public void setExtensaoArquivo(String extensaoArquivo) {
		this.extensaoArquivo = extensaoArquivo;
	}
	
	
}
