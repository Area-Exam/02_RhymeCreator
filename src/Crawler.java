import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nisansa on 6/23/2017.
 */
public abstract class Crawler {

    ArrayList<String> words=new ArrayList<String>();
    HashMap<String,String> wordRhymes=new HashMap<String, String>();
    ArrayList<String> rejectEmpty=new ArrayList<String>(); //Page exists but only the word itself is given as rhymes. Element: word,syllables eg: merchandise,3
    ArrayList<String> rejectNull=new ArrayList<String>();  //Even the page does not exist OR Page exists but only the word itself is given as rhymes. Element: word eg: supercalifragilisticexpialidocious


    String source="";
    String rejectedEmpty="";
    String rejectedNull="";
    String rhymes="";


    //Variables for "fetchRhymesOf"
    URL url=null;
    URLConnection spoof=null;
    BufferedReader in=null;
    String strLine = "";
    boolean added=false;


    public Crawler(String source, String rejectedEmpty, String rejectedNull, String rhymes) {
        this.source = source;
        this.rejectedEmpty = rejectedEmpty;
        this.rejectedNull=rejectedNull;
        this.rhymes = rhymes;
    }

    public void run(){
        readWordFile(source);
        crawlAndExtract();
    }


    public void readWordFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                String line = br.readLine();

                while (line != null) {
                    line=line.toLowerCase();
                    words.add(line);
                    line = br.readLine();
                }

            } finally {
                br.close();
            }
        } catch (Exception e) {

        }
    }


    public void crawlAndExtract(){
        for (int i = 0; i <words.size() ; i++) {
            System.out.println((i+1)+" of "+words.size());
            fetchRhymesOf(words.get(i));

            if(i%10==0){
                writeAll();
                wordRhymes=new HashMap<String,String >();
                rejectEmpty=new ArrayList<String>();
                rejectNull=new ArrayList<String>();
            }

        }

        writeAll();
        System.gc();
    }

    private void writeAll() {
        writeRhymes();
        writeRejects(rejectedEmpty,rejectEmpty);
        writeRejects(rejectedNull,rejectNull);
    }


    abstract  public void fetchRhymesOf(String word);

    private void writeRhymes(){
        try{
            FileWriter fw = new FileWriter(rhymes, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer = new PrintWriter(bw);
            String wordRhyme=null;
            for (int i = 0; i <words.size() ; i++) {
                wordRhyme=wordRhymes.get(words.get(i));
                if(wordRhyme!=null){
                    writer.println(words.get(i)+":"+wordRhyme);
                }
            }
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }

    private void writeRejects(String path, ArrayList<String> data){
        try{
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer = new PrintWriter(bw);
            for (int i = 0; i <data.size() ; i++) {
                writer.println(data.get(i));
            }
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }

}
