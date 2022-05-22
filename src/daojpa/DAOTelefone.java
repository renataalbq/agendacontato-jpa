/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Telefone;

import java.util.List;

public class DAOTelefone extends DAO<Telefone>{
	public Telefone read (Object chave){
		try{
			String numero = (String) chave;
			TypedQuery<Telefone> q = manager.createQuery("select t from Telefone t where t.numero=:numero",Telefone.class);
			q.setParameter("numero", numero);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	public List<Telefone> readAll(){
		TypedQuery<Telefone> q = manager.createQuery("select t from Telefone t order by t.id", Telefone.class);
		return  q.getResultList();
	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	public  List<Telefone> readByDigitos(String digitos) {
		TypedQuery<Telefone> q = manager.createQuery
				("select t from Telefone t where t.numero like :x  order by t.id",Telefone.class);
		q.setParameter("x", "%"+digitos+"%");
		return q.getResultList();
	}


	public  List<Telefone> consultaB() {
			TypedQuery<Telefone> q = manager.createQuery
					("select t from Telefone t where t.numero like :y", Telefone.class);
			q.setParameter("y", "3%"); 		
			return  q.getResultList();
	}
	
}

