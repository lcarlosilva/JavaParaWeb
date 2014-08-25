package com.livro.capitulo3.produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.produto.Produto;
import com.livro.capitulo3.util.HibernateUtil;

public class ProdutoDAO {

	private Session sessao;
	private Transaction transacao;
	
	//SALVAR
	public void salvar(Produto produto){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(produto);
			this.transacao.commit();
			System.out.println("Transação de salvar, concluida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir o produto. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar a operação de inserção! Erro:" + e.getMessage());
			}
		}
	}
	
	//ATUALIZAR
	public void atualizar(Produto produto) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(produto);
			this.transacao.commit();
			System.out.println("Produto atualizada com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o produto. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar a operação de atualização. Mensagem");
			}
		}
	}
	
	//EXCLUIR
	public void excluir(Produto produto) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(produto);
			this.transacao.commit();
			System.out.println("Produto removida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o produto. Erro: " + e.getMessage());
		} finally {
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar a operação de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}
	
	//BUSCAR PRODUTOS
	public Produto buscaProduto(Integer codigo) {
		
		Produto produto = null;
		
		
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			filtro.add(Restrictions.eq("categoria", codigo));
			produto = (Produto) filtro.uniqueResult();
			this.transacao.commit();
			System.out.println("Commitou a transação de busca.");
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de busca. Mensagem: " + e.getMessage());
			}
		}
		
		return produto;
	}
	
	//LISTAR
	public List<Produto> listar() {
		
		List<Produto> produtos = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			produtos = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de listagem. Mensagem: " + e.getMessage());
			}
		}
		
		return produtos;
	}
	
}
