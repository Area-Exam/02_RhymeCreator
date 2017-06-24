import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nisansa on 6/23/2017.
 */
public class Crawler {

    ArrayList<String> words=new ArrayList<String>();
    HashMap<String,String> wordRhymes=new HashMap<String, String>();
    ArrayList<String> rejectEmpty=new ArrayList<String>(); //Page exists but only the word itself is given as rhymes
    ArrayList<String> rejectNull=new ArrayList<String>();  //Even the page does not exist
}
