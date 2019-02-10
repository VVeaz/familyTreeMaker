package me.vveaz.familytreemaker;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Choose extends JFrame {
    private final FamilyTree window;
    private PersonTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;
    private boolean addingChild;
    private Person first;

    Choose(FamilyTree window, boolean addingChild, Person first){
        super(addingChild ? "Choose child":"Choose parent");
        this.window = window;
        this.addingChild = addingChild;
        this.first = first;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600,700);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i=JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window?");
                if(i==0){

                    close();
                }
            }

        });
        setLocationRelativeTo(null);
        showTable();
    }

    private void showTable() {
        List<Person> familyWithoutFirst = new ArrayList<>();
        for(Person p : window.getFamily()){
            if(p!=first){
                familyWithoutFirst.add(p);
            }
        }
        tableModel = new PersonTableModel(familyWithoutFirst);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(500, 600));
        panel = new JPanel();
        panel.add(scrollPane);
        add(panel, BorderLayout.CENTER);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {

                Person second = tableModel.getPerson(table.getSelectedRow());
                if (!addingChild && first.hasFather() && !second.isWomen()) {
                    JOptionPane.showMessageDialog(null, first.toString() + " has father.");
                    return;
                }
                if (!addingChild && first.hasMother() && second.isWomen()) {
                    JOptionPane.showMessageDialog(null, first.toString() + " has mother.");
                    return;
                }
                if (addingChild && second.hasBothParents()) {
                    JOptionPane.showMessageDialog(null, second.toString() + " has both parents.");
                    return;
                }
                if (addingChild && second.hasFather() && !first.isWomen()){
                    JOptionPane.showMessageDialog(null, second.toString() + " has father");
                    return;
                }
                if (addingChild && second.hasMother() && first.isWomen()){
                    JOptionPane.showMessageDialog(null, second.toString() + " has mother");
                    return;
                }
                int i = JOptionPane.showConfirmDialog(null, "Are you sure you choose " + second.toString() + "?");
                if (i == 0) {
                    if (addingChild) {
                        //first - parent
                        first.addChildren(second);
                        window.drawEdge(first, second);
                    } else {
                        //second - parent
                        second.addChildren(first);
                        window.drawEdge(second, first);
                    }
                    close();
                }
            }


        });

        setVisible(true);
        this.validate();
        this.repaint();
    }

    private void close(){
        this.dispose();
    }
}