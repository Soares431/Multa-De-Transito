package br.com.mildevs.DAO;

import java.util.*;

import br.com.mildevs.entity.*;
import jakarta.persistence.*;

public class VeiculoDAO {
	
	private EntityManager manager;

	public VeiculoDAO() {
		this.manager = Persistence.createEntityManagerFactory("multaDeTransito").createEntityManager();
	}
	
	public boolean insirarVeiculo(String placa) {
		
		Veiculo veiculo = manager.find(Veiculo.class, placa);
		
		if(veiculo.getCondutor() == null)
			return false;
		
		manager.getTransaction().begin();
		manager.persist(veiculo);
		manager.getTransaction().commit();

		return true;
	}
	
	public Veiculo consultaVeiculo(String placa) {
		return manager.find(Veiculo.class, placa);
	}
	

	public List<Veiculo> mostraVeiculo() {

		Query query = manager.createQuery("select c from Veiculo as c ");
		return query.getResultList();
	}
	
	public boolean removeVeiculo(String placa) {

		Veiculo veiculoASerRemovido = manager.find(Veiculo.class, placa);

		if (veiculoASerRemovido == null)
			return false;

		manager.getTransaction().begin();
		manager.remove(veiculoASerRemovido);
		manager.getTransaction().commit();

		return true;
	}
	
	public List<Multa> ListaMultas(String placa){
		
		Query query = manager.createQuery("select c from Multa as c where c.veiculo = :veiculoMultado ");
		query.setParameter("veiculoMultado", placa);
		
		List<Multa> multas = query.getResultList();
		

		return query.getResultList();
	}
	
	public boolean AtualzarVeiculo(Veiculo veiculo) {
		
		manager.getTransaction().begin();
		manager.merge(veiculo);
		manager.getTransaction().commit();

		return true;
	}
}
