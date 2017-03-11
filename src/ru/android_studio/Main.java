package ru.android_studio;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static void test() {
        HttpClient client = new DefaultHttpClient();
        //HttpPost post = new HttpPost("http://www.bbc.com/sport/football/league-one/table");
        HttpGet post = new HttpGet("http://d.myscore.ru/x/feed/f_1_0_3_ru_1");
        //HttpGet post = new HttpGet("http://myscore.ru");

        //ISO-8859-3
        post.setHeader("accept", "application/json");
        post.setHeader("accept-encoding", "gzip, deflate");
        post.setHeader("accept-Charset", "utf-8");
        post.setHeader("accept-language", "en-US,en;q=0.8");
        post.setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        post.setHeader(CoreProtocolPNames.HTTP_CONTENT_CHARSET, String.valueOf(Consts.UTF_8));

        // header
        post.setHeader("Host", "d.myscore.ru");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Pragma", "no-cache");
        post.setHeader("Cache-Control", "no-cache");
        post.setHeader("Accept", "*/*");
        post.setHeader("X-Requested-With", "XMLHttpRequest");
        post.setHeader("Accept-Language", "*");
        post.setHeader("X-Fsign", "SW9D1eZo");
        //post.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        post.setHeader("Referer", "http://d.myscore.ru/x/feed/proxy-local");
        post.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        post.setHeader("Cookie", "_ga=GA1.2.1188619457.1488915689; _dc_gtm_UA-821699-9=1");

        try {
            HttpResponse response = client.execute(post);
            HttpEntity httpEntity = response.getEntity();
            String xml = httpEntity.toString();
            System.out.println(xml);

            if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204) {

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

                String output;
                // System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

        } catch (Exception ex) {
            //logger.error("ex Code sendPut: " + ex);
            //logger.error("url:" + url);
            //logger.error("data:" + data);
        } finally {
            //httpClient.getConnectionManager().shutdown();
        }
    }


    public static void main(String[] args) {
        test();
    }
}
