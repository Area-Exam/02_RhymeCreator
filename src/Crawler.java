import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nisansa on 6/23/2017.
 */
public abstract class Crawler {

    ArrayList<String> words=new ArrayList<String>();
    HashMap<String,ArrayList<String>> wordRhymes=new HashMap<String, ArrayList<String>>();
    ArrayList<String> rejectEmpty=new ArrayList<String>(); //Page exists but only the word itself is given as rhymes. Element: word,syllables eg: merchandise,3
    ArrayList<String> rejectNull=new ArrayList<String>();  //Even the page does not exist OR Page exists but only the word itself is given as rhymes. Element: word eg: supercalifragilisticexpialidocious


    String source="";
    String rejectedEmpty="";
    String rejectedNull="";
    String rhymes="";


    public Crawler(String source, String rejectedEmpty, String rejectedNull, String rhymes) {
        this.source = source;
        this.rejectedEmpty = rejectedEmpty;
        this.rejectedNull=rejectedNull;
        this.rhymes = rhymes;
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
                writeRhymes();
                writeRejects();
                wordIPAs=new HashMap<String, String>();
                rejects=new ArrayList<String>();
            }

        }

        writeIPA();
        writeRejects();
        System.gc();
    }


    abstract  public void fetchRhymesOf(String word);

    private void writeRhymes(){
        try{
            FileWriter fw = new FileWriter(rhymes, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer = new PrintWriter(bw);
            ArrayList<String>wordRhyme=null;
            for (int i = 0; i <words.size() ; i++) {
                wordRhyme=wordRhymes.get(words.get(i));
                if(wordRhyme!=null){
                    writer.println(words.get(i)+":"+String.join(";",wordRhyme));
                }
            }
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }
}
