package lkadms;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static lkadms.BaseClass.getSearchingLog;
import static lkadms.BaseClass.getURLkibana;

public class Kibana {
    public static String searchByCID(String environment, String cid, String filename) {
        Logger log = LoggerFactory.getLogger(UnitTests.class);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String result = "";
        String urlKibanaSearch = getURLkibana(environment);
        boolean isLogFound = false;
        long startMs = System.currentTimeMillis();
        while (!isLogFound) {
            if (System.currentTimeMillis() - startMs >= 120000) {
                throw new RuntimeException("Expired: log was not found");
            }
            try {
                Date today = Calendar.getInstance().getTime();
                String newstring = new SimpleDateFormat("yyyy.MM.dd").format(today);
                String url = "http://elog.vsk.ru:9200/" + urlKibanaSearch + "-" + newstring + "/_search?q=%20message:%20%22" + cid + "%22%20&size=500";
                                HttpGet request = new HttpGet(url);
                request.addHeader("content-type", "application/json");
                request.addHeader("Accept", "application/json");
                HttpResponse response = httpClient.execute(request);
                String responseBody = EntityUtils.toString(response.getEntity());
                result = responseBody;
                String searchingLog = getSearchingLog(filename);
                if (responseBody.contains(searchingLog)) {
                    isLogFound = true;
                    log.info("URL is : " + url);
                    log.info("Response message : " + responseBody);
                }
            } catch (Exception ex) {
                log.error("!!!!!!!!!!!!!!!!!!!!Something goes wrong!!!!!!!!!!!!!!!!!!!!");
            }
        }
        return result;
    }
}
