package com.livro.capitulo3.conexao;

import org.hibernate.Session;

public class ConectaHibernateMySQL {

	public static void main(String[] args) {
		Session sessao = null;
		
		try {
			sessao = HibernateUtil.getSessionfactory().openSession();
			System.out.println("Conectou!");
		} finally {
			sessao.close();
		}
	}
	
}
