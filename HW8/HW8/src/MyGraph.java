public class MyGraph extends AbstractGraph{
    MyLinkedList arr[];
    MyLinkedList popularity;

    /**
     *
     * @param vertexNum vertex number
     * @param edgeNum edge number
     */
    public MyGraph(int vertexNum, int edgeNum) {
        super(vertexNum,edgeNum);
        arr = new MyLinkedList[vertexNum + 1];

        for (int i = 0; i < vertexNum + 1; i++) {
            arr[i] = new MyLinkedList();
        }
        popularity = new MyLinkedList();
        for (int i=1; i<vertexNum+1; ++i)
            popularity.addNode(i,0);
    }

    /**
     * add if it don't contain vertex.
     * @param src vertex
     * @param dest vertex
     * @return true or false
     */
    public boolean addEdge(int src, int dest) {
        if(src > getVertexNum() || dest > getVertexNum()) {
            System.out.println("Src and dest don't be bigger than vertex num.");
            throw new IllegalArgumentException();
        }
        if(arr[src].contains(dest))
            return false;
        arr[src].addNode(src,dest);
        return true;
    }

    /**
     * return relation between src and dest
     * @param src vertex
     * @param dest vertex
     * @return (Edge) relation
     */
    @Override
    public Edge getEdge(int src, int dest) {
        Edge edge;
        edge = arr[src].findDest(dest);
        return edge;
    }

    public void printGraph() {
        for (int i = 1; i < getVertexNum() + 1; i++) {
            System.out.printf("%d : ", i);
            arr[i].print();
        }
    }

    /**
     * set relations with transitive
     */

    public void transitive() {
        for (int i = 1; i < getVertexNum() + 1; ++i) {
            for (int j = 0; j < arr[i].size(); ++j) {
                for (int k = 0; k < arr[arr[i].get(j).getEdge().getDest()].size(); ++k) {
                    if (!(arr[i].contains(arr[arr[i].get(j).getEdge().getDest()].get(k).getEdge().getDest()))) {
                        arr[i].addNode(i,arr[arr[i].get(j).getEdge().getDest()].get(k).getEdge().getDest());
                     }
                 }
            }
        }
    }

    /**
     *  if a vertex considered popular by every other vertex, it is popular
     * @return popular num
     */

    public int findPopularNum(){
        int popular = 0;

        for (int i = 1; i < arr.length; ++i) {
            for (int j = 0; j < arr[i].size(); ++j) {
                int temp = popularity.get(arr[i].get(j).getEdge().getDest() - 1).getEdge().getDest();
                if (i != arr[i].get(j).getEdge().getDest())
                    popularity.get(arr[i].get(j).getEdge().getDest() - 1).setData(i, temp + 1);
            }
        }

       for(int i=0; i<popularity.size(); ++i){
           if(popularity.get(i).getEdge().getDest() == getVertexNum()-1)
               popular++;
       }
       return popular;
    }

    /**
     * return vertex's neighbors
     * @param vertex
     * @return Mylinkedlist
     */
    public MyLinkedList getNeighbors(int vertex){
        return arr[vertex];
    }

    /**
     * Check adjacency between 2 vertices in the graph.
     * @param vertex start vertex
     * @param other end vertex
     * @return true or false
     */
    public boolean isEdge(int vertex, int other) {
        return arr[vertex].contains(other);
    }

    /**
     * remove src-dest relation
     * @param src vertex
     * @param dest vertex
     * @return true or false
     */
    public boolean removeEdge(int src, int dest) {
        if (arr[src] == null || arr[dest] == null) {
            return false;
        }
        arr[src].remove(dest);
        arr[dest].remove(src);
        return true;
    }

    /**
     *
     * @param src vertex
     * @return  the vertex's edge list's iterator
     */
    @Override
    public MyIterator edgeIterator(int src) {
        MyIterator itr = new MyIterator(arr[src]);
        return itr;
    }
}
