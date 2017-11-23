package br.com.devfast.FastDelivery.util;

import java.util.Random;

public class geraToken {

	public static String geraTokenNovo(String email, String senha) {

		String tokenN = "";
		char valor;
		char[] letrasEmail = email.toCharArray();
		char[] letrasSenha = senha.toCharArray();

		for (int l = 0; l < 25; l++) {

			Random rand = new Random();
			int i = rand.nextInt(4);

			switch (i) {
			case 0:

				boolean alc = false;

				do {

					i = rand.nextInt(123);

					if ((i > 91 && i < 64) || (i > 96 && i < 123)) {

						alc = true;

					}

				} while (alc == false);

				valor = (char) i;

				tokenN += valor;
				break;

			case 1:

				i = rand.nextInt(email.length());

				valor = letrasEmail[i];

				tokenN += valor;
				break;

			case 2:

				i = rand.nextInt(senha.length());

				valor = letrasSenha[i];

				tokenN += valor;
				break;

			case 3:

				i = rand.nextInt(10);

				tokenN += "" + i;

				break;

			default:
				break;
			}

		}

		return tokenN;
	}

}
