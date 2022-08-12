import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EndpointConfig {

    @Bean
    public HttpClient reqresClient() {
        return CitrusEndpoints
                .http()
                .client()
                .requestUrl("https://reqres.in/")
                .build();
    }

}
