package com.loja.model;

import java.util.ArrayList;

public class Cliente {

	private int id;
	private String nome;
	private String nif;
	private ArrayList<Contato> contactos;

	public Cliente(String nome, String nif) {
		contactos = new ArrayList<>();
		this.nome = nome;
		this.nif = nif;
	}

	public Cliente(int id, String nome, String nif) {
		this(nome, nif);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public ArrayList<Contato> getContactos() {
		return contactos;
	}

	public void setContactos(ArrayList<Contato> contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nome=" + nome + ", nif=" + nif;
	}

}