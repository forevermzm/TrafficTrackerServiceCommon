package dao;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.json.TrafficStatusDocument;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class TrafficStatusDAO {
    private final static Logger LOG = LogManager.getFormatterLogger();

    public static final String BUCKET_NAME = "traffic-tracker-service-traffic-status";
    private final AmazonS3Client s3Client;
    private final ObjectMapper mapper;

    @Autowired
    public TrafficStatusDAO(AmazonS3Client s3Client, ObjectMapper mapper) {
        this.s3Client = checkNotNull(s3Client);
        this.mapper = checkNotNull(mapper);
    }

    public void save(String path, TrafficStatusDocument trafficStatusDocument) {
        try {
            s3Client.putObject(BUCKET_NAME, path, mapper.writeValueAsString(trafficStatusDocument));
        } catch (JsonProcessingException e) {
            LOG.error("Failed to serialize TrafficStatusDocument: " + trafficStatusDocument, e);
            throw new RuntimeException(e);
        }
    }

    public TrafficStatusDocument read(String path) {
        try (S3Object object = s3Client.getObject(
                new GetObjectRequest(BUCKET_NAME, path))) {
            InputStream objectData = object.getObjectContent();
            return mapper.readValue(objectData, TrafficStatusDocument.class);
        } catch (IOException e) {
            LOG.error("Failed to deserialize data at path: " + path, e);
            throw new RuntimeException(e);
        }
    }
}
