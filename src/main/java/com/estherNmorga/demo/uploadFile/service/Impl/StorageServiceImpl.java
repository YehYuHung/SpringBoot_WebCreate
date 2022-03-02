package com.estherNmorga.demo.uploadFile.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale.Category;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.estherNmorga.demo.uploadFile.Config.StorageProperties;
import com.estherNmorga.demo.uploadFile.service.StorageService;
import com.estherNmorga.demo.uploadFile.service.exception.StorageFileNotFoundException;

@Service
public class StorageServiceImpl implements StorageService {

	private final Path rootLocation;

	@Autowired
	public StorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(rootLocation);
		} catch (Exception e) {
			// TODO: handle exception
			throw new StorageFileNotFoundException("Could not initializ storage", e);
		}
	}

	@Override
	public void store(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			if (file.isEmpty()) {
				throw new StorageFileNotFoundException("Failed to storage empty file.");
			}
			Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize()
					.toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageFileNotFoundException("Cannot store file outside current directory.");
			}
			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (StorageFileNotFoundException | IOException e) {
			throw new StorageFileNotFoundException("Failed to store file.", e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Failed to read stored files", e);
		}
	}

	@Override
	public Path load(String fileName) {
		// TODO Auto-generated method stub
		return rootLocation.resolve(fileName);
	}

	@Override
	public Resource loadAsResource(String fileName) {
		try {
			Path file = load(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + fileName);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
		}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

}
