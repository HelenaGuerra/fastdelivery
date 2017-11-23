package br.com.devfast.FastDelivery.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serial {

	public static Object serializar(String path, Object obj) throws Exception {
		FileOutputStream outFile = new FileOutputStream(path);
		ObjectOutputStream s = new ObjectOutputStream(outFile);
		s.writeObject(obj);

		return s;
	}

	public static Object deserializar(String path) throws Exception {
		FileInputStream inFile = new FileInputStream(path);
		ObjectInputStream d = new ObjectInputStream(inFile);
		Object o = d.readObject();
		d.close();
		return o;
	}

}
