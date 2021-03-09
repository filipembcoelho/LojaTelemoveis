package com.loja.data.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.loja.exceptions.InsertNotExecutedException;
import com.loja.exceptions.UpdateNotExecutedException;
import com.loja.model.Contato;

public class ContatoRepository extends Repository<Contato> {

	public ContatoRepository() {
		table = "contatos";
	}

	@Override
	public ArrayList<Contato> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contato getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Contato contato) throws InsertNotExecutedException {
		try (Connection conn = DriverManager.getConnection(finalUrl, username, password)) {

			String query = "INSERT INTO " + table + " (contato, tipocontato, cliente_id)" + " VALUES (?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			String value = contato.getValue();
			String tipoContacto = contato.getTipoContato().toString();
			int clienteId = contato.getCliente_id();

			stmt.setString(1, value);
			stmt.setString(2, tipoContacto);
			stmt.setInt(3, clienteId);

			System.out.println(query);
			long affectedRows = stmt.executeUpdate(); // 1

			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				contato.setId(rs.getInt(1));
			}

//			if(affectedRows < 1)
//				throw new ();

		} catch (SQLException e) {
			System.out.println("Erro a comunicar com BD: " + e.getMessage());
		}

	}

	@Override
	public void update(Contato obj) throws UpdateNotExecutedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Contato mapEntityToObject(ResultSet obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
