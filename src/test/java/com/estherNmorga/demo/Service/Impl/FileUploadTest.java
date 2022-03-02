package com.estherNmorga.demo.Service.Impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Paths;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.estherNmorga.demo.uploadFile.service.StorageService;
import com.estherNmorga.demo.uploadFile.service.exception.StorageFileNotFoundException;

@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private StorageService storageService;

	@Test
	/**
	 * 當loadAll資料夾中所有文件時，測試文件中含有Matchers.contains(URI)
	 * (!注意) 此時的URI都皆為http, 而非https
	 * @throws Exception
	 */
	public void shouldListAllFiles() throws Exception {
		given(this.storageService.loadAll()).willReturn(Stream.of(Paths.get("aaa.png"), Paths.get("bbb.jpg")));

		this.mvc.perform(get("/upload")).andExpect(status().isOk()).andExpect(model().attribute("files",
				Matchers.contains("http://localhost/upload/files/aaa.png", "http://localhost/upload/files/bbb.jpg")));
	}

	@Test
	/**
	 * 新建MockMultipartFile物件時，post資料到預設資料夾定且預設status為302 導到其他分頁status().isFound()
	 * 定且指定的header中Location的位置在(自己定義導入的頁面，Ex:redirect:/uploadForm)
	 * @throws Exception
	 */
	public void shouldSaveUploadedFile() throws Exception {
		MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain",
				"Spring Framework".getBytes());
		this.mvc.perform(multipart("/upload").file(multipartFile)).andExpect(status().isFound())
				.andExpect(header().string("Location", "/uploadForm"));

		then(this.storageService).should().store(multipartFile);
	}

	@SuppressWarnings("unchecked")
	@Test
	/**
	 * 當loadAsResource文件找不到時，是否會指向302 status導到其他網頁
	 * status().isFound()可更換成自己設定的status
	 * @throws Exception
	 */
	public void should302WhenMissingFile() throws Exception {
		given(this.storageService.loadAsResource("test.txt")).willThrow(StorageFileNotFoundException.class);

		this.mvc.perform(get("/upload/files/test.txt")).andExpect(status().isFound());
	}

}
