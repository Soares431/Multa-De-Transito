package br.com.mildevs.programa;

import java.time.LocalDate;

import br.com.mildevs.DAO.CondutorDAO;
import br.com.mildevs.DAO.MultaDAO;
import br.com.mildevs.DAO.VeiculoDAO;
import br.com.mildevs.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.util.*;
import br.com.mildevs.programa.*;
import br.com.mildevs.servicos.*;

public class sistemaDeMulta {

	public static void SelecaoCondutor(Scanner in, CondutorDAO condutorDao) {

		int op = 0;

		do {
			menu.SubMenuCondutor();
			System.out.print("Sua Opção: ");
			op = metodos_checagem.DigitarUmNumero(in);

			switch (op) {

			case 0: {
				System.out.println("----------------");
				System.out.println("Voltando para o MENU");
				System.out.println("----------------");
				break;
			}

			case 1: {
				Condutor_Servico.adicionar_condutor(in, condutorDao);
				break;
			}

			case 2: {

				Condutor_Servico.procuraCondutor(in, condutorDao);
				break;
			}

			case 3: {
				Condutor_Servico.listaCondutores(condutorDao);
				break;
			}

			case 4: {
				Condutor_Servico.removerCondutor(in, condutorDao);
				break;
			}

			case 5: {
				Condutor_Servico.venderCarro(in, condutorDao);
				break;
			}

			default:
				System.out.println("----------------");
				System.out.println("Digite um valor válido!!!");
				System.out.println("----------------");
				break;
			}

		} while (op != 0);
	}

	public static void SelecaoVeiculo(Scanner in, CondutorDAO condutorDao, VeiculoDAO VeiculoDao) {

		int op = 0;

		do {
			menu.SubMenu("Veiculo");
			System.out.print("Sua Opção: ");
			op = metodos_checagem.DigitarUmNumero(in);

			switch (op) {

			case 0: {
				System.out.println("----------------");
				System.out.println("Voltando para o MENU");
				System.out.println("----------------");
				break;
			}

			case 1: {
				Veiculo_Servico.adicionarVeiculo(in, condutorDao, VeiculoDao);

				break;
			}
			case 2: {
				Veiculo_Servico.procurarVeiculo(in, VeiculoDao);
				break;
			}

			case 3: {
				Veiculo_Servico.listarVeiculos(VeiculoDao);
				break;
			}

			case 4: {
				Veiculo_Servico.removerVeiculo(in, VeiculoDao);
				break;
			}

			default:
				System.out.println("----------------");
				System.out.println("Digite um valor válido!!!");
				System.out.println("----------------");
				break;
			}

		} while (op != 0);
	}

	public static void SelecaoMulta(Scanner in, CondutorDAO condutorDao, VeiculoDAO veiculoDao, MultaDAO multaDao) {

		int op = 0;

		do {
			menu.SubMenu("Multa");
			System.out.print("Sua Opção: ");
			op = metodos_checagem.DigitarUmNumero(in);

			switch (op) {

			case 0: {
				System.out.println("----------------");
				System.out.println("Voltando para o MENU");
				System.out.println("----------------");
				break;
			}

			case 1: {
				Multa_Servico.adicionarMulta(in, condutorDao, veiculoDao, multaDao);
				break;
			}

			case 2: {
				Multa_Servico.procurarMulta(in, multaDao);
				break;
			}

			case 3: {
				Multa_Servico.listarMulta(multaDao);
				break;
			}

			case 4: {
				Multa_Servico.removerMulta(in, multaDao, veiculoDao);
				break;
			}

			default:
				System.out.println("----------------");
				System.out.println("Digite um valor válido!!!");
				System.out.println("----------------");
				break;
			}

		} while (op != 0);

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		CondutorDAO condutorDAO = new CondutorDAO();
		VeiculoDAO veiculoDao = new VeiculoDAO();
		MultaDAO multaDAO = new MultaDAO();
		int op = 0;

		do {

			menu.Menu();
			System.out.print("Sua Opção: ");
			op = metodos_checagem.DigitarUmNumero(in);
			LimparTela();

			switch (op) {

			case 0: {
				System.out.println("Programa encerrado....");
				break;
			}

			case 1: {
				SelecaoCondutor(in, condutorDAO);
				break;
			}

			case 2: {
				SelecaoVeiculo(in, condutorDAO, veiculoDao);
				break;
			}

			case 3: {

				SelecaoMulta(in, condutorDAO, veiculoDao, multaDAO);
				break;
			}

			default: {
				System.out.println("POR FAVOR DIGITE UM NÚMERO VÁLIDO!!!");
				break;
			}

			}

		} while (op != 0);

	}

	public static void LimparTela() {
//		if (System.getProperty("os.name").contains("Windows"))
//			try {
//				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		else
//			try {
//				Runtime.getRuntime().exec("clear");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		try {
			String operatingSystem = System.getProperty("os.name"); // Check the current operating system

			if (operatingSystem.contains("Windows")) {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			} else {
				ProcessBuilder pb = new ProcessBuilder("clear");
				Process startProcess = pb.inheritIO().start();

				startProcess.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
