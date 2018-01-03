import java.util.LinkedList;
import java.util.List;

public class Molecule {
	private List<Atom> molecule = new LinkedList<Atom>();
	
	public void addAtom(Atom atom) {
		this.molecule.add(atom);
	}
	
	public List<Atom> getMolecules(){
		return this.molecule;
	}
	
	public void modifyAtomPOS(int targetAtom, float xPos, float yPos, float zPos) {
		Atom target = this.molecule.get(targetAtom);
		target.setxPos(xPos);
		target.setyPos(yPos);
		target.setzPos(zPos);
	}
	
	public Atom getAtom(int targetAtom) {
		return this.molecule.get(targetAtom);
	}
	
	public int length(){
		return this.molecule.size();
	}
	
	@Override
	public String toString() {
		String toReturn = "Molecule:\n";
		for (Atom atom : molecule) {
			toReturn = toReturn + atom + "\n";
		}
		return toReturn;
	}
	
	public String toStringSimple() {
		String toReturn = "";
		for (Atom atom : molecule) {
			toReturn = toReturn + atom + "\n";
		}
		return toReturn;
	}

}
