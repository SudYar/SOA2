package com.mastertheboss.jaxrs.ejb.client;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * @author Sudarushkin Yaroslav (Yaroslav.Sudarushkin@lanit-tercom.ru) created on 03.12.2023.
 */
public class CustomTrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
        // Trust all clients
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
        // Trust all servers
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // Return empty array of certificates
        return new X509Certificate[0];
    }
}
