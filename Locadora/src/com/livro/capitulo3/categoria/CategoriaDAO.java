package com.livro.capitulo3.categoria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.util.HibernateUtil;

public class CategoriaDAO {

	private Session sessao;
	private Transaction transacao;
	
	//SALVAR
	public void salvar(Categoria categoria){
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(categoria);
			this.transacao.commit();
			System.out.println("Transação de salvar, concluida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir a categoria. Erro: " + e.getMessage());
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
	public void atualizar(Categoria categoria) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(categoria);
			this.transacao.commit();
			System.out.println("Categoria atualizada com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a categoria. Erro: " + e.getMessage());
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
	public void excluir(Categoria categoria) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(categoria);
			this.transacao.commit();
			System.out.println("Categoria removida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a categoria. Erro: " + e.getMessage());
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
	
	//BUSCAR CATEGORIAS
	public Categoria buscaCategoria(Integer codigo) {
		
		Categoria categoria = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Categoria.class);
			filtro.add(Restrictions.eq("categoria", codigo));
			categoria = (Categoria) filtro.uniqueResult();
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
		
		return categoria;
	}
	
	//LISTAR
	public List<Categoria> listar() {
		
		List<Categoria> categorias = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Categoria.class);
			categorias = filtro.list();
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
		
		return categorias;
	}
	
}