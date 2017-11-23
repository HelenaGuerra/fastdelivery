package br.com.devfast.FastDelivery.util;

import java.time.LocalDateTime;

public class Date {

	public static void main(String[] args) {

		Date.teste();

	}

	public static void teste() {

		LocalDateTime now = LocalDateTime.now();

		java.sql.Timestamp date = java.sql.Timestamp.valueOf(now);

		System.out.println(date);

	}

}
