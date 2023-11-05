package br.com.mildevs.entity;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Veiculo {
//	Veiculo (placa, ano, modelo, marca, Condutor, List<Multa> multas))
	
	@Id
	@Column(nullable = false)
	private String placa;
	
	@Column(nullable = false)
	private int ano;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private String marca;
	
	@OneToOne(mappedBy = "veiculo", cascade = CascadeType.ALL)
	private Condutor condutor;
	
	
	@OneToMany(mappedBy = "veiculo",  cascade = CascadeType.ALL)
	private List<Multa> multas;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Condutor getCondutor() {
		return condutor;
	}
	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	public List<Multa> getMultas() {
		return multas;
	}
	public void setMultas(List<Multa> multas) {
		this.multas = multas;
	}
	
	@Override
	public String toString() {
		
		System.out.println("====================== \n" 
				+ "Veiculo: \n "
				+ "-----------------\n"
				+ "Placa:" + placa + " \n"
				+ "Ano=" + ano + " \n"
				+ "Modelo=" + modelo + "\n" 
				+ "Marca=" + marca + " \n");
		
		System.out.println("Multas do Veiculo :");
		
		if(getMultas().size() > 0) {
			for(Multa multa : getMultas()) {
				
				System.out.println("======================");
				System.out.println("Código da Multa: " + multa.getCodigoMulta());
				System.out.println("Valor da multa: " + multa.getValor());
				System.out.println("======================");
			}
		}
		else {
			System.out.println("================");
			System.out.println("Não possui multa");
			System.out.println("================");
		}
		
		
		
		return "==========================";
		
			
	}
	
	
}
