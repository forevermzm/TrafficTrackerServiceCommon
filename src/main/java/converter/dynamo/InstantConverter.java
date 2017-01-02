package converter.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;

public class InstantConverter
        implements DynamoDBTypeConverter<String, Instant> {

    @Override
    public String convert(final Instant instant) {

        return instant.toString();
    }

    @Override
    public Instant unconvert(final String stringValue) {

        return Instant.parse(stringValue);
    }

}
