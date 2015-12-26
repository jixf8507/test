package com.qx.eakay.export.order;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 当前车辆调度
 * 
 * @author Administrator
 *
 */
public class ControlOrderExport extends BaseExport {

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
		Label label = new Label(0, 0, "当前车辆调度记录统计", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "订单号", wctB));
		wsheet.addCell(new Label(column++, 1, "车牌号码", wctB));
		wsheet.addCell(new Label(column++, 1, "姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机", wctB));
		wsheet.addCell(new Label(column++, 1, "取车时间", wctB));
		wsheet.addCell(new Label(column++, 1, "取车租赁点", wctB));
		wsheet.addCell(new Label(column++, 1, "取车公里数", wctB));
		wsheet.addCell(new Label(column++, 1, "取车续航里程", wctB));
		wsheet.addCell(new Label(column++, 1, "取车描述", wctB));
		wsheet.addCell(new Label(column++, 1, "取车经办人", wctB));
		wsheet.addCell(new Label(column++, 1, "订单状态", wctB));
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
						.get("userName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("mobilePhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("realityGetTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("gsiteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("kmsForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("surplusKmsForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("orderDescribe")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("menForGet")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("orderStatus")), wcsB));
			}
		}
	}

	@Override
	protected String fileName() {
		String filename = "当前车辆调度记录统计.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
