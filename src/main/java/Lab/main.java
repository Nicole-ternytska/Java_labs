package Lab;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class main {
    public static void main(String[] args) {

        String file_name = null;
        String result_file_name = null;
        String symbol = null;
        String result_symbol = null;
        char symb = '0';

        ArrayList<String> file_lines = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        Scanner sep = new Scanner(System.in);
        System.out.println("Input initial file path: ");
        file_name = sep.next();
        System.out.print("Input a symbol to separate: ");
        symbol = sep.next();
        if (symbol.length() != 1) {System.exit(0);}
        symb = symbol.charAt(0);
        System.out.println("Input result file path: ");
        result_file_name = sep.next();
        File file_2 = new File(result_file_name);
        if( !file_2.isFile()){ System.exit(0);}
        System.out.print("Input a symbol to result connection: ");
        result_symbol = sep.next();
        if (result_symbol.length() != 1){System.exit(0);}

        Functional function = new Functional();
        function.setFile_name(file_name);
        function.setResult_file_name(result_file_name);
        function.setSymb(symb);
        function.setResult_symbol(result_symbol);
        file_lines = function.OpenAndReadFile();
        result = function.Schitalka(file_lines);
        function.WriteAndCloseFile(result);
    }
}
