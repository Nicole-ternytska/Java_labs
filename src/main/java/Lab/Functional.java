package Lab;

import java.awt.desktop.OpenFilesEvent;
import java.io.*;
import java.util.ArrayList;

public class Functional {

    private String file_name;
    private String result_file_name;
    private char symb;
    private String result_symbol;

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setResult_file_name(String result_file_name) {
        this.result_file_name = result_file_name;
    }

    public void setResult_symbol(String result_symbol) {
        this.result_symbol = result_symbol;
    }

    public void setSymb(char symb) {
        this.symb = symb;
    }

    public String getResult_file_name() {
        return result_file_name;
    }


    public ArrayList<String> OpenAndReadFile(){
        ArrayList<String> file_lines = new ArrayList<>();
        File file = new File(file_name);
        try {
            BufferedReader filereader = new BufferedReader(new FileReader(file));
            String line = filereader.readLine();
            while(line!=null){
                file_lines.add(line);
                line = filereader.readLine();
            }
            filereader.close();
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        return file_lines;
    }

    public ArrayList<String> Schitalka (ArrayList<String> file_lines){
        int count = 0;
        int firs_index = 0;
        int last_index = 0;
        int temp = 0;
        boolean quotes = false;
        boolean coment = false;
        String temporary_string = "";

        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<String> result_result = new ArrayList<>();

        for(int k = 0; k < file_lines.size(); k++){
            char[] line_char = file_lines.get(k).toCharArray();
            for (int i = 0; i< line_char.length; i++){
                if (!coment) {
                    if((i<line_char.length-1) && ('/' == line_char[i] && '*' == line_char[i+1])){
                        coment = true;
                    }
                    else if(('\"'== line_char[i]) && (count == 0)){
                        firs_index = i;
                        for (int j = i+1; j< (line_char.length); j++){
                            if ('\"'== line_char[j]){
                                last_index = j;
                                temp = temp + ((last_index-firs_index)-1);
                                quotes = true;
                                count = 0;
                                last_index = 0;
                                firs_index = 0;
                                i=j;
                                break;
                            }
                        }
                        if (!quotes){
                            count++;
                        }
                    }
                    else if (line_char[i] != symb){
                        count++;
                    }

                    else{
                        result.add(count+temp);
                        temp = 0;
                        count = 0;
                    }
                    quotes=false;
                }
                else {
                    if((i<line_char.length-1) && ('*' == line_char[i] && '/' == line_char[i+1])){
                        coment = false;
                        i++;
                    }
                }
            }
            if (!coment){
                line_char = null;
                result.add(count+temp);
                count = 0;
                temp = 0;
                for(int i = 0; i < result.size(); i++){
                    temporary_string = temporary_string + Integer.toString(result.get(i));
                    if(i == (result.size()-1)){
                        temporary_string = temporary_string+"\n";
                    }
                    else{
                        temporary_string = temporary_string + result_symbol;
                    }
                }
                result_result.add(temporary_string);
                temporary_string = "";
                result.clear();
            }
            else{
                line_char = null;
            }
        }
        return result_result;
    }

    public void WriteAndCloseFile(ArrayList<String> result_result){
        try (FileWriter filewriter = new FileWriter(result_file_name)){
            for (int i = 0; i < result_result.size(); i++) {
                filewriter.write(result_result.get(i));
                //filewriter.append('\n');
            }
            filewriter.flush();
        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
