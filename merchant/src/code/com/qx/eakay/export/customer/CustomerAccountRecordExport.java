package com.qx.eakay.export.customer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class CustomerAccountRecordExport extends BaseExport {
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "客户账户交易明细记录", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "交易金额", wctB));
		wsheet.addCell(new Label(column++, 1, "账户余额", wctB));
		wsheet.addCell(new Label(column++, 1, "交易时间", wctB));
		wsheet.addCell(new Label(column++, 1, "交易类型", wctB));
		wsheet.addCell(new Label(column++, 1, "客户姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "身份证号", wctB));
		wsheet.addCell(new Label(column++, 1, "经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "付费方式", wctB));
		wsheet.addCell(new Label(column++, 1, "票据编号", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("addBalance")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("newBalance")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("type")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("idCard")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("describe")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("ticket")), wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "客户账户交易明细记录.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
