package com.windmill.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;


/**
 * 导出工具
 *
 * @author centit
 */
public class ExportUtil {

    public static void ListToExcel(List<Map<String, Object>> list, String excelname, String tempUrl, String[] fieldNames, String[] columnNames)
            throws Exception {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            Workbook wb = ExcelUtil.createWorkBook(list, fieldNames, columnNames);
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] content = os.toByteArray();

        InputStream is = new ByteArrayInputStream(content);
        File o1 = new File(tempUrl + File.separator + excelname + ".xls");

        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(o1);
            bos = new BufferedOutputStream(fos);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }

    }

}
