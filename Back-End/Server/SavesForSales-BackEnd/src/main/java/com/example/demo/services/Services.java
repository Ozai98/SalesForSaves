
package com.example.demo.services;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author German le yo
 */
public class Services {

	private static MessageDigest md;
	private static Pattern emailPattern;

	public static void startServices() {
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

	}

	public static String cryptPassword(String password) {
		byte[] crypt = md.digest(password.getBytes(StandardCharsets.UTF_8));
		return new String(Base64.encodeBase64(crypt));
	}

	public static void handleError(Exception ex) {
		ex.printStackTrace();
	}

	public static boolean validateEmail(String email) {

		return emailPattern.matcher(email).matches();
	}

	// compress the image bytes before storing it in the database

	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (Exception e) {}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (Exception ioe) {}
		return outputStream.toByteArray();
	}
}