package com.example.bootdemo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class HttpUtil {

    public static String get(String url) {
        CloseableHttpResponse response = null;
        String returnStr = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    returnStr = EntityUtils.toString(entity, "UTF-8");
                }
            } else {
                throw new ClientProtocolException("Unexpected response status : " + status);
            }
        } catch (ClientProtocolException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
            }
        }
        return returnStr;
    }

}
