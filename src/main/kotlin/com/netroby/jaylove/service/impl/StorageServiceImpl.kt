package com.netroby.jaylove.service.impl

import com.netroby.jaylove.config.AwsSecurityConfig
import com.netroby.jaylove.config.JayloveConfig
import com.netroby.jaylove.service.StorageService
import org.slf4j.LoggerFactory
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

    private val logger = LoggerFactory.getLogger("StorageService")
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
            logger.info("Now will upload file {}", fileName)
            logger.info("Target bucket: {}", awsSecretKey.bucket)
            fileUrl = jayloveConfig.site["cdnUrl"] + "/"  + fileName
            var cType = "image/jpeg"
            if (fileName.endsWith(".jpg")) {
                cType = "image/jpeg"
            }
            if (fileName.endsWith(".png")) {
                cType = "image/png"
            }
            if (fileName.endsWith(".gif")) {
                cType = "image/gif"
            }
            s32.putObject(PutObjectRequest.builder().bucket(awsSecretKey.bucket).key(fileName).contentType(cType)
                    .build(), RequestBody.fromBytes(upFile.bytes))
        } catch (e: Exception) {
            logger.info("upload error: {}", e)
        }

        return fileUrl
    }
    private fun generateFileName(multiPart: MultipartFile): String {
        return Date().time.toString() + multiPart.originalFilename!!.replace(" ", "_").replace("/", "_")
    }
}