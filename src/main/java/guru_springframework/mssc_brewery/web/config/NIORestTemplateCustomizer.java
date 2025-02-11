//package guru_springframework.mssc_brewery.web.config;
//
//import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
//import org.apache.hc.core5.reactor.IOReactorConfig;
//import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
//import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
//import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
//import org.apache.http.nio.reactor.ConnectingIOReactor;
//import org.apache.http.nio.reactor.IOReactorException;
//import org.springframework.boot.web.client.RestTemplateCustomizer;
//import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//public class NIORestTemplateCustomizer implements RestTemplateCustomizer {
//
//    public ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
//        final org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(IOReactorConfig.custom()
//        .setSoTimeout(3000)
//        .setIoThreadCount(4)
//        .build());
//
//        final PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager((ConnectingIOReactor) ioReactor);
//        connectionManager.setDefaultMaxPerRoute(100);
//        connectionManager.setMaxTotal(1000);
//
//        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
//                .setConnectionManager(connectionManager)
//                .build();
//
//        return HttpComponentsClientHttpRequestFactory(httpAsyncClient);
//    }
//
//    @Override
//    public void customize(RestTemplate restTemplate) {
//        restTemplate.setRequestFactory(clientHttpRequestFactory);
//    }
//}
