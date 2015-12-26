package com.qx.eakay.export.car;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class CarBatteryExport extends BaseExport {
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
		Label label = new Label(0, 0, "电池信息列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "电池VIN码", wctB));
		wsheet.addCell(new Label(column++, 1, "所在车辆", wctB));
		wsheet.addCell(new Label(column++, 1, "电池类型", wctB));
		wsheet.addCell(new Label(column++, 1, "最大电压(V)", wctB));
		wsheet.addCell(new Label(column++, 1, "最大电流(A)", wctB));
		wsheet.addCell(new Label(column++, 1, "最高温度(℃)", wctB));
		wsheet.addCell(new Label(column++, 1, "最小电压(V)", wctB));
		wsheet.addCell(new Label(column++, 1, "最小电流(A)", wctB));
		wsheet.addCell(new Label(column++, 1, "最低温度(℃)", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("vin")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("carNumber")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("batteryType")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("maxVoltag")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("maxCurrent")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("maxTemp")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("minVoltage")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("minCurrent")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("minTemp")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "电池信息列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
