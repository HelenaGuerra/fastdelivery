package br.com.devfast.FastDelivery.util;

//o que eu mais temia aconteceu...
//admoesto a todos vós que lerem esta classe que tais brutalidades 
//foram cometidas pelos componentes Chaves e Barbosa.
//Eu, Jonnathas Luis não contribuí para tal feito

public class Concerta {

	public static void main(String[] args) {

		String palavra = "1";

		concertaJunior(palavra);

	}

	public static String concerta(String palavra) {

		if (palavra.toCharArray()[palavra.length() - 1] == ',') {
			char[] letras = palavra.toCharArray();

			palavra = "";

			int i = 1;

			do {

				letras[letras.length - i] = ' ';

				i++;

			} while (letras[letras.length - i] == ',');

			palavra = String.valueOf(letras);

			palavra = palavra.trim();

		}

		return palavra;
	}

	public static void concertaJunior(String palavra) {

		char[] letras = palavra.toCharArray();

		for (int i = 0; i < letras.length; i++) {

			int l = (int) letras[i];

			System.out.println(l);

		}

	}
}
