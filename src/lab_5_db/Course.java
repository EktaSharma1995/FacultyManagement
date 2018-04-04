/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_5_db;

/**
 *
 * @author ektasharma
 */
public class Course {
    private String code;
    private String title;
    private int credits;
    private String description;
    
    public Course(){
        
    }
    
    public Course(String code) {
        setCode(code);
    }
    
    public Course(String code,String title,int credits,String description) {
        setCode(code);
        setTitle(title);
        setCredits(credits);
        setDescription(description);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws NullPointerException{
       if (code==null || code.equals("")){
            throw new NullPointerException("Code can't be null");
        } else {
            this.code = code;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws NullPointerException{
        if (title==null || title.equals("")){
            throw new NullPointerException("Title can't be null");
        } else {
            this.title = title;
        }
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) throws ArithmeticException{
        if (credits <= 0) {
            throw new ArithmeticException("Credits can't be zero or negative ");
        } else {
            this.credits = credits;
        }
    }   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws ArithmeticException{
        if (description==null || description.equals("")){
            throw new NullPointerException("Description can't be null");
        } else {
            this.description = description;
        }
    }

    @Override
    public String toString() {
        return "Course details:{" + 
                "Code=" + code + "\n"
                + "title=" + title + "\n "
                + "credits=" + credits + "\n "
                + "Description=" + description + "\n"+'}';
    }
    
}
