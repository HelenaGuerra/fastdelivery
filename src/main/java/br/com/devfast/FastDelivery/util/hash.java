package br.com.devfast.FastDelivery.util;

import java.security.MessageDigest;

public class hash {

	public static void main(String[] args) {

		String senha = "b";

		senha = embaralhar(senha);

		System.out.println(senha);

	}

	public static String embaralhar(String senha) {

		try {

			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			String senhahex = hexString.toString();

			return senhahex;

		} catch (Exception e) {

			new RuntimeException(e.getMessage());
			return senha;

		}

	}

}