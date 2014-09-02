package financeiro.teste;

import java.util.Date;

import financeiro.usuario.Usuario;

public class Teste {

	public static void main(String[] args) {

		Usuario usrUmTeste = new Usuario();
		
		usrUmTeste.setAtivo(true);
		usrUmTeste.setCelular("1234");
		usrUmTeste.setCodigo(12);
		usrUmTeste.setNascimento(new Date(1487));
		usrUmTeste.setEmail("teste@teste");
		usrUmTeste.setIdioma("portugues");
		usrUmTeste.setLogin("testeLogin");
		usrUmTeste.setNome("teste nome");
		usrUmTeste.setSenha("123");
		
		Usuario usrDoisTeste = new Usuario();
		
		usrUmTeste.setAtivo(false);
		usrUmTeste.setCelular("1234");
		usrUmTeste.setCodigo(12);
		usrUmTeste.setNascimento(new Date(1487));
		usrUmTeste.setEmail("teste@teste");
		usrUmTeste.setIdioma("portugues");
		usrUmTeste.setLogin("testeLogin");
		usrUmTeste.setNome("teste nome");
		usrUmTeste.setSenha("123");
		
		//Testando o equals.
		if (usrUmTeste.equals(usrDoisTeste)) {
			System.out.println("sim objetos iguais");
		} else {
			System.out.println("nao objeto diferentes");
		}
		
		int retorno = usrUmTeste.hashCode();
		
		System.out.println(retorno);
		
	}

}
