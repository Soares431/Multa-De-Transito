package br.com.mildevs.programa;

import java.io.IOException;

public class menu {

	
	static public void Menu() {
		System.out.println("======== SISTEMA DE MULTA =======");

		System.out.println("\n1 - CONDUTOR");
		System.out.println("2 - VEICULO");
		System.out.println("3 - MULTA");
		System.out.println("0 - SAIR");

		System.out.println("\n=================================");

	}

	static public void SubMenuCondutor() {

		System.out.println("======== MENU CONDUTOR ========");

		System.out.println("\n1 - Inserir Condutor");
		System.out.println("2 - Consultar Condutor");
		System.out.println("3 - Listar Condutor");
		System.out.println("4 - Remover condutor");
		System.out.println("5 - Vender carro");
		System.out.println("0 - Voltar");

		System.out.println("\n=============================");

	}

	static public void SubMenu(String msm) {

		System.out.println("=========== " + msm + " ===========");

		System.out.println("\n1 - Inserir " + msm);
		System.out.println("2 - Consultar " + msm);
		System.out.println("3 - Listar " + msm);
		System.out.println("4 - Remover " + msm);
		System.out.println("0 - Voltar");

		System.out.println("\n===================================");

	}
}
