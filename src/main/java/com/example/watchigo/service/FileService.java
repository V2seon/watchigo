package com.example.watchigo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
public class FileService {

    public HttpSession session;

    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File((String) session.getAttribute("upload")));
    }
}
