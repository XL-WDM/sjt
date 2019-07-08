package com.stj.config.http.tools;

import com.stj.config.http.ProxyConfig;
import lombok.Getter;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/6/28
 */
public class HttpTools {

    /**
     * 忽略证书验证
     * @return
     */
    public static SimpleClientHttpRequestFactory getUnsafeClientHttpRequestFactory() {
        return getUnsafeClientHttpRequestFactory(null);
    }

    /**
     * 忽略证书验证
     * @param proxyConfig 代理
     * @return
     */
    public static SimpleClientHttpRequestFactory getUnsafeClientHttpRequestFactory(ProxyConfig proxyConfig) {
        TrustManager[] byPassTrustManagers = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};
        final SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, byPassTrustManagers, new SecureRandom());
            sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }

        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection,
                                             @NotNull String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                if (connection instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) connection).setSSLSocketFactory(
                            sslContext.getSocketFactory());
                }
            }
        };

        setProxy(httpRequestFactory, proxyConfig);

        return httpRequestFactory;
    }

    /**
     * 代理
     * @param proxyConfig
     * @return
     */
    public static SimpleClientHttpRequestFactory getProxyClientHttpRequestFactory(ProxyConfig proxyConfig) {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        setProxy(httpRequestFactory, proxyConfig);

        return httpRequestFactory;
    }

    /**
     * 设置编码
     * @param restTemplate
     * @param charset
     */
    public static void setRestTemplateCharset(RestTemplate restTemplate, CharsetFormat charset) {
        if (restTemplate == null || charset == null) {
            return;
        }
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> messageConverter : messageConverters) {
            if (messageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter)messageConverter).setDefaultCharset(Charset.forName(charset.getCharset()));
            }
        }
    }

    /**
     * 设置代理
     * @param httpRequestFactory
     * @param proxyConfig
     */
    public static void setProxy(SimpleClientHttpRequestFactory httpRequestFactory, ProxyConfig proxyConfig) {
        if (httpRequestFactory != null && proxyConfig != null) {
            httpRequestFactory.setReadTimeout(proxyConfig.getReadTimeout());
            httpRequestFactory.setConnectTimeout(proxyConfig.getConnectTimeout());
            SocketAddress address = new InetSocketAddress(proxyConfig.getHost(), proxyConfig.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            httpRequestFactory.setProxy(proxy);
        }
    }

    /**
     * 编码格式
     */
    @Getter
    public enum CharsetFormat {
        /**
         * UTF-8
         */
        UTF_8("UTF-8"),
        /**
         * ISO-8859-1
         */
        ISO_8859_1("ISO-8859-1");

        private String charset;

        private CharsetFormat(String charset) {
            this.charset = charset;
        }
    }
}
