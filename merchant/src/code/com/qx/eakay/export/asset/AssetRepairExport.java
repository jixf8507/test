package com.qx.eakay.export.asset;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 固定资产维修
 * 
 * @author sdf
 */
public class AssetRepairExport extends BaseExport {
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
		Label label = new Label(0, 0, "固定资产维修列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "单据号", wctB));
		wsheet.addCell(new Label(column++, 1, "送修日期", wctB));
		wsheet.addCell(new Label(column++, 1, "维修原因", wctB));
		wsheet.addCell(new Label(column++, 1, "维修商", wctB));
		wsheet.addCell(new Label(column++, 1, "申请人", wctB));
		wsheet.addCell(new Label(column++, 1, "经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "维修费用", wctB));
		wsheet.addCell(new Label(column++, 1, "备注", wctB));
		wsheet.addCell(new Label(column++, 1, "记录时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, "WX_"
						+ toString(map.get("id")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("repairDate")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("repairReason")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("manufacturerName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("applyUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("fee")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("remarks")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "固定资产维修信息.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
