package sistema.eleitoral.brasileiro;

import java.util.Comparator;

public class VotoNominalUmCandidatoComparator implements Comparator <Partido>{
	
	/*
	 *  Coloca em ordem decrescente de acordo com o total de votos nominais do candidato mais votado do partido. 
	 *  Em caso de empate, o com menor número partidário terá prioridade.
	 *  Lembrando que se dois candidatos tiverem o mesmo número de votos, o mais velho terá prioridade.
	 * Pré requisito: lista de candidatos de cada partido ordenada pela quantidade de votos (ordem natural) -> aumenta eficiência
	 */
	@Override
	public int compare(Partido partido, Partido outroPartido) {
		
		
		if(partido.getVotoNominal() == 0 && outroPartido.getVotoNominal() == 0)
		{
			//garantir ordenação estável
			return 0;
		}
		else if(outroPartido.getVotoNominal() == 0)
		{
			return -1;
		}
		else if(partido.getVotoNominal() == 0)
		{
			return 1;
		}
		else
		{
			if(partido.getCandidatos().get(0).getQtdVotos() == outroPartido.getCandidatos().get(0).getQtdVotos())
			{
				return partido.getNumero() - outroPartido.getNumero();
			}
			return outroPartido.getCandidatos().get(0).getQtdVotos() - partido.getCandidatos().get(0).getQtdVotos();
	
		}
	
		
	}	
	

}
