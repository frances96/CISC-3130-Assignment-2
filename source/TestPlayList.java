import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestPlayList {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		char ch  = 'y';
		ArrayList<String> files = new ArrayList<String>();
		ArrayList<MyQueue> allTheWeeks = new ArrayList<MyQueue>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Playlist System");
		while (ch ==  'y' || ch == 'Y') {
			System.out.println("Please provide path to the files");
			files.add(sc.next());
			System.out.println("Do you want to provide more files");
			ch = sc.next().charAt(0);
		}
		for (int i = 0; i<files.size(); i++) {
			MyQueue data = new MyQueue(files.get(i));
			allTheWeeks.add(i, data);
		}
		PlayList playList = new PlayList();
		MyQueue mergedQueue = allTheWeeks.get(0);
		for(int i = 1; i<allTheWeeks.size()-1; i++) {
			mergedQueue = playList.mergingFunction(allTheWeeks.get(i), mergedQueue);
		}
		while (!mergedQueue.isEmpty()) {
			Node n = mergedQueue.dequeue();
			playList.addSong(n.getItem());
		}
		
		SongHistoryList history = new SongHistoryList();
		Song temp = playList.listenToSong();
		
		while (temp != null) {
			// Add the song to history as it is not anymore in playlist.
			history.addSong(temp);
			System.out.println("Listening to Song: "+temp.getTrack());
			temp = playList.listenToSong();
			
		}
	}
	
}
