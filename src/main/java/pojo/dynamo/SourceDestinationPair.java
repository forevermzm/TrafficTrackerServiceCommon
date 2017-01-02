package pojo.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import converter.dynamo.InstantConverter;
import converter.dynamo.OptionalInstantConverter;
import pojo.json.SrcDestPair;

import java.time.Instant;

@DynamoDBTable(tableName = "source-destination-pair")
public class SourceDestinationPair {

    private String id;
    private SrcDestPair pair;
    private Instant creationDate;
    private Instant lastUpdateTime;

    @DynamoDBHashKey(attributeName = "src-dest-pair")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    public SrcDestPair getPair() {
        return pair;
    }

    public void setPair(SrcDestPair pair) {
        this.pair = pair;
    }

    @DynamoDBTypeConverted( converter = InstantConverter.class )
    @DynamoDBAttribute
    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @DynamoDBTypeConverted( converter = InstantConverter.class )
    @DynamoDBAttribute
    public Instant getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Instant lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
