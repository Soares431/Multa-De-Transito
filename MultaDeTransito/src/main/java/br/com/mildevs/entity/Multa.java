package br.com.mildevs.entity;

import jakarta.persistence.*;

@Entity
public class Multa {
//	Multa(codigoMulta, valor, pontuacao, Veiculo)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoMulta;
	
	@Column(nullable = false, name = "valor_Multa")
	private double valor;
	
	@Column(nullable = false)
	private int pontuacao;
	
	@ManyToOne
	@JoinColumn(name = "placa_fk", referencedColumnName = "placa")
	private Veiculo veiculo;
	
	
	public int getCodigoMulta() {
		return codigoMulta;
	}
	public void setCodigoMulta(int codigoMulta) {
		this.codigoMulta = codigoMulta;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	

	@Override
	public String toString() {
		
		System.out.println( "======================\n "
				+ "Multa: \n"
				+ "-----------------\n"
				+ "Código: " + codigoMulta + "\n"
				+ "Valor da Multa: " + valor + "\n"
				+ "Pontuação: " + pontuacao + "\n"
				+ "Veiculo Multado: " + veiculo.getPlaca() + "\n");  
		
		return "======================";
	}
	
}
