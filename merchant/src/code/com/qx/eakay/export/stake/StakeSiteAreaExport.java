package com.qx.eakay.export.stake;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class StakeSiteAreaExport extends BaseExport {
	@Override
	protected void writeTile() throws Exception {
		int column = 0;
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "商家充电区域列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "区域编号", wctB));
		wsheet.addCell(new Label(column++, 1, "区域名称", wctB));
		wsheet.addCell(new Label(column++, 1, "充电站名称", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("area_code")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("area_name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("siteName")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "商家充电区域列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
