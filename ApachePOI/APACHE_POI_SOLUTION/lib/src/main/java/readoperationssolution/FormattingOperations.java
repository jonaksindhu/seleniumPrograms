package excelOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FormattingOperations {

	/**
	 * use this method to add background color to cell
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param data
	 * @param rowIndex
	 * @param colIndex
	 */
	public void applyColorToCell(String filePath, String sheetName, int rowIndex, int colIndex,
			IndexedColors fillColor, FillPatternType fillPatternType) {

		// Creating file object of existing excel file
		File fileName = new File(filePath);

		try {

			FileInputStream file = new FileInputStream(fileName);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Get the Cell number using getRow and getCell
			XSSFCell cell2Style = sheet.getRow(rowIndex).getCell(colIndex);

			XSSFCellStyle style = workbook.createCellStyle();

			style.setFillBackgroundColor(fillColor.getIndex());
			style.setFillPattern(fillPatternType);

			cell2Style.setCellStyle(style);

			// Close input stream
			file.close();

			// Crating output stream and writing the updated workbook
			FileOutputStream os = new FileOutputStream(fileName);
			workbook.write(os);

			// Close the workbook and output stream
			workbook.close();
			os.close();

			System.out.println("Excel file has been updated successfully.");

		} catch (Exception e) {
			System.err.println("Exception while updating an existing excel file.");
			e.printStackTrace();
		}

	}


	/**
	 * use this method to add background color to cell
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param data
	 * @param rowIndex
	 * @param colIndex
	 */
	public void applyAlignmentToCell(String filePath, String sheetName, int rowIndex, int colIndex,
			HorizontalAlignment horizontalAlignment) {

		// Creating file object of existing excel file
		File fileName = new File(filePath);

		try {

			FileInputStream file = new FileInputStream(fileName);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Get the Cell number using getRow and getCell
			XSSFCell cell2Style = sheet.getRow(rowIndex).getCell(colIndex);
			switch (cell2Style.getCellType()) {
				case STRING:
					System.out.print(cell2Style.getStringCellValue());
					break;
				case NUMERIC:
					System.out.print(cell2Style.getNumericCellValue());
					break;
				case BOOLEAN:
					System.out.print(cell2Style.getBooleanCellValue());
					break;
				default:
					break;
			}

			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(horizontalAlignment);

			cell2Style.setCellStyle(style);

			// Close input stream
			file.close();

			// Creating output stream and writing the updated workbook
			FileOutputStream os = new FileOutputStream(fileName);
			workbook.write(os);

			// Close the workbook and output stream
			workbook.close();
			os.close();

			System.out.println("Excel file has been updated successfully.");

		} catch (Exception e) {
			System.err.println("Exception while updating an existing excel file.");
			e.printStackTrace();
		}

	}

	/**
	 * use this method to add row's and apply font color into the existing excel
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param dataToWrite
	 */

	public void applyFontToRow(String filePath, String sheetName, Object[][] dataToWrite,
			String fontName) {

		// Creating file object of existing excel file
		File fileName = new File(filePath);

		try {

			FileInputStream file = new FileInputStream(fileName);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Getting the count of existing records
			int rowCount = sheet.getLastRowNum();

			XSSFFont font = workbook.createFont();
			font.setFontName(fontName);

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFont(font);

			// Iterating new students to update
			for (Object[] data : dataToWrite) {

				// Creating new row from the next row count
				XSSFRow row = sheet.createRow(++rowCount);
				int columnCount = 0;
				// Iterating student informations
				for (Object info : data) {
					// Creating new cell and setting the value
					XSSFCell cell = row.createCell(columnCount++);
					cell.setCellStyle(style);
					if (info instanceof String) {
						cell.setCellValue((String) info);
					} else if (info instanceof Integer) {
						cell.setCellValue((Integer) info);
					} else if (info instanceof Double) {
						cell.setCellValue((Double) info);
					}
				}
			}
			// Close input stream
			file.close();

			// Crating output stream and writing the updated workbook
			FileOutputStream os = new FileOutputStream(fileName);
			workbook.write(os);

			// Close the workbook and output stream
			workbook.close();
			os.close();

			System.out.println("Excel file has been updated successfully.");

		} catch (Exception e) {
			System.err.println("Exception while updating an existing excel file.");
			e.printStackTrace();
		}
	}

	public void validateFormattingUpdates(String filePath, String sheetName) {
		// Creating file object of existing excel file
		File fileName = new File(filePath);

		try {

			FileInputStream file = new FileInputStream(fileName);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			/* Verify setting color to cell B2 */
			XSSFCell cell2Style = sheet.getRow(1).getCell(1);

			XSSFCellStyle style = cell2Style.getCellStyle();

			System.out.println(
					"Color of cell B2 is: " + style.getFillBackgroundColorColor().getARGBHex());

			/* Verify setting alignment of "Survey" column to right alignment */
			cell2Style = sheet.getRow(0).getCell(3);
			style = cell2Style.getCellStyle();

			System.out.println(
					"Alignment of cell A4 (Survey) is: " + style.getAlignment().toString());

			// Close input stream
			file.close();

			// Crating output stream and writing the updated workbook
			FileOutputStream os = new FileOutputStream(fileName);
			workbook.write(os);

			// Close the workbook and output stream
			workbook.close();
			os.close();

			System.out.println("Excel file has been updated successfully.");

		} catch (Exception e) {
			System.err.println("Exception while updating an existing excel file.");
			e.printStackTrace();
		}
	}

	public void run() {
		String filePath = System.getProperty("user.dir") + "/src/main/resources/Activity.xlsx";
		String worksheetName = "Country Population";

		// New students records to update in excel file
		Object[][] countryRecord = {{"Israel", "Jerusalem", "9.2", "24-02-2021", "22145"},};

		// Add “Green” background color to Cell “B2”
		this.applyColorToCell(filePath, worksheetName, 1, 1, IndexedColors.BRIGHT_GREEN,
				FillPatternType.THICK_BACKWARD_DIAG);

		// Add “Right Alignment” to Column “Survey Date”
		this.applyAlignmentToCell(filePath, worksheetName, 0, 3, HorizontalAlignment.RIGHT);

		// Add font “Verdana” while entering the new row to your worksheet
		this.applyFontToRow(filePath, worksheetName, countryRecord, "Verdana");

		/* Add your logic to make the formatting updates above this line */

		// Utility method to verify formatting updates
		this.validateFormattingUpdates(filePath, worksheetName);
	}

}
