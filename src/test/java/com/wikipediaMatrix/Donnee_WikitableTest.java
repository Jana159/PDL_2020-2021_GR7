package com.wikipediaMatrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.wikipediaMatrix.exception.ExtractionInvalideException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author Groupe 5
 *
 */

@Getter
@Setter
@Slf4j
public class Donnee_WikitableTest {

	/**
	 * La methode pageComporteTableau doit renvoyer true si la page contient au moins un tableau
	 * @throws ExtractionInvalideException
	 */
	@Test
	public void pageComporteTableau() throws ExtractionInvalideException {
		// On instancie un objet de la classe Donnee_Wikitable
		Donnee_Wikitable donnee_WikitextTest  = new Donnee_Wikitable();
		donnee_WikitextTest.setWikitable("{| class=\"wikitable\" |}");
		assertTrue(donnee_WikitextTest.pageComporteTableau());
	}
	
	/**
	 * La methode pageComporteTableau doit renvoyer false si la page ne contient pas de tableau
	 * @throws ExtractionInvalideException
	 */
	@Test
	public void pageNeComportePasTableau() throws ExtractionInvalideException {
		Donnee_Wikitable donnee_WikitextTest = new Donnee_Wikitable(); 
		donnee_WikitextTest.setWikitable("");
		assertFalse(donnee_WikitextTest.pageComporteTableau());
	}
	
	/**
	 * La methode supprDonneesInutiles doit supprimer toutes les donnees qui ne sont pas utiles pour la suite
	 * @throws ExtractionInvalideException
	 */
	@Test
	//@Ignore
	public void supprDonneesInutiles() throws ExtractionInvalideException {
		Donnee_Wikitable donnee_WikitextTest = new Donnee_Wikitable();
		String test = donnee_WikitextTest.wikitableReplace("scope=col;style=\"text-align:center\"align=\"center\"|&nbsp;<br /></center>|-/");
		assertEquals(test, ",| | -/");
	}
	
	/**
	 * La methode supprEspacesInutiles doit supprimer tous les espaces qui ne sont pas utiles pour la suite
	 * @throws ExtractionInvalideException
	 */
	@Test
	public void supprEspacesInutiles() throws ExtractionInvalideException {
		Donnee_Wikitable donnee_WikitextTest = new Donnee_Wikitable();
		String test = donnee_WikitextTest.supprimerEspaceDebut("   wikitexte");
		assertEquals(test, "wikitexte");
	}

	@Test
	public void getNbTableaux() throws ExtractionInvalideException {
		Donnee_Wikitable donnee_Wikitext = new Donnee_Wikitable();
		donnee_Wikitext.setWikitable("");
		donnee_Wikitext.pageComporteTableau();
		int wikitables = donnee_Wikitext.getNbTableaux();
		assertEquals(0, wikitables);
	}

	@Test
	public void getNbTableaux2() throws ExtractionInvalideException {
		Donnee_Wikitable donnee_Wikitext = new Donnee_Wikitable();
		donnee_Wikitext.setWikitable("{| class=\"wikitable\" |}");
		donnee_Wikitext.pageComporteTableau();
		int wikitables = donnee_Wikitext.getNbTableaux();
		assertEquals(1, wikitables);
	}
}
