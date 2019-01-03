package com.netroby.jaylove.service.impl

import com.netroby.jaylove.config.AwsSecurityConfig
import com.netroby.jaylove.config.JayloveConfig
import com.netroby.jaylove.service.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.regions.Region.US_WEST_2
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

@Component
class StorageServiceImpl (
        @Autowired private val awsSecretKey: AwsSecurityConfig,
        @Autowired private val jayloveConfig: JayloveConfig
        ): StorageService {
    override fun upload(upFile: MultipartFile) : String {
        val awsCreds = AwsBasicCredentials.create(
                awsSecretKey.accessKeyId,
                awsSecretKey.secretAccessKey)
        val s32 = S3Client.builder()
                .region(Region.of(awsSecretKey.region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build()
        var fileUrl = ""
        try {
            val fileName = generateFileName(upFile)
            fileUrl = jayloveConfig.site["cdnUrl"] + "/" + awsSecretKey.bucket + "/" + fileName
            s32.putObject(PutObjectRequest.builder().bucket(awsSecretKey.bucket).key(fileName)
                    .build(), RequestBody.fromBytes(upFile.bytes))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return fileUrl
    }
    private fun generateFileName(multiPart: MultipartFile): String {
        return Date().getTime().toString() + "-" + multiPart.originalFilename!!.replace(" ", "_")
    }
}