package com.qx.eakay.export.customer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 商家已注销客户信息导出
 * 
 * @author jixf
 * @date 2015年10月12日
 */
public class CustomerCancelExport extends BaseExport {

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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "商家退款客户信息列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "身份证/驾驶证", wctB));
		wsheet.addCell(new Label(column++, 1, "性别", wctB));
		wsheet.addCell(new Label(column++, 1, "家庭住址", wctB));
		wsheet.addCell(new Label(column++, 1, "租赁保证金", wctB));
		wsheet.addCell(new Label(column++, 1, "账户余额", wctB));
		wsheet.addCell(new Label(column++, 1, "开户站点", wctB));
		wsheet.addCell(new Label(column++, 1, "经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "开户时间", wctB));
		wsheet.addCell(new Label(column++, 1, "账户状态", wctB));
		wsheet.addCell(new Label(column++, 1, "银行卡", wctB));
		wsheet.addCell(new Label(column++, 1, "银行", wctB));
		wsheet.addCell(new Label(column++, 1, "退款原因", wctB));
		wsheet.addCell(new Label(column++, 1, "退款时间", wctB));
		wsheet.addCell(new Label(column++, 1, "退款经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "退款状态", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("phone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("idCard")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("sex")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("address")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("moneyOfassure")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("balance")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("status")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("bankCardNO")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("bankType")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("describe")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deletedTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deletedUser")), wcsB));
				String subStatus = toString(map.get("subStatus"));
				wsheet.addCell(new Label(column++, i + 2, subStatus
						.equals("正常") ? "未退款" : subStatus, wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "商家退款客户信息列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
