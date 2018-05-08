
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
 
public class ManipuladorArquivo {
 
    public static Pergunta leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        Pergunta p = new Pergunta();
        for(int i = 0; i < 6; i++) {
        	linha = buffRead.readLine();
            if (linha != null) {
            	switch (i) {
				case 0:
					p.setEnunciado(linha);
					break;
				case 5:
					p.setRespostaCerta(Integer.parseInt(linha));
					break;
				default:
					p.addAlternativa(linha, i-1);
					break;
				}
            } else
                break;
            
        }
        buffRead.close();
        return p;
    }
 
    public static void escritor(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Escreva algo: ");
        linha = in.nextLine();
        buffWrite.append(linha + "\n");
        buffWrite.close();
        in.close();
    }
 
}
