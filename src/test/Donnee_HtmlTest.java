package test;

<<<<<<< HEAD
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import classes.Donnee_Html;
import classes.Url;
import exceptions.ConvertionInvalideException;
import exceptions.ExtractionInvalideException;
import exceptions.UrlInvalideException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.Mockito.*;

public class Donnee_HtmlTest {

=======
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import classes.ComparerCSV;
import classes.Donnee;
import classes.Donnee_Html;
import classes.Donnee_Wikitable;
import classes.Url;
import exceptions.UrlInvalideException;

public class Donnee_HtmlTest {

	// On "mock" les classes qui vont être utilisées dans le test
	Donnee donneeTest = Mockito.mock(Donnee.class);
	Donnee_Html donnee_HtmlTest = Mockito.mock(Donnee_Html.class);
	Url urlTest = Mockito.mock(Url.class);
	
>>>>>>> V1
	/**
	 * Renvoie un message dans le cas ou l URL est fausse
	 */
	@Test
	void testPageNonExistante() {
		/*ExtractWikitable testExtractPageNonExistante = new ExtractWikitable();
		assertThrows(IOException.class,() -> {
			testExtractPageNonExistante.extractWikiTable("en", "render", "erreurPage");
	    });*/
	}

	/**
	 * Renvoie un message dans le cas ou la langue choisie est erronee
	 */
	@Test
	void erreurLangue() {
		/*ExtractWikitable testErreurLangue = new ExtractWikitable();
		assertThrows(UnknownHostException.class,() -> {
			testErreurLangue.extractWikiTable("erreurLangue", "render", "Wikipedia:Unusual_articles/Places_and_infrastructure");
	    });*/
	}

	/**
	 * Test ou l'url est valide
	 * Aucune exception ne doit donc etre generee
	 * @param url l'url de la page wikipedia
	 * @throws UrlInvalideException
	 * @throws IOException
	 */
	@Test
	public void extraireTest1() throws UrlInvalideException, IOException {
		Mockito.when(urlTest.estUrlValide()).thenReturn(true);
		// TODO assert ne throw pas d'exception
	}
	
	/**
	 * Test ou l'url est invalide
	 * Aucune exception ne doit donc etre generee
	 * @param url l'url de la page wikipedia
	 * @throws UrlInvalideException
	 * @throws IOException
	 * TODO corriger
	 */
	@Test
	public void extraireTest2() throws UrlInvalideException, IOException {
		Mockito.when(urlTest.estUrlValide()).thenReturn(false);
		assertThrows(UrlInvalideException.class,() -> {
			donnee_HtmlTest.extraire(urlTest);
	    });
	}
	
	/**
	 * Renvoie un message si le temps d execution depasse un temps maximal
	 * @param nbATest
	 * @throws MalformedURLException 
	 * @throws ConvertionInvalideException 
	 * @throws ExtractionInvalideException 
	 * @throws UrlInvalideException 
	 */
	@Test
	void testTempsExec(long nbATest) throws MalformedURLException, UrlInvalideException, ExtractionInvalideException, ConvertionInvalideException {
		Url monUrl = new Url(new URL("https://fr.wikipedia.org/wiki/Kevin_Bacon"));
		Donnee_Html donnees = new Donnee_Html();
		donnees.extraire(monUrl);

		assertTrue("Temps d'execution de " + nbATest/1000 + " secondes", nbATest < 24000);
	}
<<<<<<< HEAD


=======
	
>>>>>>> V1
}
