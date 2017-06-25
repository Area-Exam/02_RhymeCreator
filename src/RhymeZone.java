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
        System.out.println(word);
        added=false;

        try {
            url = new URL("http://www.rhymezone.com/r/rhyme.cgi?Word="+word+"&typeofrhyme=perfect&org1=syl&org2=l&org3=y"); //apple
            spoof = url.openConnection();

            //Spoof the connection so we look like a web browser
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
            in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            strLine = "";
            String syllables="";
            String key="";
            ArrayList<String> rhymWords=new ArrayList<String>();

            while ((strLine = in.readLine()) != null) {
                System.out.println(strLine);
            }
              System.exit(0);

        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        if(!added){
            rejectNull.add(word);
        }

    }
}
