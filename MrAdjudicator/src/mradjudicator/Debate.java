package mradjudicator;

/**
 * Program: Mr. Adjudicator
 * File name: GUI.java
 * Purpose: The Debate class is technically speaking a container class which 
 * allows for easy manipulation of data pertaining to a debate.  All fields 
 * have accessor and mutator methods.  There is also a method that returns the
 * user-readable String representation of the class and a method to generate
 * a character-delimited representation of the class that can be written to
 * the disk and easily parsed on another occasion.
 * 
 * This is a hierarchal composite data structure.  The composite data structure
 * in question is the 2D array which the program stores the attributes
 * of a Debater.
 * 
 * The Debate class has a record-like structure which is
 * reflected in run-time memory and on a text file on disk.
 * Creation date: February 2012
 * Version: 1.0
 * Candidate number: 000637-008
 * School: Chinese International School 000637
 * IDE used: Netbeans 7.0.1
 * Computer used: Personal Macbook running OSX 10.6.8
 * @author Justin Chan
 * @version 1.0
 */
public class Debate 
{
    //fields reflecting the real life properties of a Debate
    private String date;
    private String motion;
    private int numberOfDebaters;
    private String[][] debatersAndScores;
    private String winningSchool;
    
    /**
     * Constructor initializes all fields to default "empty" values.
     */
    public Debate()
    {
        date = "";
        motion = "";
        numberOfDebaters = 0;
        debatersAndScores = new String[0][0];
        winningSchool = "";
    }
    
    /**
     * Returns the date that a given debate took place on.
     * @return The date that a given debate took place on.
     */
    public String getDate() 
    {
        return date;
    }

    /**
     * Sets the date of a debate.
     * @param date The date of a debate.
     */
    public void setDate(String date) 
    {
        this.date = date;
    }

    /**
     * Returns the motion being debated on.
     * @return The motion being debated on.
     */
    public String getMotion() 
    {
        return motion;
    }

    /**
     * Sets the motion being debated on.
     * @param motion The motion being debated on.
     */
    public void setMotion(String motion) 
    {
        this.motion = motion;
    }

    /**
     * Returns the number of debaters taking part in the debate.
     * @return The number of debaters taking part in the debate.
     */
    public int getNumberOfDebaters() 
    {
        return numberOfDebaters;
    }

    /**
     * Sets the number of debaters taking part in the debate.
     * @param numberOfDebaters The number of debaters taking part in the debate.
     */
    public void setNumberOfDebaters(int numberOfDebaters) 
    {
        this.numberOfDebaters = numberOfDebaters;
    }

    /**
     * Returns the array containing Debater objects.
     * @return The array containing Debater objects.
     */
    public String[][] getDebaterAndScoreArray() 
    {
        return debatersAndScores;
    }

    /**
     * Sets the array containing Debater objects.
     * @param debaterArray The array containing Debater objects.
     */
    public void setDebaterAndScoreArray(String[][] debaterArray) 
    {
        this.debatersAndScores = debaterArray;
    }

    /**
     * Returns the name of the school that won the debate.
     * @return The name of the school that won the debate.
     */
    public String getWinningSchool() 
    {
        return winningSchool;
    }

    /**
     * Sets the name of the school that won the debate.
     * @param winningSchool The name of the school that won the debate.
     */
    public void setWinningSchool(String winningSchool) 
    {
        this.winningSchool = winningSchool;
    }
    
    /**
     * This method returns the format which data within the Debate object
     * is stored on disk.  Each field is delimited by a caret (^) character.
     * @return The internal representation of the data within the Debate
     * object on disk.
     */
    public String getFileString()
    {
        String output = getDate() + "^" + getMotion() + "^" +
                getNumberOfDebaters() + "^";
        for(int i = 0; i < getDebaterAndScoreArray().length; i++)
        {
            for(int j = 0; j < getDebaterAndScoreArray()[i].length; j++)
            {
                output += getDebaterAndScoreArray()[i][j] + "^";
            }
        }
        output += getWinningSchool();
        return output;
    }
    
    /**
     * Returns a human-readable String representation of data contained in the
     * Debate object.
     * @return A human-readable String representation of data contained in the
     * Debate object.
     */
    public String toString()
    {
        String output = getDate() + " " + getMotion() + "\n";
        for(int i = 0; i < getDebaterAndScoreArray().length; i++)
        {
            for(int j = 0; j < getDebaterAndScoreArray()[i].length; j++)
            {
                output += getDebaterAndScoreArray()[i][j] + " ";
            }
        }
        output += "\n" + getWinningSchool();
        return output;
    }
    
    /**
     * This method returns the index number on the JComboBox that corresponds
     * to a specific position.
     * 
     * For example 1st Prop is the first position listed on JComboBox in
     * the GUI class, and it's index is 0.
     * @return The corresponding index number of a particular speaking position.
     */
    public int getSpeakerNumber(String position)
    {
        if(position.equals("1st Prop"))
        {
            return 0;
        }
        else if(position.equals("1st Opp"))
        {
            return 1;
        }
        else if(position.equals("2nd Prop"))
        {
            return 2;
        }
        else if(position.equals("2nd Opp"))
        {
            return 3;
        }
        else if(position.equals("3rd Prop"))
        {
            return 4;
        }
        else if(position.equals("3rd Opp"))
        {
            return 5;
        }
        //reply speech's start from 0 again because for pane 7 and 8 in the 
        //viewing and scoring GUI, only these 2 options are available for 
        //selection - not any of 1st, 2nd or 3rd speaker roles.
        else if(position.equals("Opp Reply"))
        {
            return 0;
        }
        else if(position.equals("Prop Reply"))
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
