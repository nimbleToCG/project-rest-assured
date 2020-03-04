package com.example.projectrestassured.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 只支持读取 .xlsx 所有方法读取数据时, 未对 Excel 格式类型进行判断处理 如 Excel 中有特殊数据类型, 需在 Excel
 * 中标注为文本类型, 程序才能正常处理
 *
 * @author Roger
 * @version 1.0
 */
@Slf4j
public class ExcelUtils {
    //读取excel
    public static List<Map<String, String>> readExcel(String filePath) throws IOException {
        Workbook workbook = null;
        if (filePath == null) {
            return null;
        }
        String type = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
        InputStream inputStream = new FileInputStream(filePath);
        if ("xls".equals(type)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if ("xlsx".equals(type)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            return null;
        }

        return getTestData(workbook);
    }

    /**
     * * 读取sheet里的接口用例参数：
     *
     * @param workbook
     * @return
     */
    public static List<Map<String, String>> getTestData(Workbook workbook) {

        List<Map<String, String>> sheetlist = new ArrayList<>();
        String columns[] = {"id", "module_name", "case_code", "case_name", "case_description", "is_run", "api_url", "path", "mothod", "param_type", "params"};

        //获取sheet
        for (Sheet xssfSheet : workbook) {

            //获取最大行数
            int rownum = xssfSheet.getPhysicalNumberOfRows();
            //获取最大列数
            int colnum = xssfSheet.getRow(0).getPhysicalNumberOfCells();

            //循环遍历各个sheets的行和列值添加到List集合
            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                Row row = xssfSheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        String cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                sheetlist.add(map);
            }

        }
        return sheetlist;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {

            //判断cell类型
            switch (cell.getCellType()) {

                case NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}