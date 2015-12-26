package com.qx.eakay.export.merhcant;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class SiteExport extends BaseExport {
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
		Label label = new Label(0, 0, "商家服务中心列表记录", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "服务中心名称", wctB));
		wsheet.addCell(new Label(column++, 1, "具体地址", wctB));
		wsheet.addCell(new Label(column++, 1, "负责人", wctB));
		wsheet.addCell(new Label(column++, 1, "联系电话", wctB));
		wsheet.addCell(new Label(column++, 1, "省", wctB));
		wsheet.addCell(new Label(column++, 1, "市", wctB));
		wsheet.addCell(new Label(column++, 1, "经度", wctB));
		wsheet.addCell(new Label(column++, 1, "纬度", wctB));
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
						.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("address")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("principal")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("phone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("province")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("city")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("lng")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("lat")), wcsB));
				String typeName = "";
				try {
					int type = Integer.parseInt(map.get("type") + "");
					if ((type & 1) == 1) {
						typeName += "租赁点,";
					}
					if ((type & 2) == 2) {
						typeName += "充电站,";
					}
					if ((type & 4) == 4) {
						typeName += "停车场,";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				wsheet.addCell(new Label(column++, i + 2, typeName.substring(0,
						typeName.length() - 1), wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "商家服务中心列表记录.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
