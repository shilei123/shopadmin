/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.helper;


/**
 *
 * @author aicjxopt
 */
public class Sheet {
    private String title;
    private Object[] heads;
    private Object[][] bodys;

    public Object[][] getBodys() {
        return bodys;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBodys(Object[][] bodys) {
        this.bodys = bodys;
    }

    public Object[] getHeads() {
        return heads;
    }

    public void setHeads(Object[] heads) {
        this.heads = heads;
    }
    
}
