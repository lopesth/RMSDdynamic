package RMSDdynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Takeframes{
	ArrayList<Integer> frames = new ArrayList<Integer>();
	private String trajectName;
	private int basis;

	public Takeframes(ArrayList<Integer> frames, String trajectName, int basis) {
		super();
		this.frames = frames;
		this.trajectName = trajectName;
		this.basis = basis;
	}

	public ArrayList<Integer> takeFrameLines() throws IOException {
		ArrayList<Integer> startLines = new ArrayList<Integer>();
		InputStream is = new FileInputStream(this.trajectName);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String linha; 
		int numLine = 1;
		while((linha = br.readLine()) != null) {
			String [] linhaS = linha.split("\\s++");
			if (linhaS.length < 2) {
				numLine ++;
				continue;
			}
			if (linhaS[1].equals(Integer.toString(basis))){
				startLines.add(numLine+2);
			}
			numLine ++;
		}
		br.close();
		is.close();
		isr.close();
		return startLines;
	}
}