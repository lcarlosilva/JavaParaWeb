package financeiro.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *Classe que fica responsavel por 
 *instanciar a SessionFactory<br> do Hibernate
 *e retorna-lá quando for solicitado 
 **/
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	//retorna uma sessionFactory, com base no arquivo 'hibernate.cfg.xml'
	private static SessionFactory buildSessionFactory() {
		try {
			
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			cfg.configure("hibernate.cfg.xml");
			
			return cfg.buildSessionFactory();
			
		} catch (Throwable e) {
			System.out.println("Criacao inicial do objeto SessionFactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 *@return SessionFactory 
	 **/
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
	
}
