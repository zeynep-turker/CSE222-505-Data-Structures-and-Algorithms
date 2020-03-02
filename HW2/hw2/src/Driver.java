import java.util.Iterator;

public class Driver {
    public static void main(String[] args) {
        try {
            ExperimentList myList = new ExperimentList();
            System.out.println("/* addExp Test */");
            myList.addExp(new ExperimentList.Experiment("Exp1", 1, "11:10:55", true, 10));
            myList.addExp(new ExperimentList.Experiment("Exp2", 1, "12:10:55", false, -1));
            myList.addExp(new ExperimentList.Experiment("Exp3", 1, "13:15:36", true, 20));
            myList.addExp(new ExperimentList.Experiment("Exp1", 2, "09:10:42", false, -1));
            myList.addExp(new ExperimentList.Experiment("Exp2", 2, "10:30:15", true, 47));
            myList.addExp(new ExperimentList.Experiment("Exp1", 3, "08:10:25", false, -1));
            myList.addExp(new ExperimentList.Experiment("Exp4", 1, "14:10:14", true, 88));
            myList.addExp(new ExperimentList.Experiment("Exp2", 3, "11:10:13", false, -1));
            myList.addExp(new ExperimentList.Experiment("Exp3", 2, "11:10:56", true, 99));
            myList.addExp(new ExperimentList.Experiment("Exp1", 4, "09:52:24", true, 58));
            MyIterator itr1 = (MyIterator) myList.iterator();
            while (itr1.hasNext()) {
                ExperimentList.Experiment temp = new ExperimentList.Experiment();
                temp = itr1.next();
                System.out.println(temp.toString());
            }
            System.out.println();

            System.out.println("/* getExp Test */");
            System.out.println("Call getExp(1,3) for myList");
            System.out.println(myList.getExp(1, 3).toString());
            System.out.println();

            System.out.println("/* setExp Test */");
            System.out.println("Call setExp(3,1,newNode) for myList");
            ExperimentList.Experiment newNode = new ExperimentList.Experiment("newExp", 3, "15:15:36", false, -1);
            myList.setExp(3, 1, newNode);
            MyIterator itr2 = (MyIterator) myList.iterator();
            while (itr2.hasNext()) {
                ExperimentList.Experiment temp = new ExperimentList.Experiment();
                temp = itr2.next();
                System.out.println(temp.toString());
            }
            System.out.println();

            System.out.println("/* listExp Test */");
            System.out.println("Call listExp(1) for myList");
            myList.listExp(1);
            System.out.println();

            System.out.println("/* removeDay Test */");
            System.out.println("Call removeDay(4) for myList");
            myList.removeDay(4);
            MyIterator itr3 = (MyIterator) myList.iterator();
            while (itr3.hasNext()) {
                ExperimentList.Experiment temp = new ExperimentList.Experiment();
                temp = itr3.next();
                System.out.println(temp.toString());
            }
            System.out.println();

            System.out.println("/* removeExp Test */");
            System.out.println("Call removeExp(1,0) for myList");
            myList.removeExp(1, 0);
            MyIterator itr4 = (MyIterator) myList.iterator();
            while (itr4.hasNext()) {
                ExperimentList.Experiment temp = new ExperimentList.Experiment();
                temp = itr4.next();
                System.out.println(temp.toString());
            }
            System.out.println();

            System.out.println("/* orderExperiments Test */");
            ExperimentList newList = new ExperimentList();
            newList.setHead(myList.orderExperiments());
            System.out.println("myList : ");
            MyIterator itr5 = (MyIterator) myList.iterator();
            while (itr5.hasNext()) {
                ExperimentList.Experiment temp = new ExperimentList.Experiment();
                temp = itr5.next();
                System.out.println(temp.toString());
            }
            System.out.println();
            System.out.println("newList : ");
            MyIterator itr6 = (MyIterator) newList.iterator();
            while (itr6.hasNext()) {
                ExperimentList.Experiment temp = new ExperimentList.Experiment();
                temp = itr6.next();
                System.out.println(temp.toString());
            }
            System.out.println();

            System.out.println("/* orderDay Test */");
            System.out.println("Call orderDay(1) for myList");
            myList.orderDay(1);
            MyIterator itr7 = (MyIterator) myList.iterator();
            while (itr7.hasNext()) {
                ExperimentList.Experiment temp = new ExperimentList.Experiment();
                temp = itr7.next();
                System.out.println(temp.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Error!");
        }
    }
}