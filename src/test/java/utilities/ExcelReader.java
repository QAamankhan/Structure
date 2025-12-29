package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    public Object[][] Data() throws Exception {

    	String path = System.getProperty("user.dir")+ "/Test_Data/data_testing.xlsx";

        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet("Sheet1");

        int rowsnum = sheet.getPhysicalNumberOfRows();
        int cellnum = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowsnum - 1][cellnum];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < rowsnum; i++) {
            Row row = sheet.getRow(i);

            for (int j = 0; j < cellnum; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = formatter.formatCellValue(cell);
            }
        }

        wb.close();
        fis.close();
        return data;
    }
}
