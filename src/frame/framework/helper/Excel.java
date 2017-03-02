/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.helper;

import java.util.List;

/**
 *
 * @author aicjxopt
 */
public class Excel {
    private String filePath;
    private List<Sheet> sheets;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }


    
}
