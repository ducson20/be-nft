package com.asra.developer.common.utils;

import com.asra.developer.common.enums.EMessageCode;
import com.asra.developer.common.error.BusinessException;
import org.jboss.vfs.VirtualFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public final class FileUtils {

	public static File getResource(String path) {
		URL url = FileUtils.class.getClassLoader().getResource(path);
		try {
			VirtualFile vf = null;
			Object content = url.getContent();
			if (content instanceof FileInputStream) {
				String tmpPath = path.substring(0, path.lastIndexOf('/'));
				url = FileUtils.class.getClassLoader().getResource(tmpPath);
				vf = (VirtualFile) url.getContent();
				return new File(vf.getPhysicalFile().getPath() + "\\" + path.substring(path.lastIndexOf('/') + 1));
			}

			if (content instanceof VirtualFile) {
				vf = (VirtualFile) content;
				return vf.getPhysicalFile();
			}
		} catch (IOException e) {
			throw new BusinessException(MessageUtils.getMessageFromCode(EMessageCode.E001.getMessage()));
		}
		return new File(url.getPath());
	}
}
