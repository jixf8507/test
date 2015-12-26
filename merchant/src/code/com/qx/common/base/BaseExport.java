package com.qx.common.base;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public abstract class BaseExport {

	protected OutputStream os = null;
	protected WritableWorkbook wbook = null;
	protected WritableSheet wsheet = null;
	protected WritableCellFormat wcsB = null;
	protected WritableCellFormat wctB = null;
	protected WritableCellFormat wffBold = null;

	public BaseExport() {

	}

	public void toExcel(HttpServletResponse response,
			List<Map<String, Object>> list) throws Exception {
		init(response);
		doExport(list);
		close();
	}

	/**
	 * 写入excel内容
	 * 
	 * @param list
	 */
	public void doExport(List<Map<String, Object>> list) throws Exception {
		writeTile();
		writeHeader();
		writeBody(list);
	}

	protected abstract void writeTile() throws Exception;

	protected abstract void writeHeader() throws Exception;

	protected abstract void writeBody(List<Map<String, Object>> list)
			throws Exception;

	protected abstract String fileName();

	/**
	 * 加载初始信息
	 * 
	 * @param response
	 * @param fileName
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	protected void init(HttpServletResponse response) throws IOException,
			WriteException, RowsExceededException {
		os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename="
				+ this.fileName());// 设定输出文件头
		response.setContentType("application/msexcel");
		wbook = Workbook.createWorkbook(os); // 建立excel文件
		String tmptitle = "sheet1"; // 标题
		wsheet = wbook.createSheet(tmptitle, 0); // sheet名称
		// 设置excel标题

		wcsB = new jxl.write.WritableCellFormat();
		wcsB.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		wcsB.setAlignment(jxl.format.Alignment.CENTRE);
		wctB = new jxl.write.WritableCellFormat();
		wctB.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		wctB.setAlignment(jxl.format.Alignment.CENTRE);
		wctB.setBackground(jxl.format.Colour.SKY_BLUE);

		WritableFont wfBold = new WritableFont(WritableFont.ARIAL, 14,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		jxl.write.WritableCellFormat wcsName = new jxl.write.WritableCellFormat();
		wcsName.setBorder(jxl.format.Border.ALL,
				jxl.format.BorderLineStyle.THIN);
		wfBold.setPointSize(16);
		wffBold = new WritableCellFormat(wfBold);
		wffBold.setAlignment(jxl.format.Alignment.CENTRE);
		// 设置excel标题
		wsheet.setRowView(0, 500);
	}

	/**
	 * 关闭
	 * 
	 * @throws IOException
	 */
	protected void close() throws IOException {
		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流
	}

	public String toString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

}
