package hello.world;


public enum Gender {
    
    Male("Male"),
    Female("Female");
    
    private final String gender; 
    
    Gender(String gender){
        this.gender = gender;
    }
    
    @Override
    public String toString(){
        return this.gender;
    }
    
}
