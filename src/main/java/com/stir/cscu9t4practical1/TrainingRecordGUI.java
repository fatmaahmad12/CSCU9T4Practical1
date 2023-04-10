// GUI and main program for the Training Record updated by shermina on 12.24
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

	private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField  Terrain = new JTextField(2);
    private JTextField Tempo = new JTextField(2);
    private JTextField Recovery = new JTextField(2);
    private JTextField  Repetitions = new JTextField(2);
    private JTextField Where = new JTextField(2);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel labt = new JLabel(" Terrain:");
    private JLabel labte = new JLabel(" Tempo:");
    private JLabel labp = new JLabel("pool/Outdoor:");
    private JLabel labge = new JLabel(" Recovery:");
    private JLabel labnum = new JLabel(" Repetitions:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton lookAllByDate = new JButton("Look All");
    private JButton  RemoveByDate=new  JButton ("Remove");
    
    private TrainingRecord myAthletes = new TrainingRecord();
    private JTextArea outputArea = new JTextArea(5, 50);
    public static void main(String[] args) {
        TrainingRecordGUI Applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(labt);
        add(Terrain);
        Terrain.setEditable(true);
        
        add(labte);
        add(Tempo);
        Tempo.setEditable(true);
        
        add(labge);
        add(Recovery);
        Recovery.setEditable(true);
        
        add(labnum);
        add(Repetitions);
        Repetitions.setEditable(true);
        
        add(labp);
        add(Where);
       Where.setEditable(true);
        
        add(addR);
        addR.addActionListener(this);
        
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(lookAllByDate);
        lookAllByDate.addActionListener(this);
        add(RemoveByDate);
        RemoveByDate.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == lookAllByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == RemoveByDate) {
            message = removeEntry();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
    	
    	
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        
        
        Entry e;
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
       
        String ter =Terrain.getText();
        String tem =Tempo.getText();
         
     int rec=Integer.parseInt(Recovery.getText());
          
     int rep =Integer.parseInt(Repetitions.getText());
     
     String Wh=Where.getText();
  
     if(!Recovery.getText().isEmpty()&&!Repetitions.getText().isEmpty())
    	 e= new SprintEntry(n, d, m, y, h, mm, s, km,rec,rep);
     else if (!Wh.isEmpty())
    	 e= new SwimEntry(n, d, m, y, h, mm, s, km,Wh);
     else if(!tem.isEmpty()&&!ter.isEmpty())
    	 e= new CycleEntry(n, d, m, y, h, mm, s, km,ter,tem); 
      else
    	 e= new Entry(n,d,m,y,h,mm,s,km);
       
     myAthletes.addEntry(e);
      
        return message;
   }  
    
       
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
  
    }
    
    public String lookAllByName() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("Finding all records ...");
        String message = myAthletes.lookupallEntry(d, m, y);
        return message;
    }
    
    
    public String removeEntry() {
    	String n =name.getText();
    	int m= Integer.parseInt(month.getText());
    	int d= Integer.parseInt(day.getText());
    	int y= Integer.parseInt(year.getText());
    	outputArea.setText("Removing a record...");
    	String message = myAthletes.removeEntry1(n, d ,m ,y);
    	return message;
    }
    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        Terrain.setText("");
        Tempo.setText("");
        Recovery.setText("");
        Repetitions.setText("");
        Where.setText("");
    }
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
        Terrain.setText(ent.getName());
        Tempo.setText(ent.getName());
        Recovery.setText(String.valueOf(ent.getDistance()));
        Repetitions.setText(String.valueOf(ent.getDistance()));
        Where.setText(ent.getName());
    }

} 

