/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistência de Objetos
 * Prof. Fausto Ayres
 * Aluna: Renata Albuquerque Cardoso (20182370024)
 */
package fachada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import daojpa.DAO;
import daojpa.DAOContato;
import daojpa.DAOEndereco;
import daojpa.DAOTelefone;
import modelo.Contato;
import modelo.Endereco;
import modelo.Telefone;

public class Fachada {
	private Fachada() {}		//classe nao instanciavel

	//declaracao e instanciacao dos DAOs
	private static DAOContato daocontato = new DAOContato();  
	private static DAOEndereco daoendereco = new DAOEndereco();
	private static DAOTelefone daotelefone = new DAOTelefone();  

	public static void inicializar() throws Exception 	{
		DAO.open();
	}

	public static void	finalizar() throws Exception {
		DAO.close();
	}
	
	public static Contato criarContato(String nome,String nascimento, String logradouro, String bairro)	throws  Exception {
		DAO.begin();
		nome = nome.trim();
		nascimento = nascimento.trim();
		logradouro = logradouro.trim();

		Contato contato = daocontato.read(nome);
							// ler contato
		if (contato!=null) {
			DAO.rollback();
			throw new Exception("criar contato - nome existente:" + nome);
		}

		LocalDate data;
		try {
			DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data  = LocalDate.parse(nascimento, parser); 
		}
		catch(DateTimeParseException e) {
			DAO.rollback();
			throw new Exception ("data de nascimento com formato invalido");
		}

		Endereco endereco = daoendereco.readByLogradouro(logradouro);
				//ler endereco usando logradouro
		if(endereco == null)
			endereco = new Endereco(logradouro,bairro);
		
		daoendereco.create(endereco);

		contato = new Contato(nome,data, endereco);
		daocontato.create(contato); //criar contato no banco
		DAO.commit();		
		return contato;
	}

	public static Telefone adicionarTelefoneContato(String numero, String nome)	throws  Exception{
		DAO.begin();
		nome = nome.trim();
		numero = numero.trim();

		Contato contato = daocontato.read(nome);
						//ler contato
		if (contato==null) {
			DAO.rollback();
			throw new Exception("adicionar telefone - contato inexistente:" + nome);
		}
		Telefone telefone = contato.localizarTelefone(numero);
		if(telefone != null) {
			DAO.rollback();
			throw new Exception("adicionar telefone - numero duplicado para o contato:" + numero +"/"+nome);
		}
		telefone = daotelefone.read(numero);
				//ler telefone
		if (telefone==null) {
			telefone = new Telefone(numero);
		}

		contato.adicionar(telefone);
		telefone.adicionar(contato);
		telefone = daotelefone.update(telefone); //atualizar contato no banco
		DAO.commit();
		return telefone;
	}


	public static void removerTelefoneContato(String numero, String nome) throws  Exception{
		DAO.begin();
		nome = nome.trim();
		numero = numero.trim();

		Contato contato = daocontato.read(nome);
						//ler contato
		if (contato==null) {
			DAO.rollback();
			throw new Exception("remover telefone - contato inexistente:" + nome);
		}
		Telefone telefone = contato.localizarTelefone(numero);
		if(telefone == null) {
			DAO.rollback();
			throw new Exception("remover telefone - contato nao possui o numero :" + numero +"/"+nome);
		}
		contato.remover(telefone);
		telefone.remover(contato);
		
		contato = daocontato.update(contato); //atualizar contato no banco
		telefone = daotelefone.update(telefone); //atualizar telefone no banco
		
		if(telefone.getContatos().size()==0)	//telefone nao possui contatos
			daotelefone.delete(telefone);	
			//apagar telefone no banco
		DAO.commit();
		
	}

	public static void alterarTelefone(String numero, String novo) throws  Exception{
		DAO.begin();
		novo = novo.trim();
		numero = numero.trim();

		Telefone telefone1 = daotelefone.read(numero);
							//ler telefone
		if(telefone1 == null) {
			DAO.rollback();
			throw new Exception("alterar telefone - numero inexistente:" + numero);
		}
		Telefone telefone2 = daotelefone.read(novo);
								//ler telefone
		if(telefone2 != null) {
			DAO.rollback();
			throw new Exception("alterar telefone - numero existente:" + novo);
		}

		telefone1.setNumero(novo);
		telefone1 = daotelefone.update(telefone1); //atualizar telefone no banco
		DAO.commit();
	}

	public static List<Contato> listarContatos(String caracteres) {
		if(caracteres.isEmpty())
			return daocontato.readAll(); //ler todos os contatos
		else
			return daocontato.readByCaracteres(caracteres); //consultar no banco os contatos com os caracteres
	}
	
	public static List<Telefone> listarTelefones(String digitos) {
		if(digitos.isEmpty())
			return daotelefone.readAll(); //ler todos os telefones
		else
			return daotelefone.readByDigitos(digitos);//consultar no banco os telefones com os digitos
	}

	public static List<Endereco> listarEnderecos() {
			return daoendereco.readAll();//ler todos os enderecos
	}
	
	public static List<Contato> consultaA(String bairro) 	{
		//os contatos que moram num determinado bairro
		return daocontato.consultaA(bairro);
	}
	
	public static List<Telefone> consultaB() {
		return daotelefone.consultaB();
	}

	public static void apagarContato(String nome)	throws  Exception {
		DAO.begin();
		nome = nome.trim();

		Contato contato = daocontato.read(nome); //ler contato
		if (contato==null) {
			DAO.rollback();
			throw new Exception("apagar contato - nome inexistente:" + nome);
		}
		
		//remover cada telefone do contato na memoria
		
		for (Telefone telefone : contato.getTelefones()) {			
			telefone.remover(contato);
			if(telefone.getContatos().isEmpty())   
				daotelefone.delete(telefone);	
			else
				daotelefone.update(telefone);
		}		
		
		daocontato.delete(contato);	
		DAO.commit();
		
	}
}


