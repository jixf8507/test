package com.qx.eakay.export.order;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class UnitUseOrderExport extends BaseExport {

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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "在租订单统计报表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "订单号", wctB));
		wsheet.addCell(new Label(column++, 1, "车牌号码", wctB));
		wsheet.addCell(new Label(column++, 1, "租赁套餐", wctB));
		wsheet.addCell(new Label(column++, 1, "租赁类型", wctB));
		wsheet.addCell(new Label(column++, 1, "企业名称", wctB));
		wsheet.addCell(new Label(column++, 1, "联系电话", wctB));
		wsheet.addCell(new Label(column++, 1, "取车时间", wctB));
		wsheet.addCell(new Label(column++, 1, "取车租赁点", wctB));
		wsheet.addCell(new Label(column++, 1, "取车码表数", wctB));
		wsheet.addCell(new Label(column++, 1, "取车续航里程", wctB));
		wsheet.addCell(new Label(column++, 1, "订单状态", wctB));
		wsheet.addCell(new Label(column++, 1, "取车经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "创建时间", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, "ZCDD_"
						+ toString(map.get("id")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("carNumber")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("typeName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("type")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("enterpriseName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("contactPhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("realityGetTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("gsiteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("kmsForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("surplusKmsForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("orderStatus")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("menForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "在租订单统计报表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
