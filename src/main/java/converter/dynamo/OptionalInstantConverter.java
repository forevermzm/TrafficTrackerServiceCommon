package converter.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.Instant;
import java.util.Optional;

public class OptionalInstantConverter
        implements DynamoDBTypeConverter<String, Optional<Instant>> {

    @Override
    public String convert(final Optional<Instant> instant) {
        if (instant.isPresent()) {
            return instant.get().toString();
        } else {
            return null;
        }
    }

    @Override
    public Optional<Instant> unconvert(final String stringValue) {
        if (stringValue == null) {
            return Optional.empty();
        } else {
            return Optional.of(Instant.parse(stringValue));
        }
    }

}
