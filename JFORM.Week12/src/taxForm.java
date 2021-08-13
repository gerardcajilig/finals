import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * Created by JFormDesigner on Thu Jul 29 15:37:06 PDT 2021
 */



/**
 * @author unknown
 */
public class taxForm extends JFrame {
    //hashmap created
    public static ArrayList<Employee> list1 = new ArrayList<Employee>();


    public taxForm() {
        initComponents();
    }

    private void jbnAddActionPerformed(ActionEvent e) {
        // TODO add your code here
        //go to jform, click property then events action listener
        //get the values from the text boxes

        String num = txtNum.getText();
        String name = txtEmpName.getText();
        String income = txtEmpIncome.getText();
        String status = "";
        String member = "";

        if(rboSingle.isSelected()){
            status="Single";
        }
        if(rboMarried.isSelected()){
            status="Married";
        }if(chkUnion.isSelected()){
            member="Yes";
        }else{
            member = "No";
        }

        //adding the data to obj array
        list1.add(new Employee(num, name, income, status, member));
        //calling the write file function to enter the data
        WriteFile();

        String[][] array = new String[list1.size()][5];

        //traverse the list to bring it to our list.
        for(int i = 0; i< list1.size(); ++i){
            //transer the data from hash map to our array
            array[i][0] = list1.get(i).getEmpNum();
            array[i][1] = list1.get(i).getEmpName();
            array[i][2] = list1.get(i).getMonthlyIncome();
            array[i][3] = list1.get(i).getStatus();
            array[i][4] = list1.get(i).getMember();

        }

        //column header
        String columns[] = {"Emp Number", "Emp Name", "Income", "Status", "Member Type"};

        //we are now sending to Jtable
        //passing the array which has the data and columns which is the header created.
        DefaultTableModel model = new DefaultTableModel(array, columns);

        //
        jData.setModel(model);



    }

    private void jbnEditActionPerformed(ActionEvent e) {
        // TODO add your code here
        //needs the old employee number to search for record.
        DefaultTableModel model = (DefaultTableModel) jData.getModel();
        int index1 = jData.getSelectedRow();
        //gettting the old data value
        String oldValue = model.getValueAt(index1, 0).toString();
        //using hashmap to edit
        for(int i = 0; i<list1.size(); ++i){
            //removing the old one then adding the new details.
            if(oldValue.equals(list1.get(i).getEmpNum())){
                list1.remove(i);
            }

        }

        String num = txtNum.getText();
        String name = txtEmpName.getText();
        String income = txtEmpIncome.getText();
        String status = "";
        String member = "";

        if(rboSingle.isSelected()){
            status="Single";
        }
        if(rboMarried.isSelected()){
            status="Married";
        }if(chkUnion.isSelected()){
            member="Yes";
        }else{
            member = "No";
        }

        //adding the data to obj array
        list1.add(new Employee(num, name, income, status, member));
        //calling the write file function to enter the data
        WriteFile();

        String[][] array = new String[list1.size()][5];

        //traverse the list to bring it to our list.
        for(int i = 0; i< list1.size(); ++i){
            //transer the data from hash map to our array
            array[i][0] = list1.get(i).getEmpNum();
            array[i][1] = list1.get(i).getEmpName();
            array[i][2] = list1.get(i).getMonthlyIncome();
            array[i][3] = list1.get(i).getStatus();
            array[i][4] = list1.get(i).getMember();

        }

        //column header
        String columns[] = {"Emp Number", "Emp Name", "Income", "Status", "Member Type"};

        //we are now sending to Jtable
        //passing the array which has the data and columns which is the header created.
        DefaultTableModel model2 = new DefaultTableModel(array, columns);

        //
        jData.setModel(model2);


    }

    private void jDataMouseClicked(MouseEvent e) {
        // TODO add your code here
        //different because user needs to click on the table first for the data tothen edit
        //reverse
        //j data is name of the table
        //throws data back to the txt field
        DefaultTableModel model = (DefaultTableModel) jData.getModel();
        int index1 = jData.getSelectedRow();
        txtNum.setText((String) model.getValueAt(index1, 0));
        txtEmpName.setText((String) model.getValueAt(index1, 1));
        txtEmpIncome.setText((String) model.getValueAt(index1, 2));
        //making it a string
        String status = model.getValueAt(index1, 3).toString();
        String member = model.getValueAt(index1, 4).toString();

        if(status.equals("single")){
            rboSingle.setSelected(true);
        }
        if(status.equals("Married")){
            rboMarried.setSelected(true);
        }
        if(member.equals("Yes")){
            chkUnion.setSelected(true);
        }else{
            chkUnion.setSelected(false);
        }

        double tax;
        double tafAfter = 0;
        DecimalFormat dec = new DecimalFormat("#.##");
        txtIncome.setText(txtEmpIncome.getText());


        if(rboSingle.isSelected()){
            tax = Double.parseDouble(txtEmpIncome.getText()) *.015;
            txtTotal.setText(dec.format(tax));
            tafAfter = Double.parseDouble(txtEmpIncome.getText()) - tax;
            txtAfterTax.setText(dec.format(tafAfter));

        }
        if(rboMarried.isSelected()){
            tax = Double.parseDouble(txtEmpIncome.getText()) *.010;
            txtTotal.setText(dec.format(tax));
            tafAfter = Double.parseDouble(txtEmpIncome.getText()) - tax;
            txtAfterTax.setText(dec.format(tafAfter));

        }
        if(rboMarried.isSelected()){
            tafAfter = tafAfter - 100;
            txtAfterTax.setText(dec.format(tafAfter));

        }


    }

    private void jbnDeleteActionPerformed(ActionEvent e) {
        // TODO add your code here
        DefaultTableModel model = (DefaultTableModel) jData.getModel();
        int index1 = jData.getSelectedRow();
        //gettting the old data value
        String oldValue = model.getValueAt(index1, 0).toString();
        //using hashmap to edit
        for(int i = 0; i<list1.size(); ++i){
            //removing the old one then adding the new details.
            if(oldValue.equals(list1.get(i).getEmpNum())){
                list1.remove(i);
            }

        }
        WriteFile();

        String[][] array = new String[list1.size()][5];

        //traverse the list to bring it to our list.
        for(int i = 0; i< list1.size(); ++i){
            //transer the data from hash map to our array
            array[i][0] = list1.get(i).getEmpNum();
            array[i][1] = list1.get(i).getEmpName();
            array[i][2] = list1.get(i).getMonthlyIncome();
            array[i][3] = list1.get(i).getStatus();
            array[i][4] = list1.get(i).getMember();

        }

        //column header
        String columns[] = {"Emp Number", "Emp Name", "Income", "Status", "Member Type"};

        //we are now sending to Jtable
        //passing the array which has the data and columns which is the header created.
        DefaultTableModel model2 = new DefaultTableModel(array, columns);

        //
        jData.setModel(model2);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label3 = new JLabel();
        txtNum = new JTextField();
        label2 = new JLabel();
        txtEmpName = new JTextField();
        label4 = new JLabel();
        txtEmpIncome = new JTextField();
        panel1 = new JPanel();
        label6 = new JLabel();
        txtIncome = new JTextField();
        label7 = new JLabel();
        txtTotal = new JTextField();
        label8 = new JLabel();
        txtAfterTax = new JTextField();
        rboSingle = new JRadioButton();
        rboMarried = new JRadioButton();
        chkUnion = new JCheckBox();
        scrollPane1 = new JScrollPane();
        jData = new JTable();
        jbnAdd = new JButton();
        jbnEdit = new JButton();
        jbnDelete = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label3 ----
        label3.setText("Enter Employee Number");
        contentPane.add(label3, "cell 1 0");
        contentPane.add(txtNum, "cell 2 0 3 1");

        //---- label2 ----
        label2.setText("Enter Employee Name");
        contentPane.add(label2, "cell 1 1");
        contentPane.add(txtEmpName, "cell 2 1 3 1");

        //---- label4 ----
        label4.setText("Enter Employee Salary");
        contentPane.add(label4, "cell 1 2");
        contentPane.add(txtEmpIncome, "cell 2 2 3 1");

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
            .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax
            . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,
            12 ) ,java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans
            .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e.
            getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]",
                // rows
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- label6 ----
            label6.setText("Income");
            panel1.add(label6, "cell 0 0");
            panel1.add(txtIncome, "cell 0 1");

            //---- label7 ----
            label7.setText("Total Tax");
            panel1.add(label7, "cell 0 2");
            panel1.add(txtTotal, "cell 0 3");

            //---- label8 ----
            label8.setText("Income After Tax");
            panel1.add(label8, "cell 0 4");
            panel1.add(txtAfterTax, "cell 0 5");
        }
        contentPane.add(panel1, "cell 1 3 1 5");

        //---- rboSingle ----
        rboSingle.setText("Single");
        contentPane.add(rboSingle, "cell 2 3");

        //---- rboMarried ----
        rboMarried.setText("Married");
        contentPane.add(rboMarried, "cell 4 3");

        //---- chkUnion ----
        chkUnion.setText("Union Member");
        contentPane.add(chkUnion, "cell 2 4");

        //======== scrollPane1 ========
        {

            //---- jData ----
            jData.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    jDataMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(jData);
        }
        contentPane.add(scrollPane1, "cell 2 5 3 1");

        //---- jbnAdd ----
        jbnAdd.setText("Add");
        jbnAdd.addActionListener(e -> jbnAddActionPerformed(e));
        contentPane.add(jbnAdd, "cell 2 7");

        //---- jbnEdit ----
        jbnEdit.setText("Edit");
        jbnEdit.addActionListener(e -> jbnEditActionPerformed(e));
        contentPane.add(jbnEdit, "cell 3 7");

        //---- jbnDelete ----
        jbnDelete.setText("Delete");
        jbnDelete.addActionListener(e -> jbnDeleteActionPerformed(e));
        contentPane.add(jbnDelete, "cell 4 7");
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(rboSingle);
        buttonGroup1.add(rboMarried);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label3;
    private JTextField txtNum;
    private JLabel label2;
    private JTextField txtEmpName;
    private JLabel label4;
    private JTextField txtEmpIncome;
    private JPanel panel1;
    private JLabel label6;
    private JTextField txtIncome;
    private JLabel label7;
    private JTextField txtTotal;
    private JLabel label8;
    private JTextField txtAfterTax;
    private JRadioButton rboSingle;
    private JRadioButton rboMarried;
    private JCheckBox chkUnion;
    private JScrollPane scrollPane1;
    private JTable jData;
    private JButton jbnAdd;
    private JButton jbnEdit;
    private JButton jbnDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        taxForm obj = new taxForm();
        obj.setVisible(true);
        obj.Readfile();
    }

    //first make the read file

    public void Readfile(){
        String path = "employee.txt";
        File myFile = new File(path);
        //reading the file put it into a string
        String input[];

        //checking if data has a file
        if(myFile.length()!=0){
            try{
                Scanner myReader = new Scanner(myFile);
                //reading the text file one by one
                while(myReader.hasNextLine()){
                    //store whatever the line was written will stored to data
                    String data = myReader.nextLine();
                    //splitting data into fields.
                    input = data.split("\\s+"); //splitting it into spaces.
                    //storing each field into a temporary variable
                    String num = input[0];
                    String name = input[1];
                    String income = input [2];
                    String status = input[3];
                    String member = input [4];

                    //object array class, storing objects in an array
                    list1.add(new Employee(num, name, income, status, member));
                }

                myReader.close();

                //always needs a catch
            }catch(FileNotFoundException ex){
                System.out.println("File Not Found!!!!!");
            }
        }
        //depend on the size of the list for the rows and columns is fixed with 5.
        String[][] array = new String[list1.size()][5];

        //traverse the list to bring it to our list.
        for(int i = 0; i< list1.size(); ++i){
            //transer the data from hash map to our array
            array[i][0] = list1.get(i).getEmpNum();
            array[i][1] = list1.get(i).getEmpName();
            array[i][2] = list1.get(i).getMonthlyIncome();
            array[i][3] = list1.get(i).getStatus();
            array[i][4] = list1.get(i).getMember();

        }

        //column header
        String columns[] = {"Emp Number", "Emp Name", "Income", "Status", "Member Type"};

        //we are now sending to Jtable
        //passing the array which has the data and columns which is the header created.
        DefaultTableModel model = new DefaultTableModel(array, columns);

        //
        jData.setModel(model);

    }

    public void WriteFile(){
    //reading the path
    String path = "employee.txt";
    File myFile = new File(path);

    //again making it as string;
    String input;

    try{
        FileWriter myWriter = new FileWriter(myFile);
        //reading it from the hashmap
        for (int j = 0; j <list1.size(); ++j){
            input = list1.get(j).getEmpNum() + " " + list1.get(j).getEmpName() + " " + list1.get(j).getMonthlyIncome() + " " + list1.get(j).getStatus() + " " + list1.get(j).getMember() + "\n ";
            myWriter.write(input);
        }

        myWriter.close();
    }catch(IOException ex){
        System.out.println("File Error!");
    }







    }




}
