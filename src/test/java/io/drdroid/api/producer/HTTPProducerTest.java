package io.drdroid.api.producer;

import com.sun.net.httpserver.HttpServer;
import io.drdroid.api.DrDroidClient;
import io.drdroid.api.data.EnvVars;
import io.drdroid.api.models.http.request.Data;
import io.drdroid.api.models.http.request.UUIDRegister;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.net.InetSocketAddress;

@RunWith(MockitoJUnitRunner.class)
public class HTTPProducerTest {

    private HttpServer httpServer;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);

        httpServer = HttpServer.create(new InetSocketAddress(1080), 0);

        DrDroidClient.initDrDroidClient(EnvVars.apiToken, EnvVars.sinkUrl, EnvVars.service);
    }

    @After
    public void destroy() {
        httpServer.stop(0);
    }

    @Test
    public void testSendBatch() {
        httpServer.createContext("/e/ingest/events/v2", exchange -> {
            byte[] response = "{\"count\": 1}".getBytes();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        });
        httpServer.start();
        int sentCount = HTTPProducer.getHTTPProducer().sendBatch(new Data());

        Assert.assertEquals(1, sentCount);
    }

    @Test
    public void testRegister() {
        httpServer.createContext("/e/agent/register", exchange -> {
            byte[] response = "{\"success\": true}".getBytes();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        });
        httpServer.start();

        Assert.assertTrue(HTTPProducer.getHTTPProducer().register(new UUIDRegister()));
    }

}