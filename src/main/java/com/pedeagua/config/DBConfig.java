package com.pedeagua.config;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pedeagua.entity.EmpresaProduto;
import com.pedeagua.entity.EmpresaUsuario;
import com.pedeagua.entity.Pedido;
import com.pedeagua.entity.PedidoItens;
import com.pedeagua.entity.Plano;
import com.pedeagua.entity.Agente;
import com.pedeagua.entity.AgenteEndereco;
import com.pedeagua.entity.AgenteUsuario;
import com.pedeagua.entity.Configuracao;
import com.pedeagua.entity.Empresa;
import com.pedeagua.entity.Produto;
@Configuration 
@EnableTransactionManagement
public class DBConfig {
	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}
	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource())
		   .addAnnotatedClasses(Agente.class)
		   .addAnnotatedClasses(AgenteUsuario.class)
		   .addAnnotatedClasses(AgenteEndereco.class)
		   .addAnnotatedClasses(Produto.class)
		   .addAnnotatedClasses(Plano.class)
		   .addAnnotatedClasses(Empresa.class)
		   .addAnnotatedClasses(EmpresaUsuario.class)
		   .addAnnotatedClasses(EmpresaProduto.class)
		   .addAnnotatedClasses(Pedido.class)
		   .addAnnotatedClasses(PedidoItens.class)
		   .addAnnotatedClasses(Configuracao.class)
		   .buildSessionFactory();
	}
	
	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/basepedeagua");
	    dataSource.setUsername("root");
	    dataSource.setPassword("");
	    dataSource.setTestOnBorrow(true);	    
	    dataSource.setValidationQuery("select 1");	   
	    return dataSource;
	}
	
	@Bean
	public HibernateTransactionManager hibTransMan(){
		return new HibernateTransactionManager(sessionFactory());
	}
}
