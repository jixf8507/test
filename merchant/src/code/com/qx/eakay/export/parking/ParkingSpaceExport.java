package com.qx.eakay.export.parking;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class ParkingSpaceExport extends BaseExport {
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "停车场停车位列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "停车场名称", wctB));
		wsheet.addCell(new Label(column++, 1, "车位编号", wctB));
		wsheet.addCell(new Label(column++, 1, "区域", wctB));
		wsheet.addCell(new Label(column++, 1, "出厂编号", wctB));
		wsheet.addCell(new Label(column++, 1, "设备名称", wctB));
		wsheet.addCell(new Label(column++, 1, "铭牌", wctB));
		wsheet.addCell(new Label(column++, 1, "所在充电设备位置", wctB));
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
						.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("spaceNO")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("area")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("factoryNo")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deviceNo")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("nameplate")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("position")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("status")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "停车场停车位列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
