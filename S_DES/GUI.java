package HomeWork01;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Date;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class GUI extends JFrame{

    private JTextField encryptText;
    private JTextField decryptText;
    private JTextField encryptKeyText;
    private JTextField decryptKeyText;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextArea outputArea;
    private JRadioButton binaryButton;
    private JRadioButton asciiButton;
    private Box box1,box2,box3,box4,box5,boxV;
    public GUI(){
        setTitle("S-DES算法实现");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());


        boxV = Box.createVerticalBox();
        box1 = Box.createHorizontalBox();
        box2 = Box.createHorizontalBox();
        box3 = Box.createHorizontalBox();
        box4 = Box.createHorizontalBox();
        box5 = Box.createHorizontalBox();

        JLabel title = new JLabel("S-DES算法实现");
        title.setFont(new Font("微软雅黑",Font.BOLD,45));
        box1.add(title);

        box2.add(new JLabel("加密信息（8bit）：  "));
        encryptText = new JTextField(20);
        box2.add(encryptText);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(new JLabel("密钥（10bit）：  "));
        encryptKeyText = new JTextField(20);
        box2.add(encryptKeyText);
        box2.add(Box.createHorizontalStrut(10));
        encryptButton = new JButton("加密");
        encryptButton.setFont(new Font("微软雅黑",Font.BOLD,12));
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(binaryButton.isSelected()){
                    System.out.println("二进制加密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    //正则匹配01字符串
                    Pattern pattern = Pattern.compile("[0-1]*");
                    if(!pattern.matcher(encryptText.getText()).matches() || encryptText.getText().length()!=8 ){
                        JOptionPane.showMessageDialog( null,"请输入8bit二进制加密信息！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        info = encryptText.getText();                     
                    }

                if(!pattern.matcher(encryptKeyText.getText()).matches() || encryptKeyText.getText().length()!=10){
                        JOptionPane.showMessageDialog( null,"请输入10bit二进制密钥！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        key = encryptKeyText.getText();
                    }

                    if(flag==true){
                        //System.out.println(info);

                        //加密过程
                        S_DES sdes = new S_DES();

                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        if( outputArea.getText().length()!=0){
                             outputArea.setText( outputArea.getText()+"\n\n"+"时间："+formatter.format(date)+"\n操作：二进制加密\n明文："+info+"\n密钥："+key+"\n密文："+sdes.SDES(info,key,0)[0]+"\n");
                        } else{
                             outputArea.setText("时间："+formatter.format(date)+"\n操作：二进制加密\n明文："+info+"\n密钥："+key+"\n密文："+sdes.SDES(info,key,0)[0]+"\n");
                        }

                    }else {
                        System.out.println("未加密");
                    }




                } else if (asciiButton.isSelected()) {
                    System.out.println("ascii加密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    //正则匹配01字符串
                    Pattern pattern_01 = Pattern.compile("[0-1]*");
                    Pattern pattern_ascii = Pattern.compile("\\A\\p{ASCII}*\\z");
                    if(!pattern_ascii.matcher(encryptText.getText()).matches() || encryptText.getText().length()==0 ){
                        JOptionPane.showMessageDialog( null,"请输入ASCII加密信息！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        info = encryptText.getText();
                    }

                    if(!pattern_01.matcher(encryptKeyText.getText()).matches() || encryptKeyText.getText().length()!=10){
                        JOptionPane.showMessageDialog( null,"请输入10bit二进制密钥！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        key = encryptKeyText.getText();
                    }
                    if(flag==true){
                        //System.out.println(info);
                        //加密过程
                        S_DES_ASCII sdesAscii = new S_DES_ASCII();

                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        if( outputArea.getText().length()!=0){
                             outputArea.setText( outputArea.getText()+"\n\n"+"时间："+formatter.format(date)+"\n操作：ASCII码加密\n明文："+info+"\n密钥："+key+"\n密文："+sdesAscii.transform(info,key,0)+"\n");
                        } else{
                             outputArea.setText("时间："+formatter.format(date)+"\n操作：ASCII码加密\n明文："+info+"\n密钥："+key+"\n密文："+sdesAscii.transform(info,key,0)+"\n");
                        }

                    }else {
                        System.out.println("未加密");
                    }
                }else {
                    JOptionPane.showMessageDialog( null,"请先选择输入格式","执行结果", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        box2.add(encryptButton);
        box3.add(new JLabel("解密信息（8bit）：  "));
        decryptText = new JTextField(20);
        box3.add(decryptText);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(new JLabel("密钥（10bit）：  "));
        decryptKeyText = new JTextField(20);
        box3.add(decryptKeyText);
        box3.add(Box.createHorizontalStrut(10));
        decryptButton = new JButton("解密");
        decryptButton.setFont(new Font("微软雅黑",Font.BOLD,12));
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(binaryButton.isSelected()){
                    System.out.println("二进制解密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    //正则匹配01字符串
                    Pattern pattern = Pattern.compile("[0-1]*");
                    if(!pattern.matcher(decryptText.getText()).matches() || decryptText.getText().length()!=8 ){
                        JOptionPane.showMessageDialog( null,"请输入8bit二进制解密信息！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        info = decryptText.getText();
                    }

                    if(!pattern.matcher(decryptKeyText.getText()).matches() || decryptKeyText.getText().length()!=10){
                        JOptionPane.showMessageDialog( null,"请输入10bit二进制密钥！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        key = decryptKeyText.getText();
                    }

                    if(flag==true){
                        //System.out.println(info);

                        //解密过程
                        S_DES sdes = new S_DES();

                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        if( outputArea.getText().length()!=0){
                             outputArea.setText( outputArea.getText()+"\n\n"+"时间："+formatter.format(date)+"\n操作：二进制解密\n密文："+info+"\n密钥："+key+"\n明文："+sdes.SDES(info,key,1)[0]+"\n");
                        } else{
                             outputArea.setText("时间："+formatter.format(date)+"\n操作：二进制解密\n密文："+info+"\n密钥："+key+"\n明文："+sdes.SDES(info,key,1)[0]+"\n");
                        }
                    }else {
                        System.out.println("未加密");
                    }
                } else if (asciiButton.isSelected()) {
                    System.out.println("ascii解密");
                    String info = new String();
                    String key = new String();
                    boolean flag = true;

                    //正则匹配01字符串
                    Pattern pattern_01 = Pattern.compile("[0-1]*");
                    Pattern pattern_ascii = Pattern.compile("\\A\\p{ASCII}*\\z");
//                    if(!pattern_ascii.matcher(decryptText.getText()).matches() || decryptText.getText().length()==0 ){
//                        JOptionPane.showMessageDialog( null,"请输入ASCII解密信息！","警告", JOptionPane.WARNING_MESSAGE);
//                        flag = false;
//                    }else {
                    info = decryptText.getText();
//                    }

                    if(!pattern_01.matcher(decryptKeyText.getText()).matches() || decryptKeyText.getText().length()!=10){
                        JOptionPane.showMessageDialog( null,"请输入10bit二进制密钥！","警告", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }else {
                        key = decryptKeyText.getText();
                    }
                    if(flag==true){
                        //System.out.println(info);

                        //解密过程
                        S_DES_ASCII sdesAscii = new S_DES_ASCII();

                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());

                        if( outputArea.getText().length()!=0){
                             outputArea.setText( outputArea.getText()+"\n\n"+"时间："+formatter.format(date)+"\n操作：ASCII码解密\n密文："+info+"\n密钥："+key+"\n明文："+sdesAscii.transform(info,key,1)+"\n");
                        } else{
                             outputArea.setText("时间："+formatter.format(date)+"\n操作：ASCII码解密\n密文："+info+"\n密钥："+key+"\n明文："+sdesAscii.transform(info,key,1)+"\n");
                        }

                    }
                    else {

                        System.out.println("未加密");
                    }
                }else {
                    JOptionPane.showMessageDialog( null,"请先选择输入格式","提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        box3.add(decryptButton);

         outputArea = new JTextArea(22,40);
         outputArea.setEditable(false);
        box4.add(new JScrollPane( outputArea));

        binaryButton = new JRadioButton("二进制");
        asciiButton = new JRadioButton("ASCII");
        ButtonGroup group = new ButtonGroup();
        group.add(binaryButton);
        group.add(asciiButton);
        box5.add(new JLabel("输入信息格式："));
        box5.add(binaryButton);
        box5.add(asciiButton);


        boxV.add(box1);
        boxV.add(Box.createVerticalStrut(20));
        boxV.add(box2);
        boxV.add(box3);
        boxV.add(box5);
        boxV.add(Box.createVerticalStrut(20));
        boxV.add(box4);


        add(boxV);
    }

    public static void main(String[] args){
        GUI gui = new GUI();
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
    }

}
