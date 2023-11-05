package br.com.mildevs.DAO;

import java.util.List;



import br.com.mildevs.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CondutorDAO {

	private EntityManager manager;

	public CondutorDAO() {
		this.manager = Persistence.createEntityManagerFactory("multaDeTransito").createEntityManager();
	}

	public boolean insirarCondutor(Condutor condutor) {
		
		manager.getTransaction().begin();
		manager.persist(condutor);
		manager.getTransaction().commit();

		return true;
	}

	public Condutor consultaCondutor(int nroCnh) {
		return manager.find(Condutor.class, nroCnh);
	}

	public List<Condutor> mostraCondutores() {

		Query query = manager.createQuery("select c from Condutor as c ");
		return query.getResultList();
	}

	public boolean removeCondutor(int nroCnh) {

		Condutor condutorASerRemovido = manager.find(Condutor.class, nroCnh);

		if (condutorASerRemovido == null)
			return false;

		manager.getTransaction().begin();
		manager.remove(condutorASerRemovido);
		manager.getTransaction().commit();

		return true;
	}
	
	public boolean vendaVeiculo(int nroCnhVendedor, int nroCnhComprador) {
		
		Condutor condutorVendedor = manager.find(Condutor.class, nroCnhVendedor);
		
		Condutor condutorComprador = manager.find(Condutor.class, nroCnhComprador);
	
		if(condutorVendedor == null || condutorComprador == null) {
			
			System.out.println("Comprador/Vendedor não existe!" );
			return false;
		}
			
		
		if(condutorVendedor.getVeiculo() == null || condutorComprador.getVeiculo() != null) {
			System.out.println("Comprador já possui um veiculo /Vendedor não possuir um veiculo!" );
			return false;
		}
		
		Veiculo veiculo = condutorVendedor.getVeiculo();
		
		if(veiculo.getMultas().size() > 0) {
			System.out.println("Veiculo do vendedor possuir multas!" );
			return false;
		}
			
		
		condutorComprador.setVeiculo(veiculo);
		condutorVendedor.setVeiculo(null);
		
		manager.getTransaction().begin();
		manager.merge(condutorComprador);
		manager.merge(condutorVendedor);
		
		manager.getTransaction().commit();
		
		
		return true;
	}
	
	public boolean AtualzarCondutor(Condutor condutor) {
	
		manager.getTransaction().begin();
		manager.merge(condutor);
		manager.getTransaction().commit();

		return true;
	}

}
