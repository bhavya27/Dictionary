import java.util.regex.*;
import java.awt.*;
import javax.swing.*;
public class rough {
public static void main(String args[]){
  JFrame f=new JFrame("DEmo");
  JPanel p=new JPanel();
  p.setLayout(new GridBagLayout());
  GridBagConstraints c=new GridBagConstraints();
  c.insets=new Insets(2,2,2,2);
  c.weighty=1.0;
  c.weightx=1.0;
  c.gridx=0;
  c.gridy=0;
  p.add(new JButton("java"),c);
  c.gridx=1;
  p.add(new JButton("Source"),c);
  c.gridx=0;
  c.gridy=1;
  p.add(new JButton("and"),c);
  c.gridx=1;
  p.add(new JButton("Support."),c);
  
  
  
  
  
  
}    
}
