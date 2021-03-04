package com.loja.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.loja.model.Cliente;
import com.loja.model.Produto;

public class Data {

	// Dados de conexão à BD
	private static String url = "jdbc:mysql://localhost:3306"; // Connection URL -> Connection String
	private static String database = "lojatelemoveis";
	private static String finalUrl = url + "/" + database;

	private static String username = "root";
	private static String password = "Pa$$w0rd"; // S3gr3d0

	public static ArrayList<Cliente> getAllClientes() {
		ArrayList<Cliente> temp = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM clientes;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				// Mapeamento entidadeBD -> Objeto Java
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String nif = rs.getString(3);

				Cliente cliente = new Cliente(id, nome, nif);
				temp.add(cliente);

			}

			return temp;
		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
			return null;
		}

	}

	public static ArrayList<Produto> getAllProdutos() {
		ArrayList<Produto> temp = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM produtos;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				// Mapeamento entidadeBD -> Objeto Java
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				double preco = rs.getDouble(3);
				int stock = rs.getInt(4);

				Produto cliente = new Produto(id, nome, preco, stock);
				temp.add(cliente);

			}

			return temp;
		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
			return null;
		}

	}

	public static Cliente getClienteById(int id) {
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM clientes" + "WHERE id = " + id + ";";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				// Mapeamento entidadeBD -> Objeto Java
				int tempId = rs.getInt(1);
				String nome = rs.getString(2);
				String nif = rs.getString(3);

				Cliente cliente = new Cliente(tempId, nome, nif);
				return cliente;

			}

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
		return null;
	}

	public static void inserirCliente(Cliente cliente) {
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			// Mapeamento Objeto -> Entidade

			String nome = cliente.getNome();
			String nif = cliente.getNif();

			String query = "INSERT INTO clientes (nome, nif)" + "VALUES ('" + nome + "', '" + nif + "');";

			Statement stmt = conn.createStatement();

			long affectedRows = stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
	}

}

// seed data code

// Listas de entidades

/*
 * public static ArrayList<Cliente> clientes = new ArrayList<>(); public static
 * ArrayList<Produto> produtos = new ArrayList<>(); public static void
 * seedData() { fillClientes(); fillProdutos(); } private static void
 * fillClientes() {
 * 
 * try (Connection conn = DriverManager.getConnection(finalUrl, username,
 * password)) {
 * 
 * String query = "SELECT * FROM clientes;";
 * 
 * Statement stmt = conn.createStatement();
 * 
 * ResultSet rs = stmt.executeQuery(query);
 * 
 * while (rs.next()) {
 * 
 * // Mapeamento entidadeBD -> Objeto Java int id = rs.getInt(1); String nome =
 * rs.getString(2); String nif = rs.getString(3);
 * 
 * Cliente cliente = new Cliente(id, nome, nif); clientes.add(cliente);
 * 
 * }
 * 
 * } catch (SQLException e) { System.out.println("Erro a comunicar com BD: " +
 * e.getMessage()); } }
 * 
 * private static void fillProdutos() {
 * 
 * try (Connection conn = DriverManager.getConnection(finalUrl, username,
 * password)) {
 * 
 * String query = "SELECT * FROM produtos;";
 * 
 * Statement stmt = conn.createStatement();
 * 
 * ResultSet rs = stmt.executeQuery(query);
 * 
 * while (rs.next()) {
 * 
 * // Mapeamento entidadeBD -> Objeto Java int id = rs.getInt(1); String nome =
 * rs.getString(2); double preco = rs.getDouble(3); int stock = rs.getInt(4);
 * 
 * Produto cliente = new Produto(id, nome, preco, stock); produtos.add(cliente);
 * 
 * }
 * 
 * } catch (SQLException e) { System.out.println("Erro a comunicar com BD: " +
 * e.getMessage()); } }
 */