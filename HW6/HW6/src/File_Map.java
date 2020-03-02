import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    */
   ArrayList<String> fnames = new ArrayList<>();
   ArrayList<List<Integer>> occurances = new ArrayList<>();

    @Override
    public int size() {
        return fnames.size();
    }
    @Override
    public boolean isEmpty() {
        return fnames.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if(fnames.indexOf(key) < 0) return false;
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        if(occurances.indexOf(value) < 0) return false;
        return true;
    }
    @Override
    public Object get(Object key) {
        int index = fnames.indexOf(key);
        if(index > -1) return occurances.get(index);
        return null;
    }
    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
       if(containsKey(key)){//If fnames has key,add value to occurances that same index witk key.
           int index = fnames.indexOf(key);
           for(int i=0; i<((LinkedList<Integer>)value).size(); ++i)
           occurances.get(index).add(((LinkedList<Integer>)value).get(i));
       }
       else{
           fnames.add((String) key);
           occurances.add((LinkedList<Integer>)value);
       }
        return null;
    }
    @Override
    public Object remove(Object key) {
        int index = fnames.indexOf(key);//Find file and linkedlist 's index.
        if(index < 0)//If no key,return null.
            return null;
        List<Integer> removed = occurances.get(index);
        fnames.remove(key);//And delete.
        occurances.remove(index);
        return removed;
    }
    @Override
    public void putAll(Map m) throws NullPointerException{
        if(m == null) throw new NullPointerException();//m is null and throw exception.
        for(int i=0; i<((File_Map) m).fnames.size(); ++i) {//Fill fnames and occurances.
            fnames.add(((File_Map) m).fnames.get(i));
            for (int k = 0; k < ((File_Map) m).occurances.get(i).size(); ++k)
                occurances.get(i).add(((File_Map) m).occurances.get(i).get(k));
        }
    }
    @Override
    public void clear() {
        fnames.clear();
        occurances.clear();
    }
    @Override
    public Set keySet() {
        Set<String> set = new HashSet<>(fnames.size());
        for (int i=0; i<fnames.size(); ++i)
            set.add(fnames.get(i)); //Add to set fnames.
        return set;
    }
    @Override
    public Collection values() {
        return occurances; //My values
    }
    @Override
    public Set<Entry> entrySet() {
        Set<Entry> set = new HashSet<>();
        for(int i=0; i<size(); ++i) {
            MyEnty temp = new MyEnty(fnames.get(i),occurances.get(i));
            set.add(temp);
        }
        return set;
    }
    static class MyEnty implements Entry { //I create entry because of setEntry() method implement.
        String key; //Key of my hashmap is key.
        LinkedList<Integer> value;//Value of my hashmap is value.
        MyEnty(Object key, Object value) { //Constructors of my Entry class
            this.key = (String) key; this.value = (LinkedList<Integer>) value;
        }
        //Getter and Setter of my Node class
        @Override
        public Object getKey() {
            return key;
        }
        public Object getValue(){return value;}
        @Override
        public Object setValue(Object value) {
            this.value = (LinkedList<Integer>) value;
            return value;
        }
        public void setKey(Object key){
            this.key = (String) key;
        }
    }
}