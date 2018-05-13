package br.ufrn.rest;

public class Jogador {
	
	String nome;
	int pontuacao;
	
	public Jogador(String nome) {
		this.nome = nome;
		this.pontuacao = 0;	
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public void addPontuacao(int pontuacao) {
		this.pontuacao += pontuacao;
	}

}
