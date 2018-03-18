import java.io.IOException;
import java.util.Random;

public class Montador {
	private Pergunta[] perguntas = new Pergunta[5];
	private Random gerador = new Random();
	
	public Montador() throws IOException {
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
			sb.append (String.valueOf (index));
			sb.append (".txt");
			String path = sb.toString();
			perguntas[i] = ManipuladorArquivo.leitor(path);
		}
	}
}
