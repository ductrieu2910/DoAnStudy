package com.example.doanstudy.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    public CloudinaryService(@Value("${cloudinary.cloud-name}") String cloudName,
                             @Value("${cloudinary.api-key}") String apiKey,
                             @Value("${cloudinary.api-secret}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    @Async
    public CompletableFuture<String> uploadFileAsync(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                return CompletableFuture.completedFuture(null);
            }

            // Kiểm tra loại file (video hay hình ảnh)
            String resourceType = file.getContentType().startsWith("video") ? "video" : "image";

            // Chuyển MultipartFile thành byte[] trước khi upload
            byte[] fileBytes = file.getBytes();

            // Tùy chỉnh upload video hoặc hình ảnh
            Map<?, ?> uploadResult = cloudinary.uploader().upload(fileBytes,
                    ObjectUtils.asMap(
                            "resource_type", resourceType,  // Định nghĩa loại file (video hoặc image)
                            "folder", "web-t1academy",  // Đường dẫn lưu trên Cloudinary
                            "use_filename", true,
                            "unique_filename", true,
                            "quality", "auto",
                            "fetch_format", "auto"
                    ));

            // Trả về URL của file sau khi upload thành công
            return CompletableFuture.completedFuture((String) uploadResult.get("secure_url"));
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(null);
        }
    }


    // Giữ lại URL ảnh cũ nếu không có ảnh mới
    public String getExistingImageUrl(String existingImageUrl) {
        return (existingImageUrl != null && !existingImageUrl.isEmpty()) ? existingImageUrl : null;
    }
}
