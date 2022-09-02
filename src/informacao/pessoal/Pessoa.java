package informacao.pessoal;

import utilitario.Data;

public class Pessoa {
	
	private String nome;
	private String sexo;
	private Data nascimento;
	private int idade;
	
	
	public Pessoa(String nome, String sexo, Data nascimento, int idade) {
		this.nome = nome;
		this.sexo = sexo;
		this.nascimento = nascimento;
		this.idade = idade;			
	}
	
	/*
	 * Data idade - data para calcular a idade no dia passado
	*/
	public Pessoa(String nome, String sexo, Data nascimento, String dataIdade) {
		this.nome = nome;
		this.sexo = sexo;
		this.nascimento = nascimento;
		//calcular a idade
		this.idade = calculaIdade(dataIdade, nascimento);			
	}


	public String getNome() {
		return nome;
	}
	public String getSexo() {
		return sexo;
	}
	public Data getNascimento() {
		return nascimento;
	}
	public int getIdade() {
		return idade;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setNascimento(Data nascimento) {
		this.nascimento = nascimento;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override	
	public String toString() {
		return "Pessoa [nome=" + nome + ", sexo=" + sexo + ", nascimento=" + nascimento + ", idade=" + idade + "]";
	}
	
	/*
	 * Calcula a idade da pessoa no dia da data passada
	 */
	public int calculaIdade(String dataIdade, Data nascimento)
	{
		//Separando a string data
		String[] data = dataIdade.split("/");
		int dia = Integer.parseInt(data[0]);
		int mes = Integer.parseInt(data[1]);
		int ano = Integer.parseInt(data[2]);
		
		//calcular a idade 
		int idade = ano - nascimento.getAno();
		
		if(mes > nascimento.getMes())
		{
			//já fez aniversário
			return idade;
		}
		else if (mes == nascimento.getMes())
		{
			if(dia >= nascimento.getDia())
			{
				//já fez aniversário
				return idade;
			}
		}
		return idade - 1;			
	}
}

