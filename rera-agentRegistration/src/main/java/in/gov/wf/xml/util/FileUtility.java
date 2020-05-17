package in.gov.wf.xml.util;

import java.io.File;

import in.gov.wf.constant.ProcessConstant;

public class FileUtility {

	public static void checkFolderStructure(String folderPath) throws Exception {

		try {
			while (!isFolderExists(folderPath)) {
				createFolder(folderPath);
			}

			while (!isFolderExists(folderPath + "/" + ProcessConstant.CONF_FOLDER)) {
				createFolder(folderPath + "/" + ProcessConstant.CONF_FOLDER);
			}

			while (!isFolderExists(folderPath + "/" + ProcessConstant.FRM_FOLDER)) {
				createFolder(folderPath + "/" + ProcessConstant.FRM_FOLDER);
			}
			while (!isFolderExists(folderPath + "/" + ProcessConstant.VW_FOLDER)) {
				createFolder(folderPath + "/" + ProcessConstant.VW_FOLDER);
			}

		} catch (Exception ex) {
			throw new Exception("Exception in checkFolderStructure method of FileUtility class " + ex.getMessage());
		}
	}

	private static boolean isFolderExists(String path) throws Exception {
		boolean flag = false;
		File folder = new File(path);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				return true;
			}
		}
		return flag;
	}

	private static boolean createFolder(String path) throws Exception {
		File folder = new File(path);
		System.out.println("Creating folder >>  " + path);
		return folder.mkdirs();

	}
}
