import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//exception throws if a number is divided by 0
class divisionbyzero extends Exception{
divisionbyzero(String s){
super(s);
}
}
class gui_calculator extends JFrame implements ActionListener {
JTextField t1;
JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17;
JPanel jp1,jp2,jp3,jp4;
double firstnumber;
double secondnumber;
String operator;
double result;
gui_calculator(){
setVisible(true);
setSize(400,400);
setTitle("calculator");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
t1=new JTextField(50);
b1=new JButton("7");
b2=new JButton("8");
b3=new JButton("9");
b4=new JButton("/");
b5=new JButton("4");
b6=new JButton("5");
b7=new JButton("6");
b8=new JButton("*");
b9=new JButton("1");
b10=new JButton("2");
b11=new JButton("3");
b12=new JButton("-");
b13=new JButton("0");
b14=new JButton(".");
b15=new JButton("=");
b16=new JButton("+");
b17=new JButton("C");
jp1=new JPanel();
jp2=new JPanel();
jp3=new JPanel();
jp4=new JPanel();
setLayout(new GridLayout(6,4));
jp1.setLayout(new GridLayout(1,4,5,5));
jp2.setLayout(new GridLayout(1,4,5,5));
jp3.setLayout(new GridLayout(1,4,5,5));
jp4.setLayout(new GridLayout(1,4,5,5));
jp1.add(b1);jp1.add(b2);jp1.add(b3);jp1.add(b4);
jp2.add(b5);jp2.add(b6);jp2.add(b7);jp2.add(b8);
jp3.add(b9);jp3.add(b10);jp3.add(b11);jp3.add(b12);
jp4.add(b13);jp4.add(b14);jp4.add(b15);jp4.add(b16);
t1.setFont(new Font("Arial",Font.PLAIN,30));
add(t1);
add(jp1);
add(jp2);
add(jp3);
add(jp4);
add(b17);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
b7.addActionListener(this);
b8.addActionListener(this);
b9.addActionListener(this);
b10.addActionListener(this);
b11.addActionListener(this);
b12.addActionListener(this);
b13.addActionListener(this);
b14.addActionListener(this);
b15.addActionListener(this);
b16.addActionListener(this);
b17.addActionListener(this);
}
//event handling code
public void actionPerformed(ActionEvent ae){
String cmd=ae.getActionCommand();
//if pressed button is a number
if(Character.isDigit(cmd.charAt(0))){
t1.setText(t1.getText()+cmd);

}
//if pressed button is an operator
else if(cmd.equals("+")||cmd.equals("-")||cmd.equals("*")||cmd.equals("/")){
firstnumber=Double.parseDouble(t1.getText());
operator=cmd;
t1.setText(t1.getText()+cmd);
}
//if pressed button is a (.)
else if(cmd.equals(".")){
t1.setText(t1.getText()+cmd);
}
//for calculating result
else if(cmd.equals("=")){
String s=t1.getText();

if(s.contains("+")){
String numbers[]=s.split("\\+");
secondnumber=Double.parseDouble(numbers[1]);
firstnumber=Double.parseDouble(numbers[0]);
result=firstnumber+secondnumber;
}
else if(s.contains("-")){
String numbers[]=s.split("\\-");
secondnumber=Double.parseDouble(numbers[1]);
firstnumber=Double.parseDouble(numbers[0]);
result=firstnumber-secondnumber;
}
else if(s.contains("*")){
String numbers[]=s.split("\\*");
secondnumber=Double.parseDouble(numbers[1]);
firstnumber=Double.parseDouble(numbers[0]);
result=firstnumber*secondnumber;
}
else if(s.contains("/")){
String numbers[]=s.split("\\/");
secondnumber=Double.parseDouble(numbers[1]);
firstnumber=Double.parseDouble(numbers[0]);
try{
if(secondnumber==0){
t1.setText("");
throw new divisionbyzero("can't divide");
}
else{
result=firstnumber/secondnumber;
}
}
catch(divisionbyzero x){
JOptionPane.showMessageDialog(this,x.getMessage());
return;
}
}
if(result==(int)result){
t1.setText(String.valueOf((int)result));
}
else{
t1.setText(String.valueOf(result));
}
}
//for clear button
else{
t1.setText("");
firstnumber=0;
secondnumber=0;
operator="";
result=0;
}
}
//main function
public static void main(String args[]){
new gui_calculator();
}
}


