import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        for(int i=0; i<args.length; ++i) {
            String[] variables = new String[50];
            double[] values = new double[50];
            int index = 0;
            boolean temp = true;
            String line = null;
            String str = null;
            try {
                FileReader fileReader = new FileReader(args[i]);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                str = bufferedReader.readLine();
                while (str != null && temp) {
                    String s[] = new String[3];
                    s = str.split(" =");
                    variables[index] = s[0];
                    values[index] = Double.parseDouble(s[1]);
                    index++;
                    str = bufferedReader.readLine();
                    if (str.length() == 0)
                        temp = false;
                }
                line = bufferedReader.readLine();
                bufferedReader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file '" + args[i] + "'");
            } catch (IOException ex) {
                System.out.println("Error reading file '" + args[i] + "'");
            }
            String s[] = line.split(" ");
            InfixEvaluation infix = new InfixEvaluation(s, index, values, variables);
            System.out.println("My infix expression : ");
            System.out.println(line);
            System.out.println("Values of my variables : ");
            for (int j = 0; j < index; ++j)
                System.out.println(variables[j] + " = " + values[j]);
            System.out.printf("My result is %.1f.\n\n", infix.evaluatePostfix());
        }

    }
}
