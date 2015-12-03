import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.*;
class dict extends KeyAdapter implements ActionListener,KeyListener {
    String define;
    JButton enterButton,calculate,wordOfDay,inProcess;
    JLabel heading;
    JScrollPane forDefinition;
    JTextField enterWord=new JTextField(20);
    JTextArea definition=new JTextArea(20,30);  
    JButton updateData=new JButton("Update");
    JComboBox options;
    JPanel calculatorPanel=new JPanel();
    StringBuilder sb=new StringBuilder();
    dict(){    
    String[] items={"definitions","synonyms","antonyms"};
    options=new JComboBox(items);
    enterButton=new JButton(new ImageIcon("Images/find.png"));
    enterButton.setPressedIcon(new ImageIcon("Images/searching.jpg"));
    enterWord.setFont(new Font("sansSerif",Font.BOLD,15));    
    enterButton.setBorder(null); 
    forDefinition=new JScrollPane(definition);
    definition.setFont(new Font("sansSerif",Font.ITALIC,15));    
    JLabel heading=new JLabel("Search your word",JLabel.CENTER);
    JFrame frame=new JFrame("Dictionary");
    definition.setLineWrap(true); //to wrap data with given columns or vertically.
    calculate=new JButton("Calculator");
    wordOfDay=new JButton("Word of the Day");
    inProcess=new JButton("In Process");
    JPanel optionsCol=new JPanel();
    
    GridBagLayout gbag=new GridBagLayout();
    GridBagConstraints gbc=new GridBagConstraints();
    frame.setLayout(gbag);
    gbc.gridx=0;
    gbc.gridwidth=3;
    gbag.setConstraints(heading, gbc);
    frame.add(heading);
    gbc.gridwidth=1;
    gbc.gridx=0;
    gbc.gridy=1;
    gbc.insets=new Insets(0,3,3,0);
    gbag.setConstraints(options, gbc);    
    frame.add(options);
    gbc.gridwidth=1;    
    gbc.gridx=1;
    gbc.gridy=1;
    gbc.insets=new Insets(2,5,5,0);
    gbag.setConstraints(enterWord, gbc);    
    frame.add(enterWord);
    gbc.gridwidth=1;    
    gbc.gridx=2;
    gbc.gridy=1;
    gbc.insets=new Insets(0,2,3,2);
    gbag.setConstraints(enterButton, gbc);    
    frame.add(enterButton);    
    gbc.gridx=0;
    gbc.gridy=2;
    gbc.gridwidth=3;
    gbag.setConstraints(forDefinition, gbc);
    frame.add(forDefinition);
    gbc.gridx=4;
    gbc.gridy=0;
    gbc.gridheight=3;
    gbag.setConstraints(optionsCol, gbc);
    gbc.gridx=5;
    gbc.gridy=0;
//    gbc.gridheight=3;
 //   gbag.setConstraints(calculatorPanel,gbc);
    calculatorPanel.add(new JButton("1"));
    calculatorPanel.add(new JButton("1"));
    calculatorPanel.add(new JButton("1"));
    calculatorPanel.add(new JButton("1"));
    calculatorPanel.add(new JButton("1"));
  //  frame.add(calculatorPanel);        
  //  calculatorPanel.setVisible(false);
    GridBagLayout gl=new GridBagLayout();
    GridBagConstraints gc=new GridBagConstraints();
    optionsCol.setLayout(gl);
    gc.gridx=0;
    gc.gridy=0;   
    gc.insets=new Insets(0,0,100,0);
    gl.setConstraints(updateData,gc);    
    optionsCol.add(updateData);
    gc.gridy=1;   
    gc.insets=new Insets(0,0,100,0);
    gl.setConstraints(calculate,gc);
    optionsCol.add(calculate);
    gc.gridy=2;
    gc.insets=new Insets(0,0,100,0);
    gl.setConstraints(wordOfDay, gc);
    optionsCol.add(wordOfDay);
    gc.gridy=3;  
    gc.insets=new Insets(0,0,10,0);
    gl.setConstraints(inProcess, gc);
    optionsCol.add(inProcess);
     frame.add(optionsCol);      
      frame.pack();
 //   frame.setSize(540,490);
 //   frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    frame.setVisible(true);
    calculate.addActionListener(this);
    wordOfDay.addActionListener(this);
    inProcess.addActionListener(this);
    updateData.addActionListener(this);
    options.addActionListener(this);
    enterButton.addActionListener(this);
    enterWord.addKeyListener(this);
    definition.setEditable(false);   
}
public void actionPerformed(ActionEvent ae){
    if(ae.getSource().equals(calculate)|| ae.getSource().equals(wordOfDay)||ae.getSource().equals(inProcess)){
        calculatorPanel.setVisible(true);
        definition.setText("Oops . We are Still working on that.");
    }
    if(ae.getSource().equals(updateData)){
        definition.setText("updation of database in Progress...\n It will take a looong... time\n Sit down and relax\nTo cancel,Click on close .");
        new updateDictionary().main();
    }
       if(ae.getSource().equals(enterButton)){
       String category=(String)options.getSelectedItem();
       enterButton.setEnabled(false);
       enterWord.setEnabled(false);
       define=new Database().main(enterWord.getText(),category);       
       definition.setText(define);           
       enterButton.setEnabled(true);
       enterWord.setEnabled(true);
    }
    
}
public void keyPressed(KeyEvent ke){
    String category=(String)options.getSelectedItem();
       int key=ke.getKeyCode();
       if(key==KeyEvent.VK_ENTER){
      enterButton.removeActionListener(this);           
      enterButton.doClick();     //Virtually click button.simulate the action of button clicking.invoke actionEvent.
   //    enterButton.setSelected(true); // it will not trigger the ActionEvent.But Not working now.dont know why.thats why used enterButton.removeActionListener();
       enterButton.setEnabled(false);
       enterWord.setEnabled(false);
       define=new Database().main(enterWord.getText(),category);
       definition.setText(define);    
       enterButton.setEnabled(true);
       enterWord.setEnabled(true);
       }
}
}
public class Dictionary{
    public static void main(String args[]){               
        new dict();
    }
}

