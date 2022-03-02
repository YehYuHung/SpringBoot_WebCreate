package com.estherNmorga.demo.uploadFile.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	// 初始化
	public void init();
	
	public void store(MultipartFile file);
	
	// 載入多個檔案
	public Stream<Path> loadAll();
	
	// 載入檔案
	public Path load(String fileName);

	public Resource loadAsResource(String fileName);
	
	// 刪除全部檔案
	public void deleteAll();
}
