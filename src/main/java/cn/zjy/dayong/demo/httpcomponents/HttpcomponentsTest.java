package cn.zjy.dayong.demo.httpcomponents;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/19
 * Time:14:31
 */
public class HttpcomponentsTest {

    public static void main(String[] argv) {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();

        final CountDownLatch latch = new CountDownLatch(1);
        final HttpGet request = new HttpGet("https://www.alipay.com/");

        System.out.println(" caller thread id is --------: " + Thread.currentThread().getId());

        httpclient.execute(request, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response) {
                latch.countDown();
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                System.out.println(request.getRequestLine() + "===->" + response.getStatusLine());
                try {
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    System.out.println(" response content is : " + content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void failed(final Exception ex) {
                latch.countDown();
                System.out.println(request.getRequestLine() + "==->" + ex);
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
            }

            public void cancelled() {
                latch.countDown();
                System.out.println(request.getRequestLine() + " cancelled");
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
            }

        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" caller thread id is --------: " + Thread.currentThread().getId());
        try {
            httpclient.close();
        } catch (IOException ignore) {

        }
    }
}
