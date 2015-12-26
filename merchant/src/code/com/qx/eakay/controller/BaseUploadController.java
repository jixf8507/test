package com.qx.eakay.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.util.MSG;

public class BaseUploadController {

	/*************** BaseController.java *****************/

	public static final String SESSION_USER = "user";
	protected String datafomat = "yyyy-MM-dd";
	protected Logger logger = Logger.getLogger(getClass());

	protected SessionUserBO getSessionUser(HttpSession session) {
		if (session.getAttribute(SESSION_USER) == null) {
			return null;
		}
		return (SessionUserBO) session.getAttribute(SESSION_USER);
	}

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public String getJsonStr(Object obj) {
		Gson gson = new GsonBuilder().setDateFormat(this.datafomat).create();
		String json = null;
		try {
			json = gson.toJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("json " + json);
		return json;
	}

	/*************** BaseController.java *****************/

	/*************** webuploader图片上传 *****************/
	private String allowSuffix = "jpg,png,gif,jpeg";// 允许文件格式
	private long allowSize = 2L;// 允许文件大小
	private String fileName;// 图片访问地址
	private String saveFileName;// 保存数据库
	private String[] fileNames;
	private String[] saveFileNames;
	private String[] fileAddress;// 图片上传地址

	public String getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public long getAllowSize() {
		return allowSize * 1024 * 1024;
	}

	public void setAllowSize(long allowSize) {
		this.allowSize = allowSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String[] getSaveFileNames() {
		return saveFileNames;
	}

	public void setSaveFileNames(String[] saveFileNames) {
		this.saveFileNames = saveFileNames;
	}

	public String[] getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String[] fileAddress) {
		this.fileAddress = fileAddress;
	}

	/**
	 * 功能：重新命名文件
	 * 
	 * @return
	 */
	private UUID getFileNameNew() {
		// SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		// return fmt.format(new Date());
		return UUID.randomUUID();
	}

	/**
	 * 功能：文件上传
	 * 
	 * @param files
	 * @param destDir
	 * @throws Exception
	 */
	public void uploads(MultipartFile[] files, String destDir,
			HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		try {
			fileNames = new String[files.length];
			saveFileNames = new String[files.length];
			fileAddress = new String[files.length];
			int index = 0;
			for (MultipartFile file : files) {
				String suffix = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf(".") + 1);
				int length = getAllowSuffix().indexOf(suffix);
				if (length == -1) {
					throw new Exception("请上传允许格式的文件");
				}
				if (file.getSize() > getAllowSize()) {
					throw new Exception("您上传的文件大小已经超出范围");
				}
				String realPath = request.getSession().getServletContext()
						.getRealPath("/");
				File destFile = new File(realPath + destDir);
				if (!destFile.exists()) {
					destFile.mkdirs();
				}
				String fileNameNew = getFileNameNew() + "." + suffix;
				File f = new File(destFile.getAbsoluteFile() + "\\"
						+ fileNameNew);
				file.transferTo(f);
				f.createNewFile();
				fileNames[index++] = basePath + destDir + fileNameNew;
				saveFileNames[index++] = destDir + fileNameNew;
			}
			// logger.info("------->文件上传地址： " + fileAddress);
			logger.info("------->保存数据库： " + saveFileNames);
			// logger.info("------->文件访问地址： " + fileNames);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 功能：文件上传
	 * 
	 * @param files
	 * @param destDir
	 * @throws Exception
	 */
	public MSG upload(MultipartFile file, String destDir,
			HttpServletRequest request) throws Exception {
		MSG msg = new MSG();

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		try {
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf(".") + 1);
			int length = getAllowSuffix().indexOf(suffix);
			if (length == -1) {
				msg.setInfo("请上传允许格式的文件");
				msg.setCode(0);
				return msg;
			}
			if (file.getSize() > getAllowSize()) {
				msg.setInfo("您上传的文件大小已经超出范围");
				msg.setCode(0);
				return msg;
			}

			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			File destFile = new File(realPath + destDir);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			String fileNameNew = getFileNameNew() + "." + suffix;
			File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			fileName = basePath + destDir + fileNameNew;
			saveFileName = destDir + fileNameNew;
			 logger.info("------->文件上传地址： " + f);
			 logger.info("------->保存数据库： " + saveFileName);
			 logger.info("------->文件访问地址： " + fileName);
		} catch (Exception e) {
			throw e;
		}
		msg.setCode(1);
		msg.setInfo(saveFileName);
		return msg;
	}

	/*************** webuploader图片上传 *****************/

}
