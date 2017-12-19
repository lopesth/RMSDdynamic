package RMSDdynamic;

import java.io.IOException;
import java.util.ArrayList;

public class RMSDdynamic {
	public static void main(String [] args) throws IOException {
		ArrayList<Integer> frames = new ArrayList<Integer>();
		frames.add(1);
		frames.add(2);
		FindMol x = new FindMol("/Users/thiagolopes/Desktop/vmdepin.xyz", 26);
		ArrayList<Integer> y = x.takeFrameLines();
		System.out.println(y);
	}

}
