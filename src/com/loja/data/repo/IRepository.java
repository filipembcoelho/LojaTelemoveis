package com.loja.data.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.loja.exceptions.InsertNotExecutedException;
import com.loja.exceptions.UpdateNotExecutedException;

public interface IRepository<T> {

	public abstract ArrayList<T> getAll(); // List de entidades ex: SELECT * FROM produtos;

	T getById(int id); // Entidade SELECT * FROM produtos WHERE id = 1

	void insert(T obj) throws InsertNotExecutedException; // Inserir -> create INSERT

	void update(T obj) throws UpdateNotExecutedException; // Update

	void delete(int id); // delete FROM produtos WHERE id = 1

	
}
