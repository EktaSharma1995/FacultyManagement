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
public class Faculty extends Course{
    private String id;
    private String firstName;
    private String lastName;
    
    public Faculty(){
    }
    
    public Faculty(String code,String id){
        super(code);
        setId(id);
    }
    
    
    public Faculty(String id,String firstName,String lastName){
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws NullPointerException{
        
        if (id==null || id.equals("")){
            throw new NullPointerException("Id can't be null");
        } else {
            this.id = id;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws NullPointerException{
        
        if (firstName==null || firstName.equals("")){
            throw new NullPointerException("first name can't be null");
        } else {
            this.firstName = firstName;
        }
    }     

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws NullPointerException{
       
        if(lastName==null || lastName.equals("")){
            throw new NullPointerException("first name can't be null");
        } else {
        
            this.lastName = lastName;
        }
    }

    @Override
    public String toString() {
        return super.toString()
               + "Faculty{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
    
    
    
}
