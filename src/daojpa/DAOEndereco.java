/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Endereco;

public class DAOEndereco extends DAO<Endereco>{
	public Endereco read (Object chave){
		try{
			int id = (Integer) chave;
			TypedQuery<Endereco> q = manager.createQuery("select e from Endereco e where e.id=:id",Endereco.class);
			q.setParameter("id", id);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Endereco readByLogradouro (String logradouro) {
		try{
			TypedQuery<Endereco> q = manager.createQuery("select e from Endereco e where e.logradouro=:log",Endereco.class);
			q.setParameter("log", logradouro);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	

	//  sobrescrever o metodo readAll da classe DAO 
//	public List<Contato> readAll(){
//		TypedQuery<Contato> q = manager.createQuery("select p from Contato p order by p.id", Contato.class);
//		return  q.getResultList();
//	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------

//	public  boolean  temTelefoneCelular(String nome) {
//		try{
//			Query q = manager.createQuery
//					("select count(t) from Pessoa p join p.telefones t where p.nome = :x and t.numero like :y");
//			q.setParameter("x", nome);
//			q.setParameter("y", "9%"); //inicia com 9
//			long cont = (Long) q.getSingleResult();
//			return cont>0;	
//		}catch(NoResultException e){
//			return false;
//		}
//	}
//
//	public  boolean  temTelefoneFixo(String nome) {
//		try{
//			Query q = manager.createQuery
//					("select count(t) from Pessoa p join p.telefones t where p.nome = :x and t.numero like :y");
//			q.setParameter("x", nome);
//			q.setParameter("y", "3%"); //inicia com 3
//
//			long cont = (Long) q.getSingleResult();
//			return cont>0;	
//		}catch(NoResultException e){
//			return false;
//		}
//	}


}

