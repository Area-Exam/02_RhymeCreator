public class Main {

    public static void main(String[] args) {


        String source="../Output/words.txt";
        String rejectedEmpty="../Output/rejectWordsEmpty.txt";
        String rejectedNull="../Output/rejectWordsEmpty.txt";
        String rhymes="../Output/rhymes.txt";
        String machine="Rhymer";


        if(args.length>0) {
            machine = args[0];
            source = args[1];
            rejectedEmpty = args[2];
            rejectedNull = args[3];
            rhymes = args[4];
        }

        Crawler c=null;
        if(machine.equalsIgnoreCase("Rhymer")) {
            //c = new Rhymer(source, rejectedEmpty,rejectedNull,rhymes);
        }

        if(c!=null) {
            c.run();
        }
        else{
            System.out.println("Crawler not initialized");
        }

    }
}
