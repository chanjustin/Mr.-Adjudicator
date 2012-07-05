package mradjudicator;

/**
 * Program: Mr. Adjudicator
 * File name: GUI.java
 * Purpose: 
 * This is a generic Node class that can store any type of data.  It is used as
 * a helper class as the linked list manipulates data through Node objects.
 * This Node is used for a singly linked list and only holds a reference
 * to the next node.  
 * Creation date: February 2012
 * Version: 1.0
 * Candidate number: 000637-008
 * School: Chinese International School 000637
 * IDE used: Netbeans 7.0.1
 * Computer used: Personal Macbook running OSX 10.6.8
 * @author Justin Chan
 * @version 1.0
 */
public class Node<E> 
{
    //fields: the data that the Node holds and the Node it is referring to
    private E data;
    private Node nextNode;

    /**
     * Constructor initializes all fields to default "empty" values.
     */
    public Node()
    {
        data = null;
        nextNode = null;
    }
    
    /**
     * Constructor to instantiate the Node and set the data that it holds
     * at the same time.
     * @param data Any object whose type is the same as the objects of
     * the linked list.
     */
    public Node(E data)
    {
        setData(data);
    }

    /**
     * Sets the Node that this Node refers to.
     * @param nextNode The Node that this Node refers to.
     */
    public void setNextNode(Node nextNode)
    {
        this.nextNode = nextNode;
    }

    /**
     * Returns the next Node in the linked list
     * @return The Node object that this Node refers to.
     */
    public Node getNextNode()
    {
        if(nextNode == null)
        {
            return null;
        }
        else
        {
            return nextNode;
        }
    }
    
    /**
     * Returns the data that the Node holds
     * @return The data that the Node holds
     */
    public E getData()
    {
        return data;
    }

    /**
     * Sets the data that the Node's holds.
     * @param data Any object whose type is the same as the objects of
     * the linked list.
     */
    public void setData(E data)
    {
        this.data = data;
    }
    
    /**
     * Returns a human-readable String representation of the Node's data.
     * @return A human-readable String representation of the Node's data.
     */
    public String toString()
    {
        return data.toString();
    }
}
