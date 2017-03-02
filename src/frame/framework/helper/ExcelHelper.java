/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author aicjxopt
 */
public class ExcelHelper {
    public static void main(String[] args){
        Object[][] data = ExcelHelper.getExcelData("D://运输清单_全部_2011-01-01_2012-01-01.xls");
        for(int i=0; i<data.length ;i++){
            for(int j=0; j<data[i].length ;j++){
                System.out.print(data[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }
    public static Object[][] getExcelData(String path){
        try{
            Workbook book  =  Workbook.getWorkbook(new File(path));
            jxl.Sheet sheet = book.getSheet(0);
            int colLen = sheet.getColumns();
            int rowLen = sheet.getRows();
            Object[][] data = new Object[rowLen][colLen];
            for(int i=0; i<rowLen ;i++){
                for(int j=0; j<colLen ;j++){
                    data[i][j] = sheet.getCell(j, i).getContents();
                }
            }
            book.close();
            return data;
        }catch(Exception e)   {
            System.out.println(e);
        }
        return null;
    }
    
    private static void addRow(WritableSheet sheet, Object[] data, int index) throws WriteException{
        for(int i=0; i<data.length ;i++){
            sheet.addCell(new Label(i, index, data[i]==null?"":String.valueOf(data[i])));
        }
    }
    
    public static boolean save(Excel excel){
        try{
             WritableWorkbook book  =  Workbook.createWorkbook(new File(excel.getFilePath()));
             List<Sheet> sheets = excel.getSheets();
             for(int i=0; i<sheets.size() ;i++){
                 Sheet vo = sheets.get(i);
                 WritableSheet sheet  =  book.createSheet(vo.getTitle(), i);
                 addRow(sheet, vo.getHeads(), 0);
                 Object[][] data = vo.getBodys();
                 for(int j=0; j<data.length ;j++){
                     addRow(sheet, data[j], j+1);
                 }
             }
               
             book.write();
             book.close();
             return true;
        }catch(Exception e)   {
            System.out.println(e);
            return false;
        } 
    }
    
    public static boolean jTableSaveExcel(String path, JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        int colCount = model.getColumnCount();
        Object[] cols = new Object[colCount];
        for(int i=0; i<cols.length ;i++){
            cols[i] = model.getColumnName(i);
        }
        int rowCount = model.getRowCount();
        Object[][] data = new Object[rowCount][colCount];
        for(int i=0; i<data.length ;i++){
            for(int j=0; j<data[i].length ;j++){
                data[i][j] = model.getValueAt(i, j);
            }
        }
        Sheet sheet = new Sheet();
        sheet.setTitle(path);
        sheet.setHeads(cols);
        sheet.setBodys(data);
        
        List<Sheet> list = new ArrayList(1);
        list.add(sheet);
        Excel excel = new Excel();
        excel.setFilePath(path);
        excel.setSheets(list);
        return save(excel);
    }
}
