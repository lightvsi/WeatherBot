import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.io.InputStream;

public class Request {
    private static final HttpClient client = HttpClients.createDefault();
    public static String getData(String city) throws IOException {
        String requestContent = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + Keys.getOpenweatherKey();
        HttpGet request = new HttpGet(requestContent);
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            return IOUtils.toString(instream, "UTF-8");
        } else return null;
    }
}