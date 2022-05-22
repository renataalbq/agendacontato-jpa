package dao;


import java.util.List;

import com.db4o.query.Query;

import modelo.Endereco;

public class DAOEndereco  extends DAO<Endereco>{
	
	public Endereco read (Object chave) {
		int id = (Integer) chave;

		Query q = manager.query();
		q.constrain(Endereco.class);
		q.descend("id").constrain(id);
		List<Endereco> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public Endereco readByLogradouroBairro (String logradouro, String bairro) {
		Query q = manager.query();
		q.constrain(Endereco.class);
		q.descend("logradouro").constrain(logradouro);
		q.descend("bairro").constrain(bairro);
		List<Endereco> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	

	public void create(Endereco obj){
		Integer id = getMaxId();
		obj.setId(id+1);
		manager.store(obj);
	}


}