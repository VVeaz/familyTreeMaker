package me.vveaz.familytreemaker;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FamilyTree extends JFrame
{

    private mxGraph graph;
    private mxGraphComponent graphComponent;

    private List<Person> family;

    private JMenuItem addPerson, addNewChild, addExistingChild, addNewParent,
            addExistingParent, saveFamilyTreePNG, saveFamilyTreeTREE, openFamilyTree;
    private JMenu  addChild, addParent, saveFamilyTree;
    private boolean addingNewChild;
    private boolean addingNewParent;
    private Person ParentInAMoment;
    private Person childInAMoment;

    boolean isAddingNewParent() {
        return addingNewParent;
    }

    void setAddingNewParent(boolean addingNewParent) {
        this.addingNewParent = addingNewParent;
    }

    Person getChildInAMoment() {
        return childInAMoment;
    }

    void setChildInAMoment(Person childInAMoment) {
        this.childInAMoment = childInAMoment;
    }

    public Person getParentInAMoment() {
        return ParentInAMoment;
    }

    public boolean isAddingNewChild() {
        return addingNewChild;
    }

    void setAddingNewChild(boolean addingNewChild) {
        this.addingNewChild = addingNewChild;
    }

    void setParentInAMoment(Person parentInAMoment) {
        this.ParentInAMoment = parentInAMoment;
    }

    List<Person> getFamily() {
        return family;
    }

    private FamilyTree()
    {
        super("Family tree maker - untitled.TREE");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        family = new ArrayList<>();
        graph= new mxGraph();


        addingNewChild =false;
        addingNewParent = false;

        setBounds(10,10,600,400);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        ActionListener myChoserActionListener = new MyChoserActionListener();
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenuItem add_new_family_tree = new JMenuItem("Add new family tree");
        add_new_family_tree.addActionListener(myChoserActionListener);
        file.add(add_new_family_tree);

        openFamilyTree = new JMenuItem("Open family tree");
        openFamilyTree.addActionListener(myChoserActionListener);
        file.add(openFamilyTree);

        addPerson = new JMenuItem("Add person");
        addPerson.addActionListener(myChoserActionListener);
        file.add(addPerson);
        addPerson.setEnabled(false);

        addChild = new JMenu("Add child");

        addNewChild = new JMenuItem("Add new child");
        addNewChild.addActionListener(myChoserActionListener);
        addChild.add(addNewChild);
        addExistingChild = new JMenuItem("Add existing child");
        addExistingChild.addActionListener(myChoserActionListener);
        addChild.add(addExistingChild);
        addExistingChild.setEnabled(false);
        file.add(addChild);
        addChild.setEnabled(false);


        addParent = new JMenu("Add parent");
        addNewParent = new JMenuItem("Add new parent");
        addNewParent.addActionListener(myChoserActionListener);
        addParent.add(addNewParent);
        addExistingParent = new JMenuItem("Add existing parent");
        addExistingParent.addActionListener(myChoserActionListener);
        addParent.add(addExistingParent);
        addExistingParent.setEnabled(false);
        file.add(addParent);
        addParent.setEnabled(false);

        saveFamilyTree = new JMenu("Save family tree");
        saveFamilyTreePNG = new JMenuItem("Save as PNG");
        saveFamilyTreeTREE = new JMenuItem("Save as TREE");

        saveFamilyTreePNG.addActionListener(myChoserActionListener);
        saveFamilyTreeTREE.addActionListener(myChoserActionListener);
        saveFamilyTree.add(saveFamilyTreePNG);
        saveFamilyTree.add(saveFamilyTreeTREE);
        file.add(saveFamilyTree);
        saveFamilyTree.setEnabled(false);

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(myChoserActionListener);
        file.add(quit);

        setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i=JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window?");
                if(i==0){

                    close();
                }

            }

        });

        setVisible(true);
        setLocationRelativeTo(null);




    }
    private void close(){
        this.dispose();
    }





    private class MyChoserActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String choice = e.getActionCommand();
            switch (choice) {
                case "Add new family tree":
                    if (!family.isEmpty()) {
                        FamilyTree ft = new FamilyTree();
                        ft.addPerson();
                    } else {
                        addPerson();


                    }


                    break;
                case "Add person":
                    addPerson();

                    break;
                case "Add new child":
                    setAddingNewChild(true);
                    addNewChild();
                    break;
                case "Add existing child":
                    addExistingChild();
                    break;
                case "Add new parent":
                    setAddingNewParent(true);
                    addParent();
                    break;
                case "Add existing parent":
                    addExistingParent();
                    break;
                case "Save as PNG":
                    try {
                        saveAsPNG();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    break;
                case "Save as TREE":
                    try {
                        saveASTREE();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Open family tree":
                    FamilyTree ft;
                    ft = FamilyTree.this;
                    if(!ft.family.isEmpty()) {
                        ft = new FamilyTree();
                        ft.setVisible(true);
                    }
                    try {
                        openTREE(ft);
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Quit":
                    System.exit(0);
            }
        }


    }

    private void addExistingParent() {
        new AddExisting(this, false);
    }

    private void addParent(){
        new AddNewParent(this);
    }
    private void addNewChild(){

        new AddNewChild(this);
    }
    private void addExistingChild(){
        new AddExisting(this, true);
    }
    private void saveAsPNG() throws IOException {
        JFileChooser fileChooser = new JFileChooser(".");
        int userChoice = fileChooser.showSaveDialog(null);
        if(userChoice == JFileChooser.APPROVE_OPTION){
            String filename = fileChooser.getSelectedFile().getAbsolutePath();
            BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);
            ImageIO.write(image, "PNG", new File(filename+".png"));
            JOptionPane.showMessageDialog(null,filename, "The file name", JOptionPane.PLAIN_MESSAGE);


        }
    }
    private void saveASTREE() throws IOException {
        JFileChooser fileChooser = new JFileChooser(".");
        int userChoice = fileChooser.showSaveDialog(null);
        if(userChoice == JFileChooser.APPROVE_OPTION){
            String filename = fileChooser.getSelectedFile().getAbsolutePath();
            for(Person p: family){
                p.setDrawn(false);
            }
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename+".TREE"))) {
                outputStream.writeObject(family);

            }


            this.setTitle("Family tree maker - "+filename+".TREE");

        }

    }
    private void openTREE(FamilyTree ft) throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser(".");
        int userChoice = fileChooser.showOpenDialog(null);
        if(userChoice == JFileChooser.APPROVE_OPTION){
            String filename = fileChooser.getSelectedFile().getAbsolutePath();
            String extension = filename.substring(filename.length()-4);
            //System.out.println(extension);
            if(!extension.equals("TREE")){
                JOptionPane.showMessageDialog(null,"Select the file with the TREE extension");
                openTREE(ft);
            }else {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
                    //noinspection unchecked
                    ft.family = (List<Person>) inputStream.readObject();
                    ft.drawGraph();
                }
                ft.setTitle("Family tree maker - "+filename);
            }
        }
    }

    void addPerson(){

        new AddPerson(this);



    }
    void drawEdge(Person parent, Person child){
        graph.getModel().beginUpdate();

        try{

                graph.insertEdge(null, null, null, parent.getVertex(), child.getVertex());


        }
        finally
        {
            graph.getModel().endUpdate();
        }
        graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    private void drawGraph(){
        double pX,pY;
        for(Person p: this.family){
            try {
                graph.getModel().beginUpdate();

                if (!p.isDrawn()) {
                    p.setDrawn(true);
                    int width = 150;
                    Object v;
                    String colour = "fillColor=#D3D5E8";
                    if (p.isWomen()) {
                        colour = "fillColor=#F5C8E9;";
                    }
                    pX = graph.getCellGeometry(p.getVertex()).getX();
                    pY = graph.getCellGeometry(p.getVertex()).getY();
                    v = graph.insertVertex(null, null, p.toString(), pX, pY,
                            width, 30, colour);


                    p.setVertex(v);



                }

                for (Person child : p.getChildren()) {
                    if (!child.isDrawn()) {
                        child.setDrawn(true);
                        int height = 30;
                        int width = 150;
                        String colour = "fillColor=#D3D5E8";
                        if (child.isWomen()) {
                            colour = "fillColor=#F5C8E9;";
                        }
                        pX = graph.getCellGeometry(child.getVertex()).getX();
                        pY = graph.getCellGeometry(child.getVertex()).getY();
                        Object v = graph.insertVertex(null, null, child.toString(), pX, pY,
                                width, height, colour);
                        child.setVertex(v);
                    }


                    drawEdge(p, child);


                }
            }finally {
                graph.getModel().endUpdate();
            }
            graphComponent = new mxGraphComponent(graph);
            getContentPane().add(graphComponent);
        }
        addPerson.setEnabled(true);
        addChild.setEnabled(true);
        addParent.setEnabled(true);
        saveFamilyTree.setEnabled(true);
        if(this.family.size() >= 2 ){

            addExistingChild.setEnabled(true);
            addExistingParent.setEnabled(true);
        }else{
            addExistingChild.setEnabled(false);
            addExistingParent.setEnabled(false);
        }

        this.validate();
        this.repaint();
    }

    void updateGraph(Person addingPerson){
        family.add(addingPerson);
        graph.getModel().beginUpdate();

        try
        {
            int width = 150;
            Object v;

            String colour ="fillColor=#D3D5E8";
            if(addingPerson.isWomen()){
                colour = "fillColor=#F5C8E9;";
            }

            if(addingNewChild){
                double x = graph.getCellGeometry(ParentInAMoment.getVertex()).getX();
                double y = graph.getCellGeometry(ParentInAMoment.getVertex()).getY() +70;

                v = graph.insertVertex(null, null, addingPerson.toString(),x, y,
                        width, 30, colour);
                addingPerson.setVertex(v);

                ParentInAMoment.addChildren(addingPerson);
                drawEdge(ParentInAMoment,addingPerson);

                addingNewChild = false;


            }else if(addingNewParent){
                double d = 100;
                if(addingPerson.isWomen()){
                    d=-100;
                }
                double x = graph.getCellGeometry(childInAMoment.getVertex()).getX()+d;
                double y = graph.getCellGeometry(childInAMoment.getVertex()).getY() -70;

                v = graph.insertVertex(null, null, addingPerson.toString(),x, y,
                        width, 30, colour);
                addingPerson.setVertex(v);

                addingPerson.addChildren(childInAMoment);
                drawEdge(addingPerson,childInAMoment);

                addingNewParent = false;
            } else{

                v= graph.insertVertex(null, null, addingPerson.toString(), 800, 400,
                        width, 30, colour);
                addingPerson.setVertex(v);

            }

        }
        finally
        {
            graph.getModel().endUpdate();
        }

        graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

        addPerson.setEnabled(true);
        addChild.setEnabled(true);
        addParent.setEnabled(true);
        saveFamilyTree.setEnabled(true);
        if(family.size() >= 2 ){
            addExistingChild.setEnabled(true);
            addExistingParent.setEnabled(true);
        }else{
            addExistingChild.setEnabled(false);
            addExistingParent.setEnabled(false);
        }

        this.validate();
        this.repaint();
    }


    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        FamilyTree frame = new FamilyTree();
        frame.setVisible(true);
    }

}
