import java.util.Iterator;

public class ExperimentList implements Iterable{
    private Experiment head;
    public static class Experiment{
        public Experiment next;
        public Experiment nextDay;
        public String setup;
        public int day;
        public String time;
        public boolean completed;
        public float accuracy;
        public Experiment(){}
        public Experiment(String setup,int day,String time,boolean completed,float accuracy){
            this.setup = setup;
            this.day = day;
            this.time = time;
            this.completed = completed;
            this.accuracy = accuracy;
            this.nextDay = null;
            this.next = null;
        }
        public Experiment(Experiment e){
            this.setup = e.setup;
            this.day = e.day;
            this.time = e.time;
            this.completed = e.completed;
            this.accuracy = e.accuracy;
            this.nextDay = null;
            this.next = null;
        }
        public String getSetup(){return setup;}
        public String getTime(){return time;}
        public int getDay(){return day;}
        public boolean getCompleted(){return completed;}
        public float getAccuracy(){return accuracy;}
        public void setSetup(String setup){this.setup = setup;}
        public void setTime(String time){this.time = time;}
        public void setDay(int day){this.day = day;}
        public void setCompleted(boolean completed){this.completed = completed;}
        public void setAccuracy(float accuracy){this.accuracy = accuracy;}
        @Override
        public String toString() {
            String myNode;
            myNode = "setup : "+setup+", time : "+time+", day : "+day+", accurancy : "+accuracy+", completed : "+completed;
            return myNode;
        }
    }
    public Experiment getHead(){ return head;}
    public void setHead(Experiment head){this.head = head;}
    @Override
    public Iterator iterator() {
        MyIterator itr = new MyIterator(head);
        return itr;
    }
    public ExperimentList(){
        head = null;
    }
    public ExperimentList(Experiment head){
        this.head = head;
    }

    /**
     *  Listeye experiment ekler.
     * @param e Listeye eklenmek istenilen experimenttir.
     */
    public void addExp(Experiment e){
        if(head == null) {
            head = e;
        }
        else{
            Experiment temp = new Experiment();
            Experiment temp2 = new Experiment();
            temp = head;
            /* temp eklencek günün sonuna kadar ilerler ve listenin sonuna experimenti ekler.*/
            while(temp.next != null) {
                if(temp.next.getDay() > e.getDay()){
                    e.next = temp.next;
                    temp.next = e;
                    return;
                }
                temp = temp.next;
            }
            e.next=temp.next;
            temp.next = e;

        }
        this.linkDay(head);

    }

    /**
     *  Listeden experiment alır.
     * @param day return edilmesi istenilen experimentin day idir.
     * @param index return edilmesi istenilen experimentin day de bulunduğu indextir.
     * @return Experiment tipinde bir node return eder.
     * @throws IndexOutOfBoundsException  Eğer girilen day listede yoksa veya day varsa ve day in
     * istenilen index te bir experimenti yoksa bu exception fırlatılır.
     */
    public Experiment getExp(int day, int index)throws IndexOutOfBoundsException{
        int count=0;
        Experiment temp = new Experiment();
        temp = head;
        //temp.getDay day e eşit değilse eşit olana kadar temp nextDay olarak ilerler.
        if(temp.getDay() != day) {
            while (temp.nextDay.getDay() != day) {
                temp = temp.nextDay;
            }
            temp = temp.nextDay;
        }
        while (count != index && temp.next != null){
            if(temp.next.getDay() != day) {
                throw  new IndexOutOfBoundsException();
            }
            temp = temp.next;
            count++;
        }
        if(count != index)
            throw  new IndexOutOfBoundsException();
        return temp;
    }

    /**
     *  Listedeki experiment değiştirilir.
     * @param day Değiştirilmek istenilen experimentin olduğu day dir.
     * @param index Değiştirilmek istenilen experimentin bulunduğu gündeki indextir.
     * @param e Değiştirilmek istenilen experimentin bilgileri e ile değiştirilir.
     * @throws IndexOutOfBoundsException Eğer girilen day listede yoksa veya day varsa ve day in
     *  istenilen index te bir experimenti yoksa bu exception fırlatılır.
     */
    public void setExp(int day, int index, Experiment e)throws IndexOutOfBoundsException{
        int count=0;
        Experiment temp = new Experiment();
        temp = head;
        //temp.getDay day e eşit değilse eşit olana kadar temp nextDay olarak ilerler.
        if(temp.getDay() != day) {
            while (temp.nextDay.getDay() != day) {
                if(temp.nextDay == null)
                    throw new IndexOutOfBoundsException();
                temp = temp.nextDay;
            }
            temp = temp.nextDay;
        }
        /* count ve index eşit olana kadar count artılır eşit olduğunda ise swapNode fonksiyonu
        ile experimentlerin bilgileri değiştirilir.*/
        while(temp != null && temp.getDay() == day) {
            if (count == index) {
                swapNode(temp, e,false);
                this.linkDay(head);
                return;
            }
            count++;
            temp = temp.next;
        }
        if(temp == null)
            throw new IndexOutOfBoundsException();
        if(temp.getDay() != day)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Listedeki bir günün experimentleri listelenir.
     * @param day Experimentlerinin ekrana print edilmesi istenilen gündür.
     * @throws IndexOutOfBoundsException Eğer day listede olmayan bir day se bu exception
     *          fırlatılır.
     */
    public void listExp(int day)throws IndexOutOfBoundsException{
        Experiment temp = new Experiment();
        temp = head;
        //temp.getDay day e eşit değilse eşit olana kadar temp nextDay olarak ilerler.
        if (temp.getDay() != day) {
            while (temp != null && temp.getDay() != day){
                temp = temp.nextDay;
            }
            if(temp == null){
                throw new IndexOutOfBoundsException();
            }
        }
        while(temp != null && temp.getDay() == day) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    /** Listedenin bir günündeki experimentler silinir.
     * Girilen day e sahip olan experimentler listeden silinir.
     * @param day Listeden silinmesi istenilen day dir.
     * @throws IndexOutOfBoundsException Eğer girilen day listede yoksa veya day varsa ve day in
     * istenilen index te bir experimenti yoksa bu exception fırlatılır.
     */
    public void removeDay(int day)throws IndexOutOfBoundsException{
        Experiment temp = new Experiment();
        Experiment temp2 = new Experiment();
        temp = head;
        // Day 1 se head temp in nextDay i olur ve day 1 deki experimentler silinmiş olur.
        if(temp.getDay() == day){
            head = temp.nextDay;
        }
        else{
            /*temp girilen day in ilk elemanından önceki experimentine kadar ilerler ve bir sonraki
            experimentin day i girilen day ile aynı ise bu experiment silinir.Bu işlem temp in day i day
            ile aynı olmayana kadar devam eder.*/
            while(temp.nextDay != null && temp.nextDay.getDay() != day){
                temp = temp.nextDay;
            }
            if(temp.nextDay == null)
                throw new IndexOutOfBoundsException();
            temp2 = temp;
            while (temp.next.getDay() != day){
                temp = temp.next;
            }
            temp.next = temp2.nextDay.nextDay;
        }
        linkDay(head);

    }

    /**
     * Listeden experiment siler.
     * @param day Silinmek istenilen experimentin day idir.
     * @param index Silinmek istenilen experimentin day deki bulunduğu indextir.
     * @throws IndexOutOfBoundsException Eğer girilen day listede yoksa veya day varsa ve day in
     * istenilen index te bir experimenti yoksa bu exception fırlatılır.
     */
    public void removeExp(int day, int index)throws IndexOutOfBoundsException{
        int count=0;
        Experiment temp = new Experiment();
        temp = head;
        if(day < temp.getDay())
            throw new IndexOutOfBoundsException();
        //silinmesi istenilen experiment head ise head kendinden bir sonraki experiment olur.
        if(temp.day == day && index == 0){
            head = head.next;
            this.linkDay(head);
            return;
        }
        //Eğer temp in dayi day den farklı ise temp verilen day den bir önceki day in son experimentine kadar ilerler.
        if(temp.getDay() != day) {
            while (temp.nextDay != null && temp.nextDay.getDay() != day) {
                temp = temp.nextDay;
            }
            if (temp.nextDay == null)
                throw new IndexOutOfBoundsException();
            while (temp.next.getDay() != day){
                temp = temp.next;
                if(temp.next == null){
                    throw new IndexOutOfBoundsException();
                }
            }
        }
        while(temp.next != null) {
            if(day == head.getDay()){
                /*temp head dan başladığı için count+1 == index eşitliği aranır.*/
                if (count+1 == index) {
                    temp.next = temp.next.next;
                    this.linkDay(head);
                    return;
                }
            }
            else{
                /*temp day den bir önceki experiment olacağı için count == index eşitliği aranır.*/
                if (count == index) {
                    temp.next = temp.next.next;
                    this.linkDay(head);
                    return;
                }
            }
            count++;
            temp = temp.next;
            if (temp.next == null || temp.next.getDay() > day) {
                throw new IndexOutOfBoundsException();
            }
        }
        this.linkDay(head);
    }

    /**
     * Listedeki bir günün experimentlerini accuracy lerine göre sıralar.
     * @param day Sıralanması istenilen gündür.
     * @throws IndexOutOfBoundsException Gün listedeki en sonki günü aşarsa bu exception fıralatılır.
     */
    public void orderDay(int day)throws IndexOutOfBoundsException{
        Experiment node = new Experiment();
        Experiment node2 = new Experiment();
        node = head;

        if(day != head.getDay()) { //Day eğer head.getDay() değilse day e ulaşana kadar while de temp ilerletilir.
            while (node.nextDay.getDay() != day){
                node = node.nextDay;
                if(node.nextDay == null){
                    throw new IndexOutOfBoundsException();
                }
            }
            node = node.nextDay;
        }
        /*Sıralanması istenilen gündeki elemanlar küçükten büyüğe doğru sıralanır.*/
        while(node.next != null && node.next.getDay() == day){
            node2 = node;
            while (node2.next != null && node2.next.getDay() == day){
                node2 = node2.next;
                if(node.getAccuracy() > node2.getAccuracy())
                    swapNode(node, node2,true);
            }
            node = node.next;
        }
    }

    /**
     * Listedeki experimentleri accuracy lerine göre sıralar.
     * @return Yeni oluşan sıralı listenin head i return edilir.
     */
    public Experiment orderExperiments(){
        ExperimentList newList = new ExperimentList();
        Experiment node = new Experiment();
        Experiment node2 = new Experiment();
        node = head;
        // listenin elemanlari newListe eklenir.
        while(node != null){
            Experiment temp = new Experiment(node);
            newList.addExp(temp);
            node = node.next;
        }
        node = newList.getHead();
        node2 = newList.getHead();
        /* newList in elemanlarının accurancyleri birbirleriyle karşılaştırılıp küçükten büyüğe doğru sıralanır.*/
        while(node.next != null){
            node2 = node;
            while (node2.next != null){
                node2 = node2.next;
                if(node.getAccuracy() > node2.getAccuracy())
                    swapNode(node, node2,true);
            }
            node = node.next;
        }
        return newList.getHead();
    }

    /**
     * Listenin nextDay lerini birbirine bağlayan fonksiyondur.
     * @param e listenin head i dir
     */
    public void linkDay(Experiment e){
        Experiment temp = new Experiment();
        Experiment temp2 = new Experiment();
        int n=0;
        temp = e;
        temp2 = e;
        while(temp != null){
          while (temp.getDay() == n){
              temp = temp.next;
              if(temp == null)
                  return;
          }
          temp2.nextDay = temp;
          temp2 = temp;
          n++;
          if(temp.next == null)
              return;
        }
    }

    /**
     * İki experimentin özelliklerini birbirleriyle değiştirir.
     * @param o1 İçeriğinin değiştirilmesi istenilen birinci Experiment.
     * @param o2 İçeriğinin değiştirilmesi istenilen ikinci Experiment.
     * @param choice Experimentlerin day leri birbiriyle değiştirilmek isteniyorsa true,
     *        istenilmiyorsa false olarak girilir.
     */
    public void swapNode(Experiment o1, Experiment o2,boolean choice){
        String time,setup;
        int day;
        float accuracy;
        boolean completed;
        time = o1.getTime();
        setup = o1.getSetup();
        day = o1.getDay();
        accuracy = o1.getAccuracy();
        completed = o1.getCompleted();
        o1.setTime(o2.getTime());o1.setSetup(o2.getSetup());
        o1.setAccuracy(o2.getAccuracy());
        o1.setCompleted(o2.getCompleted());
        o2.setTime(time); o2.setSetup(setup);
        o2.setAccuracy(accuracy);o2.setCompleted(completed);
        if(choice == true){
            o1.setDay(o2.getDay());
            o2.setDay(day);
        }
    }
}

