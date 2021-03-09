package com.loja.data.repo;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Repository<T> implements IRepository<T> {

	// Dados de conexão à BD
	private static String url = "jdbc:mysql://localhost:3306"; // Connection URL -> Connection String
	private String database = "lojatelemoveis?allowMultiQueries=true";
	protected String finalUrl = url + "/" + database;

	protected static String username = "root";
	protected static String password = "Pa$$w0rd"; // S3gr3d0
	protected String table;

	protected abstract T mapEntityToObject(ResultSet obj) throws SQLException;

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