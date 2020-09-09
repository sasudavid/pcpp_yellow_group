class Person{
    //instance variables of the class: id (long), name (String),
    //zip (String), address (String)
    private long id = 0L;
    private static long currentId = 0L;
    public String name;
    public String zip;
    public String address;
    //Constructor for the Person class
    public Person(){
        this.name = "name";
        this.zip = "zip";
        this.address = "address";
        this.id = this.createId();
    }
    //getName to get the name of the person object
    public synchronized String getName(){
        return name;
    }
    //getZip to get the zip of the person object
    public synchronized String getZip(){
        return zip;
    }
    //getAddress to get the address of the person object
    public synchronized String getAddress(){
        return address;
    }
    //getId to get the id of the person object
    public synchronized long getId(){
        return id;
    }
    //setZip to change the value of the zip of the person object
    private synchronized void setZip(String newZip){
        zip = newZip;
    } 
    //setAddress to change the value of the address of the person object
    private synchronized void setAddress(String newAddress){
        address = newAddress;
    }
    //setId to assign an id to a person object
    public synchronized long createId(){
        long newId = ++currentId;
        currentId = newId;
        return newId;
    }
    //updatePerson to change the value of the address and zip of the person object
    public synchronized void updatePerson(String newZip, String newAddress){
        setZip(newZip);
        setAddress(newAddress);
    }

    public void getPerson(){
        System.out.println("id: "+id+" ,Name: "+name+" Address: "+address+" Zip: "+zip);
    }

}

