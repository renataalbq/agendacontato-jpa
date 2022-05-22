
package aplicacao_console;
import fachada.Fachada;
import modelo.Contato;
import modelo.Endereco;
import modelo.Telefone;

public class AplicacaoConsole {

	public void teste1() {
		try {
			Fachada.inicializar();
			Contato contato;				
			Telefone telefone;		

			contato = Fachada.criarContato("joao", "11/01/1990","av nego 30","tambau");
			telefone = Fachada.adicionarTelefoneContato("99900000", "joao");
			telefone = Fachada.adicionarTelefoneContato("99911111", "joao");
			telefone = Fachada.adicionarTelefoneContato("99922222", "joao");
			System.out.println("contato criado:"+contato);
			contato = Fachada.criarContato("maria", "11/02/2000","av alm tamandare 100","tambau");
			telefone = Fachada.adicionarTelefoneContato("32470000", "maria");
			telefone = Fachada.adicionarTelefoneContato("32471111", "maria");
			telefone = Fachada.adicionarTelefoneContato("99933333", "maria");
			System.out.println("contato criado:"+contato);
			contato = Fachada.criarContato("jose", "11/03/2001","av guarabira 30","manaira");
			telefone = Fachada.adicionarTelefoneContato("99944444", "jose");
			telefone = Fachada.adicionarTelefoneContato("32472222", "jose");
			System.out.println("contato criado:"+contato);
			contato = Fachada.criarContato("ana", "11/04/2002","av guarabira 30","manaira");
			telefone = Fachada.adicionarTelefoneContato("99944444", "ana");
			telefone = Fachada.adicionarTelefoneContato("32472222", "ana");
			System.out.println("contato criado:"+contato);
			contato = Fachada.criarContato("paulo", "11/05/2003","av argemiro de figueiredo 100","bessa");
			telefone = Fachada.adicionarTelefoneContato("32473333", "paulo");
			System.out.println("contato criado:"+contato);


			System.out.println("\n---------listagem de contatos-----");
			for(Contato c : Fachada.listarContatos("")) 
				System.out.println(c);
			System.out.println("\n---------listagem de telefones");
			for(Telefone t : Fachada.listarTelefones("")) 
				System.out.println(t);
			for(Endereco e : Fachada.listarEnderecos()) 
				System.out.println(e.getLogradouro()+ " - " +e.getBairro());
			
			System.out.println("\n---------contatos jo");
			for(Contato c : Fachada.listarContatos("jo"))
				System.out.println(c);
			System.out.println("\n---------telefones 11");
			for(Telefone t : Fachada.listarTelefones("11"))
				System.out.println(t);
			System.out.println("\n---------consulta A");
			for(Contato c : Fachada.consultaA("tambau"))
				System.out.println(c);
			System.out.println("\n---------consulta B");
			for(Telefone t : Fachada.consultaB())
					System.out.println(t);

			Fachada.alterarTelefone("99900000", "99900001");
			Fachada.removerTelefoneContato("99944444", "jose");
			Fachada.removerTelefoneContato("99944444", "ana");
			Fachada.apagarContato("ana");
			Fachada.apagarContato("paulo");

			System.out.println("\n---------listagem de contatos-----");
			for(Contato c : Fachada.listarContatos("")) 
				System.out.println(c);
			System.out.println("\n---------listagem de telefones");
			for(Telefone t : Fachada.listarTelefones("")) 
				System.out.println(t);
			System.out.println("\n---------listagem de enderecos");
			for(Endereco e : Fachada.listarEnderecos()) 
				System.out.println(e.getId() +"-"+ e.getLogradouro()+ " - " +e.getBairro());

			Fachada.finalizar();

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
	}

	public  void teste2() {
		try {
			Fachada.inicializar();
		}catch (Exception e) {
			System.out.println(e.getMessage());}

		Contato contato;
		Telefone telefone;
		try {
			contato = Fachada.criarContato("teste1", "11/11/1990","av nego 30","tambau");
			contato = Fachada.criarContato("teste1", "11/11/1990","av nego 30","tambau");
			System.out.println("*************1--->Nao lancou excecao para criar contato "); 
		}catch (Exception e) {
			System.out.println("1ok--->"+e.getMessage());}

		try {
			telefone = Fachada.adicionarTelefoneContato("22222222", "teste1");
			telefone = Fachada.adicionarTelefoneContato("22222222", "teste1");
			System.out.println("*************2--->Nao lancou excecao para add telefone"); 
		}catch (Exception e) {
			System.out.println("2ok--->"+e.getMessage());}

		try {
			Fachada.removerTelefoneContato("22222222", "teste1");
			Fachada.alterarTelefone("22222222", "33333333");
			System.out.println("*************3--->Nao lancou excecao para alterar telefone "); 
		}catch (Exception e) {
			System.out.println("3ok--->"+e.getMessage());}


		try {
			Fachada.finalizar();
		}catch (Exception e) {
			System.out.println(e.getMessage());}
	}


	public static void main (String[] args) {
		AplicacaoConsole aplicacaoConsole = new AplicacaoConsole();
		aplicacaoConsole.teste1();
//		aplicacaoConsole.teste2();
	}
}