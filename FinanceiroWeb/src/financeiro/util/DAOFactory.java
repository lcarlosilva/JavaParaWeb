package financeiro.util;

import financeiro.usuario.UsuarioDAO;
import financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory {
	
	/**
	 *Retorna uma instancia de usuarioDAO, sendo<br> 
	 *utilizada na classe de regra de negocio de usuarioRN.
	 **/
	public static UsuarioDAO criaUsuarioDAO() {
		
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionfactory().getCurrentSession());
		
		return usuarioDAO;
	}
	
}
