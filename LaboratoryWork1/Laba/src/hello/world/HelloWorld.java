package hello.world;

public class HelloWorld {
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++){
            if (i % 2 != 0)
                System.out.println("Hellow World #" + i);
        }

        //task
        for (int i = 11; i < 31; i++)
            if (i % 2 == 0)
                System.out.println(i);


        Person person = new Person("Иван", 25, Gender.Male);

        WriterInfo writerInfo = new WriterInfo(person);

        writerInfo.writeLastname();
        writerInfo.writeAge();
        writerInfo.writeGender();


    }
}
