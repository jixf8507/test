package com.qx.eakay.export.order;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class OrderExport extends BaseExport {

	@Override
	protected void writeTile() throws Exception {
		int column = 0;
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "租赁收费记录统计报表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "订单号", wctB));
		wsheet.addCell(new Label(column++, 1, "车牌号码", wctB));
		wsheet.addCell(new Label(column++, 1, "租赁套餐", wctB));
		wsheet.addCell(new Label(column++, 1, "客户姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "取车时间", wctB));
		wsheet.addCell(new Label(column++, 1, "取车租赁点", wctB));
		wsheet.addCell(new Label(column++, 1, "取车公里数", wctB));
		wsheet.addCell(new Label(column++, 1, "取车续航里程", wctB));
		wsheet.addCell(new Label(column++, 1, "取车经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "还车时间", wctB));
		wsheet.addCell(new Label(column++, 1, "还车租赁点", wctB));
		wsheet.addCell(new Label(column++, 1, "还车公里数", wctB));
		wsheet.addCell(new Label(column++, 1, "还车续航里程", wctB));
		wsheet.addCell(new Label(column++, 1, "还车经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "行驶公里数", wctB));
		wsheet.addCell(new Label(column++, 1, "超出公里数收费", wctB));
		wsheet.addCell(new Label(column++, 1, "租赁收费", wctB));
		wsheet.addCell(new Label(column++, 1, "其他收费", wctB));
		wsheet.addCell(new Label(column++, 1, "维修收费", wctB));
		wsheet.addCell(new Label(column++, 1, "优惠券编码", wctB));
		wsheet.addCell(new Label(column++, 1, "优惠券名称", wctB));
		wsheet.addCell(new Label(column++, 1, "优惠券金额", wctB));
		wsheet.addCell(new Label(column++, 1, "优惠金额", wctB));
		wsheet.addCell(new Label(column++, 1, "总收费", wctB));
		wsheet.addCell(new Label(column++, 1, "收费经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "付费状态", wctB));
		wsheet.addCell(new Label(column++, 1, "票据编号", wctB));
		wsheet.addCell(new Label(column++, 1, "支付方式", wctB));
		wsheet.addCell(new Label(column++, 1, "核对人", wctB));
		wsheet.addCell(new Label(column++, 1, "核对描述", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		BigDecimal useCost = new BigDecimal(0);
		BigDecimal otherCost = new BigDecimal(0);
		BigDecimal maintenanceCost = new BigDecimal(0);
		BigDecimal totleCost = new BigDecimal(0);
		BigDecimal perKmsCost = new BigDecimal(0);
		BigDecimal coBalance = new BigDecimal(0);
		BigDecimal couponCost = new BigDecimal(0);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, "ZCDD_"
						+ toString(map.get("id")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("carNumber")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("typeName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("phone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("realityGetTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("gsiteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("kmsForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("surplusKmsForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("menForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("realityReturnTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("rsiteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("kmsForReturn")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("surplusKmsForReturn")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("menForReturn")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("kms")), wcsB));

				BigDecimal addBalance1 = new BigDecimal(map.get("useCost") + "");
				useCost = useCost.add(addBalance1);

				BigDecimal addBalance2 = new BigDecimal(map.get("otherCost")
						+ "");
				otherCost = otherCost.add(addBalance2);

				BigDecimal addBalance3 = new BigDecimal(
						map.get("maintenanceCost") + "");
				maintenanceCost = maintenanceCost.add(addBalance3);

				BigDecimal addBalance4 = new BigDecimal(map.get("perKmsCost")
						+ "");
				perKmsCost = perKmsCost.add(addBalance4);

				BigDecimal addBalance = new BigDecimal(map.get("totalCost")
						+ "");
				totleCost = totleCost.add(addBalance);
				
				BigDecimal addBalance6 = new BigDecimal(map.get("couponCost")
						+ "");
				couponCost = couponCost.add(addBalance6);

				if (map.get("coBalance") == null || map.get("coBalance") == "") {
					coBalance = coBalance.add(new BigDecimal(0));
				} else {
					BigDecimal addBalance5 = new BigDecimal(
							map.get("coBalance") + "");
					coBalance = coBalance.add(addBalance5);
				}

				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("perKmsCost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("useCost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("otherCost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("maintenanceCost")), wcsB));

				String couponId = "";
				if (!"".equals(map.get("couponId") + "")) {
					couponId = "ykzc-" + map.get("couponId");
				}

				wsheet.addCell(new Label(column++, i + 2, couponId, wcsB));

				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("couponName")), wcsB));

				String couponBalance = "";
				if (!"".equals(map.get("coBalance") + "")) {
					couponBalance = "-" + map.get("coBalance");
				}

				wsheet.addCell(new Label(column++, i + 2, couponBalance, wcsB));
				
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("couponCost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("totalCost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("menForCharge")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("orderStatus")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("ticket")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("payment")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("checkUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("checkDescribe")), wcsB));
			}
		}
		int column = 0;
		wsheet.setRowView(list.size() + 2, 400);
		wsheet.addCell(new Label(column++, list.size() + 2, "总计", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2,
				toString(perKmsCost), wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, toString(useCost),
				wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2,
				toString(otherCost), wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2,
				toString(maintenanceCost), wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2,
				"-"+toString(coBalance), wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2,
				toString(couponCost), wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2,
				toString(totleCost), wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
	}

	@Override
	protected String fileName() {
		String filename = "租赁收费记录统计报表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
