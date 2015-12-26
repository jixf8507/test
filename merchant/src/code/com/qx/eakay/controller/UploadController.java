package com.qx.eakay.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.bean.SystemConfig;
import com.qx.common.tools.MyDateUtil;
import com.qx.common.tools.StringTools;
import com.qx.eakay.model.sys.SysFileUrlsPO;
import com.qx.eakay.service.sys.FileUrlsService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/system/upload")
public class UploadController extends BaseUploadController {

	@Resource
	private FileUrlsService fileUrlsService;

	@RequestMapping(value = "/imageUpload.htm")
	@ResponseBody
	public HashMap<String, Object> imageUpload(
			@RequestParam(value = "upload", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		HashMap<String, Object> result = new HashMap<>();
		String fileUrl = copyFile(file, request);
		result.put("data", fileUrl);
		result.put("fileName", file.getOriginalFilename());
		result.put("success", true);
		return result;

	}

	@RequestMapping(value = "/uploadFile.htm")
	@ResponseBody
	public HashMap<String, Object> uploadFile(
			@RequestParam(value = "upload", required = false) MultipartFile file,
			String uploadFileName, String fileType, String tableName,
			Integer tableId, HttpServletRequest request, ModelMap model) {

		uploadFileName = StringTools.decodeMethod(uploadFileName);
		fileType = StringTools.decodeMethod(fileType);
		HashMap<String, Object> result = new HashMap<>();
		SysFileUrlsPO fileUrlsPO = new SysFileUrlsPO();
		fileUrlsPO.setFileName(uploadFileName);
		fileUrlsPO.setFileType(fileType);
		fileUrlsPO.setTableId(tableId);
		fileUrlsPO.setTableName(tableName);
		String fileUrl = copyFile(file, request);
		fileUrlsPO.setFileUrl(fileUrl);
		fileUrlsPO = fileUrlsService.createFileUrls(fileUrlsPO);
		if (fileUrlsPO == null) {
			result.put("success", false);
		} else {
			result.put("success", true);
			result.put("data", fileUrlsPO);
		}
		return result;
	}

	/**
	 * 保存审核图片
	 * 
	 * @param fileUrlsPO
	 * @return
	 */
	@RequestMapping(value = "/browseImgUpload.htm")
	@ResponseBody
	public MSG saveEdite(SysFileUrlsPO fileUrlsPO, Integer carId) {
		MSG msg = new MSG();
		if (fileUrlsPO.getFileUrl() == "") {
			return new MSG("请选择图片", 1000);
		}
		fileUrlsPO = fileUrlsService.createImgUpload(fileUrlsPO, carId);
		if (fileUrlsPO == null) {
			msg.setSuccess(false);
		} else {
			msg.setSuccess(true);
		}
		return msg;
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/loadFileById.htm")
	@ResponseBody
	public SysFileUrlsPO loadFileById(Integer id) {
		return fileUrlsService.get(id);
	}

	/**
	 * 查找车辆审核图片
	 * 
	 * @param paraData
	 * @return
	 */
	@RequestMapping(value = "/loadFile.htm")
	@ResponseBody
	public Map<String, Object> loadFiles(String paraData) {
		Map<String, Object> rs = new HashMap<>();
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		List<Map<String, Object>> list = fileUrlsService.findList(jsonObject);
		rs.put("files", list);
		MSG msg = new MSG();
		msg.setSuccess(true);
		rs.put("msg", msg);
		return rs;
	}

	/**
	 * 复制文件
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	private String copyFile(MultipartFile file, HttpServletRequest request) {
		ServletContext sc = request.getSession().getServletContext();

		String filePath = SystemConfig.newInstance().getUploadFile()
				+ MyDateUtil.getCurDate() + "/";
		String dir = sc.getRealPath(filePath);
		String fileName = file.getOriginalFilename();
		String extendName = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID() + extendName;

		File targetDir = new File(dir);
		File targetFile = new File(dir, newFileName);

		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String fileUrl = filePath + newFileName;
		return fileUrl;
	}

	/**
	 * 保存车辆图片
	 * 
	 * @param fileUrlsPO
	 * @return
	 */
	@RequestMapping(value = "/carImgUpload.htm")
	@ResponseBody
	public MSG carImgUpload(SysFileUrlsPO fileUrlsPO, Integer carId) {
		MSG msg = new MSG();
		if (fileUrlsPO.getFileUrl() == "") {
			return new MSG("请选择图片", 1000);
		}
		fileUrlsPO.setTableId(carId);
		fileUrlsPO.setFileType("车辆");
		fileUrlsPO.setTableName("car");
		msg.setSuccess(fileUrlsService.createCarImgUpload(fileUrlsPO, carId));
		return msg;
	}

	/**
	 * 查找车辆图片
	 * 
	 * @param paraData
	 * @return
	 */
	@RequestMapping(value = "/loadCarImgFile.htm")
	@ResponseBody
	public Map<String, Object> loadCarImgFile(String paraData) {
		Map<String, Object> rs = new HashMap<>();
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		List<Map<String, Object>> list = fileUrlsService
				.findCarImgFile(jsonObject);
		rs.put("files", list);
		MSG msg = new MSG();
		rs.put("msg", msg);
		return rs;
	}

	/**
	 * 删除车辆图片
	 * 
	 * @param paraData
	 * @return
	 */
	@RequestMapping(value = "/delete.htm")
	@ResponseBody
	public MSG delete(Integer id) {
		MSG msg = new MSG();
		msg.setSuccess(fileUrlsService.delete(id));
		return msg;
	}

	/************ webuploader图片上传 *****************/

	/**
	 * 上传图片页面
	 * 
	 * @param paraData
	 * @return
	 */
	@RequestMapping(value = "/page.htm")
	public ModelAndView uploadTest(String paraData) {

		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);

		return new ModelAndView("/upload/uploadPage", "jsonObject", jsonObject);
	}

	/**
	 * 上传图片
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/uploadPic.htm")
	@ResponseBody
	public String uploadPic(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			String fileType, Integer tableId, String tableName) {
		MSG msg = new MSG();
		try {
			msg = super.upload(file, "/upload/image/" + MyDateUtil.getCurDate()
					+ "/", request);
			response.getWriter().print(super.getFileName());
			// 上传成功
			if (1 == msg.getCode()) {
				logger.info("------------->上传图片: " + file.getOriginalFilename()
						+ "	 URL: " + msg.getInfo() + "  上传成功");

				/*
				 * SysFileUrlsPO fileUrlsPO = new SysFileUrlsPO();
				 * fileUrlsPO.setFileName(file.getOriginalFilename());
				 * fileUrlsPO.setFileUrl(msg.getInfo());
				 * 
				 * fileType = StringTools.decodeMethod(fileType);
				 * 
				 * if (tableId == null) { tableId = 0; } if (fileType == "" ||
				 * fileType == null) { fileType = ""; } if (tableName == "" ||
				 * tableName == null) { tableName = ""; }
				 * 
				 * fileUrlsPO.setFileType(fileType);
				 * fileUrlsPO.setTableId(tableId);
				 * fileUrlsPO.setTableName(tableName);
				 * 
				 * // 保存图片 MSG flag = savePic(fileUrlsPO);
				 * 
				 * if(flag.isSuccess()){
				 * System.out.println("------------->上传图片主键: "+flag.getInfo());
				 * }
				 */
				// 上传失败
			} else {
				logger.info("------------->上传图片: " + file.getOriginalFilename()
						+ "	 上传失败: " + msg.getInfo());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存图片
	 * 
	 * @param fileUrlsPO
	 * @return
	 */
	@RequestMapping(value = "/savePic.htm")
	@ResponseBody
	public HashMap<String, Object> savePic(SysFileUrlsPO fileUrlsPO) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		MSG msg = new MSG();
		if (fileUrlsPO.getFileUrl() == "") {
			msg.setSuccess(false);
			msg.setInfo("请选择图片");
		} else {
			String[] fileNames = fileUrlsPO.getFileName().split(",");
			String[] fileUrls = fileUrlsPO.getFileUrl().split(",");
			result.put("fileUrls", fileUrls);
			
			msg = fileUrlsService.savePic(fileUrlsPO, fileNames, fileUrls);
			result.put("ids", msg.getInfo());
			
			List<String> list = new ArrayList<String>();
			
			for(String fileUrl : fileUrls){
				list.add(fileUrl);
			}
			
			result.put("fileUrls", list);
		}
		result.put("msg", msg);
		return result;
	}
	/*
	result格式：
	{
	    fileUrls=[
	        /upload/image/2015-12-26/b7fda7ff-87f4-43a4-93e9-749b64171065.png,
	        /upload/image/2015-12-26/e9fc4f9b-75df-4b55-a9fd-3fd1a6673468.jpg,
	        /upload/image/2015-12-26/a2b5fe85-ad6a-49a5-b47e-91ba4b9cd1d3.jpg
	    ],
	    ids=[
	        1390,
	        1391,
	        1392
	    ],
	    msg=MSG[
	        isSuccess=true,
	        info=[
	            1390,
	            1391,
	            1392
	        ],
	        code=0
	    ]
	}
	 */
	/************ webuploader图片上传 *****************/

}
