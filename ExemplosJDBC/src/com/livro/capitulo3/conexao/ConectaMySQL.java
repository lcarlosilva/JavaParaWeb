package com.livro.capitulo3.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL {

	public static void main(String[] args) {
		Connection conexao = null;
		
		try {

			//Registrando a classe no JDBC no sistema em tempo de execucao
			String url = "jdbc:mysql://localhost/agenda";
			String usuario = "root";
			String senha = "";
			
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("CONECTOU!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro de SQL. ERRO: "+e.getMessage());
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar a conexao. ERRO: "+e.getMessage());
			}
		}
	}

}