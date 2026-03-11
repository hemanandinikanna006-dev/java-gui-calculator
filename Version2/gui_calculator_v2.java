import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//exception throws if a number is divided by 0
class divisionbyzero extends Exception{
divisionbyzero(String s){
super(s);
}
}

class gui_calculator_v2 extends JFrame implements ActionListener,Runnable {
JTextField t1;
JTextArea ta;
JLabel l;
JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21;
JPanel jp1,display;
Stack<Double> nums;
Stack<String> ops;
Thread t;
int bpos=0;
String text="JAVA GUI CALCULATOR ";

gui_calculator_v2(){
nums = new Stack<>();
ops = new Stack<>();
setVisible(true);
setSize(350,500);
//popup screen displays at middle
setLocationRelativeTo(null);
setTitle("calculator");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
Color bg=new Color(240,240,240);
getContentPane().setBackground(bg);
l=new JLabel();
ta=new JTextArea(10,20);
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
b18=new JButton("%");
b19=new JButton("^");
b20=new JButton("√");
b21=new JButton("del");

//to set margin for buttons
Insets margin = new Insets(5,5,5,5);
JButton[] buttons = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21};
for(JButton b : buttons){
b.setMargin(margin);
b.setFont(new Font("Arial", Font.BOLD, 18));
b.setOpaque(true);

if("0123456789".contains(b.getText())){
b.setBackground(Color.WHITE);
}
else if("=".contains(b.getText())){
b.setBackground(new Color(0, 120, 215));
b.setForeground(Color.WHITE);
}
else if("Cdel%".contains(b.getText())){
b.setBackground(new Color(240, 240, 240));
}
else{
b.setBackground(new Color(245, 245, 245));
b.setForeground(new Color(0, 120, 215));
}
}

jp1=new JPanel();
setLayout(new BorderLayout());
t1.setHorizontalAlignment(JTextField.RIGHT);
t1.setEditable(false);
jp1.setBackground(bg);

jp1.setLayout(new GridLayout(7,3,5,5));
jp1.add(b17);jp1.add(b21);jp1.add(b18);
jp1.add(b1);jp1.add(b2);jp1.add(b3);
jp1.add(b5);jp1.add(b6);jp1.add(b7);
jp1.add(b9);jp1.add(b10);jp1.add(b11);
jp1.add(b4);jp1.add(b8);jp1.add(b12);
jp1.add(b16);jp1.add(b13);jp1.add(b14);
jp1.add(b19);jp1.add(b20);jp1.add(b15);

t1.setFont(new Font("Arial",Font.PLAIN,30));
display=new JPanel(new BorderLayout());
display.setBackground(bg);
display.setPreferredSize(new Dimension(250,80));
add(jp1,BorderLayout.CENTER);
display.add(t1,BorderLayout.CENTER);
l.setHorizontalAlignment(JLabel.CENTER);
display.add(l,BorderLayout.NORTH);
l.setFont(new Font("Arial",Font.BOLD,20));
l.setForeground(Color.BLUE);
add(display,BorderLayout.NORTH);
ta.setEditable(false);

add(ta,BorderLayout.EAST);
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
b18.addActionListener(this);
b19.addActionListener(this);
b20.addActionListener(this);
b21.addActionListener(this);
ta.append(t1.getText()+"\n");
t=new Thread(this);
t.start();
}
public int precedence(String op){
switch(op){
case "^": return 3;
case "*": case "/": case "%": return 2;
case "+": case "-": return 1;
}
return 0;
}
public void applyOp(Stack<Double> nums, Stack<String> ops)throws divisionbyzero{
double b=nums.pop();
double a=nums.pop();
String op=ops.pop();
double res=0;
switch(op){
case "+": res=a+b; break;
case "-": res=a-b; break;
case "*": res=a*b; break;
case "/":
if(b==0){
t1.setText("");
throw new divisionbyzero("can't divide");
} 
else{
res=a/b;
}
break;
case "%": res=a%b; break;
case "^": res=Math.pow(a, b); break;
}
nums.push(res);
}
public String getLastNumber(String text){
int i=text.length()-1;
while(i>=0 && (Character.isDigit(text.charAt(i)) || text.charAt(i) == '.')){
i--;
}
return text.substring(i + 1); // returns last number as string
}
//event handling code
public void actionPerformed(ActionEvent ae){
String cmd=ae.getActionCommand();
//if pressed button is a number

if(Character.isDigit(cmd.charAt(0))){
t1.setText(t1.getText()+cmd);
}

//if pressed button is an operator
else if(cmd.equals("+")||cmd.equals("-")||cmd.equals("*")||cmd.equals("/")||cmd.equals("%")||cmd.equals("^")){
double currentNum = Double.parseDouble(getLastNumber(t1.getText()));
nums.push(currentNum);
while(!ops.isEmpty() && precedence(ops.peek()) >= precedence(cmd)){
try{
applyOp(nums, ops);
}
catch(divisionbyzero ex){
JOptionPane.showMessageDialog(this,ex.getMessage());
return;
}
}
ops.push(cmd);
t1.setText(t1.getText()+cmd);
}

//to calculate result
else if(cmd.equals("=")){
if(t1.getText().isEmpty()) return; 
double currentNum = Double.parseDouble(getLastNumber(t1.getText()));
nums.push(currentNum);
while(!ops.isEmpty()){
try{
applyOp(nums, ops);
}
catch(divisionbyzero ex){
JOptionPane.showMessageDialog(this,ex.getMessage());
return;
}
}
double result=nums.pop();
nums.clear();
ops.clear();

if(result==(int)result){
t1.setText(String.valueOf((int)result));
} 
else {
t1.setText(String.valueOf(result));
}
ta.append(result+"\n");
}
else if(cmd.equals("√")){
double n=Double.parseDouble(t1.getText());
double r=Math.sqrt(n);
t1.setText(String.valueOf(r));
}

//if pressed button is a (.)
else if(cmd.equals(".")){
String currentNum = getLastNumber(t1.getText());
if(!currentNum.contains(".")) {
t1.setText(t1.getText() + cmd);
    }
}

//for clear button
else if(cmd.equals("C")){
t1.setText("");
nums.clear();
ops.clear();
}

//to delete the last entered operator or number
if(cmd.equals("del")){
String text = t1.getText();
if(text.length() > 0){
t1.setText(text.substring(0, text.length() - 1));
}
}
}
public void run(){
try{
while(true){
bpos=(bpos+1)%text.length();
String s=text.substring(bpos)+text.substring(0,bpos);
l.setText(s);
Thread.sleep(500);
}
}
catch(Exception e){
JOptionPane.showMessageDialog(gui_calculator_v2.this,e.getMessage());
}
}
//main function
public static void main(String args[]){
new gui_calculator_v2();
}
}


