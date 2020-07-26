package topfrequency_keyword_analyser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TopKeywordAnalyser implements Runnable {

    public  String filepath;

    public TopKeywordAnalyser(String filepath)
    {
        this.filepath = filepath;
    }


    public void run() {

        ArrayList<String> keywordfiledata = FileUtility.readFileAsList(filepath);
        HashMap<String,Integer> keywordCounter = new HashMap<String,Integer>();

        for(String row : keywordfiledata)
        {
            String keywords[] = row.split(" ");

            for(String keyword : keywords)
            {
                String str = keyword.toLowerCase();

                if(!keywordCounter.containsKey(str))
                {
                    keywordCounter.put(str,1);
                }
                else
                {
                    int value = keywordCounter.get(str);
                    keywordCounter.put(str,value+1);
                }
            }

        }

        ArrayList<KeywordCount> keywordcountarraylist = new ArrayList<KeywordCount>();


        for(String key : keywordCounter.keySet())
        {
            KeywordCount keywordcounter = new KeywordCount(key, keywordCounter.get(key));
            keywordcountarraylist.add(keywordcounter);
        }

        //now we need to sort this keywordcountarraylist now


        Collections.sort(keywordcountarraylist, new Comparator<KeywordCount>() {
            public int compare(KeywordCount o1, KeywordCount o2) {
                return o2.counter - o1.counter;
            }
        });

        // commenting below line to print json objects

        for(KeywordCount  keywordcounter : keywordcountarraylist )
        {
            System.out.println(keywordcounter.keyword + " --> " + keywordcounter.counter);
         }





        //converting java object into json object

        String json = new Gson().toJson(keywordcountarraylist);
        System.out.println(json);// copy pate this output in ctrl +shift +I i console it covert in javascript



        //converting json object into java object
        String convertjson = "{'keyword':'jaya','counter':10}";// double quote can be converted into single quote by removing \
        KeywordCount keywordcount = new Gson().fromJson(convertjson,KeywordCount.class);
        System.out.println("converting into java object from json  --->  " + keywordcount.keyword + keywordcount.counter);


        //converting json object into arraylist

        String convertjsonarray = "[{'keyword':'hellojson','counter':10} , {'keyword':'hello json2','counter':10}]";
        ArrayList<KeywordCount> convertarraylist = new Gson().fromJson(convertjsonarray,new TypeToken<ArrayList<KeywordCount>>(){}.getType());
        for(KeywordCount keywordCountlist : convertarraylist)
        {
            System.out.println(keywordCountlist.keyword + " -->" + keywordCountlist.counter);
        }







    }




    public static void main(String[] args) {

        TaskManager taskmanager = new TaskManager(2);
        taskmanager.waitTillQueueIsFreeAndAddTask(new TopKeywordAnalyser("D:\\Word_Top_Frequency_Analyser\\practise\\demo\\file\\anthem.txt"));
        taskmanager.waitTillQueueIsFreeAndAddTask(new TopKeywordAnalyser("D:\\Word_Top_Frequency_Analyser\\practise\\demo\\file\\nadageetha.txt"));



    }

}
