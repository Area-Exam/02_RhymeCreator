import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nisansa on 6/23/2017.
 */
public class Rhymer extends Crawler {


    public Rhymer(String source, String rejectedEmpty, String rejectedNull, String rhymes) {
        super(source, rejectedEmpty, rejectedNull, rhymes);
    }

    @Override
    public void fetchRhymesOf(String word) {
        added=false;

      //  word="achilles"; //apple merchandise, supercalifragilisticexpialidocious, achilles
       // System.out.println(word);


        try {
            url = new URL("http://www.rhymer.com/cgi-bin/rhymer2.cgi?Word="+word+"&RhymeBtn=Rhyme&RhymeType=Double+Rhymes"); //apple
            spoof = url.openConnection();

            //Spoof the connection so we look like a web browser
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
            in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            strLine = "";
            String syllables="";
            String key="";
            ArrayList<String> rhymWords=new ArrayList<String>();

            while ((strLine = in.readLine()) != null) {

                if(strLine.contains("syllables")) {
                    syllables=strLine.substring(strLine.indexOf(">")+1,strLine.indexOf("s")-1);
               //     System.out.println(syllables);

                    if(syllables.contains("more")){  //It has "4 or more". :-(
                        syllables="4+";
                    }

                    strLine = in.readLine();
                    String[] parts=strLine.split("</a>");
                    for (int i = 0; i < parts.length; i++) {
                        if(parts[i].contains("<a")) {
                            parts[i]=parts[i].substring(parts[i].indexOf(">")+1,parts[i].length());
                            if(parts[i].equalsIgnoreCase(word)){
                                key=syllables+":";
                            }
                            else{
                                rhymWords.add(parts[i]+","+syllables);
                            }
                     //       System.out.println(parts[i]);
                        }
                    }



                    if(rhymWords.size()>1){
                        wordRhymes.put(word,key+String.join(";",rhymWords));
                    }
                    else{
                       rejectEmpty.add(word+","+syllables);
                        rejectNull.add(word);
                    }



                    added=true;

                }

            }
             //  System.exit(0);

        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        if(!added){
            rejectNull.add(word);
        }
    }
}
