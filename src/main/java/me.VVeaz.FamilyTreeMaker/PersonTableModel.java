package me.VVeaz.FamilyTreeMaker;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {
    private String[] columnNames = {"Name", "Second name", "Surname", "Date of birth", "Date of death"};
    private List<Person> myList;
    public PersonTableModel(List<Person> List){

        this.myList = List;
    }
    public Person getPerson(int rowIndex){
        if( rowIndex<0 || rowIndex>getRowCount() ){
            return null;
        }

        Person  p;
        p = myList.get(rowIndex);
        return p;
    }

    @Override
    public int getRowCount() {
        if(myList == null)
            return 0;
        return myList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex<0 || rowIndex<0 || columnIndex>getColumnCount() || rowIndex>getRowCount() ){
            return null;
        }

        Person  p=myList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return p.getName();
            case 1:
                return p.getSecond_name();
            case 2:
                return p.getSurname();
            case 3:
                return p.getDateOfBirth();
            case 4:
                return p.getDateOfDeath();
            default:
                return null;

        }
    }
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

}
