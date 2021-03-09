package com.loja.data.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.loja.exceptions.DeleteNotExecutedException;
import com.loja.exceptions.InsertNotExecutedException;
import com.loja.exceptions.UpdateNotExecutedException;
import com.loja.model.Produto;

public class ProdutoRepository extends Repository<Produto> {

	public ProdutoRepository() {
		table = "produtos";
	}

	@Override
	public ArrayList<Produto> getAll() {
		ArrayList<Produto> temp = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM " + table + ";";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Produto produto = mapEntityToObject(rs);
				temp.add(produto);

			}

			return temp;
		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
			return null;
		}

	}

	@Override
	public Produto getById(int id) { // getById(11); Produto maria = getById(11);
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM " + table 
						 + " WHERE id = " + id + ";";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Produto Produto = mapEntityToObject(rs);
				return Produto;
			}

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void insert(Produto produto) throws InsertNotExecutedException { // insert(new Produto("Rui", "857965895");
		
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "INSERT INTO " + table + " (nome, valor, stock)"
					+ " VALUES (?, ?, ?);";
			System.out.println(query);
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			int id = produto.getId();
			String nome = produto.getNome();
			double valor = produto.getPreco();
			int stock = produto.getStock();
			
			stmt.setString(1, nome);
			stmt.setDouble(2, valor);
			stmt.setInt(3, stock);
			
			long affectedRows = stmt.executeUpdate(query); // 1

			if (affectedRows < 1)
				throw new InsertNotExecutedException("Produto com id " + id + " não foi inserido com sucesso");

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
	}

	@Override
	public void update(Produto produto) throws UpdateNotExecutedException {

		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			Statement stmt = conn.createStatement();

			int id = produto.getId();
			String nome = produto.getNome();
			double valor = produto.getPreco();
			int stock = produto.getStock();

			String query = "UPDATE " + table + " SET  nome = '" + nome + "', valor = " + valor + ", stock = " + stock
					+ " WHERE id = " + id;
			System.out.println(query);

			long affectedRows = stmt.executeUpdate(query); // 1

			if (affectedRows < 1) {
				throw new UpdateNotExecutedException("update Produto com id " + produto.getId() + " não funciona");
			}

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}

	}

	@Override
	public void delete(int id) {
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			Statement stmt = conn.createStatement();

			String query = "DELETE FROM " + table + " WHERE id = " + id;
			System.out.println(query);

			long affectedRows = stmt.executeUpdate(query); // 1

			if (affectedRows < 1)
				throw new DeleteNotExecutedException("Produto com " + id + " não foi apagado com sucesso");

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}

	}

	protected Produto mapEntityToObject(ResultSet rs) throws SQLException {
		// Mapeamento entidadeBD -> Objeto Java

		int id = rs.getInt(1);
		String nome = rs.getString(2);
		double valor = rs.getDouble(3);
		int stock = rs.getInt(4);

		Produto produto = new Produto(id, nome, valor, stock);

		return produto;
	}

}