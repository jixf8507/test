package com.qx.eakay.service.statistics;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.car.CarDao;
import com.qx.eakay.export.statistics.CarUseStatisticsExport;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Service
public class StatisticsCarService extends BaseService {

	@Autowired
	private CarDao carDao;

	/**
	 * 分页查找车辆使用统计
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCarUsesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return carDao.findCarUsesPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出充电设备列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportCarUse(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CarUseStatisticsExport();
		List<Map<String, Object>> list = findCarUses(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索充电设备
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarUses(JSONObject jsonObject) {
		return carDao.findCarUses(jsonObject);
	}

}
