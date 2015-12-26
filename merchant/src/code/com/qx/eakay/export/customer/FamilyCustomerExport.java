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
public class FamilyCustomerExport extends BaseExport {
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
		Label label = new Label(0, 0, "家庭会员信息列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "户主", wctB));
		wsheet.addCell(new Label(column++, 1, "地址", wctB));
		wsheet.addCell(new Label(column++, 1, "电话", wctB));
		wsheet.addCell(new Label(column++, 1, "身份证", wctB));
		wsheet.addCell(new Label(column++, 1, "用户名", wctB));
		wsheet.addCell(new Label(column++, 1, "性别", wctB));
		wsheet.addCell(new Label(column++, 1, "保证金", wctB));
		wsheet.addCell(new Label(column++, 1, "余额", wctB));
		wsheet.addCell(new Label(column++, 1, "冻结资金", wctB));
		wsheet.addCell(new Label(column++, 1, "状态", wctB));
		wsheet.addCell(new Label(column++, 1, "开卡时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("householder")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("homeAddress")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("homePhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("idCard")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("Sex")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("moneyOfassure")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("Balance")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("freezeBalance")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("Status")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
				
				
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "家庭会员会员信息.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
