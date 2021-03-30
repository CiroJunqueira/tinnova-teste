package geral;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TesteVotacao {
	
	@Test
	public void testeVotacao() {
		Votacao v = new Votacao();
		v.setEleitores(1000);
		v.setVotosValidos(800);
		v.setBrancos(150);
		v.setNulos(50);
		
		assertEquals(v.PercentualVotosValidos(), 80);
		assertEquals(v.PercentualVotosNulos(), 5);
		assertEquals(v.PercentualVotosBrancos(), 15);
		
	}

}
