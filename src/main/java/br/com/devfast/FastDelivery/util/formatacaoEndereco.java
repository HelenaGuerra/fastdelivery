package br.com.devfast.FastDelivery.util;

public class formatacaoEndereco {

	
	public static void main(String[] args) {
		
		
		
	}
	
	public static String[] separaEndereco(String endereco) {

		char[] palavra = endereco.toCharArray();

		endereco = "";

		for (int L = 0; L < palavra.length; L++) {

			if (palavra[L] == '-') {

				palavra[L] = ',';

				endereco += palavra[L];

			} else {

				endereco += palavra[L];

			}

		}

		String array[];

		array = endereco.split(",");

		array[5] += "-" + array[6];

		array[6] = array[7];

		for (int K = 0; K < array.length - 1; K++) {

			array[K] = array[K].trim();

		}

		return array;

	}

	public static String agrupaEndereco(String[] partes) {

		String enderecoTodo = "";

		for (int K = 0; K < partes.length - 1; K++) {

			/*
			 * Formatação da concatenação Logradouro: "" Numero: ", " Bairro: " - " Cidade:
			 * ", " Estado: " - " CEP: ", " Pais: ", "
			 */
			switch (K) {

			// Logradouro
			case 0:

				enderecoTodo += partes[K];

				break;

			// Numero
			case 1:

				enderecoTodo += ", " + partes[K];

				break;

			// Bairro
			case 2:

				enderecoTodo += " - " + partes[K];

				break;

			// Cidade
			case 3:

				enderecoTodo += ", " + partes[K];

				break;

			// Estado
			case 4:

				enderecoTodo += " - " + partes[K];

				break;

			// CEP
			case 5:

				enderecoTodo += ", " + partes[K];

				break;

			// Pais
			case 6:

				enderecoTodo += ", " + partes[K];

				break;

			default:
				break;
			}

		}

		return enderecoTodo;
	}

}
