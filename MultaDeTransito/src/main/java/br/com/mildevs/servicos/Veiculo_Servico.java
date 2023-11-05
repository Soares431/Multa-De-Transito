package br.com.mildevs.servicos;

import java.util.List;

import java.util.Scanner;

import br.com.mildevs.DAO.CondutorDAO;
import br.com.mildevs.DAO.VeiculoDAO;
import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Veiculo;
import br.com.mildevs.programa.metodos_checagem;
import br.com.mildevs.servicos.*;

public class Veiculo_Servico {

	public static void adicionarVeiculo(Scanner in, CondutorDAO condutorDao, VeiculoDAO VeiculoDao) {
		System.out.println("\n");
		try {

			Veiculo novoVeiculo = InserirVeiculo(in, condutorDao, VeiculoDao, null);
		} catch (Exception e) {
			System.out.println("-----------------------");
			System.err.println("\n Erro ao Inserir um Veiculo. Tente Novamente");
			System.out.println("\n-----------------------");
			System.err.println(e);
		}
		System.out.println("\n");
	}

	public static Veiculo InserirVeiculo(Scanner in, CondutorDAO condutorDao, VeiculoDAO veiculoDao, Condutor TemCondutor) {

		Veiculo novoVeiculo = new Veiculo();
		Condutor novoCondutor = new Condutor();

		System.out.println("=========== Inserir Veiculo ==========");
		System.out.print("Placa: ");
		String placa = in.nextLine();

		System.out.print("Ano: ");
		int anoVeiculo = metodos_checagem.DigitarUmNumero(in);

		System.out.print("Marca: ");
		String Marca = in.nextLine();

		System.out.print("Modelo: ");
		String modelo = in.nextLine();

		novoVeiculo.setPlaca(placa);
		novoVeiculo.setAno(anoVeiculo);
		novoVeiculo.setMarca(Marca);
		novoVeiculo.setModelo(modelo);

		System.out.println("==============================");

		if (TemCondutor == null) {
			System.out.println("Inserir um novo condutor a esse veiculo? ");
			int resp = metodos_checagem.resposta(in, "Sim", "Não");

			if (resp == 1) {

				novoCondutor = Condutor_Servico.inserirCondutor(in, condutorDao);
				novoCondutor.setVeiculo(novoVeiculo);
				condutorDao.AtualzarCondutor(novoCondutor);

				novoVeiculo.setCondutor(novoCondutor);
				veiculoDao.insirarVeiculo(novoVeiculo.getPlaca());

			} else {
				System.out.println("Digite o número do CNH do condutor para inserir a esse veiculo: ");
				int cnh = metodos_checagem.DigitarUmNumero(in);

				ChecagemCondutorVeiculo(condutorDao, veiculoDao, novoVeiculo, cnh);

			}
		}
		else {
			
			novoCondutor = TemCondutor;
			
			novoCondutor.setVeiculo(novoVeiculo);
			condutorDao.AtualzarCondutor(novoCondutor);
			
			novoVeiculo.setCondutor(novoCondutor);
			veiculoDao.insirarVeiculo(novoVeiculo.getPlaca());
			
		}
		System.out.println("======================");
		System.out.println("\nVeiculo inserido no sistema com sucessor!");
		System.out.println("\n====================");
		return novoVeiculo;

	}

	public static void ChecagemCondutorVeiculo(CondutorDAO condutorDao, VeiculoDAO veiculoDao, Veiculo novoVeiculo,
			int cnh) {
		Condutor novoCondutor;
		novoCondutor = condutorDao.consultaCondutor(cnh);

		if (novoCondutor != null) {

			if (novoCondutor.getVeiculo() == null) {
				novoCondutor = condutorDao.consultaCondutor(cnh);
				novoCondutor.setVeiculo(novoVeiculo);
				condutorDao.AtualzarCondutor(novoCondutor);
				novoVeiculo.setCondutor(novoCondutor);

				veiculoDao.insirarVeiculo(novoVeiculo.getPlaca());
			} else {
				System.out.println("-----------------");
				System.out.println("Esse Condutor já possui Veiculo");

			}

		} else {
			System.out.println("-----------------");
			System.out.println("Condutor não encotrado, tente novamente");
			System.out.println("-----------------");
		}
	}

	public static void procurarVeiculo(Scanner in, VeiculoDAO VeiculoDao) {
		System.out.println("====== Procurar Veiculo ======");
		System.out.println("Digite a placa do veiculo: ");
		String placa = in.nextLine();

		Veiculo novoVeiculo = VeiculoDao.consultaVeiculo(placa);
		if (novoVeiculo == null) {
			System.out.println("Veiculo não encotrado!");
		} else {
			System.out.println(novoVeiculo);
		}
		System.out.println("==============================");
	}

	public static void listarVeiculos(VeiculoDAO VeiculoDao) {
		System.out.println("====== Lista de Todos os Veiculos =======");
		List<Veiculo> veiculos = VeiculoDao.mostraVeiculo();

		if (veiculos != null) {
			for (Veiculo Veiculos : veiculos) {
				System.out.println(Veiculos);
			}

		} else {
			System.out.println("Não há um condutor cadastrado!!");
		}

		System.out.println("=========================================");
	}

	public static void removerVeiculo(Scanner in, VeiculoDAO VeiculoDao) {
		System.out.println("======= Remover Veiculo =======");
		System.out.println("Digite a placa: ");
		String placa = in.nextLine();

		VeiculoDao.removeVeiculo(placa);
		System.out.println("==============================");
	}

}
