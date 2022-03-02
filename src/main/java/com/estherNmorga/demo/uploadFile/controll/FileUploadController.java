package com.estherNmorga.demo.uploadFile.controll;

import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estherNmorga.demo.uploadFile.service.StorageService;

@Controller
public class FileUploadController {

	private final StorageService storageService;
	
	private final Logger log = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/upload")
	public String listUploadedFiles(Model model) throws IOException {
		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								// fromMethodName 呼叫參數一的class(皆為Controller),對參數二methodName使用，最後參數三按照method需求的RequestParam/PathVariable添加
								// 完成後就會有新建立的URI網址參數列  (注意！URI的parentRoot為呼叫Controller的路徑往下疊加喔)
								.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
								.build().toUri().toString())
						.collect(Collectors.toList()));
		return "uploadForm";
	}

	@GetMapping("/upload/files/{fileName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
		Resource file = storageService.loadAsResource(fileName);
		// ok() -> 設定website為 200 連線品質
		return ResponseEntity.ok()
				// CONTENT_DISPOSITION為Response-hearder中的下載檔案
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/upload")
	public String handleFileUpload(
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You success fully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:upload";
	}
}
