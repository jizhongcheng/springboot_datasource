package com.windmill.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.poi.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author LHR Create By 2017/8/26
 */
public class FileUtils {

	public static String uploadFile(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			String today = LocalDate.now().toString();
			String name = file.getOriginalFilename();
			String suffix = name.substring(name.lastIndexOf("."), name.length());
			String userUploadPath = Constants.UPLOADFILE + File.separator + today;
			String fileName = UUID.randomUUID().toString() + suffix;
			File file_dir = new File(Constants.UPLOAD_PATH + File.separator + userUploadPath);
			if (!file_dir.exists()) {
				file_dir.mkdirs();
			}

			FileOutputStream fileOutputStream = null;
			BufferedOutputStream stream = null;
			try {
				fileOutputStream = new FileOutputStream(
						new File(Constants.UPLOAD_PATH + File.separator + userUploadPath + File.separator + fileName));
				stream = new BufferedOutputStream(fileOutputStream);
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fileOutputStream != null) {
					IOUtils.closeQuietly(fileOutputStream);
				}
				if (stream != null) {
					IOUtils.closeQuietly(stream);
				}
			}

			return Constants.DOWLOAD + File.separator + userUploadPath + File.separator + fileName;
		}
		return null;
	}
}
