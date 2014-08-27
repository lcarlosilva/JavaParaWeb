package com.livro.capitulo3.util;


public class FabricaDeComponentes {

	private String nome;
	private String sobrenome;
	private String nomeMae;
	private String nomePai;
	
	public FabricaDeComponentes(Build nomeBuild) {
		
		this.nome = nomeBuild.Bnome;
		this.sobrenome = nomeBuild.Bsobrenome;
		
	}
	
	static class Build{
		
		private String Bnome; 
		private String Bsobrenome; 
		
		public Build setNome(String n){
			this.Bnome = n;
			return this;
		}
		public Build setSobrenome(String n){
			this.Bsobrenome = n;
			return this;
		}
		
		public FabricaDeComponentes contruir(){
			return new FabricaDeComponentes(this);
		}

	}

}
