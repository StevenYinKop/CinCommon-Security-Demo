package com.yinzifan.security.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yinzifan.security.dto.FileInfo;

@RestController
@RequestMapping("file")
public class FileController {

	@PostMapping
	public FileInfo upload(MultipartFile keyFile) throws Exception {
		
		System.out.println(keyFile.getName());
		System.out.println(keyFile.getContentType());
		System.out.println(keyFile.getOriginalFilename());
		System.out.println(keyFile.getSize());
		System.out.println(keyFile.getBytes().toString());
		System.out.println(keyFile.getClass());
		
		String path = "E:\\Programs\\STSWorkSpace\\CinCommon-security\\CinCommon-security-demo\\src\\main\\java\\com\\yinzifan\\web\\controller";
		File localFile = new File(path, System.currentTimeMillis() + ".txt");
		keyFile.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void download(@PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response) {
		String path = "E:\\Programs\\STSWorkSpace\\CinCommon-security\\CinCommon-security-demo\\src\\main\\java\\com\\yinzifan\\web\\controller";
		try (
				InputStream is = new FileInputStream(new File(path, id+".txt"));
				OutputStream os = response.getOutputStream();
		){
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename="+System.currentTimeMillis()+".txt");
			IOUtils.copy(is, os);
			os.flush();
		} catch (Exception e) {
		}
	}
}
