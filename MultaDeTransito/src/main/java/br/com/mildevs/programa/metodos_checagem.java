package br.com.mildevs.programa;

import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import com.mysql.cj.exceptions.DataConversionException;

public class metodos_checagem {

	public static int resposta(Scanner in, String op1, String op2) {

		int resp = 0;

		while (resp != 1 && resp != 2) {
			System.out.println("1 - " + op1 + " / 2 - " + op2);
			resp = DigitarUmNumero(in);

			if (resp != 1 && resp != 2) {
				System.out.println("digite uma resposta válida: ");
			}
		}

		return resp;
	}

	public static int DigitarUmNumero(Scanner in) {

		int op = 0;
		boolean valorValido = false;

		do {
			try {

				op = in.nextInt();
				in.nextLine();
				valorValido = true;

			} catch (InputMismatchException e) {
				System.err.println("DIGITE UMA OPÇÃO VÁLIDA!!!");
				System.err.println(e); 
			}
		} while (!valorValido);

		return op;
	}

	public static double DigitarValor(Scanner in) {
		double op = 0;
		boolean valorValido = false;

		do {

			try {

				op = in.nextDouble();
				in.nextLine();
				valorValido = true;

			} catch (InputMismatchException e) {
				System.err.println("DIGITE UM VALOR VÁLIDO!!!");
			}
		} while (!valorValido);

		return op;
	}

	public static int DigitarData(Scanner in) {
		int op = 0;
		boolean valorValido = false;

		do {
			
			try {
				
				op = in.nextInt();
				in.nextLine();
				valorValido = true;
				
			} catch (DateTimeException e) {
				System.err.println("DIGITE UM VALOR VÁLIDO!!!");
			}
			
		} while (!valorValido);

		return op;
	}

}
