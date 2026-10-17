package com.example.bootdemo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpUtil {

    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            return httpClient.execute(httpGet, response -> {
                int status = response.getCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status : " + status);
                }
            });
        } catch (IOException e) {
            log.error(e.toString());
        }
        return null;
    }

}
