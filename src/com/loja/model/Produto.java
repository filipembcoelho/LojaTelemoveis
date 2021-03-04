package com.loja.model;

public class Produto {

	private int id;
	private String nome;
	private double preco;
	private int stock;

	public Produto(String nome, double preco, int stock) {
		this.nome = nome;
		this.preco = preco;
		this.stock = stock;
	}

	public Produto(int id, String nome, double preco, int stock) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.stock = stock;
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nome=" + nome + ", preco=" + preco + ", stock=" + stock;
	}

}
