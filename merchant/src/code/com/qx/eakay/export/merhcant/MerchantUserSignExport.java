package com.qx.eakay.export.merhcant;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

public class MerchantUserSignExport extends BaseExport {
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
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "商家工作人员签到信息列表", wffBold);
		wsheet.addCell(label);
	}
	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "姓名", wctB));
		wsheet.addCell(new Label(column++, 1, "手机号码", wctB));
		wsheet.addCell(new Label(column++, 1, "签到站点", wctB));
		wsheet.addCell(new Label(column++, 1, "签到时间", wctB));
		wsheet.addCell(new Label(column++, 1, "签到备注", wctB));
		wsheet.addCell(new Label(column++, 1, "签出站点", wctB));
		wsheet.addCell(new Label(column++, 1, "签出时间", wctB));
		wsheet.addCell(new Label(column++, 1, "签出备注", wctB));
		wsheet.addCell(new Label(column++, 1, "日期", wctB));
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
						.get("userName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("mobilePhone")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("siteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("inTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("inDescribe")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("outSiteName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("outTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("outDescribe")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("singDate")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("status")), wcsB));
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "商家工作人员签到信息列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
