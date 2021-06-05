package C_Compiler;
import java.io.File;
import java.awt.Color;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
public class C_Compiler {
    public static void main(String[] args) throws FileNotFoundException {

        Co_Scanner myScanner = new Co_Scanner();
//        File myFile = new File("C:\\Users\\m7mda\\OneDrive\\Desktop\\Compiler-1\\CompilerOne\\test3.txt");
  //      ArrayList lines = myScanner.scan(myFile);

        JFrame frame = new JFrame("JFrame Example");  
        JLabel label = new JLabel("JFrame By Example");  
        JLabel code;
        JTextArea textCode = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textCode); 
//        textCode.setEditable(false);
//        JTextField CodeEditor;
        JButton btn1, btn2, btn3;
        JPanel panel = new JPanel();  
        panel.setLayout(new FlowLayout());  
        btn1 = new JButton("Run");
        btn2 = new JButton("Browse");
        btn3 = new JButton("Exit");
        code = new JLabel("MyCode");
//              CodeEditor = new JTextField();
        frame.setSize(1200, 1200);
        code.setBounds(1, 1, 250, 40);
        textCode.setBounds(10, 50, 1000, 500);
        btn1.setBounds(1000, 600, 150, 40);
        btn2.setBounds(1000, 650, 150, 40);
        btn1.setBackground(Color.getHSBColor(180, 155, 255));
        btn3.setBackground(Color.getHSBColor(0, 2, 255));
        btn2.setBackground(Color.getHSBColor(100, 155, 155));
        btn1.setForeground(Color.white);
        btn2.setForeground(Color.white);
        frame.add(code);
        frame.add(textCode);
        frame.add(scrollPane);       
        frame.add(btn1);
        frame.add(btn2);
        panel.add(label);  
        frame.add(panel);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);                  
        btn1.addActionListener((ActionEvent e) -> { 
        	String username = textCode.getText();
            	try {
            				myScanner.scantext(username);
					myScanner.view_data();
            	} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

        });
        btn2.addActionListener((ActionEvent e) -> { 

        	JFileChooser file = new JFileChooser();
            file.setMultiSelectionEnabled(true);
            file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            file.setFileHidingEnabled(false);
            if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
               java.io.File f = file.getSelectedFile();
               Co_Scanner myScanne2r = new Co_Scanner();
   	             File myFile = new File(f.getPath());
					myScanner.view_data();

   	             try {
					myScanne2r.scan(myFile);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        }
        });
    }

}

