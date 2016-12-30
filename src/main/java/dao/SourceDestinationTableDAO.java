package dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.stereotype.Component;
import pojo.dynamo.SourceDestinationPair;

import java.util.List;

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

    public SourceDestinationPair read(String srcDestPair) {
        return mapper.load(SourceDestinationPair.class, srcDestPair);
    }

    public List<SourceDestinationPair> scan(int limit) {
        return mapper.scan(SourceDestinationPair.class, new DynamoDBScanExpression().withLimit(limit));
    }

    public void delete(SourceDestinationPair pair) {
        mapper.delete(pair);
    }
}
