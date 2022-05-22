package modelo;
import java.time.LocalDate;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

@Entity
public class Contato {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private LocalDate nascimento;
	@ManyToOne(
			cascade= {CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY )
	private Endereco endereco;

	@ManyToMany(
			mappedBy = "contatos", 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY )
	private ArrayList<Telefone> telefones = new ArrayList<Telefone>();
	
	@Version
	private long versao;

	public Contato() {}
	
	public Contato(String nome, LocalDate nascimento, Endereco endereco) {
		this.nome = nome;
		this.nascimento = nascimento;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public LocalDate getNascimentoStr() {
		return nascimento;
	}

	public String getEnderecoStr() {
		return endereco.getLogradouro()+" " +endereco.getBairro();
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void adicionar(Telefone p){
		telefones.add(p);
	}
	public void remover(Telefone p){
		telefones.remove(p);
	}
	public Telefone localizarTelefone(String numero){
		for(Telefone p : telefones){
			if(p.getNumero().equals(numero))
				return p;
		}
		return null;
	}

	public int getIdade() {
		//calcular a idade usando a data de nascimento e data do computador
		Integer anoNasc = getNascimento().getYear();
		Integer anoAtual = LocalDate.now().getYear();
		Integer result = anoAtual - anoNasc; //;
		return result;
	}


	@Override
	public String toString() {
		String texto = "nome=" + nome + ", nascimento="+getNascimentoStr() + 
				", endereco= "+getEnderecoStr();
		texto += ", telefones:";
		if (telefones.isEmpty())
			texto += " vazia";
		else 	
			for(Telefone p: telefones) 
				texto += " " + p.getNumero() ;

		return texto ;
	}


}



