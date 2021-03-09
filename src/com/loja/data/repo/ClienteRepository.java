package com.loja.data.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.loja.exceptions.DeleteNotExecutedException;
import com.loja.exceptions.UpdateNotExecutedException;
import com.loja.model.Cliente;

public class ClienteRepository extends Repository<Cliente> {

	public ClienteRepository() {
		table = "clientes";
	}

	@Override
	public ArrayList<Cliente> getAll() {
		ArrayList<Cliente> temp = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM " + table + ";";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				// Mapeamento entidadeBD -> Objeto Java
				Cliente cliente = mapEntityToObject(rs);
				temp.add(cliente);

			}

			return temp;
		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
			return null;
		}

	}

	@Override
	public Cliente getById(int id) { // getById(11); Cliente maria = getById(11);
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "SELECT * FROM " + table + " WHERE id = " + id + ";";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				// Mapeamento entidadeBD -> Objeto Java
				Cliente cliente = mapEntityToObject(rs);
				return cliente;
			}

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void insert(Cliente cliente) { // insert(new Cliente("Rui", "857965895");
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "INSERT INTO " + table + " (nome, nif)" + " VALUES (?, ?);";

			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			String nome = cliente.getNome();
			String nif = cliente.getNif();

			stmt.setString(1, nome);
			stmt.setString(2, nif);

			System.out.println(query);
			long affectedRows = stmt.executeUpdate(); // 1

			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				cliente.setId(rs.getInt(1));
			}

//			if(affectedRows < 1)
//				throw new ();

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}
	}

	@Override
	public void update(Cliente cliente) throws UpdateNotExecutedException {

		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			Statement stmt = conn.createStatement();

			int id = cliente.getId();
			String nome = cliente.getNome();
			String nif = cliente.getNif();

			String query = "UPDATE " + table + " SET  nome = '" + nome + "', nif = '" + nif + "'" + " WHERE id = " + id;
			System.out.println(query);
			long affectedRows = stmt.executeUpdate(query); // 1

			if (affectedRows < 1) {
				throw new UpdateNotExecutedException("update cliente com id " + cliente.getId() + " não funciona");
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
				throw new DeleteNotExecutedException("Cliente com id " + id + " não foi apagado com sucesso");

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}

	}

	protected Cliente mapEntityToObject(ResultSet rs) throws SQLException {
		// Mapeamento entidadeBD -> Objeto Java

		int tempId = rs.getInt(1);
		String nome = rs.getString(2);
		String nif = rs.getString(3);

		Cliente cliente = new Cliente(tempId, nome, nif);

		return cliente;
	}
}