import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Matrix {
    private MyIntStack whites = new MyIntStack();
    private int [][] matrix;
    private int row,column;
    public Matrix(String fileName){
        takeMatrix(fileName);
    }
    public void takeMatrix(String fileName){ //I read a file and I take row and column of matrix then I fill my matrix with matrix in file.
        MyStrStack myStack = new MyStrStack();
        row = 0;
        column = 0;
        Scanner input = null;
        try {
            String str = null;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            str = bufferedReader.readLine();
            column = str.length();
            while (str != null) {
                myStack.push(str);
                row++;
                str = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        column = column - column / 2;
        matrix = new int [row][column];
        for (int i = row - 1; i >= 0; --i) {
            String[] str = new String[column];
            str = (myStack.pop()).split(" ");
            for (int j = 0; j < column; ++j) {
                matrix[i][j] = Integer.parseInt(str[j]);
            }
        }
        findWhites();
    }

    public void findWhites(){ //This function finds white elements in matrix.
        for (int i=0; i<row; i++) {
            for (int j = 0; j < column; ++j) {
                if (matrix[i][j] == 1) {
                    whites.push(i, j, '?');
                }
            }
        }
        findNumCompotent();
    }

    public int findNumCompotent(){ //This function finds number of white components.
        MyIntStack.MyIntNode temp,top = null;
        int count = 0;
        boolean find = false;
        top = whites.peek();
        top.compo = (char)(65);
        count++;
        while(top.next != null){
            temp = top.next;
            while (temp != null) {//The components of the nodes around the top are the same as the top.
                if ((temp.i == top.i && temp.j == top.j+1) || (temp.i == top.i && temp.j == top.j-1)
                        || (temp.i == top.i+1 && temp.j == top.j) || (temp.i == top.i-1  && temp.j == top.j)) {
                    temp.compo = top.compo;
                }
                temp = temp.next;
            }
            top = top.next;
            temp = whites.peek();
            while (temp != null && find == false){
                if ((top.i == temp.i && top.j == temp.j+1 && temp.compo != '?') || (top.i == temp.i && top.j == temp.j-1 && temp.compo != '?')
                        || (top.i == temp.i+1 && top.j == temp.j && temp.compo != '?') || (top.i == temp.i-1  && top.j == temp.j && temp.compo != '?')) {
                    top.compo = temp.compo;
                    find=true;
                }
                temp = temp.next;
            } //if node is different,it enter this if.
            if(find == false) {
                top.compo = (char)(65 + count);
                count++;
            }
            find = false;
        }
        printMatrix();
        System.out.println("**Number of White Components are "+count+"**\n");
        return count;
    }
    public void printMatrix() {
        System.out.println("**My Matrix**");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; ++j) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}