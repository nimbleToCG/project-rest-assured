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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
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

  /**
   * 读取EXCLE文件的方法入口
   *
   * @param path 路径
   */
  public static List<List<String>> readExcle(String path) throws IOException {

    String type = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
    InputStream input = new FileInputStream(path);
    List<List<String>> result = new ArrayList<List<String>>();
    if ("xlsx".equals(type)) {
      result = readXlsx(input);
    }
//        else if ("xls".equals(type)) {
//            result = readXls(input);
//        }
    return result;
  }

  public static List<List<String>> readXlsx(InputStream input) throws IOException {
    List<List<String>> result = new ArrayList<List<String>>();
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    for (Sheet xssfSheet : workbook) {
      if (xssfSheet == null) {
        continue;
      }
      if (xssfSheet.getLastRowNum() <= 0) {
        continue;
      }
      int cols = xssfSheet.getRow(0).getLastCellNum();
      for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
        XSSFRow row = (XSSFRow) xssfSheet.getRow(rowNum);
        List<String> rowList = new ArrayList<String>();
        for (int i = 0; i < cols; i++) {
          XSSFCell cell = row.getCell(i);
          if (cell == null) {
            //continue;
            rowList.add("");
          } else {
            rowList.add(cell.toString());
          }
        }
        result.add(rowList);
      }
    }
    return result;
  }

  public static List<List<String>> readXls(InputStream input) throws IOException {
    List<List<String>> result = new ArrayList<List<String>>();
    HSSFWorkbook workbook = new HSSFWorkbook(input);
    for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
      HSSFSheet sheet = workbook.getSheetAt(numSheet);
      if (sheet == null) {
        continue;
      }
      if (sheet.getLastRowNum() <= 0) {
        continue;
      }
      int cols = sheet.getRow(0).getLastCellNum();
      for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
        HSSFRow row = sheet.getRow(rowNum);
        List<String> rowList = new ArrayList<String>();
        for (int i = 0; i < cols; i++) {
          HSSFCell cell = row.getCell(i);
          if (cell == null) {
            //continue;
            rowList.add("");
          } else {
            rowList.add(getStringVal(cell));
          }
        }
        result.add(rowList);
      }
    }
    return result;
  }

  private static String getStringVal(HSSFCell cell) {

    switch (cell.getCellType()) {

      case BOOLEAN:
        return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
      case FORMULA:
        return cell.getCellFormula();
      case NUMERIC:
        String value = "";
        // 如果为时间格式的内容
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
          // 注：format格式 yyyy-MM-dd hh:mm:ss
          // 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
        } else {
          cell.setCellType(CellType.STRING);
          return value = cell.getStringCellValue();
        }
        return value;

      case STRING:
        return cell.getStringCellValue();
      default:
        return null;
    }
  }
}