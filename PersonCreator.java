class PersonCreator{
    public static Person newPerson;

    public static void main(String args[]){
        int tcCount = 100;

        Thread[] threads = new Thread[tcCount];


        for(int t=0; t < 100; t++){
            threads[t] = new Thread(() -> {
               //Create a person object
               newPerson = new Person();
               newPerson.getPerson();
            });
        }

        for(int t=0; t < 100; t++){
            threads[t].start();
        }
    }
}