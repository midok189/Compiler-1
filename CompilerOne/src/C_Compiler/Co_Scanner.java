package C_Compiler;

import java.util.regex.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Co_Scanner {
	static int index = 0;
	String[][] Mydata = new String[5][];
    ArrayList<Integer> lines = new ArrayList<Integer>();
    ArrayList<JLabel> label = new ArrayList<JLabel>();
    int linenum = 1, t = 0, z = 0, hold = 0, hold1;

    public ArrayList scan(File file) throws FileNotFoundException {
        try (Scanner input = new Scanner(file)) {
            //System.out.println("Line|Lexeme|token|matchability");
		label.add(new JLabel("Line|Lexeme|token|matchability"));
            while (input.hasNext()) {
                String line = input.nextLine();
                analyze(line);
                linenum += 1;
            }
        }
        return lines;
    }
    
    public ArrayList scantext(String contents) throws FileNotFoundException {
            //System.out.println("Line|Lexeme|token|matchability");
	    label.add(new JLabel("Line|Lexeme|token|matchability"));
            String[] liness = contents.split("\\r?\\n");
            for (String line : liness) {
                      System.out.println(line);
                analyze(line);
                linenum += 1;
            }
        return lines;
    }
    
    
    public void analyze(String line) {
        String[] Datatypes = {"Omw", "SIMww", "Chji", "Seriestl", "IMwf", "SIMwf", "NOReturn", "Loli", "Yesif", "Otherwise", "main", "RepeatWhen", "Reiterate", "GetBack", "OutLoop", "Start", "Last", "Include"};
        String[] Datatypes1 = {"Integer", "signed Integer", "char", "String", "Float", "signed Float", "void", "struct", "Condition", "Condition", "main function", "Loop", "Loop", "Return", "Break", "Start", "End", "inclusion"};
        String[] rOperator = {"==", "<=", ">=", "=", "<", ">", "!="};
        String[] operators = {"*", "/", "+", "-", "%"};
        String[] braces = {"(", ")", "[", "]", "{", "}"};
        String[] sentences = line.split(" ");
        String[] Test = {"[_a-zA-Z][_a-zA-Z0-9]*"};
        boolean exponent = false;

        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            String word = "";
            String word1 = "";

            //Numbers
            for (int j = 0; j < sentence.length(); j++) {

                word += String.valueOf(sentence.charAt(j));
                if (j < (sentence.length() - 1)) {
                    word1 = String.valueOf(sentence.charAt(j + 1));
                }
                if (word.equals("/") && word1.equals("@")) {
//System.out.println(linenum + "  Comment :  " + word + word1 + "  Matched");
label.add(new JLabel(linenum + "  Comment :  " + word + word1 + "  Matched"));
                    hold = 1;
                }
                if (word.equals("@")) {
                    hold = 0;
                }
                if (word.equals("/") && word1.equals("^") && hold == 0) {
//System.out.println(linenum + "  Comment :  " + word + word1 + "  Matched");
label.add(new JLabel(linenum + "  Comment :  " + word + word1 + "  Matched"));
                    hold1 = linenum;
                }

                if (Character.isDigit(word.charAt(0))) {
                    if (hold == 1) {
                        word = "";
                    } else if (hold1 == linenum) {
                        word = "";
                    }

                    if (j + 1 == sentence.length() && !word.equals("")) {
//System.out.println(linenum + "   Number: " + word + "  Matched");
label.add(new JLabel(linenum + "   Number: " + word + "  Matched"));

                        word = "";
                    }
                    if (j + 1 < sentence.length()) {
                        if (Character.isLetter(sentence.charAt(j + 1)) && exponent == true && !word.equals("")) {
//System.out.println(linenum + "   Number: " + word + "  Matched");
label.add(new JLabel(linenum + "   Number: " + word + "  Matched"));

                            word = "";
                        }
                        if (Character.isLetterOrDigit(sentence.charAt(j + 1)) == false && !word.equals("")) {
//System.out.println(linenum + "   Number: " + word + "  Matched");
label.add(new JLabel(linenum + "   Number: " + word + "  Matched"));

                            word = "";
                        }
                    }
                }

                // key words
                if (word.length() > 0) {
                    if (Character.isLetter(word.charAt(0))) {
                        if (hold == 1) {
                            word = "";
                        } else if (hold1 == linenum) {
                            word = "";
                        }
                        if (j + 1 < sentence.length()) {
                            if ((Character.isLetterOrDigit(sentence.charAt(j + 1))) == false) {

                                if (word.length() > 0) {

                                    for (t = 0; t < Datatypes.length; t++) {
                                        if (word.equals(Datatypes[t])) {
                                            word1 = Datatypes1[t];
//System.out.println(linenum + "   " + word1 + ": " + word + "  Matched");
label.add(new JLabel(linenum + "   " + word1 + ": " + word + "  Matched"));
                                            word = "";
                                            z = 1;
                                        }
                                    }
                                    if (word.length() > 0 && z == 0) {
//System.out.println(linenum + "  Identifier : " + word + "  Matched");
  label.add(new JLabel(linenum + "  Identifier : " + word + "  Matched"));
                                        word = "";
                                    }
                                }

                            }
                        }

                        if (j + 1 == sentence.length()) {
                            for (t = 0; t < Datatypes.length; t++) {
                                if (word.equals(Datatypes[t])) {
                                    word1 = Datatypes1[t];
//System.out.println(linenum + "   " + word1 + ": " + word + "  Matched");
  label.add(new JLabel(linenum + "   " + word1 + ": " + word + "  Matched"));

                                    word = "";
                                    t = 0;
                                } else {
                                    t++;
                                }
                            }

                        }
                    }
                }
                if (hold == 1) {
                    word = "";
                } else if (hold1 == linenum) {
                    word = "";
                }

                if (word.equals("'") || word.equals("\"")) {
//System.out.println(linenum + "   " + "Quotation Mark: " + word + "  Matched");
label.add(new JLabel(linenum + "   " + "Quotation Mark: " + word + "  Matched"));

                    word = "";
                }

                if (word.length() > 0) {
                    if (Character.isLetterOrDigit(word.charAt(0)) == false) {
                        if (hold == 1) {
                            word = "";
                        } else if (hold1 == linenum) {
                            word = "";
                        }
                        if (j < sentence.length()) {
                            for (String geq : rOperator) {
                                if (word.equals(geq)) {
                                    if (word1.equals("=")) {
//System.out.println(linenum + "  " + "relational operators: " + word + word1 + "  Matched");
label.add(new JLabel(linenum + "  " + "relational operators: " + word + word1 + "  Matched"));
                                        word = "";
                                        word1 = "";
                                        j++;
                                    } else if (word.equals("=")) {
//System.out.println(linenum + "   " + "Assign :" + word + "  Matched");
label.add(new JLabel(linenum + "   " + "Assign: " + word + "  Matched"));
                                        word = "";
                                    } else {
//System.out.println(linenum + "  " + "relational operators: " + word + "  Matched");
label.add(new JLabel(linenum + "   " + "relational operators : " + word + "  Matched"));

                                        word = "";
                                    }
                                }
                            }

                            if (word.equals("&") && word1.equals("&") || word.equals("|") && word1.equals("|")) {

//System.out.println(linenum + "  " + "Logic operator: " + word + word1 + "  Matched");
label.add(new JLabel(linenum + "   " + "Logic operator: " + word + word1 +"  Matched"));

                                word = "";
                                word1 = "";
                                j++;
                            } else if (word.equals("~")) {
//System.out.println(linenum + "   " + "Logic operator :" + word + "  Matched");
label.add(new JLabel(linenum + "   " + "Logic operator: " + word +"  Matched"));
                                word = "";
                            }
                            if (word.equals("-") && word1.equals(">")) {
//System.out.println(linenum + "   Access Operator :" + word + word1 + "  Matched");
label.add(new JLabel(linenum + "   " + "Access Operator: " + word + word1 +"  Matched"));

                                word = "";
                            }

                            for (String b : braces) {
                                if (word.equals(b)) {
//System.out.println(linenum + "  " + "Braces :" + word + "  Matched");
label.add(new JLabel(linenum + "  " + "Braces :" + word + "  Matched"));

                                    word = "";
                                }
                            }

                            if (word.equals(".")) {
//System.out.println(linenum + "   line delimiter :" + word + "  Matched");
label.add(new JLabel(linenum + "  " + "line delimiter  :" + word + "  Matched"));

                                word = "";
                            }
                            if (word.equals("$")) {
//System.out.println(linenum + "   token delimiter :" + word + "  Matched");
label.add(new JLabel(linenum + "  " + "token delimiter  :" + word + "  Matched"));

                                word = "";
                            }
                        }

                        if (word.length() == 1) {
                            for (String operator : operators) {
                                if (word.equals(operator)) {
                                    //System.out.println(linenum + "   Operator: " + word + "  Matched");
                                    label.add(new JLabel(linenum + "  " + "Operator :" + word + "  Matched"));

                                    word = "";
                                }
                            }
                        }

                    }
                }
                z = 0;
            }
        }
    }

    public void view_data() 
    {
        JFrame frame = new JFrame("JFrame Example");  

        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        for (JLabel l : label) {
        	panel.add(l); 
        }
        frame.add(panel);  
	    frame.setSize(500, 800);
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);      	
    	
    }

    
}
