/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.servlet;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * <p> 设置Https的信任校验，避免没有证书导致无法访问</p>
 *
 * <p> Created At 2018-02-14 17:18  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class SSLCertificateValidation {

    public static void disable() {
        try {
            SSLContext sslc = SSLContext.getInstance("TLS");
            TrustManager[] trustManagerArray = {new NullX509TrustManager()};
            sslc.init(null, trustManagerArray, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class NullX509TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            System.out.println();
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            System.out.println();
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class NullHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
