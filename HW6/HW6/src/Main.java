import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            NLP nlp = new NLP();
            nlp.readDataset("dataset");
            String strLine;
            while((strLine =br.readLine())!=null)
            {
                String[] strings = strLine.split(" ");
                if (strLine.length() != 0) {
                    if (strings.length == 3){
                        System.out.printf("%.5f\n\n",(nlp.tfIDF(strings[1],strings[2])));
                    }
                    else if(strings.length == 2){
                        System.out.println(nlp.bigrams(strings[1])+"\n");
                    }
                    else{
                        System.out.println("Wrong input.");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}