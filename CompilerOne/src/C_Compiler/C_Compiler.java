package C_Compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class C_Compiler {

    public static void main(String[] args) throws FileNotFoundException {

        Co_Scanner myScanner = new Co_Scanner();
        File myFile = new File("C:\\CompilerOne\\CompilerOne\\test3.txt");

        ArrayList lines = myScanner.scan(myFile);

        System.out.println("---------------End of text-----------");

    }

}
