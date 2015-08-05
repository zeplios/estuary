package cn.edu.tju.ina.estuary.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

public class FileHelper {
	
	private String rootPath;
	
	public FileHelper(HttpSession session) {
		// the result doesn't end with '/'
		// rootPath = session.getServletContext().getRealPath("/resources/");
		ServletContext context = session.getServletContext();
		String path = context.getRealPath("");	//返回一直到\estuary的绝对路径
		rootPath = path.substring(0, path.lastIndexOf(File.separatorChar));	//返回\estuary之前的部分
	}
	
	/**
	 * save a file to rootPath/relativePath/filename
	 * @param image source file
	 * @param relativePath relative to root path
	 * @param filename target file name
	 * @return success or fail
	 */
	public boolean save(MultipartFile file, String relativePath, String filename) {
		String path = "";
		if (relativePath != null && relativePath.startsWith("/")) {
			path = rootPath + relativePath;
		} else {
			path = rootPath + File.separator + relativePath;
		}
		try {
			InputStream is = file.getInputStream();
			FileUtils.copyInputStreamToFile(is, new File(path, filename));
			is.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean save(MultipartFile file, String relativePath, String filename, int scaleToWidth) {
		String path = "";
		if (relativePath != null && relativePath.startsWith("/")) {
			path = rootPath + relativePath;
		} else {
			path = rootPath + File.separator + relativePath;
		}
		try {
			InputStream is = file.getInputStream();
			BufferedImage bi = ImageIO.read(is);
			int scaleToHeight = bi.getHeight() * scaleToWidth / bi.getWidth();
			
			BufferedImage scaledImage = Scalr.resize(bi,
					Scalr.Method.BALANCED, scaleToWidth, scaleToHeight);
			ImageIO.write(scaledImage, "jpg", new File(path, filename));
			is.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean delete(String filename) {
		String path = rootPath + File.separator + filename;
		File f = new File(path);
		f.delete();
		return true;
	}
	
	public boolean delete(List<String> filenames) {
		for (String filename : filenames) {
			String path = rootPath + File.separator + filename;
			File f = new File(path);
			f.delete();
		}
		return true;
	}
	
	/**
	 * cut (x, y, w, h) of source and save to target
	 * @param sourcePath start with or doesn't start with '/' is all ok, a relative path to root
	 * @param sourceName source filename
	 * @param targetPath start with or doesn't start with '/' is all ok, a relative path to root
	 * @param targetName target filename
	 * @param x x point
	 * @param y y point
	 * @param w width to cut
	 * @param h height to cut
	 * @return success or fail
	 */
	public boolean transformImage(String sourcePath, String sourceName, String targetPath, String targetName, 
								int x, int y, int w, int h) {
		String source = "", target = "";
		if (sourcePath != null && sourcePath.startsWith("/")) {
			source = rootPath + sourcePath;
		} else {
			source = rootPath + File.separator + sourcePath;
		}
		if (targetPath != null && targetPath.startsWith("/")) {
			target = rootPath + targetPath;
		} else {
			target = rootPath + File.separator + targetPath;
		}
		try {
			// it's so strange that ImageIO.write doesn't work for jpeg
			BufferedImage bi = ImageIO.read(new File(source, sourceName));
			BufferedImage clipImage = bi.getSubimage(x, y, w, h);
			BufferedImage scaledImage = Scalr.resize(clipImage,
					Scalr.Method.BALANCED, 100, 100);
//			BufferedImage targetImage = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);  
//			targetImage.getGraphics().drawImage(scaledImage, 0, 0, 100, 100, null);
//			FileOutputStream deskImage = new FileOutputStream(new File(target, targetName));
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);  
//			encoder.encode(targetImage);
//			deskImage.close();
			ImageIO.write(scaledImage, "jpg", new File(target, targetName));
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
