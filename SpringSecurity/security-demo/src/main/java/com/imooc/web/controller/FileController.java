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
 * 给Controller添加文档说明
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 4:06
 *
 * @param  @ApiOperation注解的value属性一般都是简单描述API的功能，notes属性详细描述API的功能；
 * @param @ApiImplicitParams用来描述一个方法的多个参数的注解；
 * @param @ApiImplicitParam用来表述单个参数，name属性来描述参数的名称，value用来描述参数的意思，required表示参数是否是必需值，dataTypeClass或者dataType指定了数据的类型。
 */
@RestController
@RequestMapping("/file")
public class FileController {

	private static String folder = "/Users/heng/IdeaProjects/Security/security-demo";

	@PostMapping
	@ApiOperation(value = "文件上传接口", notes = "访问此接口可以实现文件上传")
	@ApiImplicitParam(name = "file", value = "使用MultipartFile的实例对象来接收文件数据", required = true, dataTypeClass = MultipartFile.class)
	public FileInfo upload(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("上传文件的表单name值为：" + file.getName());
		System.out.println("文件路径为：" + file.getOriginalFilename());
		System.out.println("文件大小为：" + file.getSize());
		File localFile = new File(folder, System.currentTimeMillis() + ".txt");
		// 执行上传操作
		file.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "文件下载接口", notes = "访问此接口并提供文件ID即可下载文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "文件ID", required = true, dataTypeClass = String.class),
			@ApiImplicitParam(name = "request", value = "HttpServletRequest实例对象，自动注入，无需传递", required = true, dataTypeClass = HttpServletRequest.class),
			@ApiImplicitParam(name = "response", value = "HttpServletResponse实例对象，自动注入，无需传递", required = true, dataTypeClass = HttpServletResponse.class)
	})
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(folder);
		try (
				// 这是JDK7的特性，关于流的操作，可以写在try后面的圆括号里，这样就无需手动关闭流
				InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
				OutputStream outputStream = response.getOutputStream()
		) {
			// 设置下载的文件类型
			response.setContentType("application/x-download");
			// 设置下载后的文件名
			response.setHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(inputStream, outputStream);
			// 刷新输出流
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}