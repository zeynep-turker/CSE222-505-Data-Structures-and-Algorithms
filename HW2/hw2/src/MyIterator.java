import java.util.Iterator;

public class MyIterator implements Iterator{
    private ExperimentList.Experiment current = new ExperimentList.Experiment();
    private ExperimentList.Experiment temp = new ExperimentList.Experiment();

    /**
     *
     * @param head Dolaşılması istenilen listenin head i alınır.
     */
    public MyIterator(ExperimentList.Experiment head){
        current = head;
    }

    /**
     *
     * @return Current in null olup olmamasına göre boolean değer return edilir.
     */
    @Override
    public boolean hasNext() {
        if(current != null){
            return true;
        }
        return false;
    }

    /**
     *
     * @return Eğer hasNext() true return ederse currentin gösterdiği experiment return edilir.
     */
    @Override
    public ExperimentList.Experiment next() {
        if(hasNext() == true){
            temp = current;
            current = current.next;
            return temp;
        }
        return null;
    }
}
