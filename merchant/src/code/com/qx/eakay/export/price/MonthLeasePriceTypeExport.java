package com.qx.eakay.export.price;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class MonthLeasePriceTypeExport extends BaseExport {
	@Override
	protected void writeTile() throws Exception {
		int column = 0;
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "租赁业务长租套餐列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "业务套餐", wctB));
		wsheet.addCell(new Label(column++, 1, "月收费", wctB));
		wsheet.addCell(new Label(column++, 1, "日收费", wctB));
		wsheet.addCell(new Label(column++, 1, "小时收费", wctB));
		wsheet.addCell(new Label(column++, 1, "状态", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("typeName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("monthFee")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("dayFee")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("hourFee")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("flag")), wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "租赁业务长租套餐列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
