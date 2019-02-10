package me.VVeaz.FamilyTreeMaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private List<Person> children;
    private boolean drawn;

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public boolean hasBothParents(){
        return hasMother()&& hasFather();
    }

    public boolean hasMother(){
        return mother != null;
    }

    public boolean hasFather(){
        return father!=null;
    }

    public List<Person> getChildren() {
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

    public void setWomen(boolean women) {
        isWomen = women;
    }

//    public long getId() {
//        return id;
//    }

    public Object getVertex() {
        return vertex;
    }

    public void setVertex(Object vertex) {
        this.vertex = vertex;
    }

    public String getName() {
        return name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return date_of_birth;
    }

    public String getDateOfDeath() {
        return date_of_death;
    }

    public void addChildren(Person person){
        children.add(person);
        person.addParent(this);

    }

    public int getHowMayChildren(){
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
        isAlive = true;
        this.mother = null;
        this.father = null;
    }
}
