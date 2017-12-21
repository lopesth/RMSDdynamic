package RMSDdynamic;

import java.io.IOException;
import java.util.ArrayList;

public class RMSDdynamic {
	public static void main(String [] args) throws IOException {
		int basis = 26;
		String framesSourceName = "/Users/thiagolopes/Desktop/vmdepin.xyz";
		FindMol xyzData = new FindMol(framesSourceName, basis);
		ArrayList<Integer> xyzStartLines = xyzData.takeFrameLines();
		Integer[] framesToTake = new Integer[] {1, 3, 6};
		ArrayList<Molecule> frames = new Takeframes(
				basis, framesSourceName, xyzStartLines
		).returnFrames(framesToTake);

	}

}
