package com.example.blogapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FileService {
    String uploadImage(String path, MultipartFile file) throws IOException;
    InputStream getResourse(String path,String fileName) throws FileNotFoundException;
}
