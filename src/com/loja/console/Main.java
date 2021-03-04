package com.loja.console;

import java.util.ArrayList;
import java.util.Scanner;

import com.loja.data.Data;
import com.loja.model.Cliente;
import com.loja.model.Produto;

public class Main {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Bem-vindo à nossa loja de telemóveis");

		boolean exit = false;
		do {
			System.out.println("1. Listar clientes");
			System.out.println("2. Listar produtos");
			System.out.println("3. Inserir produto");
			System.out.println("4. Inserir cliente");
			System.out.println("5. Pesquisar cliente por id");
			System.out.println("0. Sair");
			System.out.print("Escolha a opção: ");
			String option = scanner.nextLine();

			switch (option) {
			case "1":
				listarClientes();
				break;
			case "2":
				listarProdutos();
				break;
			case "3":
				inserirProduto();
				break;
			case "4":
				inserirCliente();
				break;
			case "5":
				pesquisarClientePorId();
				break;
			case "0":
				System.out.println("Obrigado pela visita");
				exit = true;
				break;
			default:
				System.out.println("Opção errada");
				break;
			}
		} while (!exit);

	}

	public static void listarClientes() {
		ArrayList<Cliente> clientes = Data.getAllClientes();

		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i));
		}

	}

	public static void listarProdutos() {
		ArrayList<Produto> produtos = Data.getAllProdutos();

		for (int i = 0; i < produtos.size(); i++) {
			System.out.println(produtos.get(i));
		}
	}

	private static void inserirProduto() {

	}

	private static void inserirCliente() {

		System.out.print("Introduza o seu nome: ");
		String nome = scanner.nextLine();

		System.out.print("Introduza o seu nif: ");
		String nif = scanner.nextLine();

		Cliente c = new Cliente(nome, nif);

		Data.inserirCliente(c);
	}

	private static void pesquisarClientePorId() {
		// TODO Auto-generated method stub

	}

}
