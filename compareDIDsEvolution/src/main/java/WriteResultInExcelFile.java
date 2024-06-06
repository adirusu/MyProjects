import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class WriteResultInExcelFile {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;


    public WriteResultInExcelFile(){


            this.workbook = new XSSFWorkbook();
            this.sheet = workbook.createSheet("Evolution");


    }
    public void writeInExcelFile(String[][] headOfMatrix, String[][] evolution , String[][] oldDeleted,Map<String,ArrayList<ArrayList>> map){
        int rowCount = 0;

        try{
        FileOutputStream fileOut = new FileOutputStream("compare.xlsx");


            Row r = sheet.createRow(rowCount);
            Cell c;

                for (int j = 0; j < headOfMatrix[0].length; j++) {

                    c = r.createCell(j);
                    c.setCellValue(headOfMatrix[0][j]);

                }

            rowCount +=3;
            r = sheet.createRow(rowCount);
            c = r.createCell(0);
            c.setCellValue("Evolution");
            rowCount +=2;


           for (int i = 0; i < evolution.length; i++) {
                 r = sheet.createRow(rowCount);
                for (int j = 0; j < evolution[0].length; j++) {

                    c = r.createCell(j);
                    c.setCellValue(evolution[i][j]);

                }
                rowCount++;
            }

            rowCount = rowCount + 5;
            r = sheet.createRow(rowCount);
            c = r.createCell(0);
            c.setCellValue("Deleted");
            rowCount +=2;

            for (int i = 0 ; i < oldDeleted.length; i++) {
                r = sheet.createRow(rowCount);
                for (int j = 0; j < oldDeleted[0].length; j++) {

                    c = r.createCell(j);
                    c.setCellValue(oldDeleted[i][j]);

                }
                rowCount++;
            }
            rowCount = rowCount + 5;
            r = sheet.createRow(rowCount);
            c = r.createCell(0);
            c.setCellValue("Evolution only on fields for old DIDs ");
            rowCount +=2;

            int i = 0;
            for (Map.Entry<String, ArrayList<ArrayList>> entry : map.entrySet()) {
                String key = entry.getKey();
                ArrayList<ArrayList> value = entry.getValue();
                r = sheet.createRow(rowCount);
                c = r.createCell(0);
                c.setCellValue(key);
                for (int j = 0;j < value.size();j ++) {
                    for (int k = 0 ; k < value.get(j).size(); k++){
                        c = r.createCell(k + 1);
                        c.setCellValue(value.get(j).get(k).toString());
                    }
                    rowCount++;
                    r = sheet.createRow(rowCount);
                }

                rowCount++;
                i++;
            }

            workbook.write(fileOut);

            JOptionPane.showMessageDialog(null,"The compare is done. The compare.xlsx is generated.");

        fileOut.close();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
