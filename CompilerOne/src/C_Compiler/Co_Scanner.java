package C_Compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Co_Scanner {

    ArrayList<String> output = new ArrayList<String>();
    ArrayList<Integer> lines = new ArrayList<Integer>();
    int linenum = 1, t = 0, z = 0, hold = 0;

    public ArrayList<String> getOutput() {
        return output;
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

    public void analyze(String line) {
        String[] keywords = {"Yesif", "otherwise", "main", "return"};
        String[] Datatypes = {"Omw", "SIMww", "Chji", "Seriestl", "IMwf", "SIMwf", "NOReturn", "Loli", "Yesif", "Otherwise", "main", "RepeatWhen", "Reiterate", "GetBack", "OutLoop", "Start", "Last", "Include", "zac"};
        String[] Datatypes1 = {"Integer", "signed Integer", "char", "String", "Float", "signed Float", "void", "struct", "Condition", "Condition", "main function", "Loop", "Loop", "Return", "Break", "Start", "End", "inclusion", "zac"};
        String[] rOperator = {"==", "<=", ">=", "=", "<", ">", "!="};
        // String[] symbols = {"(", ")", "{", "}", ",", ";", "'"};
        String[] operators = {"*", "/", "+", "-", "%"};
        String[] braces = {"(", ")", "[", "]", "{", "}"};
        String[] lOperator = {"&&", "||", "~"};
        String[] sentences = line.split(" ");
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
                    System.out.println(linenum + "  Comment :" + word + word1);
                    hold = 1;
                }
                if (word.equals("@")) {
                    hold = 0;
                }

                if (Character.isDigit(word.charAt(0))) {
                    if (hold == 1) {
                        word = "";
                    }
                    if (j + 1 < sentence.length()) {
                        /*   if (sentence.charAt(j + 1) == '.') {
                            if (j + 1 < sentence.length()) {
                                //    if(Character.isDigit(sentence.charAt(j+2))){
                                word += String.valueOf(sentence.charAt(j + 1));
                                j = j + 1;
                                //    }
                            }
                        }*/
                    }

                    if (j + 1 == sentence.length() && !word.equals("")) {
                        System.out.println(linenum + "   Number: " + word);
                        output.add("Number");
                        lines.add(linenum);
                        word = "";
                    }
                    if (j + 1 < sentence.length()) {
                        if (Character.isLetter(sentence.charAt(j + 1)) && exponent == true && !word.equals("")) {
                            System.out.println(linenum + "   Number: " + word);
                            output.add("Number");
                            lines.add(linenum);
                            word = "";
                        }
                        if (Character.isLetterOrDigit(sentence.charAt(j + 1)) == false && !word.equals("")) {
                            System.out.println(linenum + "   Number: " + word);
                            output.add("Number");
                            lines.add(linenum);
                            word = "";
                        }
                    }
                }

                // key words
                if (word.length() > 0) {
                    if (Character.isLetter(word.charAt(0))) {
                        if (hold == 1) {
                            word = "";
                        }
                        if (j + 1 < sentence.length()) {
                            if ((Character.isLetterOrDigit(sentence.charAt(j + 1))) == false) {


                                //  for (String keyword : keywords) {
                                /*    if (word.equals(keyword)) {
                                        System.out.println("Keyword : " + word);
                                        output.add(keyword);
                                        lines.add(linenum);
                                        word = "";
                                    } else*/ if (word.length() > 0) {
                                    // System.out.println("Identifier: " + word);

                                    /*  if (word.equals("void")) {
                                            output.add("void");
                                        } else if (word.equals("main")) {
                                            output.add("main");
                                        } else if (word.equals("else")) {
                                            output.add("else");
                                        } else {
                                            output.add("Identifier");
                                        }*/
                                    // lines.add(linenum);
                                    for (t = 0; t < Datatypes.length; t++) {
                                        if (word.equals(Datatypes[t])) {
                                            word1 = Datatypes1[t];
                                            System.out.println(linenum + "   " + word1 + ": " + word);
                                            output.add("datatype");
                                            lines.add(linenum);
                                            word = "";
                                            z = 1;

                                        }
                                    }
                                    if (word.length() > 0 && z == 0) {
                                        System.out.println(linenum + "  Identifier : " + word);
                                        word = "";
                                    }
                                }
                                //  }
                            }
                        }
                        //if(j+1==sentence.length()){
                        //    for(String keyword: keywords){
                        //          if(word.equals(keyword)){
                        //            System.out.println("Keyword : "+word);
                        //          output.add(keyword);
                        //        lines.add(linenum);
                        //      word="";
                        //}
                        // }
                        //    }

                        if (j + 1 == sentence.length()) {
                            for (t = 0; t < Datatypes.length; t++) {
                                if (word.equals(Datatypes[t])) {
                                    word1 = Datatypes1[t];
                                    System.out.println(linenum + "   " + word1 + ": " + word);
                                    output.add("datatype");
                                    lines.add(linenum);
                                    word = "";
                                    t = 0;
                                } else {
                                    t++;
                                }
                            }
                            /* if (word.length() > 0) {
                                System.out.println("Identifier: " + word);

                                if (word.equals("main")) {
                                    output.add("main");
                                } else {
                                    output.add("Identifier");
                                }
                                lines.add(linenum);
                                word = "";
                            }*/
                        }
                    }
                }
                if (hold == 1) {
                    word = "";
                }

                if (word.equals("'") || word.equals("\"")) {
                    System.out.println(linenum + "   " + "Quotation Mark: " + word);
                    output.add(word);
                    lines.add(linenum);
                    word = "";
                }

                if (word.length() > 0) {
                    if (Character.isLetterOrDigit(word.charAt(0)) == false) {
                        if (hold == 1) {
                            word = "";
                        }
                        if (j < sentence.length()) {
                            for (String geq : rOperator) {
                                if (word.equals(geq)) {
                                    if (word1.equals("=")) {
                                        System.out.println(linenum + "  " + "relational operators: " + word + word1);
                                        output.add(">=");
                                        lines.add(linenum);
                                        word = "";
                                        word1 = "";
                                        j++;
                                    } else if (word.equals("=")) {
                                        System.out.println(linenum + "   " + "Assign :" + word);
                                        word = "";
                                    } else {
                                        System.out.println(linenum + "  " + "relational operators: " + word);
                                        output.add(">=");
                                        lines.add(linenum);
                                        word = "";
                                    }
                                }
                            }
                            // for (String le : lOperator) {
                            if (word.equals("&") && word1.equals("&") || word.equals("|") && word1.equals("|")) {

                                System.out.println(linenum + "  " + "Logic operator: " + word + word1);
                                output.add(">=");
                                lines.add(linenum);
                                word = "";
                                word1 = "";
                                j++;
                            } else if (word.equals("~")) {
                                System.out.println(linenum + "   " + "Logic operator :" + word);
                                word = "";
                            }

                            // }
                            for (String b : braces) {
                                if (word.equals(b)) {
                                    System.out.println(linenum + "  " + "Braces :" + word);
                                    output.add(">=");
                                    lines.add(linenum);
                                    word = "";
                                }
                            }
                            if (word.equals("$")) {
                                System.out.println(linenum + "   token delimiter :" + word);
                                output.add("<");
                                lines.add(linenum);
                                word = "";
                            }
                        }
                        /* for(String lessthan: symbols){
                                if(word.equals("<")){
                                    System.out.println("Less than: "+word);
                                    output.add("<");
                                    lines.add(linenum);
                                    word="";
                                }}
                              //
                              

                            for(String 	greaterthan: symbols){
                                if(word.equals(">")){
                                    System.out.println("Greater than: "+word);
                                    output.add(">");
                                    lines.add(linenum);
                                    word="";
                                }}
                            
                        for(String equal: symbols){
                            if(word.equals("=")){
                                System.out.println("Equal: "+word);
                                output.add("=");
                                lines.add(linenum);
                                word="";
                                }
                            }*/

                        //else if(word.length()==2){
                        //  for(String secondSymbol: symbols){
                        //    if(String.valueOf(word.charAt(1)).equals(secondSymbol)){
                        //      System.out.println("Sympol: "+word.charAt(1));
                        //    output.add(word);
                        //  word="";
                        //break;
                        //}
                        //}
                        //}
//                            for(String equality: symbols){
//                                if(word.equals("=")){
//                                    System.out.println("Equality: "+word);
//                                    output.add("=");
//                                    lines.add(linenum);
//                                    word="";
//                                }}
//
//
//
//
//
//                            for(String 	greaterthanorequal: symbols){
//                                if(word.equals(">=")){
//                                    System.out.println("Greater than or equal : "+word);
//                                    output.add(">=");
//                                    lines.add(linenum);
//                                    word="";
//                                }}
//
//
//
//                            for(String 	lessthanorequal : symbols){
//                                if(word.equals("<=")){
//                                    System.out.println("Less than or equal : "+word);
//                                    output.add("<=");
//                                    lines.add(linenum);
//                                    word="";
//                                }}
                        /*            for(String 	leftcurlybrackets : symbols){
                                if(word.equals("{")){
                                    System.out.println("Left Curly Brackets : "+word);
                                    output.add("{");
                                    lines.add(linenum);
                                    word="";
                                }
                            }
                            for(String 	rightcurlybrackets : symbols){
                                if(word.equals("}")){
                                    System.out.println("Right Curly Brackets : "+word);
                                    output.add("}");
                                    lines.add(linenum);
                                    word="";
                                }
                            }
                            for(String 	leftbrackets : symbols){
                                if(word.equals("(")){
                                    System.out.println("Left parenthesis : "+word);
                                    output.add("(");
                                    lines.add(linenum);
                                    word="";
                                }
                            }
                            for(String 	rightbrackets : symbols){
                                if(word.equals(")")){
                                    System.out.println("Right parenthesis : "+word);
                                    output.add(")");
                                    lines.add(linenum);
                                    word="";
                                }
                            }*/
                        if (word.length() == 1) {
                            for (String operator : operators) {
                                if (word.equals(operator)) {
                                    System.out.println(linenum + "   Operator: " + word);
                                    output.add("Operator");
                                    lines.add(linenum);
                                    word = "";
                                }
                            }
                        }
                        /* if (word.length() == 1) {
                            for (String sep : symbols) {
                                /*if(word.equals(sep)){
                            System.out.println("seperator: "+word);
                            output.add("seperator");
                            lines.add(linenum);
                            word="";
                                }
                                if (word.equals(";")) {
                                    System.out.println("line delimiter: " + word);
                                    output.add("semicolon");
                                    lines.add(linenum);
                                    word = "";
                                }
                            }
                        } //                        if(word.length()==1){
                        //                        for(String end: symbols){
                        //                            
                        //                            }
                        //                        }
                        else if (j + 1 == sentence.length()) {
                            for (String symbol : symbols) {
                                if (word.equals(symbol)) {
                                    System.out.println("Sympol: " + word);
                                    output.add(symbol);
                                    lines.add(linenum);
                                    word = "";
                                }
                            }
                        } */
                    }
                }
                z = 0;
            }
        }
    }
}
