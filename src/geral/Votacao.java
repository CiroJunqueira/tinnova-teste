package geral;

public class Votacao {

	private double eleitores;
	private double votosValidos;
	private double nulos;
	private double brancos;

	public Votacao() {
		super();
	}

	public Votacao(int eleitores, int votosValidos, int nulos, int brancos) {
		super();
		this.eleitores = eleitores;
		this.votosValidos = votosValidos;
		this.nulos = nulos;
		this.brancos = brancos;
	}

	public double PercentualVotosValidos() {
		return (votosValidos / eleitores) * 100;
	}

	public double PercentualVotosNulos() {
		return (nulos / eleitores) * 100;
	}

	public double PercentualVotosBrancos() {
		return (brancos / eleitores) * 100;
	}

	public double getEleitores() {
		return eleitores;
	}

	public void setEleitores(int eleitores) {
		this.eleitores = eleitores;
	}

	public double getVotosValidos() {
		return votosValidos;
	}

	public void setVotosValidos(int votos) {
		this.votosValidos = votos;
	}

	public double getNulos() {
		return nulos;
	}

	public void setNulos(int nulos) {
		this.nulos = nulos;
	}

	public double getBrancos() {
		return brancos;
	}

	public void setBrancos(int brancos) {
		this.brancos = brancos;
	}

}
