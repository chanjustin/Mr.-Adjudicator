package mradjudicator;

/**
 * Program: Mr. Adjudicator
 * File name: GUI.java
 * Purpose: 
 * The persister class handles the reading, writing and appending of Debate
 * objects to disk.  
 * Creation date: February 2012
 * Version: 1.0
 * Candidate number: 000637-008
 * School: Chinese International School 000637
 * IDE used: Netbeans 7.0.1
 * Computer used: Personal Macbook running OSX 10.6.8
 * @author Justin Chan
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Persister 
{
    //a constant file path to the data store which is referred to
    //throughout the class.
    private static final String dataStoreFilePath = 
            "src/mradjudicator/files/debatefile.txt";
    
    /**
     * Empty constructor.
     */
    public Persister()
    {
        
    }
    
    /**
     * Write an entire linked list of debate objects to file.
     * Used when editing old debates or deleting debates.
     * @param list The linked list of Debaters to be persisted to disk.
     * @throws IOException 
     */
    public void write(LinkedList<Debate> list) throws IOException
    {
        PrintWriter output = new PrintWriter(
                             new FileWriter(dataStoreFilePath,false));
        Node node = list.getFirst();
        while(node.getNextNode() != null)
        {
            output.println(((Debate)node.getData()).getFileString());
            node = node.getNextNode();
        }
        output.flush();
    }
    
    /**
     * Append a single debate object to the end of the text file.
     * Used when scoring a single debate.
     * @param debate The debate file to be appended to the end of the text file.
     * @throws IOException 
     */
    public void appendToFile(Debate debate) throws IOException
    {
        PrintWriter output = new PrintWriter(
                             new FileWriter(dataStoreFilePath,true));
        output.println(debate.getFileString());
        output.flush();
    }
    
    /**
     * Reads the entire text file to memory and parses them into the
     * appropriate Debate and Debater files which are then inserted
     * into a linked list.
     * @return A linked list of all debate objects in the text file.
     * @throws IOException 
     */
    public LinkedList<Debate> readFromFile() throws IOException
    {
        LinkedList<Debate> debateList = new LinkedList<Debate>();

        Scanner input = new Scanner(
                        new BufferedReader(
                        new FileReader(dataStoreFilePath)));
        input.useDelimiter("[\n^\r]");

        while(input.hasNext())
        {
            Debate debate = new Debate();
            String date = input.next();
            String motion = input.next();

            debate.setDate(date);
            debate.setMotion(motion);

            int numberOfDebaters = input.nextInt();
            String[][] debaterAndScoreArray = new String[numberOfDebaters][12];
            debate.setNumberOfDebaters(numberOfDebaters);
            for(int i = 0; i < numberOfDebaters; i++)
            {
                String firstName = input.next();
                String lastName = input.next();
                String age = input.next();
                String schoolName = input.next();
                String position = input.next();
                String contentScore = input.next();
                String styleScore = input.next();
                String organizationScore = input.next();
                String poiScore = input.next();
                String contentComments = input.next();
                String styleComments = input.next();
                String organizationComments = input.next();
                
                debaterAndScoreArray[i][0] = firstName;
                debaterAndScoreArray[i][1] = lastName;
                debaterAndScoreArray[i][2] = age;
                debaterAndScoreArray[i][3] = schoolName;
                debaterAndScoreArray[i][4] = position;
                debaterAndScoreArray[i][5] = contentScore;
                debaterAndScoreArray[i][6] = styleScore;
                debaterAndScoreArray[i][7] = organizationScore;
                debaterAndScoreArray[i][8] = poiScore;
                debaterAndScoreArray[i][9] = contentComments;
                debaterAndScoreArray[i][10] = styleComments;
                debaterAndScoreArray[i][11] = organizationComments;
            }
            debate.setDebaterAndScoreArray(debaterAndScoreArray);
            String winningSchool = input.next();
            debate.setWinningSchool(winningSchool);
            debateList.insertTail(debate);
        }
        return debateList;
    }
    
    /**
     * Used for creating a new text file for all debates assuming
     * the user is running the program for the first time or deleted
     * the text file previously.
     * @throws IOException 
     */
    public void createFile() throws IOException
    {
        File f;
        f=new File(dataStoreFilePath);
        if(!f.exists())
        {
            f.createNewFile();
        }
    }
}
