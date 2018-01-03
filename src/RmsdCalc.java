public class RmsdCalc {
	Molecule molecule1;
	Molecule molecule2;
	double rmsd;
	
	public RmsdCalc(Molecule molecule1, Molecule molecule2) {
		super();
		this.molecule1 = molecule1;
		this.molecule2 = molecule2;

	}
	
	public double returnRMSDmol() {
		double rmsd;
		double sum = 0;
		for (int i = 0; i < this.molecule1.length(); i++) {
			Atom atomMolecule1 = this.molecule1.getAtom(i);
			Atom atomMolecule2 = this.molecule2.getAtom(i);
			double x = atomMolecule1.getxPos() - atomMolecule2.getxPos();
			double y = atomMolecule1.getyPos() - atomMolecule2.getyPos();
			double z = atomMolecule1.getzPos() - atomMolecule2.getzPos();
			sum = sum + ((x*x)+(y*y)+(z*z));
		}
		sum = sum /this.molecule1.length();
		rmsd = Math.sqrt(sum);
		return rmsd;
	}

	public double returnRMSDatom(int i) {
		Atom atomMolecule1 = this.molecule1.getAtom(i);
		Atom atomMolecule2 = this.molecule2.getAtom(i);
		double x = atomMolecule1.getxPos() - atomMolecule2.getxPos();
		double y = atomMolecule1.getyPos() - atomMolecule2.getyPos();
		float z = atomMolecule1.getzPos() - atomMolecule2.getzPos();
		double sum = ((x*x)+(y*y)+(z*z));
		System.out.println(Math.sqrt(sum));
		this.rmsd = Math.sqrt(sum);
		return this.rmsd;
	}
	
	@Override
	public String toString() {
		return String.format("%.10e", this.returnRMSDmol());
	}

}
