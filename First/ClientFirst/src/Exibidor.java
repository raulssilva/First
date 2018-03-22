import java.rmi.RemoteException;

public class Exibidor {
	private int index;
	private PerguntaInterf[] perguntas = new PerguntaInterf[5];
	
	public Exibidor(PerguntaInterf[] perguntas) {
		this.index = 0;
		this.perguntas = perguntas;
	}

	public PerguntaInterf[] getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(PerguntaInterf[] perguntas) {
		this.perguntas = perguntas;
	}
	
	public void mostrarPergunta() {
		try {
			System.out.println(perguntas[index].getEnunciado());
			
			for(int i = 0; i < 4; i++) {
				System.out.println((i + 1) +"- "+ perguntas[index].getAlternativas()[i]);
			}
			System.out.println("");
			System.out.print("Qual a resposta? ");
			
			index++;
		} catch (Exception e) {
			System.out.println("FirstClient exception: " + e);
		}
	}
	
	public void resultado(boolean result) {
		if(result) {
			System.out.println("Você acertou! :D");
		}else {
			System.out.println("Você errou! :(");
		}
	}
}