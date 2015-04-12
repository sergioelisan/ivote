package br.ufrpe.bcc.ivote.data;

import java.util.Objects;

public class Chapa {
	
	private int id = 1;
	private String nome = "Branco";
	private int votos = 0;

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getVotos() {
		return this.votos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public int hashCode() {
		int hash = 3;
		hash = 41 * hash + this.id;
		hash = 41 * hash + Objects.hashCode(this.nome);
		return hash;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Chapa other = (Chapa) obj;
		if (this.id != other.id) {
			return false;
		}
		if (!Objects.equals(this.nome, other.nome)) {
			return false;
		}
		return true;
	}
}