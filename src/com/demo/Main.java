package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.loja.model.Cliente;

public class Main {

	public static void main(String[] args) {

		ArrayList<Cliente> clientes = new ArrayList<>();

		String url = "jdbc:mysql://localhost:3306"; // Connection URL -> Connection String
		String database = "lojatelemoveis";
		String finalUrl = url + "/" + database;

		String username = "root";
		String password = "Pa$$w0rd"; // S3gr3d0

		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM clientes;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			// int affectedRows = stmt.executeUpdate("DELETE * FROM clientes WHERE id =
			// 1;");

			while (rs.next()) {

				// Mapeamento entidadeBD -> Objeto Java
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String nif = rs.getString(3);

				Cliente cliente = new Cliente(id, nome, nif);
				clientes.add(cliente);

			}

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}

		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i));
		}

	}

	private static void createCliente() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Introduza o seu id: ");
		int id = Integer.parseInt(scanner.nextLine());

		System.out.print("Introduza o seu nome: ");
		String nome = scanner.nextLine();

		System.out.print("Introduza o seu nif: ");
		String nif = scanner.nextLine();

		Cliente c = new Cliente(nome, nif);
	}

}