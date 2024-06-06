import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


public class ReadFromFiles  {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ReadFromFiles(String path, int sheetNumber) {
        try{

            this.workbook = new XSSFWorkbook(path);
            this.sheet = workbook.getSheetAt(sheetNumber);

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public  int getRowCount(){
        int rowCount = 0;

            rowCount = sheet.getPhysicalNumberOfRows();
        return rowCount;
    }

    public  int getColumsCount(){
        int columsCount = 0;

        columsCount = sheet.getRow(0).getLastCellNum();
        return columsCount;

    }

    public static Object getCellData(int row, int cell){
        Object cellValue = null;

          //  DataFormatter formatter = new DataFormatter();
            cellValue = sheet.getRow(row).getCell(cell);
       // System.out.println(cellValue);

        return cellValue;
    }
}
