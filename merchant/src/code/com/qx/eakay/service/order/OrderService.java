package com.qx.eakay.service.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.qx.eakay.dao.car.CarDetailDao;
import com.qx.eakay.dao.car.CarDeviceDao;
import com.qx.eakay.dao.customer.AccountDao;
import com.qx.eakay.dao.customer.AccountRecordDao;
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.dao.customer.CustomerMessageDao;
import com.qx.eakay.dao.customer.CustomerUnitDao;
import com.qx.eakay.dao.merchant.MerchantAccountRecordDao;
import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.dao.order.OtherOrderDao;
import com.qx.eakay.dao.price.LongPriceTypeDao;
import com.qx.eakay.dao.price.PriceTypeDao;
import com.qx.eakay.dao.sys.SysFileUrlsDao;
import com.qx.eakay.export.order.OrderExport;
import com.qx.eakay.model.car.CarDevice;
import com.qx.eakay.model.car.CarPO.CarStatus;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.model.customer.AccountRecordPO.AccountRecordType;
import com.qx.eakay.model.customer.CustomerMessagePO;
import com.qx.eakay.model.customer.UnitCustomerPO;
import com.qx.eakay.model.merchant.MerchantAccountRecordPO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.model.monitor.CarContorlCmd;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.model.order.OrderPO.OrderStatus;
import com.qx.eakay.model.price.CarLeasePriceType;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.service.merchant.CouponService;
import com.qx.eakay.socket.DeviceSocket;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class OrderService extends BaseService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OtherOrderDao otherOrderDao;
	@Autowired
	private CarDao carDao;
	@Autowired
	private CarDetailDao carDetailDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private AccountRecordDao accountRecordDao;
	@Autowired
	private MerchantAccountRecordDao merchantAccountRecordDao;
	@Autowired
	private CustomerMessageDao customerMessageDao;
	@Autowired
	private PriceTypeDao priceTypeDao;
	@Autowired
	private LongPriceTypeDao longPriceTypeDao;
	@Autowired
	private CarDeviceDao carDeviceDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerUnitDao customerUnitDao;
	@Autowired
	private CustomerUnitDao unitDao;
	@Autowired
	private CouponService couponService;
	@Autowired
	private SysFileUrlsDao fileUrlsDao;

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
	public PageResults findTableOrderPage(JSONObject jsonObject,int pageSize,
			int iDisplayStart) {
		return orderDao.findTableOrderPage(jsonObject, pageSize, iDisplayStart);
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
	 * 导出订单信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportOrder(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new OrderExport();
		List<Map<String, Object>> list = findOrders(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取订单id
	 * 
	 * @param id
	 * @return
	 */
	public OrderPO getOrder(Integer id) {
		return orderDao.get(id);
	}
	
	/**
	 * 获取订单（只查询order）
	 * 
	 * @param id
	 * @return
	 */
	public OrderPO getOrderById(Integer id) {
		return orderDao.getOrderById(id);
	}

	/**
	 * 取车时保存订单
	 * 
	 * @param orderPO
	 * @return
	 */
	@Transactional
	public MSG saveOrderForGet(OrderPO orderPO) {
		Integer orderId = orderPO.getId();
		Integer carId = orderPO.getCarId();
		// Integer customerId = orderPO.getCustomerId();
		// Integer merchantId = orderPO.getMerchantId();
		// AccountPO accountPO = accountDao.getByCustomerId(customerId,
		// merchantId);
		// Integer accountId = accountPO.getId();
		if (orderId == null) {
			// CarPO carPO = carDao.get(orderPO.getCarId());
			// if (!CarStatus.空闲.name().equals(carPO.getStatus())) {
			// return new MSG("该车辆已被使用，请更换车辆！", 1);
			// }
			// int count = orderDao.findCuserUseOrders(accountId);
			// if (count > 0) {
			// return new MSG("该客户当前有未结束的租赁业务，请更换客户！", 1);
			// }
			// 没有预约，创建订单
			orderId = orderDao.create(orderPO);
		} else {
			// 有预约订单，修改订单
			orderDao.updateForGet(orderPO);
		}
		// 改变车辆为使用状态
		carDao.updateStatus(CarStatus.使用, orderPO.getCarId());
		
		//保存车辆图片（car_image--->order_image）
		saveCarImageToOrder(orderId,carId);
		
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
	 * 发送取车命令
	 * 
	 * @param cmd
	 * @param orderId
	 * @param orderTypwee
	 * @return
	 */
	public MSG ajaxSendCarCmd(CarContorlCmd cmd, Integer orderId,
			Integer orderType) {
		MSG msg = new MSG();

		OrderPO orderPo = null;
		// 车辆调度，公务用车
		if (orderType == 3 || orderType == 4) {
			orderPo = otherOrderDao.get(orderId);
			// 用户租车，企业租车
		} else {
			orderPo = orderDao.get(orderId);
		}

		Integer customerID = orderPo.getCustomerId();
		Integer car_ID = orderPo.getCarId();
		String carid = orderPo.getCarPO().getCarNumber();
		Integer merchantID = orderPo.getMerchantId();
		// orderType = orderPo.getOrderType();//
		// //////////////////////////////////////////////////

		CarDevice carDevice = carDeviceDao.carDeviceByCarId(car_ID);
		String deviceNO = carDevice.getDeviceNO();

		cmd.setOrder("8");
		cmd.setDeviceNO(deviceNO);
		cmd.setBookID(orderId + "");
		cmd.setCheckOut("123456");

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("data", cmd);
		map.put("customerID", customerID);
		map.put("orderID", orderId);
		map.put("car_ID", car_ID);
		map.put("carid", carid);
		map.put("merchantID", merchantID);

		msg = DeviceSocket.sendCarContorlCmd(map);

		// 发送命令失败，取消订单
		if (!msg.isSuccess()) {
			cancel(orderId, orderType);
		}
		return msg;
	}
	
	/**
	 * 预约取车发送取车命令
	 * 
	 * @param cmd
	 * @param orderId
	 * @param orderTypwee
	 * @return
	 */
	public MSG ajaxSendBookCarCmd(CarContorlCmd cmd, Integer orderId,
			Integer orderType) {
		MSG msg = new MSG();
		
		OrderPO orderPo = null;
		// 车辆调度，公务用车
		if (orderType == 3 || orderType == 4) {
			orderPo = otherOrderDao.get(orderId);
			// 用户租车，企业租车
		} else {
			orderPo = orderDao.get(orderId);
		}
		
		Integer customerID = orderPo.getCustomerId();
		Integer car_ID = orderPo.getCarId();
		String carid = orderPo.getCarPO().getCarNumber();
		Integer merchantID = orderPo.getMerchantId();
		// orderType = orderPo.getOrderType();//
		// //////////////////////////////////////////////////
		
		CarDevice carDevice = carDeviceDao.carDeviceByCarId(car_ID);
		String deviceNO = carDevice.getDeviceNO();
		
		cmd.setOrder("8");
		cmd.setDeviceNO(deviceNO);
		cmd.setBookID(orderId + "");
		cmd.setCheckOut("123456");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("data", cmd);
		map.put("customerID", customerID);
		map.put("orderID", orderId);
		map.put("car_ID", car_ID);
		map.put("carid", carid);
		map.put("merchantID", merchantID);
		
		msg = DeviceSocket.sendCarContorlCmd(map);
		
//		// 发送命令失败，取消订单
//		if (!msg.isSuccess()) {
//			cancel(orderId, orderType);
//		}
		return msg;
	}

	/**
	 * 取消订单
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean cancel(Integer id, Integer orderType) {
		OrderPO orderPO = null;
		// 车辆调度、公务用车
		if (orderType == 3 || orderType == 4) {
			orderPO = otherOrderDao.get(id);
			// 普通用户租车、企业租车
		} else {
			orderPO = orderDao.get(id);
		}
		// 将车辆改成空闲状态
		carDao.updateStatus(CarStatus.空闲, orderPO.getCarId());
		// 取消订单
		return orderDao.updateStatus(OrderStatus.已取消, "正常", "等待审查", id);
	}

	/**
	 * 提交保存取车审核
	 * 
	 * @param orderPO
	 * @return
	 */
	@Transactional
	public boolean saveGetConfirmEdite(OrderPO orderPO) {
		// 批量更改车辆的当前详细状态
//		carDetailDao.batchUpdateDetails(orderPO.getCarPO().getCarDetails());

//		StringBuffer sb = new StringBuffer();
//		for (CarDetailPO carDetailPO : orderPO.getCarPO().getCarDetails()) {
//			sb.append(carDetailPO.getComponent() + ":"
//					+ carDetailPO.getStatus() + ":" + carDetailPO.getDescribe()
//					+ "<br/>");
//
//		}
//		orderPO.setGetCarStatus(sb.toString());
		// 更改车辆的当前使用状态
		carDao.updateCurSite(orderPO.getRelityGetSiteId(), CarStatus.使用,
				orderPO.getKmsForGet(), orderPO.getSurplusKmsForGet(),
				orderPO.getCarId());
		// 更改订单的取车状态
		return orderDao.updateForGetConfirm(orderPO);
	}

	/**
	 * 还车时保存订单
	 * 
	 * @param orderPO
	 * @return
	 */
	@Transactional
	public boolean saveOrderForReturn(OrderPO orderPO) {
		// 更改车辆的当前使用状态
		// carDao.updateCurSite(orderPO.getRelityReturnSiteId(), CarStatus.使用,
		// orderPO.getKmsForReturn(), orderPO.getCarId());

		Integer orderType = orderPO.getOrderType();
		Integer orderId = orderPO.getId();
		String tableName = orderPO.getPriceTypeTableName();

		// 更改订单状态
		boolean flag = orderDao.updateForReturn(orderPO);

		// 普通客户,企业会员
		if (orderType == 1 || orderType == 2) {

			orderPO = orderDao.get(orderId);

			CarLeasePriceType priceTypePO;
			if ("wh_hours_price_type".equals(tableName)) {
				// 计算订单租赁费用
				priceTypePO = priceTypeDao.get(orderPO.getPriceTypeId());
			} else {
				// 计算订单租赁费用
				priceTypePO = longPriceTypeDao.get(orderPO.getPriceTypeId());
			}
			orderPO = priceTypePO.countCostForOrder(orderPO);
			// 更改订单的租赁费用
			return orderDao.updateCost(orderPO);
			// 车辆调度,公务用车
		} else {
			return flag;
		}

	}

	/**
	 * 保存还车审核
	 * 
	 * @param orderPO
	 * @return
	 */
	@Transactional
	public boolean saveReturnConfirmEdite(OrderPO orderPO) {

		// 批量更改车辆的当前详细状态
//		carDetailDao.batchUpdateDetails(orderPO.getCarPO().getCarDetails());

//		StringBuffer sb = new StringBuffer();
//		for (CarDetailPO carDetailPO : orderPO.getCarPO().getCarDetails()) {
//			sb.append(carDetailPO.getComponent() + ":"
//					+ carDetailPO.getStatus() + ":" + carDetailPO.getDescribe()
//					+ "<br/>");
//
//		}
//		orderPO.setReturnCarStatus(sb.toString());

		String currentCarStatus = orderPO.getCurrentCarStatus();
		Integer priceTypeId = orderPO.getPriceTypeId();

		// if (priceTypeId != 0) {
		// 有设备
		if (currentCarStatus.length() > 0) {
			// 更改车辆的当前使用状态
			carDao.updateCurSite(orderPO.getRelityReturnSiteId(), CarStatus.使用,
					orderPO.getKmsForReturn(),
					orderPO.getSurplusKmsForReturn(), orderPO.getCarId());
			// 更改订单的还车状态
			orderPO.setReturnCarConfirm("已审查");
			orderDao.updateForReturnConfirm(orderPO);
			// 无设备
		} else {
			// 更改车辆的当前使用状态
			carDao.updateCurSite(orderPO.getRelityReturnSiteId(), CarStatus.空闲,
					orderPO.getKmsForReturn(),
					orderPO.getSurplusKmsForReturn(), orderPO.getCarId());
			// 更改订单的还车状态
			orderPO.setReturnCarConfirm("已关门");
			orderDao.updateForReturnConfirm(orderPO);
		}
		if (priceTypeId != 0) {
			// 更改订单的租赁费用
			return orderDao.updateCost(orderPO);
		} else {
			orderPO.setUseCost(new BigDecimal(0));
			orderPO.setTotalCost(new BigDecimal(0));
			orderPO.setPerKms(0f);
			orderPO.setPerKmsCost(new BigDecimal(0));
			orderPO.setOtherCost(new BigDecimal(0));
			orderPO.setOtherDescribe("");
			orderPO.setMaintenanceCost(new BigDecimal(0));
			return orderDao.updateCost(orderPO);
		}
		// } else {
		// return returnCar(orderPO, currentCarStatus);
		// }
	}

	/**
	 * 车辆调度，公务用车还车（不收费）
	 * 
	 * @param orderPO
	 * @param currentCarStatus
	 * @return
	 */
	public boolean returnCar(OrderPO orderPO, String currentCarStatus) {
		if (currentCarStatus.length() > 0) {
			// 更改车辆的当前使用状态
			carDao.updateCurSite(orderPO.getRelityReturnSiteId(), CarStatus.使用,
					orderPO.getKmsForReturn(),
					orderPO.getSurplusKmsForReturn(), orderPO.getCarId());
			// 更改订单的还车状态
			orderPO.setReturnCarConfirm("已审查");
			return orderDao.updateForReturnConfirm(orderPO);
			// 无设备
		} else {
			// 更改车辆的当前使用状态
			carDao.updateCurSite(orderPO.getRelityReturnSiteId(), CarStatus.空闲,
					orderPO.getKmsForReturn(),
					orderPO.getSurplusKmsForReturn(), orderPO.getCarId());
			// 更改订单的还车状态
			orderPO.setReturnCarConfirm("已关门");
			orderDao.updateForReturnConfirm(orderPO);

			// 车辆调度、公务用车更改订单 状态
			orderPO.setMenForCharge(orderPO.getMenForReturn());
			return orderDao.updateForNoCharge(orderPO);
		}
	}

	/**
	 * 保存租赁收费
	 * 
	 * @param orderPO
	 * @return
	 */
	@Transactional
	public MSG saveOrderForCharge(OrderPO orderPO) {
		MSG msg = new MSG();

		String ticket = orderPO.getTicket();
		if (ticket == null) {
			orderPO.setTicket("");
		}

		if (!"余额付费".equals(orderPO.getPayment())) {
			// 现金、刷卡支付
			return saveCashPay(orderPO);
		}

		// 更改订单的收费状态
		if (!orderDao.updateForCharge(orderPO)) {
			return new MSG("订单已经完成收费，不能重复收费！", 1001);
		}
		orderPO = orderDao.get(orderPO.getId());

		// 企业会员
		if (orderPO.getUnitCustomerPO().getEnterpriseName() != null) {
			deductUnitCustomerBalance(orderPO);
			// 普通客户
		} else {
			deductCustomerBalance(orderPO);
		}

		// 创建租赁付费消息
		customerMessageDao.create(CustomerMessagePO.createChargeMsg(orderPO));
		// 增加商家账户记录
//		msg.setSuccess(addMerchantAccountBlance(orderPO));

		Integer couponId = orderPO.getCouponId();
		if (couponId != 0) {
			msg.setSuccess(changeCouponStatus(orderPO.getCustomerId(),couponId));
		}
		return msg;
	}

	/**
	 * 扣除企业会员的账户余额
	 * 
	 * @param orderPO
	 */
	private void deductUnitCustomerBalance(OrderPO orderPO) {
		// 创建企业会员账户付费记录

		Integer unitCustomerId = orderPO.getCustomerId();

		UnitCustomerPO unitCustomerPO = customerUnitDao
				.getUnitCustomer(unitCustomerId);

		BigDecimal addBalance = new BigDecimal(0).subtract(orderPO
				.getTotalCost());

		AccountRecordPO recordPO = AccountRecordPO.createUnitAddBalanceRecord(
				unitCustomerPO, addBalance, -1, AccountRecordType.租赁付费,
				"租赁点扣除", orderPO.getMenForCharge(),
				orderPO.getRelityReturnSiteId(), orderPO.getTicket());

		accountRecordDao.create(recordPO);

		// 修改企业会员账户余额
		customerUnitDao
				.addUnitBalance(recordPO.getAddBalance(), unitCustomerId);
	}

	/**
	 * 保存租赁现金收费
	 * 
	 * @param orderPO
	 * @return
	 */
	@Transactional
	public MSG saveCashPay(OrderPO orderPO) {

		// 检查订单的收费状态
		OrderPO order = orderDao.get(orderPO.getId());
		if ("已付费".equals(order.getOrderStatus())) {
			return new MSG("订单已经完成收费，不能重复收费！", 1001);
		} else {
			MSG msg = new MSG();
			msg.setSuccess(orderDao.updateForCashCharge(orderPO));

			Integer couponId = orderPO.getCouponId();
			if (couponId != null) {
				msg.setSuccess(changeCouponStatus(orderPO.getCustomerId(),couponId));
			}
			return msg;
		}

	}

	/**
	 * 使用优惠券
	 * 
	 * @param orderPO
	 */
	private boolean changeCouponStatus(Integer customerId,Integer id) {
		return couponService.useCoupon(customerId,id, "已用");
	}

	/**
	 * 扣除用户账户余额
	 * 
	 * @param orderPO
	 */
	private void deductCustomerBalance(OrderPO orderPO) {
		// 创建用户账户付费记录
		AccountPO accountPO = accountDao.getByCustomerId(
				orderPO.getCustomerId(), orderPO.getMerchantId());

		BigDecimal addBalance = new BigDecimal(0).subtract(orderPO
				.getTotalCost());
		AccountRecordPO recordPO = AccountRecordPO.createAddBalanceRecord(
				accountPO, addBalance, AccountRecordType.租赁付费, "租赁点扣除",
				orderPO.getMenForCharge(), orderPO.getRelityReturnSiteId(),
				orderPO.getTicket());

		accountRecordDao.create(recordPO);
		// 修改账户余额
		accountDao.addBalance(recordPO.getAddBalance(), accountPO.getId());
	}

	/**
	 * 增加商家账户余额
	 * 
	 * @param orderPO
	 * @return
	 */
	private boolean addMerchantAccountBlance(OrderPO orderPO) {
		MerchantPO merchantPO = merchantDao.get(orderPO.getMerchantId());
		// 创建商家账户收款记录
		MerchantAccountRecordPO accountRecordPO = MerchantAccountRecordPO
				.createAddBalanceRecord(merchantPO, orderPO.getTotalCost(),
						AccountRecordType.租赁付费, orderPO.getMenForCharge(),
						orderPO.getRelityReturnSiteId());
		return merchantAccountRecordDao.create(accountRecordPO) > 0;
		// 修改商家账户余额
		// return merchantDao.addBalance(accountRecordPO.getAddBalance(),
		// new BigDecimal(0), merchantPO.getId());
	}

	/**
	 * 查找超出时间未前来取车的订单
	 * 
	 * @param orders
	 * @return
	 */
	public List<Map<String, Object>> findOutTimeOrder() {
		return orderDao.findOutTimeOrder();
	}
	
	/**
	 * 查找超出时间未前来取车的订单
	 * 
	 * @param orders
	 * @return
	 */
	public List<Map<String, Object>> findOutReturnTimeOrder() {
		return orderDao.findOutReturnTimeOrder();
	}
	
	/**
	 * 取消用户订单
	 * 
	 * @param orders
	 */
	@Transactional
	public void cancelReturnOrders(List<Map<String, Object>> orders) {
		if (orders == null || orders.isEmpty()) {
			return;
		}

		List<Integer> orderIds = new ArrayList<>();
		List<Integer> carIds = new ArrayList<>();
		List<CustomerMessagePO> messagers = new ArrayList<>();
		for (Map<String, Object> order : orders) {
			Integer orderId = Integer.parseInt(order.get("id") + "");
			Integer customerId = Integer.parseInt(order.get("customerId") + "");
			Integer carId = Integer.parseInt(order.get("carId") + "");

			orderIds.add(orderId);
			carIds.add(carId);

			logger.info("orderId: " + order.get("id"));

			CustomerMessagePO msg = CustomerMessagePO.createOrderCancelReturnMsg(
					orderId, customerId);
			messagers.add(msg);
		}

		// 批量修改订单状态
		orderDao.batchUpdateStaus(OrderStatus.已取车, "系统取消", orderIds);
		logger.info("批量修改订单状态: orderIds: " + orderIds);		
		// 批量插入客户消息
		customerMessageDao.batchCreateMsg(messagers);
	}

	/**
	 * 取消用户订单
	 * 
	 * @param orders
	 */
	@Transactional
	public void cancelOrders(List<Map<String, Object>> orders) {
		if (orders == null || orders.isEmpty()) {
			return;
		}

		List<Integer> orderIds = new ArrayList<>();
		List<Integer> carIds = new ArrayList<>();
		List<CustomerMessagePO> messagers = new ArrayList<>();
		for (Map<String, Object> order : orders) {
			Integer orderId = Integer.parseInt(order.get("id") + "");
			Integer customerId = Integer.parseInt(order.get("customerId") + "");
			Integer carId = Integer.parseInt(order.get("carId") + "");

			orderIds.add(orderId);
			carIds.add(carId);

			logger.info("orderId: " + order.get("id"));

			CustomerMessagePO msg = CustomerMessagePO.createOrderCancelMsg(
					orderId, customerId);
			messagers.add(msg);
		}

		// 批量修改订单状态
		orderDao.batchUpdateStaus(OrderStatus.已取消, "系统取消", orderIds);
		logger.info("批量修改订单状态: orderIds: " + orderIds);
		// 批量修改车辆的状态
		carDao.batchUpdateStatus(CarStatus.空闲, carIds);
		// 批量插入客户消息
		customerMessageDao.batchCreateMsg(messagers);
	}

	/**
	 * 取消还车
	 * 
	 * @param id
	 * @return
	 */
	public boolean cancelReturn(Integer id) {
		return orderDao.updateStatus(OrderStatus.已取车, "正常", "等待审查", id);
	}

	/**
	 * 查找用户未结束的租赁业务套餐
	 * 
	 * @param accountId
	 * @return
	 */
	public Integer findCuserUseOrders(Integer customerId, Integer merchantId) {
		return orderDao.findCuserUseOrders(customerId, merchantId);
	}

	/**
	 * 查找单位会员未结束的租赁业务套餐
	 * 
	 * @param id
	 * @return
	 */
	public Integer findUnitCuserUseOrders(Integer id) {
		return orderDao.findUnitCuserUseOrders(id);
	}

	/**
	 * 核对时修改订单内容
	 * 
	 * @param id
	 * @return
	 */
	public boolean checkForCharge(OrderPO orderPO) {
		return orderDao.checkForCharge(orderPO);
	}
	
	/**
	 * 修改订单状态
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateStatus(OrderStatus orderStatus,String returnCarConfirm,Integer orderId) {
		return orderDao.updateStatus(orderStatus, "正常",returnCarConfirm, orderId);
	}

	/**
	 * 计算订单费用
	 * 
	 * @param orderPO
	 * @return
	 */
	public OrderPO countKmsCost(Integer orderId, float kmsForReturn) {

		OrderPO orderPO = orderDao.get(orderId);
		orderPO.setKmsForReturn(kmsForReturn);

		String tableName = orderPO.getPriceTypeTableName();
		CarLeasePriceType priceTypePO;

		if ("wh_hours_price_type".equals(tableName)) {
			// 计算订单租赁费用
			priceTypePO = priceTypeDao.get(orderPO.getPriceTypeId());

		} else {
			// 计算订单租赁费用
			priceTypePO = longPriceTypeDao.get(orderPO.getPriceTypeId());
		}
		return priceTypePO.countCostForOrder(orderPO);
	}

	/**
	 * 保存车辆调度、公务用车收费
	 * 
	 * @param orderPO
	 * @return
	 */
	@Transactional
	public MSG saveOtherOrderForCharge(OrderPO orderPO) {
		MSG msg = new MSG();
		OrderPO order = orderDao.getOrderById(orderPO.getId());
		if ("已付费".equals(order.getOrderStatus())) {
			return new MSG("订单已经完成收费，不能重复收费！", 1001);
		}
		
		// 更改订单为收费状态
		msg.setSuccess(orderDao.updateForNoCharge(orderPO));
		return msg;
	}

	
	
}
