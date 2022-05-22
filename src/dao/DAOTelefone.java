package dao;


import java.util.List;

import com.db4o.query.Query;
import modelo.Telefone;

public class DAOTelefone  extends DAO<Telefone>{
	
	public Telefone read (Object chave) {
		String num = (String) chave;
		
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain(num);
		List<Telefone> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	/***** CONSULTAS ****/
	
	public List<Telefone> readByDigitos(String digitos) {
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain(digitos).like();
		List<Telefone> result = q.execute(); 
		return result;
	}

	
	public List<Telefone> consultaB() {
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain("3").startsWith(true);
		List<Telefone> result = q.execute();
		return result;
	}


}