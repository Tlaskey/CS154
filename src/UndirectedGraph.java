import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Tyler Laskey
 *
 */
public class UndirectedGraph
{
    private static ArrayList<Node> nodes;
    private static int numNodes;

    /**
     * 
     * @param n
     *            number of nodes in graph
     */
    public UndirectedGraph(int n)
    {
        nodes = new ArrayList<>();
        numNodes = n;
        createRandomGraph(n);
    }

    /**
     * UndirectedGraph constructor for empty graph.
     */
    public UndirectedGraph()
    {
        nodes = new ArrayList<>();
        numNodes = 0;
    }

    /**
     * Add node to graph.
     * 
     * @param newNode
     *            node to be added.
     */
    public void addNode(int newNode)
    {
        nodes.add(new Node(newNode));
        numNodes++;
    }

    /**
     * Adds a specific node to graph.
     * 
     * @param node
     *            the node to be added.
     */
    public void addNode(Node node)
    {
        nodes.add(node);
        numNodes++;
    }

    /**
     * populates the graph with n nodes.
     * 
     * @param n
     *            number of nodes to add.
     */
    public void populateGraph(int n)
    {
        for (int i = 1; i <= n; i++)
        {
            addNode(i);
        }
    }

    /**
     * Creates a random graph of size n.
     * 
     * @param n
     *            number of nodes in graph.
     */
    public void createRandomGraph(int n)
    {
        // Populate graph with n nodes.
        populateGraph(n);
        // Randomize number of edges for each node.
        Random rand = new Random();
        int numEdges = rand.nextInt(n / 2) + 1;
        for (int i = 0; i < n; i++)
        {
            Node node = nodes.get(i);
            ArrayList<Node> nodeAdjList = node.getAdjList();
            for (int j = 0; j < numEdges; j++)
            {
                int randNum = rand.nextInt(n);
                Node randNode = nodes.get(randNum);
                ArrayList<Node> randNodeAdjList = randNode.getAdjList();
                if (!nodeAdjList.contains(randNode) && node.getID() != randNode.getID())
                {
                    nodeAdjList.add(randNode);
                    randNodeAdjList.add(node);
                }
            }
        }
    }

    /**
     * Prints the graph using adjacency list representation.
     */
    public void printGraph()
    {
        for (Node n : nodes)
        {
            System.out.println("Node: " + n.getID());
            System.out.println("Adjacent Nodes: ");
            for (Node x : n.getAdjList())
            {
                System.out.println(x.getID());
            }
        }
    }

    /**
     * Divides graph into subgraphs of size 'groupSize'
     * 
     * @param groupSize
     *            size of the subgraphs
     * @return ArrayList containing the subgraphs
     */
    public ArrayList<ArrayList<Node>> divideGraph(int groupSize)
    {
        ArrayList<Node> helper = new ArrayList<>(nodes);
        Random rand = new Random();
        ArrayList<ArrayList<Node>> dividedGraphs = new ArrayList<>();
        if (numNodes >= groupSize)
        {
            double numGroups = numNodes / groupSize + 1;
            for (int i = 1; i <= numGroups; i++)
            {
                ArrayList<Node> subGraph = new ArrayList<>();
                while (subGraph.size() != groupSize && helper.size() != 0)
                {
                    int size = helper.size();
                    int random = rand.nextInt(size);
                    if (!subGraph.contains(helper.get(random)))
                    {
                        subGraph.add(helper.get(random));
                        helper.remove(random);
                    }
                }
                if (!subGraph.isEmpty())
                {
                    dividedGraphs.add(subGraph);
                }
            }
            return dividedGraphs;
        } else
        {
            return null;
        }
    }

    /**
     * Prints the subgraphs.
     * 
     * @param subGraphs
     *            the subGraphs to be printed.
     */
    public static void printSubGraphs(ArrayList<ArrayList<Node>> subGraphs)
    {
        int i = 1;
        for (ArrayList<Node> list : subGraphs)
        {
            System.out.println("Subgraph: " + i);
            for (Node node : list)
            {
                System.out.println("Node: " + node.getID());
            }
            i++;
        }
    }

    /**
     * Checks for cliques in each subgraph.
     * 
     * @param subGraphs
     */
    public static void isClique(ArrayList<ArrayList<Node>> subGraphs)
    {
        for (ArrayList<Node> subGraph : subGraphs)
        {
            System.out.println(isSubGraphClique(subGraph));
        }
    }

    /**
     * Checks if the subGraph is a clique.
     * 
     * @param subGraph
     *            the subgraph to check.
     * @return true or false
     */
    public static boolean isSubGraphClique(ArrayList<Node> subGraph)
    {
        boolean isClique = true;
        ArrayList<Node> clique = new ArrayList<>();
        for (Node n : subGraph)
        {
            clique.add(n);
            for (Node cliqueMembers : clique)
            {
                if (cliqueMembers.equals(n))
                {
                } else if (!cliqueMembers.getAdjList().contains(n))
                {
                    isClique = false;
                }
            }
        }
        return isClique;
    }

    /**
     * gets the nodes of graph.
     * 
     * @return Arraylist of nodes.
     */
    public ArrayList<Node> getNodes()
    {
        return nodes;
    }

    public static void main(String[] args)
    {
        UndirectedGraph test = new UndirectedGraph();
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        ArrayList<Node> oneList = new ArrayList<>(Arrays.asList(four, six, seven, two));
        ArrayList<Node> twoList = new ArrayList<>(Arrays.asList(seven, eight, four, six, one));
        ArrayList<Node> threeList = new ArrayList<>(Arrays.asList(five, six));
        ArrayList<Node> fourList = new ArrayList<>(Arrays.asList(one, six, seven, five, two));
        ArrayList<Node> fiveList = new ArrayList<>(Arrays.asList(four, three));
        ArrayList<Node> sixList = new ArrayList<>(Arrays.asList(one, four, three, seven, two));
        ArrayList<Node> sevenList = new ArrayList<>(Arrays.asList(two, four, six));
        ArrayList<Node> eightList = new ArrayList<>(Arrays.asList(two));
        one.setAdjList(oneList);
        two.setAdjList(twoList);
        three.setAdjList(threeList);
        four.setAdjList(fourList);
        five.setAdjList(fiveList);
        six.setAdjList(sixList);
        seven.setAdjList(sevenList);
        eight.setAdjList(eightList);
        test.addNode(one);
        test.addNode(two);
        test.addNode(three);
        test.addNode(four);
        test.addNode(five);
        test.addNode(six);
        test.addNode(seven);
        test.addNode(eight);
        ArrayList<ArrayList<Node>> testDivide = new ArrayList<>();
        ArrayList<Node> list1 = new ArrayList<>(Arrays.asList(one, four, six, seven, two));
        ArrayList<Node> list2 = new ArrayList<>(Arrays.asList(five, eight, three));
        testDivide.add(list1);
        testDivide.add(list2);
        printSubGraphs(testDivide);
        isClique(testDivide);
    }
}

/**
 * Node class
 * 
 * @author tylerlaskey
 *
 */
class Node
{
    private int ID;
    private ArrayList<Node> adjList;

    public Node(int x)
    {
        this.ID = x;
        adjList = new ArrayList<>();
    }

    public int getID()
    {
        return ID;
    }

    public ArrayList<Node> getAdjList()
    {
        return adjList;
    }

    public void setAdjList(ArrayList<Node> adj)
    {
        this.adjList = adj;
    }

    public int nodeDegree()
    {
        return adjList.size();
    }
}
