package sistema.eleitoral.brasileiro;

import java.util.List;
import java.text.DecimalFormat;

public class DadosEleicoes {

	public static void main(String[] args) {
		
		Eleicao eleicao2020 = null;
		
		try {
			
			//abrir o arquivo e ler as informações dos candidatos
			List<Candidato> listaCandidatos = Candidato.recebeCandidatos(args[0],args[2]);
			
			//abrir o arquivo e ler as informações dos partidos do arquivo
			List<Partido> listaPartidos = Partido.recebePartidos(args[1]);
							
			//instanciar eleição
			eleicao2020 = new Eleicao(listaCandidatos, listaPartidos);
			

		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Erro na passagem de argumentos por linha de comando");
			System.out.println(e.getMessage());
		}
				
		// afiliar/ligar partidos aos candidatos e vice-versa
		eleicao2020.afiliaCandidatosPartidos();

		//preencher os demais dados sobre as eleições de 2020
		eleicao2020.processaEleicao();
		
		
		//1 - Numero de vagas
		relatorio1 (eleicao2020);
		
		//2 - Candidatos eleitos
		relatorio2 (eleicao2020);
		
		//3 - Candidatos mais votados dentro do número de vagas
		relatorio3 (eleicao2020);
		
		//4 - Candidatos não eleitos e que seriam eleitos se a votação fosse majoritária
		relatorio4 (eleicao2020);
		
		//5 - Candidatos eleitos no sistema proporcional vigente, e que não seriam eleitos se a votação fosse majoritária;
		relatorio5 (eleicao2020);
		
		//6 - Votos totalizados por partido e número de candidatos eleitos
		relatorio6(eleicao2020);

		//instanciar para arredondar os cálculos de porcentagem
		DecimalFormat df = new DecimalFormat("0.00");
				
		//7 - Votos de legenda (com a porcentagem destes votos frente ao total de votos no partido);
		relatorio7 (eleicao2020,df);
		
		//8 - Primeiro e último colocados de cada partido.
		relatorio8 (eleicao2020);
		
		//9 - Distribuição de eleitos por faixa etária, considerando a idade do candidato no dia da eleição;
		relatorio9 (eleicao2020,df);
		
		//10 - Distribuição de eleitos por sexo;
		relatorio10 (eleicao2020, df);
		
		//11 - Total de votos, total de votos nominais e total de votos de legenda.
		relatorio11 (eleicao2020, df);

	}
	
	static void relatorio1 (Eleicao eleicao)
	{
		System.out.println("Número de vagas: " + eleicao.getQtdEleitos() + "\n");
	}
	
	static void relatorio2 (Eleicao eleicao)
	{
		System.out.println("Vereadores eleitos:");
		eleicao.imprimeListaEleitos();
		System.out.println();	
	}
	
	static void relatorio3 (Eleicao eleicao)
	{
		System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
		eleicao.imprimeTopVotados(eleicao.getQtdEleitos());		
		System.out.println();	
	}

	static void relatorio4 (Eleicao eleicao)
	{
		System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n" + "(com sua posição no ranking de mais votados)");
		eleicao.imprimePrejudicadosPeloSistemaProporcional();	
		System.out.println();	
	}

	static void relatorio5 (Eleicao eleicao) {
		
		System.out.println("Eleitos, que se beneficiaram do sistema proporcional:\n" + "(com sua posição no ranking de mais votados)");
		//havia forma mais eficiente de fazer se fosse permitida a correlação de relatorios
		eleicao.imprimeBeneficiadosPeloSistemaProporcional();
		System.out.println();	
	}

	static void relatorio6 (Eleicao eleicao) {
		System.out.println("Votação dos partidos e número de candidatos eleitos:");
		eleicao.imprimeEstatisticasPartidos();
		System.out.println();	
	}

	static void relatorio7 (Eleicao eleicao, DecimalFormat df) {
		System.out.println("Votação dos partidos (apenas votos de legenda):");
		eleicao.imprimePesoVotoLegenda(df);
		System.out.println();		
	}
		
	static void relatorio8 (Eleicao eleicao)
	{
		System.out.println("Primeiro e último colocados de cada partido:");
		eleicao.imprimeMelhorPiorColocadoPartido();
		System.out.println();		
	}
	
	static void relatorio9 (Eleicao eleicao, DecimalFormat df) {
	
		System.out.println("Eleitos, por faixa etária (na data da eleição):");
		eleicao.imprimePorcentagemEleitosFaixaEtaria(df);
		
	}
	
	static void relatorio10 (Eleicao eleicao, DecimalFormat df) {
		System.out.println("Eleitos, por sexo:");
		eleicao.imprimePorcentagemEleitosSexo(df);	
		System.out.println();
	}
	
	static void relatorio11 (Eleicao eleicao, DecimalFormat df) {
		eleicao.imprimePorcentagemRelacaoVotos(df);
	}
}
