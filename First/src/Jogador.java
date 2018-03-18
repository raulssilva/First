
public class Jogador {
	private int pontuacao;
	private int id;
	
	public Jogador(int id_) {
		id = id_;
		pontuacao = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id_) {
		id = id_;
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
