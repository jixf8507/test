package com.qx.eakay.export.work;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 工单处理
 * 
 * @author sdf
 * @date 2015年12月16日
 */
public class WorkOrderHandleExport extends BaseExport {
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "工单处理列表", wffBold);
		wsheet.addCell(label);
	}
	
	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "工单编号", wctB));
		wsheet.addCell(new Label(column++, 1, "工单类型", wctB));
		wsheet.addCell(new Label(column++, 1, "工单名称", wctB));
		wsheet.addCell(new Label(column++, 1, "工单描述", wctB));
		wsheet.addCell(new Label(column++, 1, "负责人", wctB));
		wsheet.addCell(new Label(column++, 1, "负责人手机", wctB));
		wsheet.addCell(new Label(column++, 1, "发布时间", wctB));
		wsheet.addCell(new Label(column++, 1, "发布人", wctB));
		wsheet.addCell(new Label(column++, 1, "紧急程度", wctB));
		wsheet.addCell(new Label(column++, 1, "工单状态", wctB));
		wsheet.addCell(new Label(column++, 1, "处理描述", wctB));
		wsheet.addCell(new Label(column++, 1, "处理时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, "GD_"+toString(map
						.get("id")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("typeName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("workName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("workDes")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("userName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("mobilePhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactUser")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("urgency")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("workStatus")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactDes")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("updatedTime")), wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "工单处理列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
