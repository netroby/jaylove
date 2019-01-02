package com.netroby.jaylove.service.impl

import com.netroby.jaylove.config.AwsSecurityConfig
import com.netroby.jaylove.service.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.regions.Region.US_WEST_2
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials


class StorageServiceImpl (@Autowired private val awsSecretKey: AwsSecurityConfig): StorageService {
    fun upload(file: MultipartFile) : String {
        val awsCreds = AwsBasicCredentials.create(
                awsSecretKey.accessKeyId,
                awsSecretKey.secretAccessKey)
        val s32 = S3Client.builder()
                .region(Region.of("us-east-1"))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build()
        return ""
    }
}