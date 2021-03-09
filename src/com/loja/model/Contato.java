package com.loja.model;

public class Contato {

	private int id;
	private String value;
	private TipoContato tipoContato;
	private int cliente_id;

	public Contato(String value, TipoContato tipoContato) {
		this.value = value;
		this.tipoContato = tipoContato;
	}

	public Contato(int id, String value, TipoContato tipoContato) {
		this(value, tipoContato);
		this.id = id;
	}

	public Contato(String value, TipoContato tipoContato, int cliente_id) {
		this(value, tipoContato);
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public int getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}

}