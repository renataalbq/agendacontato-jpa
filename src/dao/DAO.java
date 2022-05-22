/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

package dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import modelo.Contato;
import modelo.Telefone;
import modelo.Endereco;




public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

	public static void open(){	
		if(manager==null){		
			abrirBancoLocal();
		}
	}
	
	public static void abrirBancoLocal(){		
		//new File("banco.db4o").delete();  //apagar o banco local para cirar novo banco
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // 0,1,2,3...

		config.common().objectClass(Contato.class).cascadeOnUpdate(true);
		config.common().objectClass(Contato.class).cascadeOnDelete(false);
		config.common().objectClass(Contato.class).cascadeOnActivate(true);
		config.common().objectClass(Telefone.class).cascadeOnUpdate(true);
		config.common().objectClass(Telefone.class).cascadeOnDelete(false);
		config.common().objectClass(Telefone.class).cascadeOnActivate(true);
		config.common().objectClass(Endereco.class).cascadeOnUpdate(true);
		config.common().objectClass(Endereco.class).cascadeOnDelete(false);
		config.common().objectClass(Endereco.class).cascadeOnActivate(true);

		//		profundidade da ativação e atualização do grafo
		config.common().objectClass(Contato.class).updateDepth(5);
		config.common().objectClass(Contato.class).minimumActivationDepth(5);

		// 		indices
		config.common().objectClass(Contato.class).objectField("nome").indexed(true);
		config.common().objectClass(Telefone.class).objectField("numero").indexed(true);

		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
	}

	public static void close(){
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}

	//----------CRUD-----------------------
	public void create(T obj){
		manager.store( obj );
	}
 
	public abstract T read(Object chave);

	public T update(T obj){
		manager.store(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.delete(obj);
	}

	@SuppressWarnings("unchecked")
	//recuperar todos objetos de um tipo (e subtipo)
	public List<T> readAll(){
		manager.ext().purge();  	//limpar cache do manager
		
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}

	@SuppressWarnings("unchecked")
	//deletar todos objetos de um tipo (e subtipo)
	public void deleteAll(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		Query q = manager.query();
		q.constrain(type);
		for (Object t : q.execute()) {
			manager.delete(t);
		}
	}


	//------------transação---------------
	public static void begin(){	
	}		// tem que manter vazio

	public static void commit(){
		manager.commit();
	}
	public static void rollback(){
		manager.rollback();
	}

	//apagar todos os objetos do banco
	public static void clear(){
		Query q = manager.query();
		q.constrain(Object.class);
		for (Object o : q.execute()) 
			manager.delete(o);
		manager.commit();
	}

	
	//	obter o maior id para o tipo
	//  cria consulta para ordenar decrescentemente e depois acessa o id do primeiro resultado
	public int getMaxId() {
		@SuppressWarnings("unchecked")
		Class<T> type =(Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		int id;

		//verificar se o banco esta vazio 
		if(manager.query(type).size()==0) {
			id=0;			//nenhum objeto armazenado para o tipo
		}
		else {
			//consulta ordenada por id
			Query q = manager.query();
			q.constrain(type);
			q.descend("id").orderDescending();
			List<T> resultados =  q.execute();
			T objetomaiorid = null;
			try {
				objetomaiorid =  resultados.get(0);
				Field atributo = type.getDeclaredField("id") ;
				atributo.setAccessible(true);
				id = (Integer) atributo.get(objetomaiorid);  //maior id
			} catch(NoSuchFieldException e) {
				throw new RuntimeException("classe "+type+" nao tem id");
			} catch (IllegalAccessException e) {
				throw new RuntimeException("classe "+type+" id inacessivel");
			}
		}
		return id;
	}

}

