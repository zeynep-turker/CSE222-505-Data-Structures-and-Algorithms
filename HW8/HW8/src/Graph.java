public interface Graph {
    int getVertexNum();
    int getEdgeNum();
    boolean isDirected();
    boolean addEdge(int src, int dest);
    Edge getEdge(int src,int dest);
    boolean isEdge(int src, int dest);
    boolean removeEdge(int src, int dest);
    MyIterator edgeIterator(int src);
}
