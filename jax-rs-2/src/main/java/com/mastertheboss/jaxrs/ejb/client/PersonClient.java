package com.mastertheboss.jaxrs.ejb.client;

import com.mastertheboss.jaxrs.backend.domain.entity.Person;
import org.jboss.ejb3.annotation.Pool;

import javax.ejb.Stateless;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Stateless
@Pool("slsb-strict-max-pool")
public class PersonClient {

	public static final String JAX_RS_1_DRAGON = "https://localhost:21572/jax-rs-1/dragon";
	HostnameVerifier hostnameVerifier = (arg0, arg1) -> true;
	public Person getPersonById(Long id) {
		String url = JAX_RS_1_DRAGON + "/persons/" + id.toString();
		try {
			SSLContext sslContext = SSLContext.getInstance("ssl");
			sslContext.init(null, new TrustManager[] {new CustomTrustManager()}, null);
			Client client = ClientBuilder.newBuilder()
					.hostnameVerifier(hostnameVerifier).sslContext(sslContext).build();

			System.out.println("URL для запроса пациента");
			System.out.println(url);
			Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();

			Person person = response.readEntity(Person.class);

			client.close();

			return person;
		} catch (ProcessingException | NoSuchAlgorithmException | KeyManagementException e) {
			System.out.println("ОШИБКА ЗАПРОСА НАЧ");
			e.printStackTrace();
			System.out.println("ОШИБКА ЗАПРОСА КОН");
			return null;
		}

	}
}
