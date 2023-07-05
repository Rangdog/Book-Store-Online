package poly.bean;

import java.io.File;

import javax.servlet.ServletContext;
import org.springframework.web.multipart.MultipartFile;

public class Uploader {
	public static String upload(MultipartFile image, String location, ServletContext context) {
		String virtualPath = String.format("resources\\image\\%s\\", location), imagePath = image.getOriginalFilename();
		String realPath = context.getRealPath(virtualPath + imagePath);
		try {
			image.transferTo(new File(realPath));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return virtualPath + imagePath;
	}
}
