import org.junit.Test;
import Lab.Functional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class Functional_test {
    @Test
    public void test_function () throws IOException {
        Functional func = new Functional();
        func.setSymb(';');
        func.setResult_symbol("=");
        func.setFile_name("C:\\Users\\HP\\IdeaProjects\\Lab2\\src\\test\\java\\a.csv");
        func.setResult_file_name("C:\\Users\\HP\\IdeaProjects\\Lab2\\src\\test\\java\\b.txt");
        assertEquals(';',func.getSymb());
        assertEquals("=",func.getResult_symbol());
        assertEquals("C:\\Users\\HP\\IdeaProjects\\Lab2\\src\\test\\java\\a.csv",func.getFile_name());
        assertEquals("C:\\Users\\HP\\IdeaProjects\\Lab2\\src\\test\\java\\b.txt",func.getResult_file_name());
        ArrayList<String> File_lines = new ArrayList();
        File_lines = func.OpenAndReadFile();
        assertEquals("/*fjan;kl", File_lines.get(0));
        assertEquals("\"123\";sfgs/*5*/t", File_lines.get(5));
        ArrayList<String> File_lines_result = new ArrayList<String>();
        File_lines_result = func.Schitalka(File_lines);
        assertEquals("1=3\n",File_lines_result.get(0));
        assertEquals("3=3=4=8=4=6\n",File_lines_result.get(5));
        assertEquals("4=3=5=8\n",File_lines_result.get(6));
        func.WriteAndCloseFile(File_lines_result);
        File res_file = new File(func.getResult_file_name());
        assertNotEquals("0",res_file.length()==0);
    }


}
