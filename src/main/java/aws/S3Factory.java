package aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
public class S3Factory {

    @Bean
    @Scope("prototype")
    public static AmazonS3Client createS3Client() {
        return new AmazonS3Client();
    }

    @Bean
    public static ObjectMapper createJsonMapper() {
        return new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .registerModule(new GuavaModule())
                .registerModule(new JavaTimeModule());
    }
}
