package me.vveaz.familytreemaker;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class AddNewChild extends JFrame {
    private final FamilyTree window;
    private PersonTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;

    AddNewChild(FamilyTree window){
        super("Who should I give a child?");
        this.window = window;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600,700);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i=JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window?");
                if(i==0){
                    window.setAddingNewChild(false);
                    close();
                }
            }

        });

        setLocationRelativeTo(null);
        showTable();
    }

    private void showTable(){
        tableModel = new PersonTableModel(window.getFamily());
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(500, 600));
        panel = new JPanel();
        panel.add(scrollPane);
        add(panel,BorderLayout.CENTER);
        table.getSelectionModel().addListSelectionListener(event -> {
            Person parent = tableModel.getPerson(table.getSelectedRow());
            window.setParentInAMoment(parent);
            window.addPerson();
            close();
        });

        setVisible(true);
        this.validate();
        this.repaint();
    }

    private void close(){
        this.dispose();
    }
}