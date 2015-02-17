import java.util.*;

/**
 * Created by Demian on 2/6/15.
 */
public class GraphTester {

    public static void main(String[] args){
        System.out.println("test");
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");
        Vertex v5 = new Vertex("E");
        Vertex v6 = new Vertex("F");


        Set<Vertex> nodes = new HashSet<Vertex>();
        nodes.add(v1);
        nodes.add(v2);
        nodes.add(v3);
        nodes.add(v4);
        nodes.add(v5);
        nodes.add(v6);

        Edge e1 = new Edge(v1, v2, 3);
        Edge e2 = new Edge(v2, v3, 6);
        Edge e3 = new Edge(v3, v4, 4);
        Edge e4 = new Edge(v5, v4, 1);
        Edge e5 = new Edge(v5, v1, 4);
        Edge e6 = new Edge(v4, v2, 2);


        Set<Edge> edges = new HashSet<Edge>();
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);
        edges.add(e6);

        Graph g = new MyGraph(nodes, edges);

        // get and print all reachable vertices, A B C D
        Collection<Vertex> r = g.reachableVertices(v1);
        System.out.println("Reachable Vertices: " + Arrays.toString(r.toArray()));


        // print B C E
        System.out.println("Adjacent vertices of " + v4.toString() + ": " + g.adjacentVertices(v4));

        // test for existence of edge1, returns 3
        System.out.println(v1.toString() + " is adjacent to " + v2.toString() +
                "? And has cost of: " + g.isAdjacent(v1, v2) + " (-1 if not adjacent)");

        // returns -1
        System.out.println(v2.toString() + " is adjacent to " + v1.toString() +
                "? And has cost of: " + g.isAdjacent(v2, v1) + " (-1 if not adjacent)");

        System.out.println("Topological sort of graph: " + g.topologicalSort());





    }

}
