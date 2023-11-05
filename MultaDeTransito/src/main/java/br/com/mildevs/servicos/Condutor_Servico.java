package br.com.mildevs.servicos;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import br.com.mildevs.DAO.CondutorDAO;
import br.com.mildevs.entity.Condutor;
import br.com.mildevs.programa.metodos_checagem;

public class Condutor_Servico {

	public static void adicionar_condutor(Scanner in, CondutorDAO condutorDao) {
		try {

			Condutor novoCondutor = inserirCondutor(in, condutorDao);
		} catch (Exception e) {
			System.out.println("-----------------------");
			System.err.println("\n Erro ao Inserir um Novo Condutor. Tente Novamente");
			System.out.println("\n-----------------------");
			System.err.println(e);
		}

	}

	public static Condutor inserirCondutor(Scanner in, CondutorDAO condutorDao) {

		Condutor novoCondutor = new Condutor();

		System.out.println("=========== Inserir Condutor ==========");

		System.out.println("\n==== Data De emisão ====");
		System.out.print("Digite o Ano de Emissão: ");
		int ano = metodos_checagem.DigitarData(in);

		System.out.print("Digite o Mes da Emissão: ");
		int mes = metodos_checagem.DigitarData(in);

		System.out.print("Digite o Dia da Emissão: ");
		int dia = metodos_checagem.DigitarData(in);

		novoCondutor.setDataEmissao(LocalDate.of(ano, mes, dia));
		
		System.out.println("-----------------------");

		System.out.print("\nDigite o Orgão Emissão: ");
		String orgao = in.nextLine();

		
		novoCondutor.setOrgaoEmisso(orgao);

		condutorDao.insirarCondutor(novoCondutor);

		System.out.println("\n====================");
		System.out.println("\nCondutor inserido no sistema com sucessor!");
		System.out.println("-----------------------");
		System.out.println("\nNumero CNH adicionado ao Condutor: " + novoCondutor.getNroCnh());
		System.out.println("\n-----------------------");

		System.out.println("\n====================");

		return novoCondutor;

	}

	public static void procuraCondutor(Scanner in, CondutorDAO condutorDao) {
		System.out.println("====== Procurar Condutor ======");
		System.out.println("Digite o numero de CNH do condutor");
		int nrm = metodos_checagem.DigitarUmNumero(in);

		Condutor novoCondutor = condutorDao.consultaCondutor(nrm);
		if (novoCondutor == null) {
			System.out.println("Condutor não encotrado!");
			return;
		} else {
			System.out.println(novoCondutor);
		}
		System.out.println("===============================");
	}

	public static void listaCondutores(CondutorDAO condutorDao) {
		System.out.println("====== Lista de todos os Condutores ======");
		List<Condutor> condutores = condutorDao.mostraCondutores();

		if (condutores.size() > 0) {
			for (Condutor Condutores : condutores) {
				System.out.println(Condutores);
			}
			
			return;
		}
		System.out.println("------------------------------------------");
		System.out.println("Não há um condutor cadastrado no sistema!!");
		System.out.println("------------------------------------------");

		System.out.println("==============================");
	}

	public static void removerCondutor(Scanner in, CondutorDAO condutorDao) {
		System.out.println("====== Remover Condutor ======");
		System.out.println("Digite o número do CNH: ");
		int nroCnh = metodos_checagem.DigitarUmNumero(in);

		try {
			condutorDao.removeCondutor(nroCnh);
			System.out.println("------------------------------");
			System.out.println("Condutor removido do sistema!!");
			System.out.println("------------------------------");
		} catch (Exception e) {
			System.out.println("-----------------");
			System.err.println("Erro ao Remover!!");
			System.out.println("-----------------");
		}
		System.out.println("==============================");
	}

	public static void venderCarro(Scanner in, CondutorDAO condutorDao) {
		System.out.println("====== Vender Veiculos ======");

		System.out.print("\nNúmeor do CNH do Comprador: ");
		int nroCnhComprador = metodos_checagem.DigitarUmNumero(in);

		System.out.print("Número do CNH do Vendedor: ");
		int nroCnhVendedor = metodos_checagem.DigitarUmNumero(in);

		boolean vendarFeita = false;
		
		try {
			vendarFeita = condutorDao.vendaVeiculo(nroCnhVendedor, nroCnhComprador);
			
		} catch (Exception e) {
			System.out.println("-----------------------");
			System.err.println(e);
		}
		
		if (vendarFeita) {

			System.out.println("---------------------------");
			System.out.println("Compra realizada com suceso");
			System.out.println("---------------------------");
			return;
		}

		else {
			System.out.println("-----------------------");
			System.out.println("Erro ao vender veiculo ");
			System.out.println("-----------------------");
		}
		System.out.println("=============================");
	}

}
