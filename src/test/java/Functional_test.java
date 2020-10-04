import org.junit.Test;
import Lab.Functional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class Functional_test {

    @Test
    public void test_OpenAndReadFile () throws IOException {
        Functional func = new Functional();
        func.setFile_name("src/test/java/a.csv");
        ArrayList<String> File_lines = new ArrayList();
        File_lines = func.OpenAndReadFile();
        assertEquals("/*fjan;kl", File_lines.get(0));
        assertEquals("\"123\";sfgs/*5*/t", File_lines.get(5));
        assertEquals("\"kfk\"k;fs/*geerg*/f;\"ere\"ww;\"wew3342",File_lines.get(12));

    }
    @Test
    public void test_Schitalka() throws IOException{
        Functional func = new Functional();
        func.setSymb(';');
        func.setResult_symbol("=");
        ArrayList<String> File_lines = new ArrayList();
        ArrayList<String> File_lines_result = new ArrayList<String>();
        File_lines.add("/*fjan;kl");
        File_lines.add("dfha/*xndng*/d;dsv");
        File_lines.add("\"kfk\"k;fs/*geerg*/f;\"ere\"ww;\"wew3342");
        File_lines_result = func.Schitalka(File_lines);
        assertEquals("1=3\n",File_lines_result.get(0));
        assertEquals("4=3=5=8\n",File_lines_result.get(1));
    }
    @Test
    public void test_WriteAndCloseFile() throws IOException{
        Functional func = new Functional();
        ArrayList<String> File_lines_result = new ArrayList<String>();
        File_lines_result.add("1=3\n");
        File_lines_result.add("4=3=5=8\n");
        func.setResult_file_name("src/test/java/b.txt");
        func.WriteAndCloseFile(File_lines_result);
        File res_file = new File(func.getResult_file_name());
        assertNotEquals("0",res_file.length());
    }

}
