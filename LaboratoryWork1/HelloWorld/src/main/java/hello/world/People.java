package hello.world;

import java.util.ArrayList;
import java.util.List;


public class People {
    
    
    private List<Person> persons;
    
    public WriterInfo writeInfo;
    
    
    public People(){
        this.persons = new ArrayList<>();
        this.writeInfo = new WriterInfo();
    }
    
    
    public void addPerson(Person person){
        this.persons.add(person);
    }
    
    public float getAverageAge(){
        float averageAge = 0f;
        for (Person person : this.persons){
            averageAge += person.getAge();
        }
        return averageAge / persons.size();
    }
    
    public int getCountMales(){
        return (int)this.persons.stream()
                .filter(person -> person.getGender().equals(Gender.Male)).count();
        
    }
    
    
    public class WriterInfo {
        
        public void printPeople(){
            persons.stream()
                    .forEach(person -> System.out.println(person));
        }
        
        public void printAverageAge(){
            System.out.println("Средний возраст людей:" + getAverageAge());
        }
        
        public void printCountMales(){
            System.out.println("Количество мужчин:" + getCountMales());
        }
        
    }
    
    
}
