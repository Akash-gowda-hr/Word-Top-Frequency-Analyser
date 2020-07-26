package topfrequency_keyword_analyser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtility {

    public static ArrayList<String> readFileAsList(String path)
    {
        File file = new File(path);
        ArrayList<String> arraylist = new ArrayList<String>();

        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                String s = sc.nextLine();
                arraylist.add(s);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally
        {
            if(sc != null)
            {
                sc = null;
            }
        }

        return arraylist;

    }


    public static void main(String[] args) {

        String str = "D:\\TopFrequencyAnalyser\\practise\\mini project\\reading file\\Anthem";

        ArrayList<String> array = readFileAsList(str);

        for(String s1 : array)
        {
            System.out.println(s1);
        }

    }
}
