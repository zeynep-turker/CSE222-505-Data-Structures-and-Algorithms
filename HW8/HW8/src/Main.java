import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringBuilder sb = new StringBuilder();
        String tempStr = null;
        try { //Read lines from file
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("/");
                line = br.readLine();
            }
            tempStr = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        String[] str = tempStr.split("/");
        Graph graph = new MyGraph(Character.getNumericValue(str[0].charAt(0)),Character.getNumericValue(str[0].charAt(2)));
        for(int i=1; i<str.length; ++i) //fill graph
            graph.addEdge(Character.getNumericValue(str[i].charAt(0)),Character.getNumericValue(str[i].charAt(2)));
        ((MyGraph) graph).transitive();
        System.out.println(((MyGraph) graph).findPopularNum());


    }
}
