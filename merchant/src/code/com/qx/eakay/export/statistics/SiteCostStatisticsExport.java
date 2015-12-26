package com.qx.eakay.export.statistics;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class SiteCostStatisticsExport extends BaseExport {
	@Override
	protected void writeTile() throws Exception {
		int column = 0;
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "租赁点租赁统计", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "租赁点名称", wctB));
		wsheet.addCell(new Label(column++, 1, "类型", wctB));
		wsheet.addCell(new Label(column++, 1, "取车次数", wctB));
		wsheet.addCell(new Label(column++, 1, "还车次数", wctB));
		wsheet.addCell(new Label(column++, 1, "租赁收费", wctB));
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
				Integer type = Integer.parseInt(map.get("type") + "");
				String typeName = "";
				if ((type & 1) == 1) {
					typeName += "租赁点,";
				}
				if ((type & 2) == 2) {
					typeName += "充电站,";
				}
				if ((type & 4) == 4) {
					typeName += "停车场,";
				}
				wsheet.addCell(new Label(column++, i + 2, typeName, wcsB));
				String getCount = toString(map.get("getCount"));
				wsheet.addCell(new Label(column++, i + 2,
						getCount.equals("") ? "0" : getCount, wcsB));
				String returnCount = toString(map.get("returnCount"));
				wsheet.addCell(new Label(column++, i + 2, returnCount
						.equals("") ? "0" : returnCount, wcsB));
				String totalCost = toString(map.get("totalCost"));
				wsheet.addCell(new Label(column++, i + 2,
						totalCost.equals("") ? "0" : totalCost, wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "租赁点租赁统计.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
