package com.qx.eakay.service.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.customer.AccountDao;
import com.qx.eakay.dao.order.PeccancyRecordDao;
import com.qx.eakay.export.order.PeccancyRecordExport;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.AccountPO.AccountStatus;
import com.qx.eakay.model.order.OrderPeccancyRecordPO;
import com.qx.eakay.service.BaseService;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 
 * @author jixf
 * @date 2015年7月8日
 */
@Service
public class PeccancyRecordService extends BaseService {

	@Autowired
	private PeccancyRecordDao peccancyRecordDao;

	/**
	 * 分页查询违章记录列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findPeccancyRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return peccancyRecordDao.findPeccancyRecordPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 条件搜索违章记录信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findPeccancyRecords(JSONObject jsonObject) {
		return peccancyRecordDao.findPeccancyRecords(jsonObject);
	}

	/**
	 * 导出违章记录信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportPeccancyRecord(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new PeccancyRecordExport();
		List<Map<String, Object>> list = findPeccancyRecords(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增违章记录
	 * 
	 * @param peccancyRecordPO
	 * @return
	 */
	public boolean createPeccancyRecord(OrderPeccancyRecordPO peccancyRecordPO) {
		return peccancyRecordDao.create(peccancyRecordPO) > 0;
	}

	/**
	 * 根据id查找违章记录
	 * 
	 * @param id
	 * @return
	 */
	public OrderPeccancyRecordPO getById(Integer id) {
		return peccancyRecordDao.getById(id);
	}

	/**
	 * 保存修改违章记录
	 * 
	 * @param peccancyRecordPO
	 * @return
	 */
	public boolean editPeccancyRecord(OrderPeccancyRecordPO peccancyRecordPO) {
		return peccancyRecordDao.editPeccancyRecord(peccancyRecordPO);
	}
	
   /**
    * *根据用户上传文件查询订单
    * @param resultList
    * @return
    */
    public List<Map<String, Object>> selectOrder(
			List<Map<String, Object>> resultList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : resultList) {
			JSONObject jsonObject = new JSONObject();

			for (String key : map.keySet()) {
				jsonObject.put(key, map.get(key));
			}
			if((jsonObject.get("carNumber")!="")&&(jsonObject.get("time")!="")){
				if ((peccancyRecordDao.selectOrder(jsonObject)) != null){
					List<Map<String, Object>> result = peccancyRecordDao
							.selectOrder(jsonObject);
					map.put("orderId", (result.get(0).get("id")));
					map.put("schemingGetTime", (result.get(0)).get("schemingGetTime"));
					map.put("schemingReturnTime", (result.get(0)).get("schemingReturnTime"));
					
					list.add(map);
				}
			}
			else{ continue;}
		}
		return list;
	}

     /**
      * 
      * 插入用户导入成功的数据
      *
      */
	public boolean save_insert(OrderPeccancyRecordPO peccancyRecordPO) {
		
		return peccancyRecordDao.save_insert_PeccancyRecord(peccancyRecordPO)>0;
	}
	
	/**
	 * 查询两次违章的用户返回是否发送成功
	 * 
	 * 
	 */
	public boolean peccancyRecordSendMessage() {
	
		return peccancyRecordDao.peccancyRecordSendMessage();
	}
	
}


