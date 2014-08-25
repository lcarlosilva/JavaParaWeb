package com.livro.capitulo3.locadora;

import java.sql.Date;

import com.livro.capitulo3.categoria.Categoria;
import com.livro.capitulo3.categoria.CategoriaDAO;
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
		
	}

	private void cadastraFilmes() {
		
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		String[] filmesDescricao = {
				"Senhor do Anéis",
				"Transformers",
				"GhostBusters"
		};
		
		//AQUI SUBTRAÍMOS O ANO DE LANÇAMENTO DO FILME DE 1900,
		//PARA GRAVARMOS O ANO CORRETO NO BANCO
		Date[] filmesAnoProducao = {
				new Date(2001-1900, 11, 19),
				new Date(2007-1900, 6, 20),
				new Date(1985-1900, 1, 1),
				
		};
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

}
