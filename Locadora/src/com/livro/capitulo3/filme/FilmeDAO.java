package com.livro.capitulo3.filme;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.filme.Filme;
import com.livro.capitulo3.util.HibernateUtil;


public class FilmeDAO { 

	private Session sessao;
	private Transaction transacao;
	
	//SALVAR
	public void salvar(Filme filme){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(filme);
			this.transacao.commit();
			System.out.println("Transação de salvar, concluida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir a filme. Erro: " + e.getMessage());
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
	public void atualizar(Filme filme) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(filme);
			this.transacao.commit();
			System.out.println("Filme atualizado com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a filme. Erro: " + e.getMessage());
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
	public void excluir(Filme filme) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(filme);
			this.transacao.commit();
			System.out.println("Filme removido com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a filme. Erro: " + e.getMessage());
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
	
	//BUSCAR FILMES
	public Filme buscaFilme(Integer codigo) {
		
		Filme filmes = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Filme.class);
			filtro.add(Restrictions.eq("filme", codigo));
			filmes = (Filme) filtro.uniqueResult();
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
		
		return filmes;
	}
	
	//LISTAR
	public List<Filme> listar() {
		
		List<Filme> filmes = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Filme.class);
			filmes = filtro.list();
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
		
		return filmes;
	}
	
}
