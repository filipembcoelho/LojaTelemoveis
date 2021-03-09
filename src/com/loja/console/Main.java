package com.loja.console;

import java.util.ArrayList;
import java.util.Scanner;

import com.loja.data.repo.ClienteRepository;
import com.loja.data.repo.ContatoRepository;
import com.loja.data.repo.ProdutoRepository;
import com.loja.exceptions.UpdateNotExecutedException;
import com.loja.model.Cliente;
import com.loja.model.Contato;
import com.loja.model.Produto;
import com.loja.model.TipoContato;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static ClienteRepository clienteRepo = new ClienteRepository();
	private static ProdutoRepository produtoRepo = new ProdutoRepository();
	private static ContatoRepository contactoRepo = new ContatoRepository();

	public static void main(String[] args) {

		System.out.println("Bem-vindo à nossa loja de telemóveis");

		boolean exit = false;
		do {
			System.out.println("1. Listar clientes");
			System.out.println("2. Listar produtos");
			System.out.println("3. Inserir produto");
			System.out.println("4. Inserir cliente");
			System.out.println("5. Pesquisar cliente por id");
			System.out.println("6. Atualizar cliente");
			System.out.println("7. Apagar cliente");
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
			case "6":
				actualizarCliente();
				break;
			case "7":
				apagarCliente();
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
		// iteração sobre uma lista de clientes

		// ir à BD buscar clientes

		// iterar sobre a list sysout(E)

		ArrayList<Cliente> clientes = clienteRepo.getAll();

		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i));
		}

	}

	public static void listarProdutos() {
		ArrayList<Produto> produtos = produtoRepo.getAll();

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

		clienteRepo.insert(c);

		// TODO: Criar método à parte
		System.out.println(c); // id

		// Adicionar contato
		Contato contato = new Contato("email@email.com", TipoContato.EMAIL, c.getId());

		c.getContactos().add(contato);

		contactoRepo.insert(contato);

	}

	private static Cliente pesquisarClientePorId() {
		System.out.print("Qual o id do cliente: ");
		int id = Integer.parseInt(scanner.nextLine());

		Cliente cliente = clienteRepo.getById(id);
		System.out.println(cliente);
		return cliente;
	}

	private static void actualizarCliente() {
		Cliente cliente = pesquisarClientePorId();

		boolean sair = false;
		do {
			System.out.println("1. Nome: " + cliente.getNome());
			System.out.println("2. Nif: " + cliente.getNif());
			System.out.print("O que pretende alterar: ");
			int option = Integer.parseInt(scanner.nextLine());

			switch (option) {
			case 1:
				System.out.print("Introduza o novo nome: ");
				String nome = scanner.nextLine();
				cliente.setNome(nome);
				break;
			case 2:
				System.out.print("Introduza o novo nif: ");
				String nif = scanner.nextLine();
				cliente.setNif(nif);
				break;
			}

			System.out.print("Pretende alterar mais alguma coisa (s/n): ");
			String choice = scanner.nextLine();

			if (choice.toLowerCase().equals("s")) {
				sair = false;
			} else {
				sair = true;
			}

		} while (!sair);
		try {
			clienteRepo.update(cliente);
		} catch (UpdateNotExecutedException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void apagarCliente() {
		Cliente cliente = pesquisarClientePorId();

		System.out.print("Tem a certeza que quer apagar este cliente (s/n)? ");
		String option = scanner.nextLine();

		if (option.toLowerCase().equals("s"))
			clienteRepo.delete(cliente.getId());

	}

}
