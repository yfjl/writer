package com.winterchen;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("com.winterchen.dao")
public class Springboot2MybatisDemoApplication {


	/**
	 * 文件上传配置
	 *
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		String tmpUtl ="";
		tmpUtl = "/home/test/upload/";
		File tmp = new File(tmpUtl);
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//  单个数据大小
		factory.setMaxFileSize("1024000KB"); // KB
		/// 总上传数据大小
		factory.setMaxRequestSize("1024000KB");
		factory.setLocation(tmpUtl);
		return factory.createMultipartConfig();
	}
	public static void main(String[] args) {


		SpringApplication.run(Springboot2MybatisDemoApplication.class, args);

	}
}
