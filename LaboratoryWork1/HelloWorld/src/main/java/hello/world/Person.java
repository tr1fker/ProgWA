package hello.world;

public class Person {
    
    
    private String lastname;
    private int age;
    private Gender gender;
    
    
    public Person(String lastname, int age, Gender gender){
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
    }
    
    
    
    public String getLastname(){
        return this.lastname;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public Gender getGender(){
        return this.gender;
    }
    
    
    
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public void setGender(Gender gender){
        this.gender = gender;
    }
    
    
    @Override
    public String toString(){
        return "Lastname:" + this.lastname + " Age:" + this.age + " Gender:" + this.gender;
    }
    
}
