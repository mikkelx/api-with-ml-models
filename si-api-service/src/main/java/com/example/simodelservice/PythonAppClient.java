package com.example.simodelservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

//localhost - for development
//si-model-service - for communication between docker containers
@FeignClient(name = "python-app", url = "http://si-model-service:5000")
public interface PythonAppClient {
    @PostMapping(value = "/predict", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ModelResultDto predict(@RequestPart("image") MultipartFile image);

    @GetMapping(value = "/getLastPicture", produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity<Resource> getPicture();
}
