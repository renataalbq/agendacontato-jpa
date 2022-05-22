package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Version;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;			
	private String bairro;
	@Lob
	private String logradouro;  //campo longo

	@Version
	private long versao;
	
	public Endereco() {}
	
	public Endereco(String logradouro, String bairro) {
		this.logradouro = logradouro;
		this.bairro = bairro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
