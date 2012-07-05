package mradjudicator;

/**
 * Program: Mr. Adjudicator
 * File name: GUI.java
 * Purpose: This is a custom table model to view a summary of Debate objects.
 * It is an extension of the AbstractTableModel.  The class allows for
 * easy searching of Debate files by motion and winning school.
 * @author Justin Chan
 * Creation date: February 2012
 * Version: 1.0
 * Candidate number: 000637-008
 * School: Chinese International School 000637
 * IDE used: Netbeans 7.0.1
 * Computer used: Personal Macbook running OSX 10.6.8
 * @author Justin Chan
 * @version 1.0
 */

import java.io.IOException;
import javax.swing.table.AbstractTableModel;

public class DebateTableModel extends AbstractTableModel 
{
    private Persister persister;
    private String[] columnNames = {"Date", "Motion","Winning school"};
    //contains just the debate sessions relevant to the user's 
    //search query
    private LinkedList<Debate> currentList;
    //contains every debate session in the text file
    private LinkedList<Debate> masterList; 
    
    /**
     * Constructor sets up the JTable model
     * @throws IOException 
     */
    public DebateTableModel() throws IOException
    {
        persister = new Persister();
        retrieveMasterList();
    }
    
    /**
     * Retrieves all the debate records from the text file again.
     * Used to refresh table when records have been edited, 
     * added or deleted.
     * @throws IOException 
     */
    public void retrieveMasterList() throws IOException
    {
        masterList = currentList = persister.readFromFile();
        fireTableDataChanged();
    }
    
    /**
     * Searches for a particular string in the motions and winning schools
     * of all past debates.
     * @param searchString 
     */
    public void search(String searchString)
    {
        LinkedList<Debate>newList = new LinkedList<Debate>();
        Node node = masterList.getFirst();
        while(node.getNextNode() != null)
        {
            if(((Debate)node.getData()).getMotion().toLowerCase().
               contains(searchString.toLowerCase()) ||
               ((Debate)node.getData()).getWinningSchool().toLowerCase().
               contains(searchString.toLowerCase()))
            {
                newList.insertTail(((Debate)node.getData()));
            }
            node = node.getNextNode();
        }
        currentList = newList;
        fireTableDataChanged();
    }
    
    /**
     * Called after the user has completed a search and empties the
     * search query box.  Shows every debate session on file.
     */
    public void restoreMasterList()
    {
        currentList = masterList;
        fireTableDataChanged();
    }
    
    /**
     * Returns the number of rows within the table.
     * @return The number of rows within the table.
     */
    public int getRowCount()
    {
        return currentList.size();
    }
    
    /**
     * Returns the number of columns within the table.
     * @return The number of columns within the table.
     */
    public int getColumnCount()
    {
        return columnNames.length;
    }
    
    /**
     * Returns the name of a particular column.
     * 
     * @param columnNumber The index of the column whose name you want returned.
     * @return The name of a column specified by an index.
     */
    public String getColumnName(int columnNumber)
    {
        return columnNames[columnNumber];
    }
    
    /**
     * Returns the object at a particular cell of the DebateTableModel,
     * internally used to populate the table.
     * 
     * @param row The row of a particular cell in the table, whose value you
     * want to retrieve
     * @param column The column of a particular cell in the table, whose
     * value you want to retrieve.
     * @return The object at a particular cell in the table.  The cell
     * is determined by the parameters.
     */
    public Object getValueAt(int row, int column)
    {
        Debate debate = currentList.getNode(row);
        if(column == 0)
        {
            return debate.getDate();
        }
        else if(column == 1)
        {
            return debate.getMotion();
        }
        else
        {
            return debate.getWinningSchool();
        }
    }
}