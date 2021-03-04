package com.loja.model;

public class Cliente {

	private int id;
	private String nome;
	private String nif;

	public Cliente(String nome, String nif) {
		this.nome = nome;
		this.nif = nif;
	}

	public Cliente(int id, String nome, String nif) {
		this.id = id;
		this.nome = nome;
		this.nif = nif;
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

	@Override
	public String toString() {
		return "id=" + id + ", nome=" + nome + ", nif=" + nif;
	}

	
}