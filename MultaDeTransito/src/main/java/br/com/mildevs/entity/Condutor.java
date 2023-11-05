package br.com.mildevs.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Condutor {
//	Condutor(nroCnh, dataEmissao, orgaoEmissor, pontuacao, Veiculo)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nroCnh;
	
	@Column(nullable =  false, name = "data_Emissão")
	private LocalDate dataEmissao;
	
	@Column(nullable =  false, name = "orgão_Emissor")
	private String orgaoEmisso;
	
	@Column(nullable =  false, name = "Pontos")
	private int pontuação;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "placa_fk", referencedColumnName = "placa")
	private Veiculo veiculo;
	
	@Override
	public String toString() {
		
		System.out.println( "======================\n "
				+ "Condutor: \n"
				+ "-----------------\n"
				+ "Número do CNH: " + nroCnh + " \n"
				+ "Data de Emissão: " + dataEmissao + " \n"
				+ "Pontuação: " + pontuação + " \n");
		
		if(getVeiculo() != null) 
			System.out.println("Condutor do Veiculo da Placa: " + veiculo.getPlaca());
		else
			System.out.println("Não possui Veiculo Cadastrado");
		
		return "======================";
	}
	
	public int getNroCnh() {
		return nroCnh;
	}
	public void setNroCnh(int nroCnh) {
		this.nroCnh = nroCnh;
	}
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getOrgaoEmisso() {
		return orgaoEmisso;
	}
	public void setOrgaoEmisso(String orgaoEmisso) {
		this.orgaoEmisso = orgaoEmisso;
	}
	public int getPontuação() {
		return pontuação;
	}
	public void setPontuação(int pontuação) {
		this.pontuação = pontuação;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	
	
	
}
