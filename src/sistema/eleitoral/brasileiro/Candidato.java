package sistema.eleitoral.brasileiro;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.LinkedList;

import java.lang.Comparable;

import informacao.pessoal.Pessoa;
import utilitario.Data;

public class Candidato extends Pessoa implements Comparable<Candidato> {
	
	private String nomeUrna;
	private int numero;
	private int qtdVotos;
	private String situacao;
	private int numPartido;
	private Partido partido;
	
	public Candidato(String nome, String sexo, Data nascimento, String dataEleição, String nomeUrna, int numero, int qtdVotos, String situacao, int numPartido) 
	{
		super(nome, sexo, nascimento, dataEleição);
		this.nomeUrna = nomeUrna;
		this.numero = numero;
		this.qtdVotos = qtdVotos;
		this.situacao = situacao;
		this.numPartido = numPartido;
	}

	public String getNomeUrna() {
		return nomeUrna;
	}
	public int getNumero() {
		return numero;
	}
	public int getQtdVotos() {
		return qtdVotos;
	}
	public String getSituacao() {
		return situacao;
	}
	public int getNumPartido() {
		return numPartido;
	}
	public Partido getPartido() {
		return partido;
	}
	public void setNomeUrna(String nomeUrna) {
		this.nomeUrna = nomeUrna;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setQtdVotos(int qtdVotos) {
		this.qtdVotos = qtdVotos;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public void setNumPartido(int numPartido) {
		this.numPartido = numPartido;
	}
	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	
	@Override
	public String toString() {
		
		String voto = "voto";
		
		if(qtdVotos > 1)
		{
			voto +="s";	
		}
			return getNome() + " / " + nomeUrna + " (" + partido.getSigla() + ", " + qtdVotos + " " + voto + ")";
	}

	/*
	 * Abre um arquivo e faz uma leitura formatada dos candidados
	 * aloca os mesmos em memória e retorna uma lista com todos eles, além de fechar o arquivo
	 * Obs: a data da eleição é necessária para calcular a idade do candidato
	 * Situação : Testado
	 */
	public static List<Candidato> recebeCandidatos(String arquivo, String dataEleição){
		
		List<Candidato> listaCandidatos = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
			
			//estrutura para armazenar lista de candidatos
			listaCandidatos = new LinkedList<>();
			
			//le cabeçalho do arquivo
			String line = br.readLine();
			//ler primeira linha de informações
			line = br.readLine();
			
			while(line != null)
			{
				//recortar nas virgulas
				String[] vetor = line.split(",");
				
				int numero = Integer.parseInt(vetor[0]);
				int qtdVotos = Integer.parseInt(vetor[1]);	
				String situacao = vetor[2];
				String nome = vetor[3];
				String nomeUrna = vetor[4];
				String sexo = vetor[5];
				//separando a data
				String dataNascimento = vetor[6];
				String[] data = dataNascimento.split("/");
				int dia = Integer.parseInt(data[0]);
				int mes = Integer.parseInt(data[1]);
				int ano = Integer.parseInt(data[2]);
				String destino = vetor[7];
				int numPartido = Integer.parseInt(vetor[8]);
				
				//só destinos marcados como válido serão considerados para o armazenamento
				if(destino.equals("Válido"))
				{
				//aloca a data de nascimento 
				Data nascimento = new Data(dia, mes, ano);
				
				//alocando o candidato
				Candidato candidato = new Candidato(nome, sexo, nascimento, dataEleição, nomeUrna, numero, qtdVotos, situacao, numPartido);
				
				listaCandidatos.add(candidato);
				}
				
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
		for(Candidato candidato: listaCandidatos )
		{
			System.out.println(candidato);
		}*/	
		return listaCandidatos;
		
	}

	@Override
	public int compareTo(Candidato candidato) {
		if (this.qtdVotos == candidato.qtdVotos)
		{
			return candidato.getIdade() - this.getIdade();
		}
		else
		{
			return candidato.qtdVotos - this.qtdVotos;
		}
	}
}
