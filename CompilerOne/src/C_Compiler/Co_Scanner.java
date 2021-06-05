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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Co_Scanner {

    static int index = 0;
    String[][] Mydata = new String[5][];
    ArrayList<Integer> lines = new ArrayList<Integer>();
    int linenum = 1, t = 0, z = 0, hold = 0, hold1;
    JTable jt = new JTable();
    DefaultTableModel tableModel;
    Object[] row;

    public Co_Scanner() {
        String column[] = {"Line", "Lexeme", "token", "matchability"};
        tableModel = new DefaultTableModel(column, 0);
        jt.setModel(tableModel);
    }

    public ArrayList scan(File file) throws FileNotFoundException {
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                String line = input.nextLine();
                analyze(line);
                linenum += 1;
            }
        }
        return lines;
    }

    public ArrayList scantext(String contents) throws FileNotFoundException {
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
                    row = new Object[]{String.valueOf(linenum), "Comment", word + word1, "Matched"};
                    tableModel.addRow(row);
                    hold = 1;
                }
                if (word.equals("@")) {
                    hold = 0;
                }
                if (word.equals("/") && word1.equals("^") && hold == 0) {
                    row = new Object[]{String.valueOf(linenum), "Comment", word + word1, "Matched"};
                    tableModel.addRow(row);
                    hold1 = linenum;
                }

                if (Character.isDigit(word.charAt(0))) {
                    if (hold == 1) {
                        word = "";
                    } else if (hold1 == linenum) {
                        word = "";
                    }

                    if (j + 1 == sentence.length() && !word.equals("")) {
                        row = new Object[]{String.valueOf(linenum), "Number", word, "Matched"};
                        tableModel.addRow(row);
                        word = "";
                    }
                    if (j + 1 < sentence.length()) {
                        if (Character.isLetter(sentence.charAt(j + 1)) && exponent == true && !word.equals("")) {
                            row = new Object[]{String.valueOf(linenum), "Number", word, "Matched"};
                            tableModel.addRow(row);
                            word = "";
                        }
                        if (Character.isLetterOrDigit(sentence.charAt(j + 1)) == false && !word.equals("")) {
                            row = new Object[]{String.valueOf(linenum), "Number", word, "Matched"};
                            tableModel.addRow(row);
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
                                            row = new Object[]{String.valueOf(linenum), word1, word, "Matched"};
                                            tableModel.addRow(row);
                                            word = "";
                                            z = 1;
                                        }
                                    }
                                    if (word.length() > 0 && z == 0) {
                                        row = new Object[]{String.valueOf(linenum), "Identifier", word, "Matched"};
                                        tableModel.addRow(row);
                                        word = "";
                                    }
                                }

                            }
                        }

                        if (j + 1 == sentence.length()) {
                            for (t = 0; t < Datatypes.length; t++) {
                                if (word.equals(Datatypes[t])) {
                                    word1 = Datatypes1[t];
                                    row = new Object[]{String.valueOf(linenum), word1, word, "Matched"};
                                    tableModel.addRow(row);
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
                    row = new Object[]{String.valueOf(linenum), "Quotation Mark", word, "Matched"};
                    tableModel.addRow(row);
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
                                        row = new Object[]{String.valueOf(linenum), "Relational operators", word + word1, "Matched"};
                                        tableModel.addRow(row);
                                        word = "";
                                        word1 = "";
                                        j++;
                                    } else if (word.equals("=")) {
                                        row = new Object[]{String.valueOf(linenum), "Assign", word, "Matched"};
                                        tableModel.addRow(row);
                                        word = "";
                                    } else {
                                        row = new Object[]{String.valueOf(linenum), "Relational operators", word, "Matched"};
                                        tableModel.addRow(row);
                                        word = "";
                                    }
                                }
                            }

                            if (word.equals("&") && word1.equals("&") || word.equals("|") && word1.equals("|")) {
                                row = new Object[]{String.valueOf(linenum), "Logic operator", word + word1, "Matched"};
                                tableModel.addRow(row);
                                word = "";
                                word1 = "";
                                j++;
                            } else if (word.equals("~")) {
                                row = new Object[]{String.valueOf(linenum), "Logic operator", word, "Matched"};
                                tableModel.addRow(row);
                                word = "";
                            }
                            if (word.equals("-") && word1.equals(">")) {
                                row = new Object[]{String.valueOf(linenum), "Access Operator", word + word1, "Matched"};
                                tableModel.addRow(row);
                                word = "";
                            }

                            for (String b : braces) {
                                if (word.equals(b)) {
                                    row = new Object[]{String.valueOf(linenum), "Braces", word, "Matched"};
                                    tableModel.addRow(row);
                                    word = "";
                                }
                            }

                            if (word.equals(".")) {
                                row = new Object[]{String.valueOf(linenum), "line delimiter", word, "Matched"};
                                tableModel.addRow(row);
                                word = "";
                            }
                            if (word.equals("$")) {
                                row = new Object[]{String.valueOf(linenum), "token delimiter", word, "Matched"};
                                tableModel.addRow(row);
                                word = "";
                            }
                        }

                        if (word.length() == 1) {
                            for (String operator : operators) {
                                if (word.equals(operator)) {
                                    row = new Object[]{String.valueOf(linenum), "Operator", word, "Matched"};
                                    tableModel.addRow(row);
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

    public void view_data() {
        JFrame frame = new JFrame("JFrame Example");

        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);

        JScrollPane sp = new JScrollPane(jt);
        panel.add(sp);

        frame.add(panel);
        frame.setSize(500, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
