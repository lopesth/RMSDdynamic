import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class ExtractSolvent {
	
	public static void main(String[] args) throws IOException {
		int basis = takeBasis();
		System.out.println(basis);
		ArrayList<Integer> pos = takePos();
		String framesSourceName = "/Users/thiagolopes/Desktop/TRAJEC.xyz";
		ArrayList<Integer> xyzStartLines = new FindMol(framesSourceName, basis).takeFrameLines();
		ArrayList<Molecule> solutes = removeSolute(pos, xyzStartLines, framesSourceName, basis);
		makeTraject(solutes, "/Users/thiagolopes/Desktop/traject_withou_solute.xyz", basis);
	}
	
	private static int takeBasis() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of atoms in the dynamics box:");
		String resp = sc.nextLine().replaceAll("[^\\d,]", "");
		int basis = Integer.parseInt(resp);
		return basis;
	}

	private static ArrayList<Integer> takePos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the positions of the solute atoms in the box:");
		String[] resp = sc.nextLine().split("[^\\d,]");
		ArrayList<Integer> valors = new ArrayList<Integer>();
		for (String string : resp) {
			valors.add(Integer.parseInt(string.replaceAll("[,]", "")));
		}
		return valors;
	}
	
	public static ArrayList<Molecule> removeSolute(ArrayList<Integer> atons, ArrayList<Integer> startValues, String framesSourceName, int basis) throws IOException{
		ArrayList<Molecule> molecules = new ArrayList<Molecule>();			
		ArrayList<String> fileBuffer = new ArrayList<String>();
		InputStream is = new FileInputStream(framesSourceName);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String linha; 
		while((linha = br.readLine()) != null) {
			fileBuffer.add(linha);
		}
		for (Integer startValue : startValues) {
			Molecule molecule = new Molecule();
			for (Integer atom : atons) {
				Integer atomPos = atom - 2 + startValue;
				String lineC = fileBuffer.get(atomPos);
				String[] lineCsplited = lineC.split("\\s++");
				molecule.addAtom(new Atom(lineCsplited[1], Float.parseFloat(lineCsplited[2]), Float.parseFloat(lineCsplited[3]), Float.parseFloat(lineCsplited[4])));
			}
			molecules.add(molecule);
		}
		return molecules;
	}
	
	private static void makeTraject(ArrayList<Molecule> moleculesToPrint, String fileName, int basis) throws IOException {
		FileWriter writer = new FileWriter(fileName); 
		int step = 1;
		for (Molecule molecule : moleculesToPrint) {
			writer.write("         "+basis+'\n');
			writer.write(" STEP:           "+step+'\n');
			writer.write(molecule.toStringSimple());
			step = step +5;
		}
		writer.close();
	}
	
}
