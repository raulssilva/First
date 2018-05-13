package br.ufrn.rest;

import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/First") 
public class Main {
	
	public static int qtdJogadores = 2;
	public static int countMostrarPergunta = 0;
	public static boolean flagResponder = false;
	public static Vector<Jogador> jogadores = new Vector<Jogador>();
	public static Stack<Pergunta> perguntas;
	public static Jogador jogadorAtual; 
	
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
				System.out.println("Jogador " + jogador.getNome() + " adicionado!");
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
	@Path("/mostrarPergunta")
	public String mostrarPergunta() {
		countMostrarPergunta++;
		while(countMostrarPergunta != qtdJogadores) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String perguntaM = perguntas.peek().getEnunciado()
		+ "\n" + 
		"1) " + perguntas.peek().getAlternativa(0) + "\n" + 
		"2) " + perguntas.peek().getAlternativa(1) + "\n" + 
		"3) " + perguntas.peek().getAlternativa(2) + "\n" + 
		"4) " + perguntas.peek().getAlternativa(3);
		
		countMostrarPergunta = 0;
		flagResponder = false;
		
		return perguntaM;
	}

	@GET
	@Path("/aceitarResponder/{nome}")
	public String aceitarResponder(@PathParam("nome") String nome) {
		if(!flagResponder) {
			flagResponder = true;
			for (int i = 0; i < jogadores.size(); i++) {
				if(jogadores.elementAt(i).getNome().equals(nome)) {
					jogadorAtual = jogadores.elementAt(i);
				}
			}
			return "Qual a sua resposta? ";
		}else{
			return "Jogador " + jogadorAtual.getNome() + " escolheu responder primeiro!";
		}
	}
	
	@GET
	@Path("/responder")
	public String responder(@QueryParam("nome") String nome, @QueryParam("resposta") int resposta) {
		if(this.perguntas.pop().getRespostaCerta() == resposta-1) {
			for(int i=0; i < jogadores.size(); i++) {
				if(jogadores.elementAt(i).getNome().equals(nome)) {
					jogadores.elementAt(i).addPontuacao(1);
					break;
				}
			}
			return "Resposta certa!";
		}else {
			for(int i=0; i < jogadores.size(); i++) {
				if(!jogadores.elementAt(i).getNome().equals(nome)) {
					jogadores.elementAt(i).addPontuacao(1);
				}
			}
			return "Resposta errada!";
		}
	}
	
	@GET
	@Path("/resultado")
	public String resultado(@QueryParam("nome") String nome) {
			
		countMostrarPergunta++;
		while(countMostrarPergunta != qtdJogadores) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int pontuacao_maxima = 0; 
		for(int i = 0; i < jogadores.size(); i++) {
			if(pontuacao_maxima < jogadores.elementAt(i).getPontuacao()) {
				pontuacao_maxima = jogadores.elementAt(i).getPontuacao();
			}
		}
		
		int pontos = 0;
		
		for(int i=0; i < jogadores.size(); i++) {
			if(jogadores.elementAt(i).getNome().equals(nome)) {
				pontos = jogadores.elementAt(i).getPontuacao();
				break;
			}
		}
		if(pontos < pontuacao_maxima) {
			return "Sua pontuação foi " + pontos + " ponto(s). Você perdeu :(";
		}else {
			return "Sua pontuação foi " + pontos + " ponto(s). Você ganhou :D";
		}
	}

}
