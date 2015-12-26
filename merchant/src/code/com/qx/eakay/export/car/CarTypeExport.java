package com.qx.eakay.export.car;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class CarTypeExport extends BaseExport {
	@Override
	protected void writeTile() throws Exception {
		int column = 0;
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "车辆型号列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "车辆型号", wctB));
		wsheet.addCell(new Label(column++, 1, "供应商", wctB));
		wsheet.addCell(new Label(column++, 1, "供应商全称", wctB));
		wsheet.addCell(new Label(column++, 1, "座位数", wctB));
		wsheet.addCell(new Label(column++, 1, "颜色", wctB));
		wsheet.addCell(new Label(column++, 1, "类型", wctB));
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
						.get("manufacturerName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("fullName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("seatCount")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("color")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("energy")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "车辆型号列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
