package com.qx.eakay.export.asset;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 总固定资产
 * 
 * @author sdf
 */
public class AssetTotelExport extends BaseExport {
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
		wsheet.setColumnView(column++, 20);
		wsheet.setColumnView(column++, 20);
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "总固定资产列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "资产名称", wctB));
		wsheet.addCell(new Label(column++, 1, "资产类型", wctB));
		wsheet.addCell(new Label(column++, 1, "规格型号", wctB));
		wsheet.addCell(new Label(column++, 1, "所在租赁点", wctB));
		wsheet.addCell(new Label(column++, 1, "资产价值", wctB));
		wsheet.addCell(new Label(column++, 1, "购置日期", wctB));
		wsheet.addCell(new Label(column++, 1, "增加方式", wctB));
		wsheet.addCell(new Label(column++, 1, "计量单位", wctB));
		wsheet.addCell(new Label(column++, 1, "资产状态", wctB));
		wsheet.addCell(new Label(column++, 1, "使用员工", wctB));
		wsheet.addCell(new Label(column++, 1, "经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "记录时间", wctB));
		wsheet.addCell(new Label(column++, 1, "供应商", wctB));
		wsheet.addCell(new Label(column++, 1, "供应商地址", wctB));
		wsheet.addCell(new Label(column++, 1, "供应商邮箱", wctB));
		wsheet.addCell(new Label(column++, 1, "供应商联系人", wctB));
		wsheet.addCell(new Label(column++, 1, "供应商联系电话", wctB));
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
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("model")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("fee")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("purchaseDate")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("increasingMode")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("unit")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("flag")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("userName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("fullName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("address")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("email")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("linkman")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("linkphone")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "总固定资产信息.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
