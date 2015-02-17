import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    private Collection<Vertex> myVertices; // the vertices in this graph
    private Collection<Edge> myEdges;       // the edges in this graph

    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     *
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {


        myVertices = v;
        myEdges = e;
    }

    /**
     * Return the collection of vertices of this graph
     *
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
        return myVertices;
    }

    /**
     * Return the collection of edges of this graph
     *
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
        return myEdges;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     * i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     *
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
        Set<Vertex> result = new HashSet<Vertex>();
        if (!myVertices.contains(v)){
            throw new IllegalArgumentException("Vertex " + v.toString() + " not found");
        }

        result.addAll(outNeighbors(v));
        result.addAll(inNeighbors(v));
        return result;
    }

    /**
     * Return a collection of vertices that are reachable from a given vertex v.
     * A vertex is reachable if a path exists from v to the other vertex.
     *
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices that are reachable from v in the graph
     */

    public Collection<Vertex> reachableVertices(Vertex v) {
        Set<Vertex> result = new HashSet<Vertex>();
        Set<Vertex> frontier = new HashSet<Vertex>();
        Set<Vertex> nextFrontier = new HashSet<Vertex>();

        // a vertex is reachable to itself (path length 0)
        result.add(v);

        frontier.add(v);

        while (!frontier.isEmpty()) {

            Iterator itr = frontier.iterator();

            // start at level 0, get all level 1 nodes
            // union the nodes into the result set
            // build up level 1

            while (itr.hasNext()) {
                Vertex current = (Vertex) itr.next();
                Set<Vertex> outNodes = outNeighbors(current);

                // remove all outneighbors we've already visited
                outNodes.removeAll(result);
                nextFrontier.addAll(outNodes);
                outNodes.clear();
            }

            result.addAll(nextFrontier);

            frontier.clear();
            frontier.addAll(nextFrontier);
            nextFrontier.clear();
        }

        return result;

    }


    /**
     * Returns a topological sorting of the vertices in the graph.
     *
     * @return an ordered list of vertices in topological sort order
     */
    public List<Vertex> topologicalSort() {
        ArrayList<Vertex> result = new ArrayList<Vertex>();
        boolean visited = false;

        for(Vertex v : myVertices) {
            for (Vertex g : result) {
                if (inNeighbors(v).size() < inNeighbors(g).size()) {
                    result.add(result.indexOf(g), v);
                    visited = true;
                    break;
                }
            }
            if (visited){
                visited = false;
                continue;
            }

            result.add(v);
        }

        return result;


    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     *
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph,
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int isAdjacent(Vertex a, Vertex b) {
        if (!myVertices.contains(b) || !myVertices.contains(a)){
            throw new IllegalArgumentException("Vertex " + a.toString() +  "or " + b.toString()+ " not found");
        }


        for (Edge e : myEdges) {
            if (e.to.equals(b) && e.from.equals(a)) {

                    return e.w;

            }
        }
        return -1;


    }

    /**
     * Returns the shortest path from a to b in the graph.  Assumes positive
     * edge weights.  Uses Dijkstra's algorithm.
     *
     * @param a    the starting vertex
     * @param b    the destination vertex
     * @param path a list in which the path will be stored, in order, the first
     *             being the start vertex and the last being the destination vertex.  The
     *             list will be empty if no such path exists.  NOTE: the list will be
     *             cleared of any previous data.  path is not expected to contain any data
     *             needed by the method when the method is called.  It is used to allow
     *             us to return multiple values from the function.
     * @return the length of the shortest path from a to b, -1 if no such path
     * exists.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public int shortestPath(Vertex a, Vertex b, List<Vertex> path) {

        // YOUR CODE HERE
        return -1;

    }

    // When provided with a vertex called v
    // It should return a set of all the "incoming"
    // neighbor vertexes of v
    public Set<Vertex> inNeighbors(Vertex v) {
        Set<Vertex> result = new HashSet<Vertex>();
        for (Edge e : myEdges) {
            if (e.to.equals(v)) {
                // This is an "in-neighbor"
                result.add(e.from);
            }
        }

        return result;
    }

    public Set<Vertex> outNeighbors(Vertex v) {
        Set<Vertex> result = new HashSet<Vertex>();
        for (Edge e : myEdges) {
            if (e.from.equals(v)) {
                // This is an "out-neighbor"
                result.add(e.to);
            }
        }

        return result;
    }
    public Set<Vertex> minNodes(Vertex v) {
        // create an empty set
        Set<Vertex> result = new HashSet<Vertex>();

        for (Vertex node : myVertices) {
            Set<Vertex> inNbrs = inNeighbors(node);

            if (inNbrs.isEmpty()) {
                result.add(node);
            }
        }
        return result;
    }
}