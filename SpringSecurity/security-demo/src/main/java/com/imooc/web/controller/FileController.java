/**
 * 
 */
package com.imooc.web.controller;

import com.imooc.dto.FileInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * ��Controller����ĵ�˵��
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 4:06
 *
 * @param  @ApiOperationע���value����һ�㶼�Ǽ�����API�Ĺ��ܣ�notes������ϸ����API�Ĺ��ܣ�
 * @param @ApiImplicitParams��������һ�������Ķ��������ע�⣻
 * @param @ApiImplicitParam������������������name�������������������ƣ�value����������������˼��required��ʾ�����Ƿ��Ǳ���ֵ��dataTypeClass����dataTypeָ�������ݵ����͡�
 */
@RestController
@RequestMapping("/file")
public class FileController {

	private static String folder = "/Users/heng/IdeaProjects/Security/security-demo";

	@PostMapping
	@ApiOperation(value = "�ļ��ϴ��ӿ�", notes = "���ʴ˽ӿڿ���ʵ���ļ��ϴ�")
	@ApiImplicitParam(name = "file", value = "ʹ��MultipartFile��ʵ�������������ļ�����", required = true, dataTypeClass = MultipartFile.class)
	public FileInfo upload(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("�ϴ��ļ��ı�nameֵΪ��" + file.getName());
		System.out.println("�ļ�·��Ϊ��" + file.getOriginalFilename());
		System.out.println("�ļ���СΪ��" + file.getSize());
		File localFile = new File(folder, System.currentTimeMillis() + ".txt");
		// ִ���ϴ�����
		file.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "�ļ����ؽӿ�", notes = "���ʴ˽ӿڲ��ṩ�ļ�ID���������ļ�")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "�ļ�ID", required = true, dataTypeClass = String.class),
			@ApiImplicitParam(name = "request", value = "HttpServletRequestʵ�������Զ�ע�룬���贫��", required = true, dataTypeClass = HttpServletRequest.class),
			@ApiImplicitParam(name = "response", value = "HttpServletResponseʵ�������Զ�ע�룬���贫��", required = true, dataTypeClass = HttpServletResponse.class)
	})
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(folder);
		try (
				// ����JDK7�����ԣ��������Ĳ���������д��try�����Բ����������������ֶ��ر���
				InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
				OutputStream outputStream = response.getOutputStream()
		) {
			// �������ص��ļ�����
			response.setContentType("application/x-download");
			// �������غ���ļ���
			response.setHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(inputStream, outputStream);
			// ˢ�������
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}