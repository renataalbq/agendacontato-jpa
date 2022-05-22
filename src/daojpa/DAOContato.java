/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Contato;

public class DAOContato extends DAO<Contato>{
	public Contato read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Contato> q = manager.createQuery("select c from Contato c where c.nome=:nome",Contato.class);
			q.setParameter("nome", nome);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	public List<Contato> readAll(){
		TypedQuery<Contato> q = manager.createQuery("select p from Contato p order by p.id", Contato.class);
		return  q.getResultList();
	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	public  List<Contato> readByCaracteres(String caracteres) {
		TypedQuery<Contato> q = manager.createQuery
				("select c from Contato c where c.nome like :x order by c.nome",Contato.class);
		q.setParameter("x", "%"+caracteres+"%");
		return q.getResultList();
	}


	public  List<Contato> consultaA(String bairro) {
			TypedQuery<Contato> q = manager.createQuery
					("select c from Contato c join c.endereco e where e.bairro = :bairro", Contato.class);
			q.setParameter("bairro", bairro);
			return q.getResultList();
	}

}

