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
public class Assignment extends Faculty{
    private String term;
    
    public Assignment(){
    }
    
    public Assignment(String code, String id, String term){
        super(code,id);
        setTerm(term);
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Assignment{" + "term=" + term + '}';
    }
    
    
}
