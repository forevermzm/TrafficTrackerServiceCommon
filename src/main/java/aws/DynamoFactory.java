package aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class DynamoFactory {
    private static final AmazonDynamoDBClientBuilder builder =
            AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.US_WEST_2);

    @Bean
    @Scope("prototype")
    public static AmazonDynamoDB createDDBClient() {
        return builder.build();
    }

    @Bean
    @Scope("prototype")
    public static DynamoDBMapper createDDBMapper() {
        return new DynamoDBMapper(builder.build());
    }
}
