package com.qx.eakay.service.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.dao.merchant.MerchantFamilyCustomerDao;
import com.qx.eakay.dao.merchant.MerchantFamilyDao;
import com.qx.eakay.export.customer.FamilyCustomerExport;
import com.qx.eakay.export.customer.UnitCustomerExport;
import com.qx.eakay.model.merchant.MerchantFamilyCustomerPO;
import com.qx.eakay.model.merchant.MerchantFamilyPO;
import com.qx.eakay.model.sys.SysFileUrlsPO;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.service.sys.FileUrlsService;

@Service
public class MerchantFamilyService extends BaseService {

	@Autowired
	private MerchantFamilyDao merchantFamilyDao;

	@Autowired
	private MerchantFamilyCustomerDao merchantFamilyCustomerDao;

	@Autowired
	private FileUrlsService fileUrlsService;
	
	@Autowired
	private CustomerDao customerDao;

	/**
	 * 条件分页查询
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findMerchantFamilyPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return merchantFamilyDao.findMerchantFamilyPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 分页查询家庭成员列表
	 */

	public PageResults findMerchantFamilyMemberPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return merchantFamilyDao.findMerchantFamilyMemberPage(jsonObject, pageSize, iDisplayStart);

	}
	
	/**
	 * 导出家庭会员信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportFamilyCustomer(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new FamilyCustomerExport();
		List<Map<String, Object>> list = findFamilyCustomer(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	

	/**
	 * 根据id查找merchantFamily信息
	 * 
	 * @param id
	 * @return
	 */
	public MerchantFamilyPO getMerchantFamilyPO(Integer id) {
		return merchantFamilyDao.getMerchantFamilyPO(id);
	}
	
	
	
	/**
	 * 新建家庭检查选择的负责人是否已经是另外一个家庭的负责人，成员
	 */
	public String  checkCustomer(JSONObject jsonObject){
		List<Map<String, Object>> familylist=merchantFamilyDao.findMerchantFamilyByJSONObject(jsonObject);
		List<Map<String, Object>> familyRelocationlist=merchantFamilyCustomerDao.findMerchantFamilyCustomerByJSONObject(jsonObject);
		if (familylist.size()>0&&null!=familylist) {
			return familylist.get(0).get("id")+"";//返回家庭的id
		}
		if (familyRelocationlist.size()>0&&null!=familyRelocationlist) {
			return familyRelocationlist.get(0).get("familyId")+"";//返回家庭的id
		}
		
		return null;
	}
	
	/**
	 * 加入成员的时候只检查是否是家庭的成员，不检查是否是家庭的负责人
	 * @param jsonObject
	 * @return
	 */
	public String  checkCustomer2(JSONObject jsonObject){
		List<Map<String, Object>> familyRelocationlist=merchantFamilyCustomerDao.findMerchantFamilyCustomerByJSONObject(jsonObject);
		if (familyRelocationlist.size()>0&&null!=familyRelocationlist) {
			return familyRelocationlist.get(0).get("familyId")+"";//返回家庭的id
		}
		return null;
	}
	

	/**
	 * 条件家庭查询记录
	 */
	public List<Map<String, Object>> findMerchantFamilyByJSONObject(JSONObject jsonObject) {
		return merchantFamilyDao.findMerchantFamilyByJSONObject(jsonObject);
	}
	
	
	/**
	 * 条件家庭用户关系查询记录
	 */
	public List<Map<String, Object>> findMerchantFamilyCustomerByJSONObject(JSONObject jsonObject) {
		return merchantFamilyCustomerDao.findMerchantFamilyCustomerByJSONObject(jsonObject);
	}
	
	/**
	 * 创建家庭
	 */
	@Transactional
	public Boolean createFamily(MerchantFamilyPO merchantFamilyPO, String url,
			String fileName) {
		try {
			// 1.增加一条merchantFamily信息
			Integer tableId = merchantFamilyDao
					.createMerchantFamilyPO(merchantFamilyPO);

			// 2.将户主加入到家庭关系(MerchantFamilyCustomer)里面,！！！！现在在新建家庭的时候不将负责人加入到家庭关系中去，在编辑家庭的时候可以改变负责人
//			MerchantFamilyCustomerPO merchantFamilyCustomerPO = new MerchantFamilyCustomerPO();
//			merchantFamilyCustomerPO.setFamilyid(tableId);
//			merchantFamilyCustomerPO.setCustomerid(merchantFamilyPO
//					.getCustomerid());
//			merchantFamilyCustomerDao
//					.createMerchantFamilyCustomerPO(merchantFamilyCustomerPO);

			// 3.记录图片的上传路径到sys_file_url表里
			SysFileUrlsPO fileUrlsPO = new SysFileUrlsPO();
			fileUrlsPO.setFileName(fileName);
			fileUrlsPO.setFileType("创建家庭");
			fileUrlsPO.setTableId(tableId);
			fileUrlsPO.setTableName("merchant_family");
			fileUrlsPO.setFileUrl(url);
			fileUrlsPO = fileUrlsService.createFileUrls(fileUrlsPO);
			return true;
		} catch (Exception e) {
			logger.error("创建家庭失败:", e);
			e.printStackTrace();
		}
		return false;
	}

	
	
	/**
	 * 修改家庭信息包括图片路径的修改
	 */
	@Transactional
	public Boolean updateFamily(MerchantFamilyPO merchantFamilyPO,String newCustomerid,String url,String fileName,String fileId) {
		try {
			//1.修改一条merchantFamily信息
			MerchantFamilyPO localmerchantFamilyPO=merchantFamilyDao.getMerchantFamilyPO(merchantFamilyPO.getId());
			localmerchantFamilyPO.setHomeaddress(merchantFamilyPO.getHomeaddress());
			localmerchantFamilyPO.setHomephone(merchantFamilyPO.getHomephone());
			localmerchantFamilyPO.setHouseholder(merchantFamilyPO.getHouseholder());
			localmerchantFamilyPO.setCustomerid(Integer.parseInt(newCustomerid));
			merchantFamilyDao.updateMerchantFamilyPO(localmerchantFamilyPO);
			
			//2.更新负责人的家庭关系(删除原有的记录在新增一条记录)！！！！现在在新建家庭的时候不将负责人加入到家庭关系中去，在编辑家庭的时候可以改变负责人，在更新家庭的时候不用更新负责人的家庭关系。
			//merchantFamilyCustomerDao.updateMerchantFamilyCustomerId(merchantFamilyPO.getCustomerid(), merchantFamilyPO.getId(),Integer.parseInt(newCustomerid));
			//3.如果url不为空则修改的图片上传路径到sys_file_url表里
			if (null!=url) {
					//创建一条新的图片上传关系
				 	SysFileUrlsPO fileUrlsPO = new SysFileUrlsPO();
					fileUrlsPO.setFileName(fileName);
					fileUrlsPO.setFileType("创建家庭");
					fileUrlsPO.setTableId(merchantFamilyPO.getId());
					fileUrlsPO.setTableName("merchant_family");
					fileUrlsPO.setFileUrl(url);
					fileUrlsPO = fileUrlsService.createFileUrls(fileUrlsPO);
					//删旧的图片关系
					fileUrlsService.deleteSysFileUrl(Integer.parseInt(fileId));
			}
		   
			return true;
		} catch (Exception e) {
			logger.error("创建家庭失败:",e);
			e.printStackTrace();
		}
		return false;
	}
	

	/**
	 * 批量创建家庭关系
	 */
	@Transactional
	public Boolean createFamilyCustomerByIds(String idsStr,String familyId) {
			String[] ids = idsStr.split(",");
			for (String id : ids) {
				try {
						MerchantFamilyCustomerPO merchantFamilyCustomerPO =new MerchantFamilyCustomerPO();
						merchantFamilyCustomerPO.setCustomerid(Integer.parseInt(id));
						merchantFamilyCustomerPO.setFamilyid(Integer.parseInt(familyId));
						merchantFamilyCustomerDao.createMerchantFamilyCustomerPO(merchantFamilyCustomerPO);
				} catch (Exception e) {
					logger.error("批量创建家庭失败(customerId:"+id+")",e);
					e.printStackTrace();
				}
			}
			return true;
	}
	
	
	/**
	 * 查找家庭的照片,按照创建时间倒序查询，只查询最新的图片
	 */
	public SysFileUrlsPO  findFamilyImg(Integer tableId){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tableId", tableId);
		SysFileUrlsPO sysFileUrlsPO = new SysFileUrlsPO();
		List<Map<String, Object>> imgs=merchantFamilyDao.findMerchantFamilyImg(jsonObject);
		if (imgs.size()>0&&null != imgs) {
			//根据map装配对象
			Map<String, Object> map=imgs.get(0);
			sysFileUrlsPO.setFileName((String) map.get("fileName"));
			sysFileUrlsPO.setFileType((String) map.get("fileType"));
			sysFileUrlsPO.setFileUrl((String) map.get("fileUrl"));
			sysFileUrlsPO.setTableId(tableId);
			sysFileUrlsPO.setTableName((String) map.get("tableName"));
			sysFileUrlsPO.setId(Integer.parseInt(map.get("id")+""));
		}	
		return sysFileUrlsPO;
	}
	
	
	/**
	 * 根据id删除删除家庭，和家庭成员
	 */
	@Transactional
	public boolean deleteFamily(Integer id) {
		try {
			//删除家庭，和家庭成员
			merchantFamilyDao.deleteMerchantFamily(id);
			merchantFamilyCustomerDao.deleteMerchantFamilyCustomerByFamilyId(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 根据familyId删除家庭关系
	 */
	public boolean deleteFamilyRelationStatus(Integer id) {
		return merchantFamilyCustomerDao.deleteMerchantFamilyCustomerByFamilyId(id);
	}
	
	
	/**
	 * 修改家庭的status
	 */
	public boolean updateFamilyStatus(MerchantFamilyPO merchantFamilyPO) {
		return merchantFamilyDao.updateFamilyStatus(merchantFamilyPO);
	}
	
	
	/**
	 * 批量删除成员关系
	 */
	@Transactional
	public boolean bachDeleteFamilyMember(String[] ids,String familyId){
		try {
			for (int i = 0; i < ids.length; i++) {
				merchantFamilyCustomerDao.deleteMerchantFamilyCustomerByCustomerId(Integer.parseInt(familyId),Integer.parseInt(ids[i]));
			}
			return true;
		} catch (Exception e) {
			logger.error("批量删除成员失败"+e);
			e.printStackTrace();
		}
		
		 return false;
	}
	
	/**
	 * 删除成员关系
	 */
	public boolean deleteFamilyMember(Integer familyId,Integer customerId){
		return merchantFamilyCustomerDao.deleteMerchantFamilyCustomerByCustomerId(familyId, customerId);
	}
	
	
	
	/**
	 * 创建一条家庭关系
	 */
	public Boolean createFamily(MerchantFamilyCustomerPO merchantFamilyCustomerPO) {
		return  merchantFamilyCustomerDao.createMerchantFamilyCustomerPO(merchantFamilyCustomerPO)>0;
	}
	
	
	
	/**
	 * 增加一条merchantFamily信息
	 */
	public Boolean createMerchantFamilyPO(MerchantFamilyPO merchantFamilyPO) {
		return merchantFamilyDao.createMerchantFamilyPO(merchantFamilyPO) > 0;
	}
	

	/**
	 * 修改一条信息
	 * 
	 * @param merchantFamilyPO
	 * @return
	 */
	public boolean updateMerchantFamilyPO(MerchantFamilyPO merchantFamilyPO) {
		return merchantFamilyDao.updateMerchantFamilyPO(merchantFamilyPO);
	}
	
	

	/**
	 * 删除一条信息，从数据库删除。
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteMerchantFamily(Integer id) {
		return merchantFamilyDao.deleteMerchantFamily(id);
	}

	/**
	 * 分页查询家庭的用户
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findMerchantFamilyCustomerPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return merchantFamilyDao.findMerchantFamilyCustomerPage(jsonObject,
				pageSize, iDisplayStart);
	}
	
	
	
	
	/**
	 * 分页查询家庭的用户无重复记录
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findFamilyCustomerByPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return merchantFamilyDao.findFamilyCustomerByPage(jsonObject,
				pageSize, iDisplayStart);
	}
	
	
	

	
	
	/**
	 * 条件查询家庭账户的所有用户
	 */
	public List<Map<String, Object>> findFamilyCustomer(JSONObject jsonObject) {
		return merchantFamilyDao.findFamilyCustomer(jsonObject);
	}

	
	
}
