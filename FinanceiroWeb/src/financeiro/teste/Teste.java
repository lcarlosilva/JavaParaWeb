package financeiro.teste;

import java.util.Date;

import financeiro.usuario.Usuario;

public class Teste {

	public static void main(String[] args) {

		Usuario usrUmTeste = new Usuario();
		
		usrUmTeste.setAviso(true);
		usrUmTeste.setCelular("1234");
		usrUmTeste.setCodigo(12);
		usrUmTeste.setDataNacimento(new Date(1487));
		usrUmTeste.setEmail("teste@teste");
		usrUmTeste.setIdioma("portugues");
		usrUmTeste.setLogin("testeLogin");
		usrUmTeste.setNome("teste nome");
		usrUmTeste.setSenha("123");
		
		Usuario usrDoisTeste = new Usuario();
		
		usrUmTeste.setAviso(false);
		usrUmTeste.setCelular("1234");
		usrUmTeste.setCodigo(12);
		usrUmTeste.setDataNacimento(new Date(1487));
		usrUmTeste.setEmail("teste@teste");
		usrUmTeste.setIdioma("portugues");
		usrUmTeste.setLogin("testeLogin");
		usrUmTeste.setNome("teste nome");
		usrUmTeste.setSenha("123");
		
		if (usrUmTeste.equals(usrDoisTeste)) {
			System.out.println("sim objetos iguais");
		} else {
			System.out.println("nao objeto diferentes");
		}
		
	}

}
