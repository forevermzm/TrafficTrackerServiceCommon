package dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.stereotype.Component;
import pojo.dynamo.SourceDestinationPair;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class SourceDestinationTableDAO {
    private final DynamoDBMapper mapper;

    public SourceDestinationTableDAO(DynamoDBMapper mapper) {
        this.mapper = checkNotNull(mapper);
    }

    public void save(SourceDestinationPair pair) {
        mapper.save(pair);
    }
}
