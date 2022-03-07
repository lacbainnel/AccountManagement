//LACBAIN, Jannel C.
// TN31 - 201912305 (BSCSSE)
// Mr. Rhonnel Paculanan
//February 27, 2022

//package com.mycompany.textdemo;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

class AccountManagement extends JFrame {
    //creation of elements
    static JFrame f;
    static JLabel l_id, l_name, l_main1, l_main2, l_course, l_level, l_add, l_email, l_num, statusLabel,l2_Course,l2_level;
    static JTextField t_id, t_name, t_add, t_email, t_num,rid, rname, rcourse, rlevel, radd, remail, rnum;
    static JComboBox c_course, c_level,r2course,r2level;
    static JButton b_add, b_exit, b_clear, b_search, b_update, b_view;
    static JPanel p;
    static JOptionPane op;
    Path path;
    ArrayList<Long> recs_id = new ArrayList<>(); 
    ArrayList<String> recs_name = new ArrayList<>(); 
    ArrayList<String> recs_course = new ArrayList<>(); 
    ArrayList<String> recs_level = new ArrayList<>(); 
    ArrayList<String> recs_add = new ArrayList<>();
    ArrayList<String> recs_email = new ArrayList<>();
    ArrayList<Long> recs_num = new ArrayList<>();
    int recs_course_ind,recs_level_ind;
    Long num,id;
    
    public AccountManagement(){
        acgui() ;
    }
    
    private void acgui() {  
        //creation  of elements with specified label and action
        //For Main Frame        
        GridLayout gl = new GridLayout(0,2);
        f = new JFrame("ACCOUNT MANAGER");
        f.setLocationRelativeTo(null);
        
        //Label: Account Information
        l_main1 = new JLabel("Account ", SwingConstants.RIGHT);   
        l_main2 = new JLabel("Information", SwingConstants.LEFT);  
        
        //Student ID label and text field
        l_id = new JLabel("Student ID: ");
        t_id = new JTextField();
        
        //Name label and text field
        l_name = new JLabel("Name: ");
        t_name = new JTextField();
        
        //Course label and text field
        l_course = new JLabel("Course: ");
        c_course = new JComboBox();
        c_course.addItem("Select Course");
        c_course.addItem("BSCS-SE");
        c_course.addItem("BSIT-AGD");
        c_course.addItem("BSIT-DA");
        c_course.addItem("BSIT-SMBA");
        c_course.addItem("BSIT-WMA");
        c_course.addItem("BSMA");
        c_course.addItem("BSCE");
        c_course.addItem("BSCpE");  
        c_course.addItem("BSEE");
        c_course.addItem("BSECE");
        c_course.addItem("BSME"); 
        
        //Level label and text field
        l_level = new JLabel("Level: ");
        c_level = new JComboBox();
        c_level.addItem("Select Level");
        c_level.addItem("1");
        c_level.addItem("2");
        c_level.addItem("3");
        c_level.addItem("4");  
        c_level.addItem("5");
        
        //Address label and text field
        l_add = new JLabel ("Address: ");
        t_add = new JTextField ();
        
        //Email label and text field
        l_email = new JLabel ("Email: ");
        t_email = new JTextField ();
        
        //Contact number label and text field
        l_num = new JLabel ("Contact Number: ");
        t_num = new JTextField(13);
        
        //Buttons
        b_add = new JButton("ADD");
        b_exit = new JButton ("EXIT");
        b_clear = new JButton("CLEAR");
        b_search = new JButton("SEARCH");
        b_update = new JButton("UPDATE");
        b_view = new JButton("VIEW");
         
        //Panel        
        JPanel p = new JPanel();
        p.setLayout(gl);
        
        //Putting margin and border to labels
        l_id.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        l_name.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        l_course.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        l_level.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        l_add.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        l_email.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        l_num.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));  
        
        //Adding elements to panel
        p.add(l_main1); p.add(l_main2);
        p.add(l_id); p.add(t_id);
        p.add(l_name); p.add(t_name);
        p.add(l_course); p.add(c_course);
        p.add(l_level); p.add(c_level);
        p.add(l_add); p.add(t_add);
        p.add(l_email); p.add(t_email);
        p.add(l_num); p.add(t_num);
        p.add(b_add); p.add(b_exit);
        p.add(b_clear); p.add(b_search);
        p.add(b_view);
        
        //Function of ADD BUTTON
        b_add.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e)  {
                if (t_id.getText().equals("")) {
                    JOptionPane.showMessageDialog(f,"Invalid ID!","Alert",JOptionPane.WARNING_MESSAGE);
                } else if (t_name.getText().equals("")){
                    JOptionPane.showMessageDialog(f,"Invalid Name!","Alert",JOptionPane.WARNING_MESSAGE);
                } else if (t_add.getText().equals("")){
                    JOptionPane.showMessageDialog(f,"Invalid Address!","Alert",JOptionPane.WARNING_MESSAGE);
                } else if (t_email.getText().equals("")){
                    JOptionPane.showMessageDialog(f,"Invalid Email!","Alert",JOptionPane.WARNING_MESSAGE);
                } else if (t_num.getText().equals("")){
                    JOptionPane.showMessageDialog(f,"Invalid Contact Number!","Alert",JOptionPane.WARNING_MESSAGE);
                } else if (c_course.getSelectedItem().equals("Select Course")){
                    JOptionPane.showMessageDialog(f,"Invalid Course!","Alert",JOptionPane.WARNING_MESSAGE);
                } else if (c_level.getSelectedItem().equals("Select Level")){
                    JOptionPane.showMessageDialog(f,"Invalid Level!","Alert",JOptionPane.WARNING_MESSAGE);
                } else {                   
                    try {
                        num = new parseLong(t_num.getText());
                        id = new parseLong(t_id.getText());
                        
                        //Duplicate checking for ID, Name, and Email since these are unique in value
                        if(recs_id.contains(id)) {
                            JOptionPane.showMessageDialog(f,"Student ID Already Registered!","Alert",JOptionPane.INFORMATION_MESSAGE);
                        } else if(recs_name.contains(t_name.getText())) {
                            JOptionPane.showMessageDialog(f,"Student Name Already Registered!","Alert",JOptionPane.INFORMATION_MESSAGE);
                        } else if(recs_email.contains(t_email.getText())) {
                            JOptionPane.showMessageDialog(f,"Email Already Registered!","Alert",JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            recs_id.add(id);
                            recs_num.add(num);
                            System.out.println("false");
                             JOptionPane.showMessageDialog(f,"Successfully Added!","Alert",JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(f,"Invalid Input!","Alert",JOptionPane.WARNING_MESSAGE);
                    } 
                        recs_name.add(t_name.getText());
                        recs_add.add(t_add.getText());
                        recs_email.add(t_email.getText());
                        recs_course.add(c_course.getSelectedItem().toString());
                        recs_level.add(c_level.getSelectedItem().toString());
                        recs_course_ind = c_course.getSelectedIndex();
                        recs_level_ind = c_level.getSelectedIndex();
                                                                   
                    try {
                        FileWriter f_recs = new FileWriter("StudentRecords.txt");
                        
                          // trying to create a file based on the object
                          for (int i=0; i<recs_id.size(); i++) {
                            f_recs.write("\nStudent ID: " + recs_id.get(i).toString() + "\n");
                            f_recs.append("Name: " + recs_name.get(i) + "\n");
                            f_recs.append("Course: " + recs_course.get(i) + "\n");                            
                            f_recs.append("Level: " + recs_level.get(i) + "\n");
                            f_recs.append("Address: " + recs_add.get(i) + "\n");
                            f_recs.append("Email: " + recs_email.get(i) + "\n");
                            f_recs.append("Contact Number: " + recs_num.get(i).toString() + "\n");
                          }                         
                            f_recs.flush();
                            f_recs.close();
                        } catch(Exception ioe) {
                            JOptionPane.showMessageDialog(f,"File not created!","Alert",JOptionPane.WARNING_MESSAGE);
                        }
                    }   
                t_id.setText("");
                t_name.setText("");
                c_course.setSelectedIndex(0);
                c_level.setSelectedIndex(0);
                t_add.setText("");
                t_email.setText("");
                t_num.setText("");                        
            }   
        });
        
        //Function of SEARCH BUTTON
        b_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame recf = new JFrame("RECORDS");
                JPanel recp = new JPanel();
                recp.setLayout(gl);
                recf.setLocationRelativeTo(null);
                int temp = 0;
                int val = 0;
                boolean found = false;
                
                if(recs_id != null) {
                    //for getting the index of the matching record
                    for (int i = 0; i<recs_id.size(); i++) {
                        if(recs_id.get(i).toString().equals(t_id.getText())) {                        
                            temp = recs_id.indexOf(recs_id.get(i));
                            val = temp;
                            found = true;
                        } 
                    }
                 
                    if(found) {
                           System.out.println(recs_name.get(val).toString());
                           rid = new JTextField (recs_id.get(temp).toString());
                           rname = new JTextField (recs_name.get(temp));

                            l2_Course = new JLabel("Course: ");
                            r2course = new JComboBox();
                            r2course.addItem("Select Course");
                            r2course.addItem("BSCS-SE");
                            r2course.addItem("BSIT-AGD");
                            r2course.addItem("BSIT-DA");
                            r2course.addItem("BSIT-SMBA");
                            r2course.addItem("BSIT-WMA");
                            r2course.addItem("BSMA");
                            r2course.addItem("BSCE");
                            r2course.addItem("BSCpE");  
                            r2course.addItem("BSEE");
                            r2course.addItem("BSECE");
                            r2course.addItem("BSME"); 
                              
                            l2_level = new JLabel("Level: ");
                            r2level = new JComboBox();
                            r2level.addItem("Select Level");
                            r2level.addItem("1");
                            r2level.addItem("2");
                            r2level.addItem("3");
                            r2level.addItem("4");  
                            r2level.addItem("5");
        
                           radd = new JTextField(recs_add.get(temp));
                           remail = new JTextField(recs_email.get(temp));
                           rnum = new JTextField(recs_num.get(temp).toString());
                           
                           r2course.setSelectedIndex(recs_course_ind);
                           r2level.setSelectedIndex(recs_level_ind);
                           
                           recp.add(l_main1); recp.add(l_main2);
                           recp.add(l_id); recp.add(rid); 
                           recp.add(l_name); recp.add(rname); 
                           recp.add(l2_Course); recp.add(r2course);
                           recp.add(l2_level); recp.add(r2level);
                           recp.add(l_add); recp.add(radd);
                           recp.add(l_email); recp.add(remail);
                           recp.add(l_num); recp.add(rnum);
                           recp.add(b_update); //recp.add(b_exit);
                           recf.add(recp); 
                           recf.setSize(300, 300);
                           recf.setVisible(true);
                           recf.setResizable(false);
                    } else {
                        JOptionPane.showMessageDialog(f,"No Record Found" ,"Alert",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });           
        
        //Function of EXIT BUTTON
        b_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
 
        //Function of VIEW BUTTON
        b_view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {  
                    //dynamically gets the absolute path of the text file in 
                    //which PC it is running
                    path = Paths.get("StudentRecords.txt");
                    Path pathStr = path.toAbsolutePath();
                    String absPath = pathStr.toAbsolutePath().toString();
                    File file = new File(absPath);
                    
                    System.out.println(path.toAbsolutePath());
                    if(!Desktop.isDesktopSupported()) { //check if Desktop is supported by Platform or not  {  
                        JOptionPane.showMessageDialog(f,"Not Supported" ,"Alert",JOptionPane.WARNING_MESSAGE); 
                        return;  
                    }  
                    Desktop desktop = Desktop.getDesktop();  
                    if(file.exists()) //checks file exists or not  
                        desktop.open(file); //opens the specified file  
                } catch(Exception gee) {  
                    JOptionPane.showMessageDialog(f,"Not Supported" ,"Alert",JOptionPane.WARNING_MESSAGE);  
                }  
            }                                   
        });        
        
        //Function of CLEAR BUTTON
        b_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t_id.setText("");
                t_name.setText("");
                c_course.setSelectedIndex(0);
                c_level.setSelectedIndex(0);
                t_add.setText("");
                t_email.setText("");
                t_num.setText("");
            }
        });    
        
        //Function of UPDATE BUTTON
        b_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i<recs_id.size(); i++) {       
                    if (recs_id.get(i) == Long.parseLong(rid.getText())) {
                    Integer j = recs_id.indexOf(recs_id.get(i));
                    
                    try {
                        recs_id.set(j, Long.parseLong(rid.getText()));
                        recs_num.set(j, Long.parseLong(rnum.getText()));
                    } catch (NumberFormatException nfe2) {
                        JOptionPane.showMessageDialog(f,"Invalid Input!","Alert",JOptionPane.WARNING_MESSAGE);
                    }
                    recs_name.set(j, rname.getText());
                    recs_course.set(j, r2course.getSelectedItem().toString());
                    recs_level.set(j, r2level.getSelectedItem().toString());
                    recs_course_ind = r2course.getSelectedIndex() ;
                    recs_level_ind = r2level.getSelectedIndex();
                   
                    recs_add.set(j, radd.getText());
                    recs_email.set(j,remail.getText());
  
                    try {
                        FileWriter f_recs = new FileWriter("StudentRecords.txt");
                          // trying to create a file based on the object
                          for (int k=0; k<recs_id.size(); k++) {
                            f_recs.write("\nStudent ID: " + recs_id.get(k).toString() + "\n");
                            f_recs.append("Name: " + recs_name.get(k) + "\n");
                            f_recs.append("Course: " + recs_course.get(k) + "\n");                            
                            f_recs.append("Level: " + recs_level.get(k) + "\n");
                            f_recs.append("Address: " + recs_add.get(k) + "\n");
                            f_recs.append("Email: " + recs_email.get(k) + "\n");
                            f_recs.append("Contact Number: " + recs_num.get(k).toString() + "\n");
                          }                         
                            f_recs.flush();
                            f_recs.close();
                        } catch(Exception ioe) {
                            JOptionPane.showMessageDialog(f,"File not updated!","Alert",JOptionPane.WARNING_MESSAGE);
                        }                   
                    }
                }  
                JOptionPane.showMessageDialog(f,"Record Successfully Updated!","Alert",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        f.add(p);
        f.setSize(300, 300);
        f.setVisible(true);
        f.setResizable(false);   
    } 
    
    public static void main(String[] args)  {
        AccountManagement am = new AccountManagement();
    }
}