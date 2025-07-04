package com.black_opeartive.schat.services;

import com.black_opeartive.schat.config.DotEnvConfig;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
public class S3Service {
    private final S3Client s3;
    private final String bucket;

    public S3Service() {
        DotEnvConfig dotEnvConfig = new DotEnvConfig();
        this.bucket = dotEnvConfig.get("AWS_BUCKET_NAME");

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                dotEnvConfig.get("AWS_ACCESS_KEY"),
                dotEnvConfig.get("AWS_SECRET_KEY")
        );

        this.s3 = S3Client.builder()
                .region(Region.of(dotEnvConfig.get("AWS_REGION")))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    public String uploadFile(
            byte[] content,
            String originalFileName,
            String contentType
    ) {
        String key = UUID.randomUUID() + "_" + originalFileName;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .build();

        s3.putObject(putObjectRequest, RequestBody.fromBytes(content));

        return String.format("https://%s.s3.amazonaws.com/%s", bucket, key);
    }
}
