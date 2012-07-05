package mradjudicator;

/**
 * Program: Mr. Adjudicator
 * File name: GUI.java
 * Purpose: 
 * This is a generic singly linked list class that can be used to hold any type
 * of Object.  Internally, the class stores Node objects which stores
 * its own data and a reference to the next node in the linked list.
 * It is implements the Iterable and Iterator interfaces,
 * this means that the contents of the class can be itereated using
 * a for-each loop.
 * 
 * This is a hierarchal composite data structure.  The composite data structure
 * in question are the Debate files which the program stores in this linked 
 * list, Debate objects have a record-like structure which are represented
 * during run-time memory and on the text file on disk.
 * 
 * This linked list is hierarchal as more than one Debate object can be
 * stored in it at any one time. 
 * Creation date: February 2012
 * Version: 1.0
 * Candidate number: 000637-008
 * School: Chinese International School 000637
 * IDE used: Netbeans 7.0.1
 * Computer used: Personal Macbook running OSX 10.6.8
 * @author Justin Chan
 * @version 1.0
 */

public class LinkedList<E>
{
    //fields for the linked list
    private Node head;
    private Node tail;
    private Node currentNode;
    private int size;

    /**
     * Constructor initializing all fields to default "empty" values.
     */
    public LinkedList()
    {
        size = 0;
        head = null;
        tail = null;
    }
    
    /**
     * Allows the user to insert any object to the head of the linked list
     * without interaction with Node objects.
     * @param data The object which is to be added to the head of the 
     * linked list, it should be the same type as all other 
     * objects in the linked list.
     */
    public void insertHead(E data)
    {
        if(head == null && tail == null)
        {
            head = new Node(data);
            tail = head;
            currentNode = head;
        }
        else
        {
            Node temp = new Node(data);
            temp.setNextNode(head);
            head = temp;
        }
    }
    
    /**
     * Allows the user to insert any object to the tail of the linked list
     * without interaction with Node objects.
     * @param data The object which is to be added to the tail of the 
     * linked list, it should be the same type as all other 
     * objects in the linked list.
     */
    public void insertTail(E data)
    {
        if(head == null && tail == null)
        {
            head = new Node(data);
            tail = head;
            currentNode = head;
        }
        else
        {
            Node tempNode = new Node(data);
            tail.setNextNode(tempNode);
            tail = tempNode;
        }
        size++;
    }

    /**
     * Removes and replaces the head of the linked list and 
     * returns the data it contains.
     * @return The data contained in the head of the linked list.
     */
    public E first()
    {
        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else
        {
            Node oldHead = head;
            Node tempNode = oldHead.getNextNode();
            currentNode = head = tempNode;
            return (E)oldHead.getData();
        }
    }

    /**
     * Removes and replaces the tail of the linked list and 
     * returns the data it contains.
     * @return The data contained in the tail of the linked list.
     */
    public E last()
    {
        if(isEmpty())
        {
            throw new NullPointerException();
        }
        else
        {
            Node tempNode = head;
            while(tempNode.getNextNode() != tail)
            {
                tempNode = tempNode.getNextNode();
            }
            tail = tempNode;
            currentNode = head;
            return (E)tail.getData();
        }
    }

    /**
     * Removes a node depending on its index within the linked list.
     * @param index The index of the node that is to be removed.
     * @return The data contained in the node that was removed.
     */
    public E remove(int index)
    {
        if(index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0)
        {
            Node temp = head;
            first();
            size--;
            currentNode = head;
            return (E)temp.getData();
        }
        else if(index == size)
        {
            Node temp = tail;
            last();
            size--;
            currentNode = head;
            return (E)temp.getData();
        }
        else
        {
            Node prevNode = head;
            for(int i = 0; i < index-1; i++)
            {
                prevNode = prevNode.getNextNode();
            }
            
            if(prevNode.getNextNode().getNextNode() != null)
            {
                prevNode.setNextNode(prevNode.getNextNode().getNextNode());
            }
            else
            {
                prevNode.setNextNode(null);
            }
            size--;
            currentNode = head;
            return (E)prevNode.getData();
        }
    }
    
    /**
     * Returns the data held in the node before the node whose data is
     * specified in the parameter.
     * @param e The data of the node which is after the node whose data is
     * returned.
     * @return The data held in the node before the node whose data is
     * specified in the parameter.
     */
    public E before(E e)
    {
        Node temp = head;
        while(temp != tail)
        {
            if(temp.getNextNode().getData().equals(e))
            {
                return (E)temp.getData();
            }
            temp = temp.getNextNode();
        }
        return null;
    }
    
    /**
     * Returns the data held in the node after the node which contains
     * the specified the data.
     * @param e The specified data of the node which is before the node
     * whose data is to be returned.
     * @return The data of node that is after the node whose data is
     * provided in the parameter.
     */
    public E after(E e)
    {
        Node temp = head;
        while(temp != tail)
        {
            if(temp.getData().equals(e))
            {
                return (E)temp.getNextNode().getData();
            }
            temp = temp.getNextNode();
        }
        return null;
    }
    
    /**
     * Returns whether the list is empty or not.
     * @return Whether the list is empty or not.
     */
    public boolean isEmpty()
    {
        return size() == 0 ? true : false;
    }
    
    /**
     * Returns the size of the list.
     * @return The size of the list.
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns the head of the list.
     * @return The head of the list.
     */
    public Node getFirst()
    {
        return head;
    }

    /**
     * Returns the tail of the list.
     * @return The tail of the list.
     */
    public Node getLast()
    {
        return tail;
    }

    /**
     * Returns but does not remove the node at a specific index within the
     * linked list.
     * @param index The index of the node that is to be retrieved. 
     * @return The data within the node that was retrieved.
     */
    public E getNode(int index)
    {
        if(index < 0 || index > size())
        {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for(int i = 0; i < index; i++)
        {
            node = node.getNextNode();
        }
        return (E)node.getData();
    }
    
    /**
     * Returns a String representation of all the nodes within the linked list.
     * @return A string representation of all the nodes within the linked list.
     */
    public String toString()
    {
        Node currentNode = head;
        String string = "";

        while(currentNode != tail)
        {
            string += currentNode;
            currentNode = currentNode.getNextNode();
        }
        string += tail;
        currentNode = head;
        return string;
    }
}