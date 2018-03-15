public class Pergunta {
	private String enunciado;
	private String[] alternativas = new String[4];
	private int respostaCerta;
	private int valor;
	
	public Pergunta() {
		valor = 1;
	}
	
	public Pergunta(String enunciado, String[] alternativas, int respostaCerta) {
		this.enunciado = enunciado;
		this.alternativas = alternativas;
		this.respostaCerta = respostaCerta;
		this.valor = 1;
	}
	
	public Pergunta(String enunciado, String[] alternativas, int respostaCerta, int valor) {
		this.enunciado = enunciado;
		this.alternativas = alternativas;
		this.respostaCerta = respostaCerta;
		this.valor = valor;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String[] getAlternativas() {
		return alternativas;
	}
	
	public String getAlternativa(int index) {
		return alternativas[index];
	}

	public void setAlternativas(String[] alternativas) {
		this.alternativas = alternativas;
	}
	
	public void addAlternativa(String alternativa, int index) {
		this.alternativas[index] = alternativa;
	}

	public int getRespostaCerta() {
		return respostaCerta;
	}

	public void setRespostaCerta(int respostaCerta) {
		this.respostaCerta = respostaCerta;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}