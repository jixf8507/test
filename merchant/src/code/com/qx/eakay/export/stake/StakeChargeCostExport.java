package com.qx.eakay.export.stake;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class StakeChargeCostExport extends BaseExport {
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "充电收费记录", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "充电站", wctB));
		wsheet.addCell(new Label(column++, 1, "充电桩出厂编号", wctB));
		wsheet.addCell(new Label(column++, 1, "充电口", wctB));
		wsheet.addCell(new Label(column++, 1, "充电卡号", wctB));
		wsheet.addCell(new Label(column++, 1, "客户姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "车牌", wctB));
		wsheet.addCell(new Label(column++, 1, "开始时间", wctB));
		wsheet.addCell(new Label(column++, 1, "结束时间", wctB));
		wsheet.addCell(new Label(column++, 1, "开始SOC", wctB));
		wsheet.addCell(new Label(column++, 1, "结束SOC", wctB));
		wsheet.addCell(new Label(column++, 1, "充电量", wctB));
		wsheet.addCell(new Label(column++, 1, "费用", wctB));
		wsheet.addCell(new Label(column++, 1, "状态", wctB));
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
						.get("factoryNo")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("chargePosition")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("cardID")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("phone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("CarNo")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("startCharge")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("lastCharge")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("startSoc")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("endSoc")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("EQ")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("cost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("orderStatus")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "充电收费记录.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
