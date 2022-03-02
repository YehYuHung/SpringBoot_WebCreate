package com.estherNmorga.demo.Service.Impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.estherNmorga.demo.uploadFile.Config.StorageProperties;
import com.estherNmorga.demo.uploadFile.service.StorageService;
import com.estherNmorga.demo.uploadFile.service.Impl.StorageServiceImpl;
import com.estherNmorga.demo.uploadFile.service.exception.StorageFileNotFoundException;

// 記得添加 SpringBootTest，否則一般的JUnit不會有Context容器對Autowired注入物件
// 只會一直有error產生
@SpringBootTest
public class StorageServiceImplTest {

	@Autowired
	private StorageProperties properties;

	private StorageService service;

	@BeforeEach // 此註釋可以在其他method執行前，都需要排在最前面處理的順序
	/**
	 * 前置處理，新增一個資料夾，路徑產生從properties檔獲取相對路徑資料 也可以使用Setter功能重新設定別的路徑資料夾產生
	 */
	public void init() {
//		properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
		service = new StorageServiceImpl(properties);
		service.init();
	}

	/* ---------------------------- save 測試 ---------------------------- */
	@Test
	public void saveAndLoad() {
		String OriginalFileName = "bbb.txt";
		byte[] bt = "yolohello1111".getBytes();
		// MockMultipartFile 引用 MutiplepartFile，new 只是產生新物件
		service.store(new MockMultipartFile("aa", OriginalFileName, MediaType.TEXT_PLAIN_VALUE, bt));
		assertThat(service.load(OriginalFileName)).exists();
	}

	@Test
	/**
	 * 存取相對路徑但不允許儲存
	 */
	public void saveRelativePathNotPermitted() {
		assertThrows(StorageFileNotFoundException.class, () -> {
			service.store(
					new MockMultipartFile("foo", "../ggg.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
		});
	}

	@Test
	/**
	 *  存取絕對路徑但不允許儲存
	 */
	public void saveAbsolutePathNotPermitted() {
		assertThrows(StorageFileNotFoundException.class, () -> {
			service.store(new MockMultipartFile("foo", "/etc/passwd/xxx.txt", MediaType.TEXT_PLAIN_VALUE,
					"Hello, World".getBytes()));
		});
	}

	@Test
	/**
	 * 儲存原本絕對路經且可保留
	 */
	public void savePermitted() {
		service.store(
				new MockMultipartFile("foo", "bar/../foo.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
	}

	/* ---------------------------- delete 測試 ---------------------------- */
	@Test
	/**
	 * 刪除掉資料(包刮資料夾)
	 * 注意！若有使用@BeforeEach在init()方法上，則每次的dirPath需要固定，不可以用Random隨機創建
	 * 否則在刪除時看不出變化，最好使用固定的路徑
	 */
	public void deleteCreatFile() {
			service.deleteAll();
	}

}
