package com.qx.eakay.export.stake;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class StakeDeviceExport extends BaseExport {
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "商家充电设备列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "出厂编号", wctB));
		wsheet.addCell(new Label(column++, 1, "设备名称", wctB));
		wsheet.addCell(new Label(column++, 1, "设备类型", wctB));
		wsheet.addCell(new Label(column++, 1, "铭牌", wctB));
		wsheet.addCell(new Label(column++, 1, "充电站名称", wctB));
		wsheet.addCell(new Label(column++, 1, "区域名称", wctB));
		wsheet.addCell(new Label(column++, 1, "经度", wctB));
		wsheet.addCell(new Label(column++, 1, "维度", wctB));
		wsheet.addCell(new Label(column++, 1, "充电端口类型", wctB));
		wsheet.addCell(new Label(column++, 1, "充电套餐名称", wctB));
		wsheet.addCell(new Label(column++, 1, "设备类型名称", wctB));
		wsheet.addCell(new Label(column++, 1, "生产厂商", wctB));
		wsheet.addCell(new Label(column++, 1, "功率(kw)", wctB));
		wsheet.addCell(new Label(column++, 1, "电压(v)", wctB));
		wsheet.addCell(new Label(column++, 1, "联网状态", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("factoryNo")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deviceNo")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deviceTypeName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("nameplate")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("area_name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("lng")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("lat")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("chargePort")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("deviceTypeName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("manufacturer")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("devicePower")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("fixedVoltage")), wcsB));
				String status = map.get("status") + "";
				if ("废弃".equals(status)) {
					status = "不联网";
				}
				wsheet.addCell(new Label(column++, i + 2, status, wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "商家充电设备列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
