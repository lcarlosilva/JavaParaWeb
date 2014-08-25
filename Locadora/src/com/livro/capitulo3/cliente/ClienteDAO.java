package com.livro.capitulo3.cliente;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.midia.Midia;
import com.livro.capitulo3.util.HibernateUtil;

public class ClienteDAO {

	private Session sessao;
	private Transaction transacao;

	// SALVAR
	public void salvar(Cliente cliente) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(cliente);
			this.transacao.commit();
			System.out.println("Transação de salvar, concluida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir o cliente. Erro: " + e.getMessage());
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

	// ATUALIZAR
	public void atualizar(Cliente cliente) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(cliente);
			this.transacao.commit();
			System.out.println("Cliente atualizado com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o cliente. Erro: " + e.getMessage());
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
	public void excluir(Cliente cliente) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(cliente);
			this.transacao.commit();
			System.out.println("Cliente removida com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o cliente. Erro: " + e.getMessage());
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
	public Cliente buscaCliente(Integer codigo) {
		
		Cliente cliente = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			filtro.add(Restrictions.eq("cliente", codigo));
			cliente = (Cliente) filtro.uniqueResult();
			this.transacao.commit();
			System.out.println("Commintou a transação de busca.");
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
		
		return cliente;
	}
	
	//LISTAR
	public List<Cliente> listar() {
		
		List<Cliente> clientes = null;
		
		try {
			this.sessao = HibernateUtil.getSessionfactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Midia.class);
			clientes = filtro.list();
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
		
		return clientes;
	}
	
}
