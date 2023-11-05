package br.com.mildevs.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.mildevs.DAO.CondutorDAO;
import br.com.mildevs.DAO.MultaDAO;
import br.com.mildevs.DAO.VeiculoDAO;
import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;
import br.com.mildevs.servicos.*;
import br.com.mildevs.programa.*;

public class Multa_Servico {
	
	public static void adicionarMulta(Scanner in, CondutorDAO condutorDao, VeiculoDAO veiculoDao, MultaDAO multaDao) {
		System.out.println("\n");
		try {

			Multa novaMulta = inserirMulta(in, condutorDao, veiculoDao, multaDao);

		} catch (Exception e) {
			System.out.println("-----------------------");
			System.err.println("\n Erro ao Inserir a Multa. Tente Novamente");
			System.out.println("\n-----------------------");
			System.err.println(e);
			e.printStackTrace();
		}
		
		System.out.println("\n");
	}

	public static Multa inserirMulta(Scanner in, CondutorDAO condutorDao, VeiculoDAO veiculoDao, MultaDAO multaDao) {


//		Condutor(nroCnh, dataEmissao, orgaoEmissor, pontuacao, Veiculo)

//		Veiculo (placa, ano, modelo, marca, Condutor, List<Multa> multas))
		
		System.out.println("=========== Inserir Multa ==========");
		
		Multa novaMulta = new Multa();
		Condutor novoCondutor = new Condutor();
		Veiculo novoVeiculo = new Veiculo();

		System.out.println("\n====== Condutor ======");
		System.out.println("\nAdicionar um novo condutor ou multar um condutor ja cadastrado no sistema? ");
		int resp = metodos_checagem.resposta(in, "Novo Condutor", "Condutor ja Cadastrado");

		if (resp == 1) {
			
			novoCondutor = Condutor_Servico.inserirCondutor(in, condutorDao);
		} else {
			System.out.println("Digite a o numero do CNH: ");
			int cnh = metodos_checagem.DigitarUmNumero(in);

			novoCondutor = condutorDao.consultaCondutor(cnh);
			
		}
		
		//--------------------------------------
		
		if(novoCondutor.getVeiculo() == null) {
			
			System.out.print("\n====== Veiculo ======");
			System.out.println("\nCadastrando um veiculo a esse condutor: ");
			
			novoVeiculo = Veiculo_Servico.InserirVeiculo(in, condutorDao, veiculoDao, novoCondutor);
		
			
		}
		else {
			novoVeiculo = novoCondutor.getVeiculo();
			System.out.println("\nCondutor do veiculo da Placa: " + novoCondutor.getVeiculo().getPlaca());
		}
		
		
		//--------------------------------------
		
		System.out.println("====== Multa ======");
		System.out.println("Pontos: ");
		
		int pontos = sistemaDePonto(in, novaMulta);
		
		System.out.println("----------------");
		System.out.printf("Valor da multa a ser paga: %.2f\n\n", novaMulta.getValor());
		
		System.out.println("----------------");
	
		novaMulta.setVeiculo(novoVeiculo);
		novoCondutor.setPontuação(novaMulta.getPontuacao() + novoCondutor.getPontuação());
		
		multaDao.criarMulta(novaMulta);
		multaDao.insirarMulta(novaMulta.getCodigoMulta(), novoCondutor.getNroCnh(), novoVeiculo.getPlaca());
		condutorDao.AtualzarCondutor(novoCondutor);
//		veiculoDao.AtualzarVeiculo(novoVeiculo);
		
		System.out.println("==============================");
		return novaMulta;
		
	}
	
	public static void procurarMulta(Scanner in, MultaDAO multaDao) {
		System.out.println("====== Procurar Multa =======");
		System.out.println("Digite o codigo da multa: ");
		int cod = metodos_checagem.DigitarUmNumero(in);

		Multa multa = multaDao.consultaMulta(cod);
		if (multa == null) {
			System.out.println("Multa não encotrado!");
			return;
		} else {
			System.out.println(multa);
		}
		System.out.println("==============================");
	}
	
	public static void listarMulta(MultaDAO multaDao) {
		System.out.println("====== Lista de Todas as Multas Inseridas =======");
		List<Multa> Multas = multaDao.mostraMulta();

		if (Multas != null) {
			for (Multa multas : Multas) {
				System.out.println(multas);
			}
			
			return;

		}

		System.out.println("Não há uma Multa Inseria no Sistema!!");
		System.out.println("=================================================");
	}
	
	public static void removerMulta(Scanner in, MultaDAO multaDao, VeiculoDAO veiculoDao) {
		System.out.println("====== Pagar Multa =======");

		System.out.println("Digite a placa do carro: ");
		String placa = in.nextLine();

		System.out.println("Digite o codigo da multa: ");
		int cod = metodos_checagem.DigitarUmNumero(in);

		System.out.println("Valor a ser pago: ");
		double valor = metodos_checagem.DigitarValor(in);
		
		try {
			
			if(multaDao.removerMulta(placa, cod, valor, veiculoDao)) {
				System.out.println("------------");
				System.out.println("Multa Paga com Sucesso!");
				System.out.println("------------");
			}
			else {
				System.out.println("------------");
				System.out.println("Erro, tente novamente");
				System.out.println("------------");
			}
			
		}catch(Exception e){
			System.out.println("------------");
			System.err.println("Erro ao pagar multa");
			System.out.println("------------");
			System.err.println(e);
		}
		
		System.out.println("==========================");
	}
	
	public static int sistemaDePonto(Scanner in, Multa novaMulta) {

		int pontos = metodos_checagem.DigitarUmNumero(in);

		do {
			switch (pontos) {

			case 3: {

				novaMulta.setPontuacao(3);
				novaMulta.setValor(88.38);

				break;
			}

			case 4: {

				novaMulta.setPontuacao(4);
				novaMulta.setValor(130.16);

				break;
			}

			case 5: {

				novaMulta.setPontuacao(5);
				novaMulta.setValor(195.23);

				break;
			}

			case 7: {

				novaMulta.setPontuacao(7);
				novaMulta.setValor(293.47);
				break;
			}

			default: {

				System.out.println("Digite um valor valido!");
				break;
			}

			}
		} while (pontos != 3 && pontos != 4 && pontos != 5 && pontos != 7);

		return pontos;
	}
	
}
