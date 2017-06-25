public class Main {

    public static void main(String[] args) {

        String path="../Output/02_Rhyme/";
        String machine="Rhymer";
        String source=path+"words.txt";
        String rejectedEmpty=path+"rejectWordsEmpty_"+machine+".txt";
        String rejectedNull=path+"rejectWordsNull_"+machine+".txt";
        String rhymes=path+"rhymes_"+machine+".txt";

        // Rhymer "../Output/02_Rhyme/" "../Output/02_Rhyme/words.txt"

        if(args.length>0) {
            machine = args[0];
            path = args[1];
            source = args[2];
            rejectedEmpty=path+"rejectWordsEmpty_"+machine+".txt";
            rejectedNull=path+"rejectWordsNull_"+machine+".txt";
            rhymes=path+"rhymes_"+machine+".txt";
        }

        Crawler c=null;
        if(machine.equalsIgnoreCase("Rhymer")) {
            c = new Rhymer(source, rejectedEmpty,rejectedNull,rhymes);
        }
        else if(machine.equalsIgnoreCase("RhymeZone")) {
            c = new RhymeZone(source, rejectedEmpty,rejectedNull,rhymes);
        }

        if(c!=null) {
            c.run();
        }
        else{
            System.out.println("Crawler not initialized");
        }

    }
}
