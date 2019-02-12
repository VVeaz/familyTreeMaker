package me.vveaz.familytreemaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class AddExisting extends JFrame {
    private final FamilyTree window;
    private PersonTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;
    private final boolean addingChild;

    AddExisting(FamilyTree window, boolean addingChild){
        super(addingChild ? "Who should I give a child?":"Who should I give a parent?");
        this.window = window;
        this.addingChild = addingChild;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600,700);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i=JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window?",
                        "Closing "+(addingChild ? "who should I give a child?":"who should I give a parent?"), JOptionPane.YES_NO_CANCEL_OPTION);
                if(i==0){

                    close();
                }
            }

        });

        setLocationRelativeTo(null);
        ShowTable();
    }

    private void ShowTable() {
        tableModel = new PersonTableModel(window.getFamily());
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(500, 600));
        panel = new JPanel();
        panel.add(scrollPane);
        add(panel, BorderLayout.CENTER);
        table.getSelectionModel().addListSelectionListener(event -> {
            Person first = tableModel.getPerson(table.getSelectedRow());

            if(!addingChild && first.hasBothParents()){
                JOptionPane.showMessageDialog(null, "This person has both parents.");
            }else {

                int i = JOptionPane.showConfirmDialog(null, "Are you sure you choose " + first.toString() + "?", "Choosing "+(addingChild ? "who I should give a child":"who I should give a parent?"),JOptionPane.YES_NO_CANCEL_OPTION);
                if (i == 0) {
                    new Choose(window, addingChild, first);
                    close();

                }
            }

            // window.setParentInAMoment(parent);
            //window.addPerson();
        });
        setVisible(true);
        this.validate();
        this.repaint();
    }

    private void close(){
        this.dispose();
    }
}