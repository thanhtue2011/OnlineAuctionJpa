package com.asiantech.onlineauction.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

public interface ImageService {
	ResponseEntity<byte[]> getResponseImage(String urlImage) throws IOException;
}
