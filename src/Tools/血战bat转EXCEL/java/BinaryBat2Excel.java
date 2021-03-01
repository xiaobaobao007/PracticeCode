package Tools.血战bat转EXCEL.java;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.sun.javafx.binding.StringFormatter;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BinaryBat2Excel {

	public static final String IN_PATH = "F:\\PracticeCode\\src\\Tools\\血战bat转EXCEL\\resources\\srcDir\\";
	public static final String OUT_PATH = "F:\\PracticeCode\\src\\Tools\\血战bat转EXCEL\\resources\\outDir\\";
	public static final String SPECIALL_NAME = "BaseMessageszh-CN.dat";
	public static final String SPLIT = "*";
	public static final String FORMAT = "%-40s| %-6s| %-20s| %-10s| %s\n";
	public static final StringBuilder SB = new StringBuilder(StringFormatter.format(FORMAT, "excel_name", "index", "column_name", "result", "param").getValue());

	private static class Model {
		List<String> keys = new ArrayList<>();
		List<String> types = new ArrayList<>();
		List<List<Object>> lines = new ArrayList<>();
	}

	private static Model parseModel(File file, Map<String, String> msgMap) throws Exception {
		Model model = new Model();
		DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		short keyNum = dataInputStream.readShort();
		while (keyNum > 0) {
			String key = readString(dataInputStream);
			String type = readString(dataInputStream);
			model.keys.add(key);
			model.types.add(type);
			keyNum--;
		}

		short line = dataInputStream.readShort();
		int[][] intOrString = new int[model.types.size()][2];
		while (line > 0) {
			List<Object> values = new ArrayList<>();
			for (int i = 0; i < model.types.size(); i++) {
				String type = model.types.get(i);
				Object value = null;

				if ("INT".equalsIgnoreCase(type)) {
					value = dataInputStream.readInt();
				} else if ("SHORT".equalsIgnoreCase(type)) {
					value = dataInputStream.readShort();
				} else if ("FLOAT".equalsIgnoreCase(type)) {
					value = dataInputStream.readFloat();
				} else if ("STRING".equalsIgnoreCase(type)) {
					// id*string
					value = readString(dataInputStream);

					if (msgMap != null) {
						boolean isInt = true;
						for (char c : value.toString().toCharArray()) {
							if ('0' > c || c > '9') {
								isInt = false;
								break;
							}
						}
						if (isInt) {
							if ("0".equals(value)) {
								value += SPLIT + "0";
							} else if (msgMap.containsKey(value)) {
								value += SPLIT + msgMap.get(value);
								intOrString[i][1]++;
							} else if (((String) value).length() > 0) {
								value += SPLIT;
								intOrString[i][0]++;
							} else {
								value = SPLIT;
							}
						} else {
							intOrString[i][1]++;
							value = SPLIT + value;
						}
					}
				} else {
					System.out.println(file.getName() + " 未知类型:" + type);
				}

				values.add(value);
			}
			model.lines.add(values);
			line--;
		}

		if (msgMap == null) {
			return model;
		}

		int[] ints;
		int index;
		String s;
		for (int i = intOrString.length - 1; i >= 0; i--) {
			ints = intOrString[i];
			if (ints[0] == 0 && ints[1] == 0) {
				continue;
			}
			if (ints[0] > ints[1]) {
				for (List<Object> lineList : model.lines) {
					s = lineList.get(i).toString();
					index = s.indexOf(SPLIT);
					lineList.set(i, s.substring(0, index));
				}
				if (ints[1] != 0) {
					SB.append(StringFormatter.format(FORMAT, file.getName(), intToExcelIndex(i), model.keys.get(i), "string", Arrays.toString(ints)).getValue());
				}
			} else if (ints[0] < ints[1]) {
				for (List<Object> lineList : model.lines) {
					s = lineList.get(i).toString();
					index = s.indexOf(SPLIT);
					lineList.set(i, s.substring(index + 1));
				}
				if (ints[0] != 0) {
					SB.append(StringFormatter.format(FORMAT, file.getName(), intToExcelIndex(i), model.keys.get(i), "chinese", Arrays.toString(ints)).getValue());
				}
			} else {
				for (List<Object> lineList : model.lines) {
					s = lineList.get(i).toString();
					index = s.indexOf(SPLIT);
					lineList.set(i, s.substring(0, index));
				}
				SB.append(StringFormatter.format(FORMAT, file.getName(), intToExcelIndex(i), model.keys.get(i), "_string", Arrays.toString(ints)).getValue());
			}
		}
		return model;
	}

	/**
	 * 默认两位
	 */
	public static String intToExcelIndex(int a) {
		if (a >= 26) {
			return (char) (a / 26 + 64) + String.valueOf(((char) (a % 26 + 65)));
		}
		return String.valueOf((char) (a + 65));
	}

	public static void main(String[] args) {

		try {
			String msgFilePath = IN_PATH + SPECIALL_NAME;
			Model model;
			model = parseModel(new File(msgFilePath), null);
			Map<String, String> msgMap = new HashMap<>();
			for (List<Object> list : model.lines) {
				String key = String.valueOf((int) list.get(0));
				String msg = list.get(1) != null ? list.get(1).toString() : "";
				msgMap.put(key, msg);
			}

			File dir = new File(IN_PATH);
			File[] files = dir.listFiles();
			int fileNums;
			if (files == null || (fileNums = files.length) == 0) {
				System.out.println("加载的dat文件数为0");
				return;
			}
			System.out.println("加载的dat文件数为" + fileNums);
			int index = 0;
			for (File file : files) {
				index++;
				try {
					String name = file.getName();
					model = parseModel(file, msgMap);

					String excelFileName = OUT_PATH + name.substring(0, name.lastIndexOf('.') + 1) + "xlsx";
					File excelFile = new File(excelFileName);
					if (!excelFile.exists()) {
						excelFile.createNewFile();
					}

					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet sheet = workbook.createSheet();
					// 字段名
					XSSFRow row = sheet.createRow(0);
					for (int i = 0; i < model.keys.size(); i++) {
						row.createCell(i).setCellValue(model.keys.get(i));
					}

					// 类型
					row = sheet.createRow(1);
					for (int i = 0; i < model.types.size(); i++) {
						row.createCell(i).setCellValue(model.types.get(i));
					}

					// 值
					List<List<Object>> lines = model.lines;
					for (int i = 0; i < lines.size(); i++) {
						List<Object> line = lines.get(i);
						row = sheet.createRow(i + 3);

						for (int j = 0; j < line.size(); j++) {
							Object o = line.get(j);
							if (o == null) {
								continue;
							}
							String type = model.types.get(j);
							if ("INT".equalsIgnoreCase(type)) {
								row.createCell(j).setCellValue((int) o);
							} else if ("SHORT".equalsIgnoreCase(type)) {
								row.createCell(j).setCellValue((short) o);
							} else if ("FLOAT".equalsIgnoreCase(type)) {
								row.createCell(j).setCellValue((float) o);
							} else if ("STRING".equalsIgnoreCase(type)) {
								row.createCell(j).setCellValue((String) o);
							}
						}
					}

					// 以文件的形式输出工作簿对象
					FileOutputStream fileOut = new FileOutputStream(excelFile);
					workbook.write(fileOut);
					fileOut.flush();
					fileOut.close();
					System.out.printf("%-3d/%-3d   %s IS DONE\n", index, fileNums, file.getName());
				} catch (Exception e) {
					System.out.printf("%-3d/%-3d                    %s IS ERROR\n", index, fileNums, file.getName());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nALL IS OVER !!!\n");
		System.out.println(SB.toString());
	}

	private static String readString(DataInputStream dataInputStream) throws Exception {
		int len = dataInputStream.readShort();
		if (len == 0) {
			return "";
		}

		byte[] bytes = new byte[len];
		dataInputStream.read(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}
}
