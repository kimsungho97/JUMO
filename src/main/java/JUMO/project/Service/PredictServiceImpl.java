package JUMO.project.Service;

import JUMO.project.Entity.StockRow;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class PredictServiceImpl implements PredictService{

    private final String predictURL = "http://127.0.0.1:5000";

    @Override
    public String getRecommend(String name) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(predictURL + "/predict/recommendStock")
                .queryParam("name", name)
                .build(false);  // 인코딩 하지않음
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // api 호출 타임아웃
        factory.setReadTimeout(5000);   // api 읽기 타임아웃

        RestTemplate template = new RestTemplate(factory);
        ResponseEntity<StockRow> response = template.exchange(
                uriComponents.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), StockRow.class);
        return response.getBody().getIsFullmaesu();
    }
}
