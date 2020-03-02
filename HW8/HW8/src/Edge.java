public class Edge {
    private int src;
    private int dest;
    private double weight;

    public Edge(int src, int dest){
        this.src = src;
        this.dest = dest;
    }

    public Edge(int src, int dest, int weight){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public void setSrc(int src){
        this.src = src;
    }

    public void setDest(int dest){
        this.dest = dest;
    }

    @Override
    public boolean equals(Object obj) {
        if(((Edge)obj).src == this.src && ((Edge)obj).dest == this.dest )
            return true;
        return false;
    }

    public int getDest(){
        return dest;
    }

    public int getSrc(){
        return src;
    }

    public double getWeight(){
        return weight;
    }

    public String toString(){
        String str = "Source : "+src+" Destination : "+dest;
        return str;
    }
}
