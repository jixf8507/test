package com.qx.eakay.export.car;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class CarDeviceSimExport extends BaseExport {
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "车载设备卡列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "设备卡号", wctB));
		wsheet.addCell(new Label(column++, 1, "运营商", wctB));
		wsheet.addCell(new Label(column++, 1, "月流量", wctB));
		wsheet.addCell(new Label(column++, 1, "设备卡状态", wctB));
		wsheet.addCell(new Label(column++, 1, "所在设备", wctB));
		wsheet.addCell(new Label(column++, 1, "所在车辆", wctB));
		wsheet.addCell(new Label(column++, 1, "车辆类型", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("simCard")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("facilitator")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("flowOfMonth")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("status")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deviceNO")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("carNumber")), wcsB));
				String carTableName = map.get("carTableName") + "";
				if ("car".equals(carTableName)) {
					carTableName = "商家车辆";
				} else if ("customer_cars".equals(carTableName)) {
					carTableName = "客户车辆";
				} else {
					carTableName = "";
				}
				wsheet.addCell(new Label(column++, i + 2, carTableName, wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "车载设备卡列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
