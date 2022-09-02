package sistema.eleitoral.brasileiro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.lang.Comparable;

public class Partido implements Comparable<Partido>{
	
	private String nome;
	private int numero;
	private int votoLegenda;
	private String sigla;
	private int votoNominal;
	private List<Candidato> candidatos;
	private int qtdEleitos;
	
	public String getNome() {
		return nome;
	}
	public int getNumero() {
		return numero;
	}
	public int getVotoLegenda() {
		return votoLegenda;
	}
	public String getSigla() {
		return sigla;
	}
	public int getVotoNominal() {
		return votoNominal;
	}
	public List<Candidato> getCandidatos() {
		return candidatos;
	}
	public int getQtdEleitos() {
		return qtdEleitos;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setVotoLegenda(int votoLegenda) {
		this.votoLegenda = votoLegenda;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public void setVotoNominal(int votoNominal) {
		this.votoNominal = votoNominal;
	}
	public void setAddVotoNominal(int votoNominal) {
		this.votoNominal = this.votoNominal + votoNominal;
	}
	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	public void setAddQtdEleitos(int qtdEleitos) {
		this.qtdEleitos = this.qtdEleitos + qtdEleitos;
	}
	public void setQtdEleitos(int qtdEleitos) {
		this.qtdEleitos = qtdEleitos;
	}
	//só esse pacote pode adicionar um candidato a lista de candidatos de um partido
	void addCandidato(Candidato candidato) {
		this.candidatos.add(candidato);
	}
	
	public Partido(String nome, int numero, int votoLegenda, String sigla) {
		this.nome = nome;
		this.numero = numero;
		this.votoLegenda = votoLegenda;
		this.sigla = sigla;
		this.candidatos = new LinkedList<>();
	}
		
	@Override
	public String toString() {
		
		String voto = " voto";
		String nominal = " nominal";
		String candidatoEleito = " candidato eleito";
		
		if(votoNominal > 1)
		{
			nominal = " nominais";
			voto += "s"; 
		}
		else if (votoLegenda + votoNominal > 1)
		{
			voto += "s"; 

		}
		 if (qtdEleitos > 1)
		 {
			 candidatoEleito = " candidatos eleitos";
		 }
		
		
		return sigla + " - " + numero + ", " + (votoLegenda+votoNominal) + voto + " (" + votoNominal + nominal + " e " + votoLegenda + " de legenda), " + qtdEleitos + candidatoEleito;
	}
	
	/*
	 * Abre um arquivo e faz uma leitura formatada dos partidos
	 * aloca os mesmos em memória e retorna uma lista com todos eles
	 * além de fechar o arquivo
	 * Situação : testado
	 */
	public static List<Partido> recebePartidos(String arquivo){
		
		List<Partido> listaPartidos = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
			
			//estrutura para armazenar lista de candidatos
			listaPartidos = new LinkedList<>();
			
			//le cabeçalho do arquivo
			String line = br.readLine();
			//ler primeira linha de informações
			line = br.readLine();
			
			while(line != null)
			{
				//recortar nas virgulas
				String[] vetor = line.split(",");
				
				int numero = Integer.parseInt(vetor[0]);
				int votoLegenda = Integer.parseInt(vetor[1]);	
				String nome = vetor[2];
				String sigla = vetor[3];
															
				//alocando o partido
				Partido partido = new Partido(nome, numero, votoLegenda, sigla);
				
				listaPartidos.add(partido);
				
				line = br.readLine();
			}			
		} catch (FileNotFoundException e) {
			System.out.println("Falha na abertura do arquivo:" + arquivo);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		} 		
		
		//teste de armazenamento - ok
		/*
		for(Partido partido: listaPartidos )
		{
			System.out.println(partido);
		}*/
		
		return listaPartidos;
	}
	
	
	@Override
	public int compareTo(Partido partido) {
		if((this.votoLegenda+this.votoNominal) == (partido.votoLegenda+partido.votoNominal))
		{
			return this.numero - partido.numero;
		}
		else
		{
			return (partido.votoLegenda+partido.votoNominal)-(this.votoLegenda+this.votoNominal);
		}
	}	
}




