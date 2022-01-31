package com.istrategies.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    Cloudinary cloudinary;
    private Map<String, Object> valueMap = new HashMap<>();

    public CloudinaryService() {
        valueMap.put("cloud_name", "dfnvezpow");
        valueMap.put("api_key", "676256672862355");
        valueMap.put("api_secret", "yuL4mbLkXnrz2mG_1XeHdY5nlTM");
        valueMap.put("secure", true);
        cloudinary = new Cloudinary(valueMap);
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = this.convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }
}
