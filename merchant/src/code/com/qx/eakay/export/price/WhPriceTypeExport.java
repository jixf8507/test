package com.qx.eakay.export.price;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class WhPriceTypeExport extends BaseExport {
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
		Label label = new Label(0, 0, "租赁业务套餐列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "业务套餐", wctB));
		wsheet.addCell(new Label(column++, 1, "每小时收费", wctB));
		wsheet.addCell(new Label(column++, 1, "晚间收费", wctB));
		wsheet.addCell(new Label(column++, 1, "当日最高收费", wctB));
		wsheet.addCell(new Label(column++, 1, "每天租赁开始时间", wctB));
		wsheet.addCell(new Label(column++, 1, "每天租赁结束时间", wctB));
		wsheet.addCell(new Label(column++, 1, "每天套餐开始时间", wctB));
		wsheet.addCell(new Label(column++, 1, "每天套餐结束时间", wctB));
		wsheet.addCell(new Label(column++, 1, "每小时最多公里数", wctB));
		wsheet.addCell(new Label(column++, 1, "夜间最大行驶公里数", wctB));
		wsheet.addCell(new Label(column++, 1, "当日最大行驶公里数", wctB));
		wsheet.addCell(new Label(column++, 1, "超出每公里单价", wctB));
		wsheet.addCell(new Label(column++, 1, "最少租赁天数", wctB));
		wsheet.addCell(new Label(column++, 1, "最多租赁天数", wctB));
		wsheet.addCell(new Label(column++, 1, "预约预留分钟数", wctB));
		wsheet.addCell(new Label(column++, 1, "是否需要预付费", wctB));
		wsheet.addCell(new Label(column++, 1, "套餐状态", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("typeName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("oneHoursCost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("nightCost")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("maxCostForDay")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("startTimeForWork")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("endTimeForWork")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("startTimeForDay")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("endTimeForDay")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("perHoursKmsForDay")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("maxKmsForNight")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("maxKmsForDay")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("perKmsPrice")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("minDays")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("maxDays")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("reservedMinute")), wcsB));
				
				String prepayment = map.get("prepayment")+"";
				
				if("0".equals(prepayment)){
					prepayment = "否";
				}else{
					prepayment = "是";
				}
				
				wsheet.addCell(new Label(column++, i + 2, toString(prepayment), wcsB));
				
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("flag")), wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "租赁业务套餐列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
