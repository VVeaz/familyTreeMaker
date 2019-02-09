import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddPerson extends JFrame implements ActionListener {
    private FamilyTree window;
    private JButton jButton1Add;
    private JTextField jTextField1Name;
    private JTextField jTextField2SecondName;
    private JTextField jTextField3Surname;
    private JTextField jTextField4DateOfBirth;
    private JTextField jTextField5DateOfDeath;
    private JRadioButton option1Woman;
    private JRadioButton option2Man;
    private ButtonGroup sex;

    AddPerson(FamilyTree window){

        super("Add person");
        this.window = window;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jTextField1Name =new JTextField("Name");
        jTextField1Name.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fEvt) {
                JTextField tField = (JTextField)fEvt.getSource();
                tField.selectAll();
            }
        });

        jTextField2SecondName =new JTextField("Second name");
        jTextField2SecondName.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fEvt) {
                JTextField tField = (JTextField)fEvt.getSource();
                tField.selectAll();
            }
        });

        jTextField3Surname =new JTextField("Surname");
        jTextField3Surname.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fEvt) {
                JTextField tField = (JTextField)fEvt.getSource();
                tField.selectAll();
            }
        });

        jTextField4DateOfBirth =new JTextField("Date of birth");
        jTextField4DateOfBirth.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fEvt) {
                JTextField tField = (JTextField)fEvt.getSource();
                tField.selectAll();
            }
        });

        jTextField5DateOfDeath =new JTextField("Date of death");
        jTextField5DateOfDeath.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fEvt) {
                JTextField tField = (JTextField)fEvt.getSource();
                tField.selectAll();
            }
        });


        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));

        panel1.add(jTextField1Name);
        panel1.add(Box.createRigidArea(new Dimension(0,10)));
        panel1.add(jTextField2SecondName);
        panel1.add(Box.createRigidArea(new Dimension(0,10)));
        panel1.add(jTextField3Surname);
        panel1.add(Box.createRigidArea(new Dimension(0,10)));
        panel1.add(jTextField4DateOfBirth);
        panel1.add(Box.createRigidArea(new Dimension(0,10)));
        panel1.add(jTextField5DateOfDeath);

        option1Woman = new JRadioButton("Woman",true);
        option2Man = new JRadioButton("Man");
        sex = new ButtonGroup();
        sex.add(option1Woman);
        sex.add(option2Man);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(option1Woman);
        panel2.add(option2Man);



        jButton1Add =new JButton("Add person");
        jButton1Add.addActionListener(this);

        panel1.add(panel2);
        panel1.add(jButton1Add);


        setLayout(new GridBagLayout());
        add(panel1);

        setSize(375,300);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i=JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window?");
                if(i==0){
                    window.setAddingNewChild(false);
                    window.setAddingNewParent(false);
                    close();
                }
                //close();
            }

        });
        setVisible(true);
        setLocationRelativeTo(null);

    }



    public void actionPerformed(ActionEvent e) {
        String nameText= jTextField1Name.getText();
        String second_nameText= jTextField2SecondName.getText();
        String surnameText= jTextField3Surname.getText();
        String date_of_birthText = jTextField4DateOfBirth.getText();
        String date_of_deathText= jTextField5DateOfDeath.getText();



        Person newPerson =new Person(nameText,second_nameText,surnameText,date_of_birthText,date_of_deathText);

        if(option2Man.isSelected()){ //we are adding man
            newPerson.setWomen(false);
            if(window.isAddingNewParent() && window.getChildInAMoment().hasFather()){
                JOptionPane.showMessageDialog(null, window.getChildInAMoment().toString()+" has father.");
            }else{

                window.updateGraph(newPerson);
                this.dispose();
            }
        }else if(window.isAddingNewParent()&&window.getChildInAMoment().hasMother()){
            JOptionPane.showMessageDialog(null, window.getChildInAMoment().toString()+" has mother.");
        }else {


            window.updateGraph(newPerson);
            this.dispose();
        }
    }
    private void close(){
        this.dispose();
    }




}