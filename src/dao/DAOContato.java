package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Contato;

public class DAOContato  extends DAO<Contato>{

	public Contato read (Object chave) {
		String nome = (String) chave;	
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("nome").constrain(nome);
		List<Contato> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	
	/********* CONSULTAS ***********/
	
	public List<Contato> readByCaracteres(String caracteres) {
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("nome").constrain(caracteres).like();
		List<Contato> result = q.execute(); 
		return result;
	}
	

	public List<Contato> consultaA(String bairro) {
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("endereco").descend("bairro").constrain(bairro).like();
		List<Contato> result = q.execute(); 
		return result;
	}

}
