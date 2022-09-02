package sistema.eleitoral.brasileiro;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import java.text.DecimalFormat;

public class Eleicao {

	private int qtdEleitos;
	private List<Candidato> candidatos;
	private List<Candidato> eleitos;
	private List<Partido> partidos;
	private int qtdVotosNominais;
	private int qtdVotosLegenda;
	private int qtdVotosValidos;
	private int qtdMulheresEleitas;
	private int qtdHomensEleitos;
	
	
	@Override
	public String toString() {
		return "Eleicao [qtdEleitos=" + qtdEleitos + ", candidatos=" + candidatos + ", eleitos=" + eleitos
				+ ", partidos=" + partidos + ", qtdVotosNominais=" + qtdVotosNominais + ", qtdVotosLegenda="
				+ qtdVotosLegenda + ", qtdVotosValidos=" + qtdVotosValidos + ", qtdMulheresEleitas="
				+ qtdMulheresEleitas + ", qtdHomensEleitos=" + qtdHomensEleitos + "]";
	}
	public int getQtdEleitos() {
		return qtdEleitos;
	}
	public List<Candidato> getCandidatos() {
		return candidatos;
	}
	public List<Candidato> getEleitos() {
		return eleitos;
	}
	public List<Partido> getPartidos() {
		return partidos;
	}
	public int getQtdVotosNominais() {
		return qtdVotosNominais;
	}
	public int getQtdVotosLegenda() {
		return qtdVotosLegenda;
	}
	public int getQtdVotosValidos() {
		return qtdVotosValidos;
	}
	public int getQtdMulheresEleitas() {
		return qtdMulheresEleitas;
	}
	public int getQtdHomensEleitos() {
		return qtdHomensEleitos;
	}
	public void setNumEleitos(int numEleitos) {
		this.qtdEleitos = numEleitos;
	}
	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	public void setEleitos(List<Candidato> eleitos) {
		this.eleitos = eleitos;
	}
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
	public void setQtdVotosNominais(int qtdVotosNominais) {
		this.qtdVotosNominais = qtdVotosNominais;
	}
	public void setQtdVotosLegenda(int qtdVotosLegenda) {
		this.qtdVotosLegenda = qtdVotosLegenda;
	}
	public void setQtdVotosValidos(int qtdVotosValidos) {
		this.qtdVotosValidos = qtdVotosValidos;
	}
	public void setQtdMulheresEleitas(int qtdMulheresEleitas) {
		this.qtdMulheresEleitas = qtdMulheresEleitas;
	}
	public void setQtdHomensEleitos(int qtdHomensEleitos) {
		this.qtdHomensEleitos = qtdHomensEleitos;
	}
	
	public Eleicao(List<Candidato> candidatos, List<Partido> partidos) {
		this.candidatos = candidatos;
		this.partidos = partidos;
		this.eleitos = new LinkedList<>();
	}
	
	/*
	 * Afilia/liga candidatos a seus partidos e vice-versa 
	 */
	public void afiliaCandidatosPartidos()
	{
		int numPartido = 0;
		Partido partido = null;
		
		for(Candidato candidato : this.candidatos)
		{
			//aproveitando que candidatos com o mesmo partido estão juntos
			//ganha eficiência, sem fazer disso uma regra
			if(numPartido != candidato.getNumPartido())
			{
				//procura o partido do afiliado
				for(Partido buscaPartido: this.partidos)
				{
					if(buscaPartido.getNumero() == candidato.getNumPartido())
					{
						//achou o partido do candidato
						partido = buscaPartido;
						numPartido = buscaPartido.getNumero();
						break;
					}
				}
			}
			
			//afilia
			candidato.setPartido(partido);
			partido.addCandidato(candidato);
		}
	}
	
	/*
	 *  Preenche todos os campos indicativos de quantidade referente a eleição e a lista de eleitos;
	 *  Aproveita para preencher 2 informações sobre resultado da eleicao no partido: "votoNominal" e "qtdEleitos";
	 */
	public void processaEleicao(){
		
		for(Candidato candidato : this.candidatos)
		{
			this.qtdVotosNominais += candidato.getQtdVotos(); 
			
			//votos nominais no partido respectivo
			(candidato.getPartido()).setAddVotoNominal(candidato.getQtdVotos());
		
			if(candidato.getSituacao().equals("Eleito"))
			{
				this.qtdEleitos ++;
				this.eleitos.add(candidato);
				
				//quantidade de eleitos pro partido
				candidato.getPartido().setAddQtdEleitos(1);
				
				if(candidato.getSexo().equals("F"))
				{
					this.qtdMulheresEleitas ++;
				}
				else if(candidato.getSexo().equals("M"))
				{
					this.qtdHomensEleitos ++;
				}
			}			
		}
		
		for(Partido partido : this.partidos) {
			this.qtdVotosLegenda += partido.getVotoLegenda();
		}
		
		this.qtdVotosValidos = this.qtdVotosLegenda + this.qtdVotosNominais;		
	}
	
	/*
	 * Imprime,em ordem natural, uma lista com os eleitos;
	 */
	public void imprimeListaEleitos()
	{	
		int i=0;
			
		//ordenar lista antes de imprimir 
		Collections.sort(this.eleitos);
			
		for(Candidato eleito: this.eleitos)
		{
			i++;
			System.out.println(i + " - " + eleito);
				
		}
	}
	
	/*
	 * Imprime, em ordem natural, os Tops N mais votados;
	 */
	public void imprimeTopVotados(int n)
	{
		//ordenar lista antes de imprimir 
		Collections.sort(this.candidatos);
		//OBS: PODE MELHORAR COM ORDENAÇÃO PARCIAL
			
		for(int i=0; i<n ; i++)
		{
			System.out.println(i+1 + " - " + this.candidatos.get(i));	
		}
	}
	
	/*
	 * Imprime, em ordem natural, candidatos não eleitos e que seriam eleitos se a votação fosse majoritária;
	 */
	public void imprimePrejudicadosPeloSistemaProporcional()
	{
		//ordenar lista antes de imprimir
		Collections.sort(this.candidatos);
		//OBS: PODE MELHORAR COM ORDENAÇÃO PARCIAL
		
		for(int i=0; i<this.qtdEleitos; i++)
		{
			String situacao = this.candidatos.get(i).getSituacao();
						
			if(situacao.equals("Não eleito") || situacao.equals("Suplente"))
			{
				System.out.println(i+1 + " - " + this.candidatos.get(i));
			}				
		}
	}
	
	/*
	 * Imprime, em ordem natural, lista de candidatos eleitos no sistema proporcional e que não seriam eleitos se a votação fosse majoritária
	 * 
	 */
	public void imprimeBeneficiadosPeloSistemaProporcional()
	{
		//ordenar lista antes de imprimir
		Collections.sort(this.candidatos);
		//OBS: PODE MELHORAR COM ORDENAÇÃO PARCIAL
		
		//pegar o "ponto de corte de votos" da eleição majoritária
		int qtdVotoCorte = this.candidatos.get(this.qtdEleitos-1).getQtdVotos();		
		
		for(Candidato eleito: this.eleitos)
		{				
			if(eleito.getQtdVotos() < qtdVotoCorte)
			{
				//foi beneficiado pelo sistema proporcional
				System.out.println(this.candidatos.indexOf(eleito)+1 + " - " + eleito);
			}				
		}
	}
	/*
	 * Imprime a estatística do partido, em ordem natural, com os votos totalizados por partido e número de candidatos eleitos;
	 */
	public void imprimeEstatisticasPartidos()
	{
		//ordena lista de partidos
		Collections.sort(this.partidos);
		
		int i=0;
		
		for(Partido partido: this.partidos)
		{
			System.out.println(++i + " - " + partido);
		}	
	}
	/*
	 * Imprime o peso do voto de legenda para o partido em ordem estabelecida pela classe VotoLegendaComparator;
	 */
	public void imprimePesoVotoLegenda(DecimalFormat df)
	{
		//ordenar partidos
		VotoLegendaComparator comparador = new VotoLegendaComparator();
		Collections.sort(this.partidos,comparador);
		
		 int i=0;
		 double percentual = 0;
		
		for(Partido partido: this.partidos)
		{
			if(partido.getVotoLegenda() + partido.getVotoNominal() != 0)
			{
				percentual = (partido.getVotoLegenda()*100.00)/(partido.getVotoLegenda() + partido.getVotoNominal());
			}
			else
			{
				percentual = 0;
			}

			
			System.out.print(++i + " - " + partido.getSigla() + " - " + partido.getNumero() + ", " + partido.getVotoLegenda() + " voto");
			
			if (partido.getVotoLegenda()>1)
			{
				System.out.print("s");
			}
			
			System.out.print(" de legenda ");
			
			if(percentual == 0)
			{
				System.out.println("(proporção não calculada, 0 voto no partido)");
			}
			else
			{
				System.out.println("(" + df.format(percentual) + "% do total do partido)");
			}
			
		}		
	}

	/*
	 * Imprime primeiro e último colocados de cada partido em ordem estabelecida pela classe VotoNominalUmCandidato
	 */
	public void imprimeMelhorPiorColocadoPartido()
	{
		//ordenar lista de candidados de cada partido
		for(Partido partido : this.partidos)
		{
			Collections.sort(partido.getCandidatos());			
		}
		
		//instanciar comparador 
		VotoNominalUmCandidatoComparator comparador = new VotoNominalUmCandidatoComparator();
		Collections.sort(this.partidos, comparador);
		
		int i=0;
		
		Candidato primeiro, ultimo;
		
		for(Partido partido: this.partidos)
		{
			if(partido.getVotoNominal() > 0)
			{
				System.out.print(++i + " - " + partido.getSigla() + " - " + partido.getNumero() + ", ");
								
				//pegar o primeiro / pegar o ultimo
				primeiro = (partido.getCandidatos()).get(0);
				ultimo = ((LinkedList<Candidato>) partido.getCandidatos()).getLast();
				
				System.out.print(primeiro.getNomeUrna() + " (" + primeiro.getNumero() + ", " + primeiro.getQtdVotos() + " voto");
				
				if(primeiro.getQtdVotos() > 1)
				{
					System.out.print("s");
				}
				
				System.out.print(") / " + ultimo.getNomeUrna() + " (" + ultimo.getNumero() + ", " + ultimo.getQtdVotos() + " voto");
				
				if(ultimo.getQtdVotos() > 1)
				{
					System.out.print("s");
				}
					System.out.println(")");
			}
		}
		
	}
	
	public void imprimePorcentagemEleitosFaixaEtaria(DecimalFormat df)
	{
		int faixaMenor30, faixa30, faixa40, faixa50, faixa60Mais;
		faixaMenor30 = faixa30 = faixa40 = faixa50 = faixa60Mais = 0;

		for(Candidato candidato: this.getEleitos())
		{
			int idade = candidato.getIdade();
			
			if(idade<30){faixaMenor30++;}
				else if(idade<40){faixa30++;}
					else if(idade<50){faixa40++;}
						else if(idade<60){faixa50++;}
							else {faixa60Mais++;}
		}
		
		double percentual = (faixaMenor30 * 100.0)/this.getQtdEleitos();
		System.out.println("      Idade < 30: " + faixaMenor30 + " (" + df.format(percentual) + "%)");
		
		percentual = (faixa30 * 100.0)/this.getQtdEleitos();
		System.out.println("30 <= Idade < 40: " + faixa30 + " (" + df.format(percentual) + "%)");
		
		percentual = (faixa40 * 100.0)/this.getQtdEleitos();
		System.out.println("40 <= Idade < 50: " + faixa40 + " (" + df.format(percentual) + "%)");

		percentual = (faixa50 * 100.0)/this.getQtdEleitos();
		System.out.println("50 <= Idade < 60: " + faixa50 + " (" + df.format(percentual) + "%)");
		
		percentual = (faixa60Mais * 100.0)/this.getQtdEleitos();
		System.out.println("60 <= Idade     : " + faixa60Mais + " (" + df.format(percentual) + "%)\n");		
	}
	
	public void imprimePorcentagemEleitosSexo(DecimalFormat df) {
		double percentual = (this.getQtdMulheresEleitas()*100.0)/this.getQtdEleitos();
		System.out.println("Feminino: " + this.getQtdMulheresEleitas() + " (" + df.format(percentual) + "%)");
		
		percentual = (this.getQtdHomensEleitos()*100.0)/this.getQtdEleitos();
		System.out.println("Masculino: " + this.getQtdHomensEleitos() + " (" + df.format(percentual) + "%)");
	}

	public void imprimePorcentagemRelacaoVotos(DecimalFormat df) {
		
		System.out.println("Total de votos válidos: " + this.getQtdVotosValidos());
		
		double percentual = (this.getQtdVotosNominais()*100.0)/this.getQtdVotosValidos();
		System.out.println("Total de votos nominais: " + this.getQtdVotosNominais() + " (" + df.format(percentual) + "%)");
		
		percentual = (this.getQtdVotosLegenda()*100.0)/this.getQtdVotosValidos();
		System.out.print("Total de votos de legenda: " + this.getQtdVotosLegenda() + " (" + df.format(percentual) + "%)");
	}
}
