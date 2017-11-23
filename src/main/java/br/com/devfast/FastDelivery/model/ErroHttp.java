package br.com.devfast.FastDelivery.model;
import org.springframework.http.HttpStatus;

public class ErroHttp {
	private HttpStatus statusError;
	private String descricao;

	public ErroHttp(HttpStatus statusError, String descricao) {		
		this.statusError = statusError;
		this.descricao = descricao;
	}

	public HttpStatus getStatusError() {
		return statusError;
	}

	public void setStatusError(HttpStatus statusCode) {
		this.statusError = statusCode;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getStatusCode(){
		return statusError.value();
	}

}