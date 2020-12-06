package MinMaxAlgorithms;

import java.util.Stack;

public class Node {
    public int number;
    public Stack<Integer> st;

    public Node(int a) {
        this.number=a;
        this.st=new Stack<Integer>();
    }
}