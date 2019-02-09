import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private String name;
    private String second_name;
    private String surname;
    private String date_of_birth;
    private String date_of_death;
    //private long id;
    private Person mother;
    private Person father;
    private boolean isAlive;
    private boolean isWomen;
    private Object vertex;

    private ArrayList<Person> children;
    private boolean drawn;

    boolean isDrawn(){return drawn;}
    void setDrawn(boolean drawn){this.drawn = drawn;}
    boolean hasBothParents(){
        return hasMother()&& hasFather();
    }
    boolean hasMother(){
        return mother != null;
    }
    boolean hasFather(){
        return father!=null;
    }
    public ArrayList<Person> getChildren() {
        return children;
    }

    public Person getMother() {
        return mother;
    }



    public Person getFather() {
        return father;
    }



    boolean isWomen() {
        return isWomen;
    }

    void setWomen(boolean women) {
        isWomen = women;
    }

//    public long getId() {
//        return id;
//    }

    Object getVertex() {
        return vertex;
    }

    void setVertex(Object vertex) {
        this.vertex = vertex;
    }

    String getName() {
        return name;
    }

    String getSecond_name() {
        return second_name;
    }

    String getSurname() {
        return surname;
    }

    String getDateOfBirth() {
        return date_of_birth;
    }

    String getDateOfDeath() {
        return date_of_death;
    }

    void addChildren(Person person){
        children.add(person);
        person.addParent(this);

    }
    int getHowMayChildren(){
        return children.size();
    }
    private void addParent(Person parent){
        if(parent.isWomen){
            addMother(parent);
        }else {
            addFather(parent);
        }
    }
    private void addMother(Person mother){
        this.mother = mother;
    }
    private void addFather(Person father){
        this.father = father;
    }

    Person(String name, String second_name, String surname, String date_of_birth, String date_of_death) {
        children = new ArrayList<>();
        isWomen = true; //later i will keep this
       // this.id = index;
        this.name = name;
        this.second_name = second_name;
        this.surname = surname;

        this.date_of_birth = date_of_birth;
        drawn = false;

        isAlive = date_of_death.isEmpty();
        this.date_of_death = date_of_death;
        this.mother = null;
        this.father = null;
    }



    @Override
    public String toString() {
        if(second_name.isEmpty())
            return name+" "+surname+" \n"+date_of_birth+" "+ date_of_death;
        return name+" "+second_name+" "+ surname+" \n"+date_of_birth+" "+ date_of_death;
    }
    public Person(Person person){
        this.name = person.name;
        this.second_name = person.second_name;
        this.surname = person.surname;
        this.date_of_birth = person.date_of_birth;
        this.date_of_death = person.date_of_death;

        isWomen = person.isWomen; //later i will keep this
        isAlive = date_of_death.isEmpty();
        this.mother = person.mother;
        this.father = person.father;
        this.vertex = person.vertex;
        this.children=new ArrayList<>();
        for(Person p: person.children){
            this.children.add(new Person(p));
        }
    }
    public Person() {
        this.name = "";
        this.second_name = "";
        this.surname = "";
        this.date_of_birth = "";
        this.date_of_death = "";
        children = new ArrayList<>();
        isWomen = true; //later i will keep this
        isAlive = date_of_death.isEmpty();
        this.mother = null;
        this.father = null;
    }
}
