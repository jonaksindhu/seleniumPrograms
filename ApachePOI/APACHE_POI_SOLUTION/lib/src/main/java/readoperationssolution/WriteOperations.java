package excelOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteOperations {

	/**
	 * use this method to add row's into the existing excel
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param dataToWrite
	 */
	public void writeInToExcel(String filePath, String sheetName, Object[][] dataToWrite) {
		System.out.println("*Adding new row to: " + sheetName + "*");

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

			// Iterating new students to update
			for (Object[] data : dataToWrite) {

				// Creating new row from the next row count
				XSSFRow row = sheet.createRow(++rowCount);
				int columnCount = 0;
				// Iterating student informations
				for (Object info : data) {
					// Creating new cell and setting the value
					XSSFCell cell = row.createCell(columnCount++);
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

			System.out.println("Excel file has been updated successfully.\n");

		} catch (Exception e) {
			System.err.println("Exception while updating an existing excel file.\n");
			e.printStackTrace();
		}
	}

	/**
	 * use this method to update the existing value of Cell
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param data
	 * @param rowIndex
	 * @param colIndex
	 */
	public void updateCellValue(String filePath, String sheetName, String data, int rowIndex,
			int colIndex) {
		System.out.println("*Updating data in cell of row no. " + rowIndex + " col no. " + colIndex
				+ " of: " + sheetName + "*");

		// Creating file object of existing excel file
		File fileName = new File(filePath);

		try {

			FileInputStream file = new FileInputStream(fileName);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Get the Cell number using getRow and getCell
			XSSFCell cell2Update = sheet.getRow(rowIndex).getCell(colIndex);

			cell2Update.setCellValue(data);

			// Close input stream
			file.close();

			// Crating output stream and writing the updated workbook
			FileOutputStream os = new FileOutputStream(fileName);
			workbook.write(os);

			// Close the workbook and output stream
			workbook.close();
			os.close();

			System.out.println("Cell value has been updated successfully.\n");

		} catch (Exception e) {
			System.err.println("Exception while updating an existing excel file.\n");
			e.printStackTrace();
		}
	}

	public void addColumn(String filePath, String sheetName, String[] colValues) {
		System.out.println("*Adding new column to sheet: " + sheetName + "*");

		// Creating file object of existing excel file
		File fileName = new File(filePath);

		try {

			FileInputStream file = new FileInputStream(fileName);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Get all the rows
			Iterator<Row> iterator = sheet.iterator();

			// Iterate over column values which you want to add
			for (String colValue : colValues) {
				while (iterator.hasNext()) {
					Row currentRow = iterator.next();
					// Create a new Cell in row
					Cell cell = currentRow.createCell(currentRow.getLastCellNum(), CellType.STRING);
					// Set Cell value
					cell.setCellValue((String) colValue);
					break;
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

	public void run() {

		String filePath = System.getProperty("user.dir") + "/src/main/resources/Activity.xlsx";
		String worksheetName = "Country Population";

		// New students records to update in excel file
		Object[][] countryRecord = {{"UK", "London", "6.72", "15-02-2021"},
				{"US", "Washington,D.C", "32.95", "09-02-2021"}};

		// Add given rows into existing worksheet “Country Population”
		this.writeInToExcel(filePath, worksheetName, countryRecord);

		// Update the survey Date for country “India” from “27-02-2011” to “27-02-2021”
		this.updateCellValue(filePath, worksheetName, "27-02-2021", 1, 3);

		// Create a new column “Area(Km)” with given data
		String[] colValues =
				{"Area (Km2)", "3287000", "54394", "30688", "302068", "17100000", "42933"};
		this.addColumn(filePath, worksheetName, colValues);
	}

}
