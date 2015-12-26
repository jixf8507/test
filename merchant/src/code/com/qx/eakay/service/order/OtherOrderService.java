package com.qx.eakay.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.car.CarDao;
import com.qx.eakay.dao.order.OtherOrderDao;
import com.qx.eakay.dao.sys.SysFileUrlsDao;
import com.qx.eakay.export.order.ControlOrderExport;
import com.qx.eakay.export.order.ControlOrderRecordExport;
import com.qx.eakay.export.order.OfficialOrderExport;
import com.qx.eakay.export.order.OfficialOrderRecordExport;
import com.qx.eakay.model.car.CarPO.CarStatus;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.model.order.OrderPO.OrderStatus;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.MSG;

/**
 * 车辆调度、公务用车
 * 
 * @author sdf
 * @date 2015年10月12日
 */
/**
 * @author Administrator
 * 
 */
@Service
public class OtherOrderService extends BaseService {

	@Autowired
	private OtherOrderDao orderDao;
	@Autowired
	private CarDao carDao;
	@Autowired
	private SysFileUrlsDao fileUrlsDao;

	/**
	 * 取车时保存订单
	 * 
	 * @param orderPO
	 * @return
	 */
	@Transactional
	public MSG saveOrderForGet(OrderPO orderPO) {
		// 创建订单
		Integer orderId = orderDao.create(orderPO);
		// 改变车辆为使用状态
		carDao.updateStatus(CarStatus.使用, orderPO.getCarId());
		
		//保存车辆图片（car_image--->order_image）
		saveCarImageToOrder(orderId,orderPO.getCarId());
		
		MSG msg = new MSG();
		msg.setCode(orderId);

		return msg;
	}
	
	/**
	 * 保存车辆图片（car_image--->order_image）
	 * 
	 */
	@Transactional
	private void saveCarImageToOrder(Integer orderId,Integer carId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("carId", carId);
		//查找车辆图片
		List<Map<String, Object>> list = fileUrlsDao.findCarImgFile(jsonObject);
		List<Integer> fileIds = new ArrayList<Integer>();
		for (Map<String, Object> dataMap : list) {
			Integer fileId = Integer.parseInt(dataMap.get("fileId")+"");
			fileIds.add(fileId);
		}
		//保存至order_image表
		fileUrlsDao.batchCreateOrderImgRelation(fileIds,orderId,"取车");
	}

	/**
	 * 取消订单
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean cancel(Integer id) {
		// 将车辆改成空闲状态
		OrderPO orderPO = orderDao.get(id);
		carDao.updateStatus(CarStatus.空闲, orderPO.getCarId());
		// 取消订单
		return orderDao.updateStatus(OrderStatus.已取消, "正常", "等待审查", id);
	}

	/**
	 * 分页查询订单列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return orderDao.findOrderPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 分页查询订单列表（只查询表格显示内容）
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findTableOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return orderDao.findTableOrderPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出订单信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportOrder(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = null;
		// 公务用车
		if (Integer.parseInt(jsonObject.getString("orderType")) == 4) {
			export = new OfficialOrderExport();
			// 车辆调度
		} else {
			export = new ControlOrderExport();
		}

		List<Map<String, Object>> list = findOrders(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出员工用车记录信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void exportTotleToExcel(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = null;
		// 公务用车
		if (Integer.parseInt(jsonObject.getString("orderType")) == 4) {
			export = new OfficialOrderRecordExport();
			// 车辆调度
		} else {
			export = new ControlOrderRecordExport();
		}

		List<Map<String, Object>> list = findOrders(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索订单信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findOrders(JSONObject jsonObject) {
		return orderDao.findOrders(jsonObject);
	}

	/**
	 * 获取订单
	 * 
	 * @param id
	 * @param orderType
	 * @return
	 */
	public OrderPO getOrder(Integer id) {
		return orderDao.get(id);
	}

}
