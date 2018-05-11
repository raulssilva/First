package br.ufrn.rest;

import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/First") 
public class Main {
	
	public static int qtdJogadores = 2;
	public static int countMostrarPrimeiraPergunta = 0;
	public static Vector<Jogador> jogadores = new Vector<Jogador>();
	public static Stack<Pergunta> perguntas;
	
	@GET
	@Path("/start/")
	public String start() throws IOException {
		if(jogadores.size() == 0) {
			Montador m = new Montador();
			this.perguntas = m.getPerguntas();
		}
		return "";
	}
	
	
	@GET
	@Path("/init/{nome}")
	public String init(@PathParam("nome") String nome) throws IOException  {
		
		if(jogadores.size()== 0) {
			jogadores = new Vector<Jogador>();
			Jogador jogador = new Jogador(nome);
			jogadores.addElement(jogador);
			
			System.out.println("Jogador " + jogador.getNome() + " adicionado!");
			return "Defina a quantidade de jogadores";
		}else {
			if(jogadores.size() < qtdJogadores) {
				Jogador jogador = new Jogador(nome);
				jogadores.addElement(jogador);
				return "Aguardando os outros jogadores";
			}
			return "Sala Cheia";
		}
		
	}
	
	@GET
	@Path("/definirQtd/{qtd}")
	public String definirQtd(@PathParam("qtd") int qtd) {
		qtdJogadores = qtd;
		return "Aguardando os outros jogadores";
	}
	
	@GET
	@Path("/mostrarPrimeiraPergunta")
	public String mostrarPrimeiraPergunta() {
		countMostrarPrimeiraPergunta++;
		while(countMostrarPrimeiraPergunta != qtdJogadores) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		String perguntaM = this.perguntas.peek().getEnunciado()
		+ "\n" + 
		"1) " + perguntas.peek().getAlternativa(0) + "\n" + 
		"2) " + perguntas.peek().getAlternativa(1) + "\n" + 
		"3) " + perguntas.peek().getAlternativa(2) + "\n" + 
		"4) " + perguntas.peek().getAlternativa(3);
		
		return perguntaM;
	}
	

}
