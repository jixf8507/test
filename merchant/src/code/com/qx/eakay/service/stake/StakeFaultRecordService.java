package com.qx.eakay.service.stake;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.common.highcharts.ChartsBO;
import com.qx.common.highcharts.PieSerieBO;
import com.qx.common.highcharts.PointData;
import com.qx.common.highcharts.SerieBO.SerieType;
import com.qx.eakay.dao.stake.StakeFaultRecordDao;
import com.qx.eakay.export.stake.StakeFaultRecordExport;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Service
public class StakeFaultRecordService extends BaseService {

	@Autowired
	private StakeFaultRecordDao faultRecordDao;

	/**
	 * 分页查找充电故障
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findFaultRecordPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return faultRecordDao.findFaultRecordPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 导出充电故障列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportFaultRecord(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new StakeFaultRecordExport();
		List<Map<String, Object>> list = findFaultRecord(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索充电故障
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findFaultRecord(JSONObject jsonObject) {
		return faultRecordDao.findFaultRecord(jsonObject);
	}

	public ChartsBO<PointData> getFaultSeries(JSONObject jsonObject) {
		ChartsBO<PointData> chartsBO = new ChartsBO<PointData>();
		PieSerieBO pieSerieBO = new PieSerieBO("充电设备故障比例");
		pieSerieBO.setType(SerieType.pie);
		List<Map<String, Object>> list = faultRecordDao
				.findFaultRecordStatistics(jsonObject);
		for (Map<String, Object> dataMap : list) {
			PointData pointData = new PointData(dataMap.get("faultNo") + "");
			pointData.setY(Integer.parseInt(dataMap.get("cs") + ""));
			pieSerieBO.getData().add(pointData);
		}
		chartsBO.getSeries().add(pieSerieBO);
		return chartsBO;
	}

}
