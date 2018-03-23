public class Jogador {
	private int pontuacao;
	private String nome;
	
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
	
	public void setPontuacao(int pont) {
		pontuacao = pont;
	}
	
	public void addPontuacao(int pont) {
		pontuacao += pont;
	}
}