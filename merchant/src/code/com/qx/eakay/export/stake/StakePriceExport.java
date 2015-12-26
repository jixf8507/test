package com.qx.eakay.export.stake;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 充电套餐
 * 
 * @author Administrator
 * 
 */
public class StakePriceExport extends BaseExport {

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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "商家充电套餐列表", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "套餐名称", wctB));
		wsheet.addCell(new Label(column++, 1, "时间段A", wctB));
		wsheet.addCell(new Label(column++, 1, "收费A", wctB));
		wsheet.addCell(new Label(column++, 1, "时间段B", wctB));
		wsheet.addCell(new Label(column++, 1, "收费B", wctB));
		wsheet.addCell(new Label(column++, 1, "时间段C", wctB));
		wsheet.addCell(new Label(column++, 1, "收费C", wctB));
		wsheet.addCell(new Label(column++, 1, "时间段D", wctB));
		wsheet.addCell(new Label(column++, 1, "收费D", wctB));
		wsheet.addCell(new Label(column++, 1, "创建时间", wctB));
		wsheet.addCell(new Label(column++, 1, "备注", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);

				String Hour1 = toString(map.get("Hour1"));
				String Minute1 = toString(map.get("Minute1"));
				String Hour2 = toString(map.get("Hour2"));
				String Minute2 = toString(map.get("Minute2"));
				String Hour3 = toString(map.get("Hour3"));
				String Minute3 = toString(map.get("Minute3"));
				String Hour4 = toString(map.get("Hour4"));
				String Minute4 = toString(map.get("Minute4"));

				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("name")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, Hour1 + ":" + Minute1
						+ " -- " + Hour2 + ":" + Minute2, wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("PriceA")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, Hour2 + ":" + Minute2
						+ " -- " + Hour3 + ":" + Minute3, wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("PriceB")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, Hour3 + ":" + Minute3
						+ " -- " + Hour4 + ":" + Minute4, wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("PriceC")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, Hour4 + ":" + Minute4
						+ " -- 次日" + Hour1 + ":" + Minute1, wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("PriceD")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("memo")), wcsB));

			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "商家充电套餐列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
