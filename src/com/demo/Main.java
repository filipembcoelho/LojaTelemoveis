package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main /* extends Object */ {

	static String url = "jdbc:mysql://localhost:3306"; // Connection URL -> Connection String
	static String database = "lojatelemoveis?allowMultiQueries=true";
	static String finalUrl = url + "/" + database;

	static String username = "root";
	static String password = "Pa$$w0rd"; // S3gr3d0

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// SQL Injection

		// getById(5);
		do {
			System.out.print("Pesquise por nome: ");
			String nome = scanner.nextLine();

			getByNome(nome);
		} while (true);

	}

	private static void sqlPrompt() {
		boolean exit = false;
		do {
			System.out.print("MySQL> ");
			String query = scanner.nextLine();

			if (query.equals(".exit")) {
				exit = true;
			} else {
				executeQuery(query);
				exit = false;
			}

		} while (!exit);
	}

	private static void executeQuery(String query) {
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			Statement stmt = conn.createStatement();

			boolean exec = stmt.execute(query);

			if (exec) {
				// SELECT
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getObject(2));
				}
			} else {
				// INSERT / UPDATE / DELETE
				int affectedRows = stmt.getUpdateCount();
				System.out.println(affectedRows + (affectedRows > 1 ? " rows" : " row"));
			}

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
	}

	private static void getById(int id) {
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM clientes" + " WHERE id = " + id;

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
	}

	private static void getByNome(String nome) {
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM clientes"
						 + " WHERE nome LIKE ?";

			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setString(1, nome + "%");

			System.out.println(query + "\n");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
	}
}