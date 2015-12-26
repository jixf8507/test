package com.qx.eakay.export.car;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class CarDeviceExport extends BaseExport {
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
		Label label = new Label(0, 0, "车载设备列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "设备编号", wctB));
		wsheet.addCell(new Label(column++, 1, "设备状态", wctB));
		wsheet.addCell(new Label(column++, 1, "车牌号码", wctB));
		wsheet.addCell(new Label(column++, 1, "车辆类型", wctB));
		wsheet.addCell(new Label(column++, 1, "客户姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "设备卡号", wctB));
		wsheet.addCell(new Label(column++, 1, "最后在线时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deviceNO")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("status")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("carNumber")), wcsB));
				String carTableName = toString(map.get("carTableName"));
				if ("car".equals(carTableName)) {
					carTableName = "商家车辆";
				} else if ("customer_cars".equals(carTableName)) {
					carTableName = "客户车辆";
				} else {
					carTableName = "";
				}
				wsheet.addCell(new Label(column++, i + 2, carTableName, wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("customerName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("phone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("simCard")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("lastOnlineTime")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "车载设备列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
