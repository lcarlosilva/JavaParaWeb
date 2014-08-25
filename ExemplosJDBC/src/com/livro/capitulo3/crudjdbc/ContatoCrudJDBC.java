package com.livro.capitulo3.crudjdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContatoCrudJDBC {

	public void salvar(Contato contato) {
		
		//obtenho uma conexao com o banco
		Connection conexao = this.geraConexao();
		PreparedStatement insereSt = null;
		
		//monto uma instrução SQL
		String sql = "insert into contato(nome, telefone,email,dt_cad,obs) "
				   + "values (?, ?, ?, ?, ?)";

		try {
			
			//preparacao do SQL
			insereSt = conexao.prepareStatement(sql);
			
			//seto todos os parametros do contato,
			//que será inserido no banco, na tabela de contatos.
			insereSt.setString(1, contato.getNome());
			insereSt.setString(2, contato.getTelefone());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, contato.getDataCadastro());
			insereSt.setString(5, contato.getObservacao());
			
			//executa o comando insert.
			insereSt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao incluir o contato. Mensagem: " + e.getMessage());
		} finally {
			try {
				insereSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operacoes de insercao. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Contato contato) {
	}

	public void excluir(Contato contato) {
	}
	
	public Contato buscaContato(int valor) {
		
		//obtenho uma conexao com o banco.
		Connection conexao = this.geraConexao();
		Contato contato = null;
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		
		String sql = "select * from contato where codigo = ?";
		
		try {
			
			//consulta recebe uma conexao que tem uma instrucao SQL.
			consulta = conexao.prepareStatement(sql);
			
			//consulta seta o valor que representa o codigo 
			//que faz referencia ao do contato que será recuperado. 
			consulta.setInt(1, valor);
			
			//recebe o resultado da consulta, que foi executada.
			resultado = consulta.executeQuery(sql);
			
			contato = new Contato();
			contato.setCodigo(new Integer(resultado.getInt("codigo")));
			contato.setNome(resultado.getString("nome"));
			contato.setTelefone(resultado.getString("telefone"));
			contato.setEmail(resultado.getString("email"));
			contato.setDataCadastro(resultado.getDate("dt_cad"));
			contato.setObservacao(resultado.getString("obs"));
			
		} catch (SQLException e) {
			System.out.println("Erro buscar contato. Mensagem: " + e.getMessage());
		} finally {
			try {
				conexao.close();
				consulta.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar as operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		
		return contato;
	}

	public List<Contato> listar() {

		Connection conexao = this.geraConexao();
		List<Contato> contatos = new ArrayList<Contato>();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;

		String sql = "select * from contato";

		try {

			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);

			while (resultado.next()) {

				contato = new Contato();
				contato.setCodigo(new Integer(resultado.getInt("codigo")));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("telefone"));
				contato.setDataCadastro(resultado.getDate("dt_cad"));
				contato.setObservacao(resultado.getString("obs"));

				contatos.add(contato);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar o codigo do contato. Mensagem: "+ e.getMessage());
		} finally {
			try {

				// Tento fechar todas as conexoes
				consulta.close();
				resultado.close();
				conexao.close();

			} catch (Throwable e) {
				System.out.println("Erro ao fechar operacoes de consulta, Mensagem: " + e.getMessage());
			}
		}
		return contatos;
	}

	public Connection geraConexao() {
		
		String url = "jdbc:mysql://localhost/ibere";
		String usuario = "root";
		String senha = "";

		Connection conexao = null;
		
		try {
			conexao = DriverManager.getConnection(url,usuario, senha);
			System.out.println("Conectou!");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar. Mensagem: " + e.getMessage());
		}
		
		return conexao;
	}

	public static void main(String[] args) {
		ContatoCrudJDBC contatoCRUDJDBC = new ContatoCrudJDBC();
		
		//criando um contato
		Contato beltrano = new Contato();
		beltrano.setNome("Beltrano Solar");
		beltrano.setTelefone("(47)555-3333");
		beltrano.setEmail("beltrano@teste.com.br");
		beltrano.setDataCadastro(new Date(System.currentTimeMillis()));
		beltrano.setObservacao("novo cliente");
		
		contatoCRUDJDBC.salvar(beltrano);
		
		//criando um segundo contato
		Contato fulano = new Contato();
		fulano.setNome("Fulano Lunar");
		fulano.setTelefone("(47)777-2222");
		fulano.setEmail("fulano@teste.com.br");
		fulano.setDataCadastro(new Date(System.currentTimeMillis()));
		fulano.setObservacao("Novo contato - possivel cliente");
		
		contatoCRUDJDBC.salvar(fulano);
		
		System.out.println("Contatos cadastrados. " + contatoCRUDJDBC.listar().size());
		
	}
}
