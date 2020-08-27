package Tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BatFile {

	// static String inPath = "C:\\Users\\Administrator\\Desktop\\basedata\\BaseAchievementTypeList.dat";
	// static String inPath = "resources\\Serialize\\dataSrcFile.dat";
	// static String outPath = "resources\\Serialize\\";

	static String inPath = "C:\\Users\\Administrator\\Desktop\\basedata\\";
	static String outPath = "C:\\Users\\Administrator\\Desktop\\outFile\\";

	public static void main(String[] args) throws Exception {
		// doFile(inPath, "data");

		File file = new File(inPath);
		if (!file.isDirectory()) {
			return;
		}
		File[] files = file.listFiles();
		if (files == null || files.length == 0) {
			return;
		}
		for (File childFile : files) {
			doFile(childFile.getPath(), childFile.getName().split("\\.")[0]);
		}
	}

	public static void doFile(String inPath, String outFileName) throws Exception {
		DataInputStream in = new DataInputStream(new FileInputStream(inPath));
		Workbook workbook = new XSSFWorkbook();
		// Workbook workbook= new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();

		int length = in.readShort();
		int oneLength;
		List<String> nameList = new ArrayList<>();
		List<String> typeList = new ArrayList<>();
		while (length-- > 0) {
			oneLength = in.readShort();
			String name = getString(in, oneLength);
			oneLength = in.readShort();
			String type = getString(in, oneLength);
			nameList.add(name);
			typeList.add(type);
		}
		Row row = sheet.getRow(0);
		if (row == null) {
			row = sheet.createRow(0);
		}
		for (int size = nameList.size() - 1; size >= 0; size--) {
			Cell cell = row.getCell(size);
			if (cell == null) {
				cell = row.createCell(size);
			}
			cell.setCellValue(nameList.get(size));
		}
		row = sheet.getRow(1);
		if (row == null) {
			row = sheet.createRow(1);
		}

		for (int size = typeList.size() - 1; size >= 0; size--) {
			Cell cell = row.getCell(size);
			if (cell == null) {
				cell = row.createCell(size);
			}
			cell.setCellValue(typeList.get(size));
		}

		length = in.readShort();

		int LEN = 3;
		int IND;
		while (length-- > 0) {
			row = sheet.getRow(LEN);
			if (row == null) {
				row = sheet.createRow(LEN);
			}
			IND = 0;
			for (String s : typeList) {
				Cell cell = row.getCell(IND);
				if (cell == null) {
					cell = row.createCell(IND);
				}
				switch (s) {
					case "INT":
						cell.setCellValue(in.readInt());
						break;
					case "STRING":
						cell.setCellValue(getString(in, in.readShort()));
						break;
					case "FLOAT":
						cell.setCellValue(in.readFloat());
						break;
					case "SHORT":
						cell.setCellValue(in.readShort());
						break;
					default:
						break;
				}
				IND++;
			}
			LEN++;
		}

		try (FileOutputStream out = new FileOutputStream(outPath + outFileName + ".xlsx")) {
			workbook.write(out);
		}

		System.out.println(outFileName + " is done");
		in.close();
	}

	public static String getString(DataInputStream in, int length) throws IOException {
		byte[] bytes = new byte[length];
		in.read(bytes);
		return new String(bytes);
	}

}
