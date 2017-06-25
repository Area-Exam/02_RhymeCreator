import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nisansa on 6/24/2017.
 */
public class RhymeZone extends Crawler {

    // RhymeZone "../Output/02_Rhyme/" "../Output/02_Rhyme/rejectWordsNull_Rhymer.txt"

    public RhymeZone(String source, String rejectedEmpty, String rejectedNull, String rhymes) {
        super(source, rejectedEmpty, rejectedNull, rhymes);
    }

    @Override
    public void fetchRhymesOf(String word) {

       // word="seller"; //"super"; //"carrot";

      //  System.out.println(word);
        added=false;


        try {
            url = new URL("http://www.rhymezone.com/r/rhyme.cgi?Word="+word+"&typeofrhyme=perfect&org1=syl&org2=l&org3=y"); //apple
            spoof = url.openConnection();

            //Spoof the connection so we look like a web browser
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
            in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            strLine = "";
            String syllables="";
            String key="X:";
            ArrayList<String> rhymWords=new ArrayList<String>();

            while ((strLine = in.readLine()) != null) {

                if(strLine.contains("syllable")) {
                    syllables=strLine.substring(strLine.indexOf(">")+1,strLine.indexOf("s")-1);

                   // System.out.println(syllables);

                    while ((strLine = in.readLine()) != null && !strLine.contains("<br>")) {
                        if(strLine.contains("<b>")) {
                            strLine=strLine.substring(strLine.indexOf("\">"),strLine.indexOf("/"));
                            strLine=strLine.substring(strLine.indexOf(">")+1,strLine.indexOf("<"));
                            if(!strLine.contains("&nbsp;")) { //Get rid of multi-word ones
                                ///System.out.println(strLine+","+syllables);

                                rhymWords.add(strLine+","+syllables);
                            }
                        }
                    }
                }

              //  if(strLine.contains("almost rhyme")){  //Getting rid of almost rhymes
                //    break;
              //  }

            }

            if(rhymWords.size()>0){
                wordRhymes.put(word,key+String.join(";",rhymWords));
            }
            else{
                rejectEmpty.add(word+",X");
                rejectNull.add(word);
            }

            added=true;

          //    System.exit(0);

        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        if(!added){
            rejectNull.add(word);
        }

    }
}
