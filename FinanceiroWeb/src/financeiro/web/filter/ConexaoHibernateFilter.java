package financeiro.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import financeiro.util.HibernateUtil;

public class ConexaoHibernateFilter implements Filter {

	private SessionFactory sf;

	@Override
	//executado somante quando o aplicativo web em questão é colocado no AR
	public void init(FilterConfig config) throws ServletException {
		//foi realizada a criação do 'SessionFactory' do hibernate
		//que fornecerá sessoes do hibernate para cada requisição.
		this.sf = HibernateUtil.getSessionfactory();
	}
	
	@Override
	//metodo onde as requisiçoes WEB são interceptadas.
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		try{
			this.sf.getCurrentSession().beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			this.sf.getCurrentSession().getTransaction().commit();
			this.sf.getCurrentSession().close();
		}catch(Throwable ex){
			try {
				if(this.sf.getCurrentSession().getTransaction().isActive()){
					this.sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			throw new ServletException(ex);
		}
		
	}
	
	@Override
	//esse metodo é executado quando o aplicativo web 
	//é desativado ou o servior é retirado do AR.
	public void destroy() {
		
	}

}
