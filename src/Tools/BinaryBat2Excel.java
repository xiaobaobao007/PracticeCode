package Tools;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BinaryBat2Excel {

	public static final String IN_PATH = "resources\\srcDir\\";
	public static final String OUT_PATH = "resources\\outDir\\";
	public static final String SPECIALL_NAME = "BaseMessageszh-CN.dat";

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
					value = readString(dataInputStream);
					if (msgMap != null && msgMap.containsKey(value)) {
						value = msgMap.get(value);
					}
				} else {
					System.out.println("未知类型:" + type);
				}
				values.add(value);
			}
			model.lines.add(values);
			line--;
		}
		return model;
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
			if (files == null || files.length == 0) {
				System.out.println("加载的dat文件数为0");
				return;
			}
			System.out.println("加载的dat文件数为" + files.length);
			for (File file : files) {
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
				System.out.println(file.getName() + " IS DONE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ALL IS OVER");
	}

	private static String readString(DataInputStream dataInputStream) throws Exception {
		int len = dataInputStream.readShort();
		if (len == 0) {
			return "";
		}

		byte[] bytes = new byte[len];
		dataInputStream.read(bytes);
		return new String(bytes);
	}
}
