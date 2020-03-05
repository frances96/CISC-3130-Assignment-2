import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Node {
    private Song item;
    Node next;
    
    public Node (Song item) {
	this.item = item;
	this.next = null;
    }
    
    public int compare(Node n) {
	return this.item.getTrack().compareToIgnoreCase(n.item.getTrack());
    }
	
    public Song getItem() {
	return item;
    }
}

public class MyQueue {
    Node front, rear;
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    
    //Constructor 
    public MyQueue() {
    }
  
    public MyQueue(String filename) throws FileNotFoundException {
      front = null;
      rear = null;
      ArrayList<String> array = new ArrayList<>();
      Scanner scanner = new Scanner(new File(filename));
      String track;
      scanner.nextLine();
      scanner.nextLine();
      Node temp = null;
	    while(scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            String str = line.get(1);
            if (line.get(1).charAt(0) == '"') {
		            str = line.get(1).substring(1);
            }
            track = str; //Fetch the track name
            array.add(track);		
	    }
	    Collections.sort(array, String.CASE_INSENSITIVE_ORDER);
        for(int i = 0; i<array.size(); i++) {
            Song song = new Song(array.get(i));
            temp = new Node(song);
            this.enqueue(temp);
        }
    }
	
    public Node peek() {
	    return this.front;
    }
	
    public Node dequeue() {
      if (this.isEmpty()) 
        return null; 
        Node temp = this.front; 
        this.front = this.front.next;
        if (this.front == null) 
        this.rear = null; 
        return temp;
    }
    
    public void enqueue(Node node) {
	    if (this.rear == null) { 
            this.front = this.rear = node; 
            return; 
        }
	      this.rear.next = node; 
        this.rear = node; 
    }
	
    public boolean isEmpty() {
	    if (this.front == null)
            return true;
            return false;
    }
    
    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }
	
    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {
        List<String> result = new ArrayList<>();
        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }
        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }
        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } 
                else {
                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } 
                    else {
                        curVal.append(ch);
                    }
                }
            } 
            else {
                if (ch == customQuote) {
                    inQuotes = true;
                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }
                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } 
                else if (ch == separators) {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    startCollectChar = false;
                } 
                else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } 
                else if (ch == '\n') {
                    //the end, break!
                    break;
                } 
                else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }
}