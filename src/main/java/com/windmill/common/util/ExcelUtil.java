package com.windmill.common.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * excel导出工具类
 *
 * @author ygw@centit.com
 * @ClassName: ExcelUtil
 * @date 2015年12月13日 下午3:47:45
 */
public class ExcelUtil {
    /**
     * 创建excel文档，
     *
     * @param list        数据
     * @param keys        list中map的key数组集合
     * @param columnNames excel的列名
     */
    public static Workbook createWorkBook(List<Map<String, Object>> list, String[] keys, String columnNames[]) {

        HSSFWorkbook wb = new HSSFWorkbook();

        Font f2 = wb.createFont();
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());
        CellStyle cs2 = wb.createCellStyle();
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);

        // 创建第一个sheet（页），并命名
        int num = 0;
        int rownum = 0;
        Sheet sheet = null;
        //设置每行每列的值
        for (int i = 0; i < list.size(); i++) {
            if (i % 10000 == 0) {
                num++;
                sheet = createSheet(wb, columnNames, num);
                rownum = 0;
            }
            rownum++;
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            if (sheet != null) {
                Row row1 = sheet.createRow((short) rownum);
                // 在row行上创建一个方格
                for (int j = 0; j < keys.length; j++) {
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list.get(i).get(keys[j]).toString());
                    cell.setCellStyle(cs2);
                }
            }

        }
        return wb;
    }

    public static Sheet createSheet(Workbook wb, String columnNames[], int num) {
        Sheet sheet = wb.createSheet("Sheet" + num);

        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < columnNames.length; i++) {  // keys={"rownumber","person_name","xueli","zhicheng","lzjx"}
            sheet.setColumnWidth((short) i, (short) (35.7 * 120));
        }

        // 创建第一行 标题
        Row row = sheet.createRow((short) 0);
        // 创建单元格格式
        CellStyle cs = wb.createCellStyle();
        // 创建两种字体
        Font f = wb.createFont();

        // 字体样式--列名
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        //设置列名
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cs);
            cell.setCellValue(columnNames[i]);
        }

        return sheet;
    }


}
