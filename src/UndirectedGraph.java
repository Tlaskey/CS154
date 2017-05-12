import java.util.ArrayList;

public class UndirectedGraph
{
    private ArrayList<Node> nodes;

    public UndirectedGraph(int n)
    {
        nodes = createRandomGraph(n);
    }

    public static ArrayList<Node> createRandomGraph(int n)
    {
        return null;
    }

    public static ArrayList<Node> setAdjacencyList()
    {
        return null;
    }

    public int findMaxClique()
    {
        return 0;
    }
}

class Node
{
    private ArrayList<Node> adjacencyList;
    private int n;

    public Node(int n)
    {
        adjacencyList = new ArrayList<Node>();
        this.n = n;
    }
}
