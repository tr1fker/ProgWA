package hello.world;

public class HelloWorld {
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 7; i++){
            if (i % 2 != 0)
                System.out.println("Hellow World #" + i);
        }
        
        //task
        for (int i =  20; i < 40; i++)
            if (i % 2 == 0)
                System.out.println(i);
        
        People people = new People();
        
        people.addPerson(new Person("Иван", 25, Gender.Male));
        people.addPerson(new Person("Анна", 22, Gender.Female));
        people.addPerson(new Person("Петр", 30, Gender.Male));
        people.addPerson(new Person("Мария", 27, Gender.Female));
        people.addPerson(new Person("Алексей", 35, Gender.Male));
        
        
        people.writeInfo.printPeople();
        people.writeInfo.printCountMales();
        people.writeInfo.printAverageAge();
        
    }
    
}
