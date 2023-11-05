package br.com.mildevs.DAO;


import java.util.*;

import br.com.mildevs.entity.*;
import br.com.mildevs.DAO.*;
import jakarta.persistence.*;

public class MultaDAO {

	private EntityManager manager;

	public MultaDAO() {
		this.manager = Persistence.createEntityManagerFactory("multaDeTransito").createEntityManager();
	}
	
	public boolean criarMulta(Multa multa) {
		
		manager.getTransaction().begin();
		manager.persist(multa);
		manager.getTransaction().commit();

		return true;
	}
	
	public boolean insirarMulta(int codigoMulta, int nroCnhCondutor, String placaVeiculo) {
		
		Multa multaASerInseria = manager.find(Multa.class, codigoMulta);
		Veiculo veiculoASerMultado = manager.find(Veiculo.class, placaVeiculo);
		Condutor CondutorASerMultado = manager.find(Condutor.class, nroCnhCondutor);
		
		if(veiculoASerMultado == null || CondutorASerMultado == null || multaASerInseria == null)
			return false;
	
		veiculoASerMultado.getMultas().add(multaASerInseria);
//		CondutorASerMultado.setPontuação(multaASerInseria.getPontuacao() + CondutorASerMultado.getPontuação());
		
		manager.getTransaction().begin();
		manager.merge(multaASerInseria);
		manager.merge(veiculoASerMultado);
		manager.merge(CondutorASerMultado);
		manager.getTransaction().commit();

		return true;
		
	}	
	
	public Multa consultaMulta(int codigoMulta) {
		return manager.find(Multa.class, codigoMulta);
	}
	
	public List<Multa> mostraMulta() {
			
		Query query = manager.createQuery("select c from Multa as c ");
		return query.getResultList();
	}
	
	public boolean removerMulta(String placa, int codigoMulta, double valorPago, VeiculoDAO veiculoDao) {

		
		Veiculo veiculoMultado = manager.find(Veiculo.class, placa);
		Multa multaDoVeiculo = manager.find(Multa.class, codigoMulta);
		
	
		if(veiculoMultado == null || multaDoVeiculo == null)
			return false;
		
		if(multaDoVeiculo.getValor() > valorPago)
		{
			System.out.println("------------");
			System.out.println("Valor insuficiente!");
			System.out.println("------------");
			return false;
		}
		else {
			for(int i = 0; i < veiculoMultado.getMultas().size(); i++) {
				
				if(veiculoMultado.getMultas().get(i).getCodigoMulta() == multaDoVeiculo.getCodigoMulta()) {
					veiculoMultado.getMultas().remove(i);
					veiculoDao.AtualzarVeiculo(veiculoMultado);
					
					manager.getTransaction().begin();
					manager.remove(multaDoVeiculo);
					
					if(veiculoMultado.getMultas().size() == 1) 
					{
						manager.remove(veiculoMultado);
						System.out.println("======================");
						System.out.println("Todas as multas desse Veiculo foram pagas. \n"
								+ "Veiculo removido do Sistema!");
						System.out.println("======================");
					}
						
					manager.getTransaction().commit();
					
					return true;
				}
			}
			
			return false;
			
		}
		
	}
	
}
