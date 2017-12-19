import java.util.List;

public class Molecule {
	private List<Atom> molecule;
	
	public Molecule (List<Atom> molecule){
		this.molecule = molecule;
	}
	
	public List<Atom> getMolecules(){
		return this.molecule;
	}
	
}
