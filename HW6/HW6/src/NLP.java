import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class NLP {
    public Word_Map wmap;//My hashmap
    private File[] fileNames; //all files in directory

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir) {
        File folder = new File(dir);
        fileNames = folder.listFiles();
        //Create word Map
        wmap = new Word_Map();
        //Read files
        for (int i = 0; i < fileNames.length; ++i) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileNames[i]))) {
                String strLine;
                // Read lines from the file, returns null when end of stream
                // is reached
                int count = 1; //for word's position
                while ((strLine = br.readLine()) != null) {
                    if (strLine.length() != 0) {
                        String word = strLine.trim().replaceAll("\\p{Punct}", "");
                        String[] strings = word.split(" ");//split sentence by space
                        for (int j = 0; j < strings.length; ++j) {
                            File_Map fileMap = new File_Map();
                            LinkedList<Integer> temp = new LinkedList<>();
                            temp.add(count);
                            fileMap.put(fileNames[i].toString(), temp);//Add fileMap
                            wmap.put(strings[j], fileMap);//Add WordMap
                            count++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word) throws NullPointerException{
        List<String> list = new LinkedList<>();
        File_Map values = ((File_Map) wmap.get(word));
        try {
            if (values == null) {
                System.out.println("Word not found.");
                throw new NullPointerException();
            }
            for (int i = 0; i < values.fnames.size(); ++i) {
                for (int j = 0; j < values.occurances.get(i).size(); ++j) {
                    String temp = word + " ";
                    String temp2=OtherWord(values.fnames.get(i), values.occurances.get(i).get(j));
                    temp = temp + temp2;//List don't add same biagram.
                    if (temp2 !=null && !list.contains(temp) ) list.add(temp);
                    //If word is end of file ' word,not biagram and not add.
                }
            }
        }catch (NullPointerException e){
        }
        return list;
    }
    private String OtherWord(String fileName,int position){//wanted biagram word's next word
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String strLine;
            int count = 1;
            while ((strLine = br.readLine()) != null) {
                if (strLine.length() != 0) {
                    String word = strLine.trim().replaceAll("\\p{Punct}", "");
                    String[] strings = word.split(" ");
                    for (int j = 0; j < strings.length; ++j) {
                        if(count == position + 1) { //Finded given word's next word
                            return strings[j];
                        }
                        count++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*Calculates the tfIDF value of the given word for the given file */
    public double tfIDF(String word, String fileName) throws FileNotFoundException {
        double IDF = IDF(word);
        double TF = TF(word,fileName);
        return TF*IDF;
    }

    /*Print the WordMap by using its iterator*/
    public void printWordMap() {
        MyIterator itr = (MyIterator) wmap.iterator();
        while (itr.hasNext()){
            Word_Map.Node temp = (Word_Map.Node)itr.next();
            System.out.println(temp.word+" ==> "+temp.value.occurances);
        }

    }
    public double TF(String word,String fileName){
        int wordNum = 0;
        int allNum = 0;
        double Tf;
        int i,k=0;
        fileName = "dataset"+"\\" + fileName;
        for (i=0; i<fileNames.length;++i) {//Find file's index in fileNames
            if (fileName.equals(fileNames[i].toString())) {
                k = i;
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileNames[k]))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (strLine.length() != 0) {
                    String str = strLine.trim().replaceAll("\\p{Punct}", "");
                    String[] strings = str.split(" ");
                    for (int j = 0; j < strings.length; ++j) {
                        if (word.equals(strings[j])) {
                            wordNum++;//Number of times word appears in fileName.
                        }
                        allNum++;//Total number of terms in fileName.
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(allNum == 0)throw new NullPointerException();//If dividing is zero,throw exception.
        Tf = (double)wordNum/(double)allNum;

        return Tf;
    }
    public float IDF(String word){
        int count = 0,temp;
        float Idf;
        for (int i = 0; i < fileNames.length; ++i) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileNames[i]))) {
                String strLine;
                temp=0;
                while ((strLine = br.readLine()) != null) {
                    if (strLine.length() != 0) {
                        String str = strLine.trim().replaceAll("\\p{Punct}", "");
                        String[] strings = str.split(" ");
                        for (int j = 0; j < strings.length; ++j) {
                            if (word.equals(strings[j])) {
                                temp++;
                            }
                        }
                    }
                }
                if(temp > 0)count++;//Number of documents with word in it)
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (count == 0) throw new NullPointerException();//If dividing is zero, throw exception.
        Idf = (float) Math.log((double)fileNames.length/(double)count);
        return Idf;
    }
}