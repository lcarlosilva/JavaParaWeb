package com.livro.capitulo3.crudxml;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.livro.capitulo3.conexao.HibernateUtil;
import com.livro.capitulo3.crudannotations.ContatoAnnotations;

public class ContatoCrudXML {

	// SALVAR
	public void salvar(ContatoAnnotations contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(contato);
			transacao.commit();//gera a intrução de commit no banco, confirmando a transação
			System.out.println("Contato(s) salvao com sucesso.");
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir o contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operacao de inserção. Mensagem: " + e.getMessage());
			}
		}
	}

	// ATUALIZAR
	public void atualiza(ContatoAnnotations contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o contato. Erro: "+ e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operacao de atualização. Mensagem: "+ e.getMessage());
			}
		}
	}

	// EXCLUIR
	public void excluir(ContatoAnnotations contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o contato. Erro: "+ e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar operacao de exclusão. Mensagem: "+ e.getMessage());
			}
		}
	}

	// LISTAR
	public List<ContatoAnnotations> listar() {
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		List<ContatoAnnotations> resultado = null;

		try {
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			
			/*utilizo o mesmo nome que é usado na classe modelo,
			conforme foi mapeado no arquivo de .xml*/
			consulta = sessao.createQuery("from Contato");
			
			resultado = consulta.list();
			transacao.commit();
			return resultado;
		} catch (HibernateException e) {
			System.out.println("Não foi possível selecionar contatos. Erro: "+ e.getMessage());
			throw new HibernateException(e);
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar operação de consulta. Mensagem: "+ e.getMessage());
			}
		}
	}

	// BUSCAR CONTATO
	public ContatoAnnotations buscaContato(int valor) {
		ContatoAnnotations contato = null;
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;

		try {
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from contato where codigo = :parametro");
			consulta.setInteger("parametro", valor);
			contato = (ContatoAnnotations) consulta.uniqueResult();
			transacao.commit();
			return contato;
		} catch (HibernateException e) {
			System.out.println("Não foi possível buscar contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de buscar. Mensagem: " + e.getMessage());
			}
		}

		return contato;
	}

	public static void main(String[] args) {
		
		ContatoCrudXML contatoCrudXML = new ContatoCrudXML();

//		Contato c = new Contato();
//		
//		Field[] attrs = Contato.class.getDeclaredFields();
//		try {
//			Field  meuAttr = attrs[0];
//			meuAttr.get;
//			meuAttr.set(c, "");
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
		
		String[] nomes = { 
				"Paulo Amaral", 
				"Rodrigo Mendes", 
				"Felipe Camara" 
		};
		
		String[] fones = { 
				"(16) 3333-5555", 
				"(13) 9999-4848", 
				"(89) 7171-0005" 
		};
		
		String[] emails = { 
				"paulinho@teste.com.br", 
				"rodrigo@teste.com.br",
				"camara@teste.com.br" 
		};
		
		String[] observacoes = { 
				"Novo cliente", 
				"Encomendas para entregar",
				"Devendo tres parcelas" 
		};
		
		ContatoAnnotations contato = null;
		
		for (int i = 0; i < nomes.length; i++) {
			
			contato = new ContatoAnnotations();
			contato.setNome(nomes[i]);
			contato.setTelefone(fones[i]);
			contato.setEmail(emails[i]);
			contato.setDataCadastro(new Date(System.currentTimeMillis()));
			contato.setObservacao(observacoes[i]);
			contatoCrudXML.salvar(contato);
			
		}
		
		System.out.println("Total de registros cadastrados: " + contatoCrudXML.listar().size());

	}

}
