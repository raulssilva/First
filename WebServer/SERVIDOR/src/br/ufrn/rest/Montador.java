package br.ufrn.rest;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class Montador {
	private Stack<Pergunta> perguntas;
	private Random gerador = new Random();
	
	public Montador() throws IOException {
		perguntas = new Stack<Pergunta>();
		int[] perguntasLidas = new int[5];
		for (int i = 0; i < 5; i++) {
			int index = gerador.nextInt(12);
			index++;
			for(int j = 0; j < 5; j++) {
				while(perguntasLidas[j] == index) {
					index++;
					if(index > 12) {
						index = 1;
					}
				}
			}
			perguntasLidas[i] = index;	
			StringBuilder sb = new StringBuilder ("Perguntas/pergunta");
//			StringBuilder sb = new StringBuilder ("/Users/raulsilveirasilva/Documents/UFRN/ProgramacÌ§aÌƒo DistribuiÌ�da/First/WebServer/SERVIDOR/Perguntas/pergunta");
//			StringBuilder sb = new StringBuilder ("/home/petcc07/Documentos/Deba/First/WebServer/SERVIDOR/Perguntas/pergunta");
			sb.append (String.valueOf (index));
			sb.append (".txt");
			String path = sb.toString();
			perguntas.push(ManipuladorArquivo.leitor(path));
		}
	}
	
	public Stack<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(Stack<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
}
