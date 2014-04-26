package net.locplus.sdk.wechat.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Created by Dean on 2014/4/26.
 */
public class HttpUtil {

    private CloseableHttpClient httpClient;

    private HttpUtil(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static HttpUtil getHttpInstance() {
        return HttpUtilSingletonHolder.httpInstance;
    }

    public static HttpUtil getHttpsInstance() {
        return HttpUtilSingletonHolder.httpsInstance;
    }

    /**
     * Http get request, which params like:
     * List<NameValuePair> params = new ArrayList<NameValuePair>();
     * params.add(new BasicNameValuePair("name", "xxx"));
     * params.add(new BasicNameValuePair("pwd", "xxx"));
     *
     * @param url
     * @param params
     * @return
     */
    public String get(String url, List<NameValuePair> params) {
        String result = null;
        try {
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));
            result = get(url, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Http get request, params like:
     * name=xxx&pwd=xxx
     *
     * @param url
     * @param params
     * @return
     */
    public String get(String url, String params) {
        if (params != null && !params.isEmpty()) {
            url = new StringBuilder(url).append("?").append(params).toString();
        }
        return get(url);
    }

    /**
     * Http get rquest url like:
     * http://xxx.com?name=xxx&pwd=xxx
     * or http://xxx.com/xxx
     *
     * @param url
     * @return
     */
    public String get(String url) {
        String result = null;

        CloseableHttpResponse httpResponse = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public String post(String url, List<NameValuePair> params) {
        String result = null;
        try {
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));
            result = post(url, str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String post(String url, String params) {
        String result = null;

        CloseableHttpResponse httpResponse = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (params != null && !params.isEmpty()) {
                StringEntity entity = new StringEntity(params, Charset.forName("UTF-8"));
                httpPost.setEntity(entity);
            }
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private static class HttpUtilSingletonHolder {
        private static HttpUtil httpInstance = new HttpUtil(HttpClients.createDefault());
        private static HttpUtil httpsInstance = new HttpUtil(createSSLInsecureClient());

        private static CloseableHttpClient createSSLInsecureClient() {
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return true;
                    }
                }).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
                return HttpClients.custom().setSSLSocketFactory(sslsf).build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            return HttpClients.createDefault();
        }
    }
}
