package com.livro.capitulo3.util;

public class Teste {

	public static void main(String[] args) {
		
		FabricaDeComponentes f = new FabricaDeComponentes.Build()
										.setNome("")
										.setSobrenome("")
										.contruir();
		
	}

}
