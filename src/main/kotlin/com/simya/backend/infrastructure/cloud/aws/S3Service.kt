package com.simya.backend.infrastructure.cloud.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile

import java.util.*



@Service
class S3Service (
    private val amazonS3Client: AmazonS3
){

    @Value("\${cloud.aws.s3.bucket}")
    private val bucketName: String? = null
    fun uploadFile(multipartFile: MultipartFile): String {
        validateFileExists(multipartFile)
        val fileName = createFileName(Objects.requireNonNull(multipartFile.originalFilename))
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = multipartFile.contentType
        try {
            multipartFile.inputStream.use { inputStream ->
                amazonS3Client.putObject(
                    PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
                )
            }
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
        return fileName
    }

    fun deleteFile(fileName: String?) {
        amazonS3Client.deleteObject(DeleteObjectRequest(bucketName, fileName))
    }

    private fun validateFileExists(multipartFile: MultipartFile) {
        if (multipartFile.isEmpty) {
            throw RuntimeException()
        }
    }

    private fun createFileName(fileName: String?): String {
        return UUID.randomUUID().toString() + getFileExtension(fileName)
    }

    private fun getFileExtension(fileName: String?): String {
        if (!StringUtils.hasText(fileName!!.substring(fileName.lastIndexOf(".")))) {
            throw StringIndexOutOfBoundsException()
        }
        return fileName.substring(fileName.lastIndexOf("."))
    }
}