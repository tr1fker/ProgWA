package hello.world;

public class WriterInfo {

    private Person person;

    public WriterInfo(Person person) {
        this.person = person;
    }


    public void writeLastname(){
        person.printLastname();
    }

    public void writeAge(){
        person.printAge();
    }

    public void writeGender(){
        person.printGender();
    }

}
