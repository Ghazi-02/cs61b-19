public class UnionFind {

    //Always link root of smaller tree to larger tree
    //Track tree size(number of elements)

    private int[] parents;
    private int weight;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parents = new int[n];
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if ( 0 < vertex || vertex > parents.length-1){
            throw new RuntimeException("Invalid vertex");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int vertex) {
        // TODO
        int size = 0;

        for(int x = 0; x < parents.length;x++){
            if (connected(vertex,x)){
                size = size + sizeOfBranch(x);
            }
        }


        return size;

    }
    private int sizeOfBranch(int vertex){
        int parentVal = 0;
        while (vertex >= 0){
            weight += 1;
            parentVal = parents[vertex];
            vertex = parentVal;
        }
        return weight;
    }


    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        if(sizeOf(v1) > sizeOf(v2)){
            parents[rootV2] = rootV1;
        }else if(sizeOf(v1) < sizeOf(v2)){
            parents[rootV1] = rootV2;
        }else {
            parents[rootV2]=rootV1;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int parentVal = 0;

        while (vertex >= 0){
            parentVal = parents[vertex];
            vertex = parentVal;
        }
        return vertex;
    }

}
