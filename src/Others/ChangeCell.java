package Others;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 操控Excel表格
 */
public class ChangeCell {
	public static int rowStart = 4;
	public static int coloumStart = 4;
	public static int coloumEnd = 6;

	public static void main(String[] args) {
		String file = "C:\\Users\\Administrator\\Desktop\\t_magicTrain.xlsx";
		try {
			Workbook workbook;
			FileInputStream input = new FileInputStream(file);
			try {
				workbook = new XSSFWorkbook(input);// 2007 or later
			} catch (Exception e) {
				workbook = new HSSFWorkbook(input);// 2003 or before
			}
			Sheet sheet = workbook.getSheetAt(0);
			for (int i = rowStart; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (null != row) {
					for (int coloum = coloumStart; coloum <= coloumEnd; coloum++) {
						Cell cell = row.getCell(coloum);
						double v = get(sheet, i, coloum);
						if (cell == null) {
							cell = row.createCell(coloum);
						}
						cell.setCellValue(v);
					}
				}
			}
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(file);
				workbook.write(out);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static double get(Sheet sheet, int rowIndex, int columnIndex) {
		Row row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(columnIndex);
		if (cell != null && cell.getNumericCellValue() > 0) {
			return cell.getNumericCellValue();
		} else {
			while (--rowIndex >= rowStart) {
				Row row1 = sheet.getRow(rowIndex);
				Cell cell1 = row1.getCell(columnIndex);
				double value1 = cell1.getNumericCellValue();
				if (value1 > 0) {
					return value1;
				}
			}
			return 0;
		}
	}
}
