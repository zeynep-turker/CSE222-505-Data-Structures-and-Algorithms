public abstract class AbstractGraph implements Graph {
    private int vertexNum, edgeNum;
    boolean directed = false;

    AbstractGraph(int vertexNum,int edgeNum){
       this.vertexNum = vertexNum;
       this.edgeNum = edgeNum;
    }

    @Override
    public int getVertexNum() {
        return vertexNum;
    }

    @Override
    public int getEdgeNum() {
        return edgeNum;
    }

    public boolean isDirected() {
        return directed;
    }

    public abstract boolean addEdge(int src, int dest);

    public abstract Edge getEdge(int src, int dest);

    public abstract boolean isEdge(int src, int dest);

    public abstract boolean removeEdge(int src, int dest);

    public abstract MyIterator edgeIterator(int src);
}
