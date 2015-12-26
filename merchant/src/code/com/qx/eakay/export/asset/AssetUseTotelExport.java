package com.qx.eakay.export.asset;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 固定资产领用记录
 * 
 * @author sdf
 */
public class AssetUseTotelExport extends BaseExport {
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
		Label label = new Label(0, 0, "固定资产领用记录列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "资产名称", wctB));
		wsheet.addCell(new Label(column++, 1, "领用日期", wctB));
		wsheet.addCell(new Label(column++, 1, "领用点", wctB));
		wsheet.addCell(new Label(column++, 1, "领用人", wctB));
		wsheet.addCell(new Label(column++, 1, "经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "资产状态", wctB));
		wsheet.addCell(new Label(column++, 1, "备注", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("assetsName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("useDate")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("userName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("flag")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("remarks")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "固定资产领用记录信息.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
