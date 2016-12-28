package pojo.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import pojo.json.SrcDestPair;

import java.util.Date;

@DynamoDBTable(tableName = "source-destination-pair")
public class SourceDestinationPair {

    private String id;
    private SrcDestPair pair;
    private Date creationDate;

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

    @DynamoDBAttribute
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
