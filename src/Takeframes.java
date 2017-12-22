
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Takeframes{
	private int basis;
	private String framesSourceName;
	ArrayList<Integer> xyzStartLines;

	public Takeframes(int basis, String framesSourceName, ArrayList<Integer> xyzStartLines) {
		super();
		this.basis = basis;
		this.framesSourceName = framesSourceName;
		this.xyzStartLines = xyzStartLines;
	}
	
	public ArrayList<Molecule> returnFrames(ArrayList<Integer> framesToTake) throws IOException {
		ArrayList<Molecule> framesToWork = new ArrayList<Molecule>();
		for (int i = 0; i < framesToTake.size(); i++) {
			Molecule frame = this.takeframe(framesToTake.get(i));
			framesToWork.add(frame);
		}
		return framesToWork;
		
	}
	private Molecule takeframe(int frameNumber) throws IOException {
		Molecule molecule = new Molecule();
		int startValue = this.xyzStartLines.get(frameNumber-1);
		int endValue = startValue +  this.basis;
		InputStream is = new FileInputStream(this.framesSourceName);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String linha; 
		int numLine = 1;
		while((linha = br.readLine()) != null) {
			if (numLine >= startValue && numLine < endValue) {
				String [] linhaS = linha.split("\\s++");
				String atomType = linhaS[1];
				float xPos = Float.parseFloat(linhaS[2]);
				float yPos = Float.parseFloat(linhaS[3]);
				float zPos = Float.parseFloat(linhaS[4]);
				Atom atom = new Atom(atomType, xPos, yPos, zPos);
				molecule.addAtom(atom);
			}
			numLine ++;
		}
		return molecule;
	}


}