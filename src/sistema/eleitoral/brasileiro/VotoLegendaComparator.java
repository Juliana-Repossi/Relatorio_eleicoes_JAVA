package sistema.eleitoral.brasileiro;

import java.util.Comparator;

public class VotoLegendaComparator implements Comparator <Partido>{

	/*
	 * Coloca em ordem decrescente de acordo com o número de votos de legenda do partido.
	 *  Em caso de empate, o partido com mais votos nominais terá prioridade. 
	 *  Em caso de novo empate, o com menor número partidário terá prioridade.
	 */
	@Override
	public int compare(Partido partido, Partido outroPartido) {
		if(partido.getVotoLegenda() == outroPartido.getVotoLegenda())
		{
			if(partido.getVotoNominal() == outroPartido.getVotoLegenda())
			{
				return partido.getNumero() - outroPartido.getNumero();
			}
			else
			{
				return outroPartido.getVotoNominal() - partido.getVotoNominal();
			}
		}
		return outroPartido.getVotoLegenda() - partido.getVotoLegenda();
	}	
}