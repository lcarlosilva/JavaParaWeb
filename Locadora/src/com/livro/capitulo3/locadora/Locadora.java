package com.livro.capitulo3.locadora;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.livro.capitulo3.categoria.Categoria;
import com.livro.capitulo3.categoria.CategoriaDAO;
import com.livro.capitulo3.cliente.Cliente;
import com.livro.capitulo3.cliente.ClienteDAO;
import com.livro.capitulo3.endereco.Endereco;
import com.livro.capitulo3.endereco.EnderecoDAO;
import com.livro.capitulo3.filme.Filme;
import com.livro.capitulo3.filme.FilmeDAO;
import com.livro.capitulo3.locacao.Locacao;
import com.livro.capitulo3.locacao.LocacaoDAO;
import com.livro.capitulo3.midia.Midia;
import com.livro.capitulo3.midia.MidiaDAO;
import com.livro.capitulo3.util.HibernateUtil;

/**
 *
 * CLASSE DE TESTE PARA O SISTEMA DA LOCADORA 
 * 
 **/
public class Locadora {
	public static void main(String[] args) {
		
		HibernateUtil.getSessionfactory().openSession();
		
		Locadora locadora = new Locadora();
		locadora.cadastraCategorias();
		locadora.cadastraFilmes();
		locadora.cadastrarMidias();
		
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = new Endereco();
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		
		cliente.setCelular("(47) 1111-2222");
		cliente.setEmail("solaris@javapro.com.br");
		cliente.setNome("Fulano Solaris");
		
		cliente.setTelefone("(47) 3333-7777");
		cliente.setEndereco(endereco);
		
		endereco.setBairro("Centro");
		endereco.setCep("89000-000");
		endereco.setCidade("Joinville");
		endereco.setComplemento("casa");
		endereco.setNumero(new Integer(1));
		endereco.setRua("Av. Principal");
		endereco.setUf("SC");
		endereco.setCliente(cliente);
		
		clienteDAO.salvar(cliente);
		enderecoDAO.salvar(endereco);
		
		LocacaoDAO locacaoDAO = new LocacaoDAO();
		Locacao locacao = new Locacao();
		locacao.setDataDevolucao(new Date(System.currentTimeMillis()));
		locacao.setDataEmprestimo(new Date(System.currentTimeMillis()));
		locacao.setObservacao("Devolução de final de semana");
		locacao.setHoraEmprestimo(new Time(System.currentTimeMillis()));
		
		locacao.setCliente(cliente);
		
		MidiaDAO midiaDAO = new MidiaDAO();
		Midia midia = (Midia) midiaDAO.buscaMidia(new Integer(1));
		locacao.setMidia(midia);
		locacaoDAO.salvar(locacao);
		System.out.println("Cadastros gerados com sucesso.");
		
	}

	private void cadastraCategorias() {

		//CRIANDO AS CATEGORIAS DOS FILMES
		String categorias[] = {
				"Aventuras",
				"Ação",
				"Comédia"
		};
		
		Categoria categoria = null;
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		for (int i = 0; i < 3; i++) {
			categoria = new Categoria();
			categoria.setDescricao(categorias[i]);
			categoriaDAO.salvar(categoria);
		}
		
	}
	
	private void cadastraFilmes() {
		
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		String[] filmesDescricao = {
				"Senhor do Anéis",
				"Transformers",
				"GhostBusters"
		};
		
		/*AQUI SUBTRAÍMOS O ANO DE LANÇAMENTO DO FILME DE 1900,
		 *PARA GRAVARMOS O ANO CORRETO NO BANCO
		 *Date[] filmesAnoProducao = {
		 *		new Date(2001-1900,11,19),
		 *		new Date(2007-1900,6,20),
		 *		new Date(1985-1900,1,1),
		 *		
		 *};
		 */
		
		Date[] filmesAnoProducao = {
				new Date(2002),
				new Date(2012),
				new Date(1994),
				
		};
		
		FilmeDAO filmeDAO = new FilmeDAO();
		Filme filme = null;
		
		for (int i = 0; i < 3; i++) {
			filme = new Filme();
			filme.setDescricao(filmesDescricao[i]);
			filme.setAno(filmesAnoProducao[i]);
			filme.setCategoria(categoriaDAO.buscaCategoria(i+1));
			filmeDAO.salvar(filme);
			
		}
	}
	
	private void cadastrarMidias() {
		
		Midia midia = null;
		Filme filme = null;
		MidiaDAO midiaDAO = new MidiaDAO();
		FilmeDAO filmeDAO = new FilmeDAO();
		List<Filme> resultado = filmeDAO.listar();
		for (int i = 0; i < 3; i++) {
			midia = new Midia();
			filme = resultado.get(i);
			midia.setFilme(filme);
			midia.setInutilizada("N");
			midiaDAO.salvar(midia);
		}
	}

}
