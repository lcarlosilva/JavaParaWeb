package com.livro.capitulo3.locacao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.locacao.Locacao;
import com.livro.capitulo3.util.HibernateUtil;

public class LocacaoDAO {
	
	private Session sessao;
	private Transaction transacao;
	
	//SALVAR
	public void salvar(Locacao locacao){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(locacao);
			this.transacao.commit();
			System.out.println("Transação de salvar, concluida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir a Locação. Erro: " + e.getMessage());
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
	public void atualizar(Locacao locacao) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(locacao);
			this.transacao.commit();
			System.out.println("Categoria atualizada com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a Locação. Erro: " + e.getMessage());
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
	public void excluir(Locacao locacao) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(locacao);
			this.transacao.commit();
			System.out.println("Locação removida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a Locação. Erro: " + e.getMessage());
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
	
	//BUSCAR LOCAÇÕES
	public Locacao buscaLocacoes(Integer codigo) {
		
		Locacao locacao = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Locacao.class);
			filtro.add(Restrictions.eq("categoria", codigo));
			locacao = (Locacao) filtro.uniqueResult();
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
		
		return locacao;
	}
	
	//LISTAR
	public List<Locacao> listar() {
		
		List<Locacao> locacoes = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Locacao.class);
			locacoes = filtro.list();
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
		
		return locacoes;
	}
	
}
