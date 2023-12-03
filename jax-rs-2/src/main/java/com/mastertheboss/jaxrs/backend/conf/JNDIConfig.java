package com.mastertheboss.jaxrs.backend.conf;

import com.mastertheboss.jaxrs.ejb.service.PersonService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.NotFoundException;
import java.util.Properties;

public class JNDIConfig {

	public static PersonService personService(){
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProps.put("jboss.naming.client.ejb.context", true);
		jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:21577");
		try {
			final Context context = new InitialContext(jndiProps);
			return  (PersonService) context
					.lookup("ejb:/jax-rs-2/PersonServiceImpl!com.mastertheboss.jaxrs.ejb.service.PersonService");
		} catch (NamingException e){
			System.out.println("Не получилось :(");
			e.printStackTrace();
			throw new NotFoundException();
		}
	}
}
