package com.qx.eakay.export.merhcant;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jxl.write.Label;

import com.qx.common.base.BaseExport;

/**
 * 单位会员退款记录
 * 
 * @author Administrator
 *
 */
public class MerchantUnitUserRefundRecordExport extends BaseExport {
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
		Label label = new Label(0, 0, "单位会员退款记录明细", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		wsheet.addCell(new Label(column++, 1, "企业名称", wctB));
		wsheet.addCell(new Label(column++, 1, "联系人", wctB));
		wsheet.addCell(new Label(column++, 1, "联系电话", wctB));
		wsheet.addCell(new Label(column++, 1, "退款金额", wctB));
		wsheet.addCell(new Label(column++, 1, "退款时间", wctB));
		wsheet.addCell(new Label(column++, 1, "退款类型", wctB));		
		wsheet.addCell(new Label(column++, 1, "经办人", wctB));
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		BigDecimal totleCost = new BigDecimal(0);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("enterpriseName")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("contactPerson")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("contactPhone")), wcsB));
				BigDecimal addBalance = new BigDecimal(map.get("addBalance")
						+ "");
				totleCost = totleCost.add(addBalance);
				wsheet.addCell(new Label(column++, i + 2, toString(addBalance),
						wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("createdTime")), wcsB));
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("type")), wcsB));
				
				wsheet.addCell(new Label(column++, i + 2, toString(map
						.get("transactUser")), wcsB));
			}
		}
		int column = 0;
		wsheet.setRowView(list.size() + 2, 400);
		wsheet.addCell(new Label(column++, list.size() + 2, "总计", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2,
				toString(totleCost), wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		wsheet.addCell(new Label(column++, list.size() + 2, "", wcsB));
		
	}

	@Override
	protected String fileName() {
		String filename = "商家退款记录明细.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
}
