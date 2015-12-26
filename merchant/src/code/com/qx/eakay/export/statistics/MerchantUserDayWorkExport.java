package com.qx.eakay.export.statistics;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 每日工作报表
 * 
 * @author jixf
 * @date 2015年10月14日
 */
public class MerchantUserDayWorkExport extends BaseExport {

	private List<Map<String, Object>> getCarList;
	private List<Map<String, Object>> returnCarList;
	private List<Map<String, Object>> createCustomerList;

	public void toExcel(HttpServletResponse response, Map<String, Object> map)
			throws Exception {
		getCarList = (List<Map<String, Object>>) map.get("getCarList");
		returnCarList = (List<Map<String, Object>>) map.get("returnCarList");
		createCustomerList = (List<Map<String, Object>>) map
				.get("createCustomerList");
		init(response);
		doExport(null);
		close();
	}

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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "每日业务报表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		wsheet.setRowView(1, 400);
		wsheet.mergeCells(0, 1, 3, 1);
		wsheet.addCell(new Label(0, 1, "取车(" + getCarList.size() + ")", wcsB));
		wsheet.mergeCells(4, 1, 7, 1);
		wsheet.addCell(new Label(4, 1, "还车(" + returnCarList.size() + ")", wcsB));
		wsheet.mergeCells(8, 1, 12, 1);
		wsheet.addCell(new Label(8, 1, "开户(" + createCustomerList.size() + ")",
				wcsB));

		int column = 0;
		wsheet.setRowView(2, 400);
		wsheet.addCell(new Label(column++, 2, "车牌号码", wcsB));
		wsheet.addCell(new Label(column++, 2, "取车时间", wcsB));
		wsheet.addCell(new Label(column++, 2, "租赁点", wcsB));
		wsheet.addCell(new Label(column++, 2, "经办人", wcsB));
		wsheet.addCell(new Label(column++, 2, "车牌号码", wcsB));
		wsheet.addCell(new Label(column++, 2, "还车时间", wcsB));
		wsheet.addCell(new Label(column++, 2, "租赁点", wcsB));
		wsheet.addCell(new Label(column++, 2, "经办人", wcsB));
		wsheet.addCell(new Label(column++, 2, "客户手机号码", wcsB));
		wsheet.addCell(new Label(column++, 2, "姓名", wcsB));
		wsheet.addCell(new Label(column++, 2, "开户时间", wcsB));
		wsheet.addCell(new Label(column++, 2, "租赁点", wcsB));
		wsheet.addCell(new Label(column++, 2, "经办人", wcsB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		int maxNum = getCarList.size() > returnCarList.size() ? getCarList
				.size() > createCustomerList.size() ? getCarList.size()
				: createCustomerList.size()
				: returnCarList.size() > createCustomerList.size() ? returnCarList
						.size() : createCustomerList.size();

		for (int i = 0; i < maxNum; i++) {
			int column = 0;
			wsheet.setRowView(i + 3, 400);
			Map<String, Object> getCarItem;
			Map<String, Object> returnCarItem;
			Map<String, Object> createCustomerItem;
			if (getCarList.size() > i) {
				getCarItem = getCarList.get(i);
				wsheet.addCell(new Label(column++, i + 3, toString(getCarItem
						.get("carNumber")), wcsB));
				wsheet.addCell(new Label(column++, i + 3, toString(getCarItem
						.get("realityGetTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 3, toString(getCarItem
						.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 3, toString(getCarItem
						.get("menForGet")), wcsB));
			} else {
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
			}
			if (returnCarList.size() > i) {
				returnCarItem = returnCarList.get(i);
				wsheet.addCell(new Label(column++, i + 3,
						toString(returnCarItem.get("carNumber")), wcsB));
				wsheet.addCell(new Label(column++, i + 3,
						toString(returnCarItem.get("realityReturnTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 3,
						toString(returnCarItem.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 3,
						toString(returnCarItem.get("menForReturn")), wcsB));
			} else {
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
			}
			if (createCustomerList.size() > i) {
				createCustomerItem = createCustomerList.get(i);
				wsheet.addCell(new Label(column++, i + 3,
						toString(createCustomerItem.get("phone")), wcsB));
				wsheet.addCell(new Label(column++, i + 3,
						toString(createCustomerItem.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 3,
						toString(createCustomerItem.get("createdTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 3,
						toString(createCustomerItem.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 3,
						toString(createCustomerItem.get("transactUser")), wcsB));
			} else {
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
				wsheet.addCell(new Label(column++, i + 3, "", wcsB));
			}

		}
	}

	@Override
	protected String fileName() {
		String filename = "每日业务报表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
