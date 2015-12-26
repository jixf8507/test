package com.qx.eakay.export.customer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 单位会员信息
 * 
 * @author Administrator
 *
 */
public class UnitCustomerExport extends BaseExport {
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "企业信息列表", wffBold);
		wsheet.addCell(label);
	}


	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "企业名称", wctB));
		wsheet.addCell(new Label(column++, 1, "联系人", wctB));
		wsheet.addCell(new Label(column++, 1, "联系电话", wctB));
		wsheet.addCell(new Label(column++, 1, "地址", wctB));
		wsheet.addCell(new Label(column++, 1, "账户余额", wctB));
		wsheet.addCell(new Label(column++, 1, "保证金", wctB));
		wsheet.addCell(new Label(column++, 1, "账户状态", wctB));
		wsheet.addCell(new Label(column++, 1, "开户银行", wctB));
		wsheet.addCell(new Label(column++, 1, "银行卡号", wctB));
		wsheet.addCell(new Label(column++, 1, "开卡经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "开卡时间", wctB));
		wsheet.addCell(new Label(column++, 1, "注销/冻结原因", wctB));
		wsheet.addCell(new Label(column++, 1, "注销/冻结经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "注销/冻结时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("enterpriseName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("contactPerson")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("contactPhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("address")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("balance")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("moneyOfassure")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("status")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("bankType")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("bankCardNO")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("describe")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deleteUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deletedTime")), wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "企业信息.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
