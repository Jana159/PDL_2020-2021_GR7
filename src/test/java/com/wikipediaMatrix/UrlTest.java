package com.wikipediaMatrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.wikipediaMatrix.exception.ArticleInexistantException;
import com.wikipediaMatrix.exception.UrlInvalideException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
public class UrlTest {

	/**
	 * Renvoie une exception si la langue n'est pas geree par notre application
	 * @throws UrlInvalideException si l'url est invalide
	 * @throws MalformedURLException si l'url n'est pas correcte
	 */
	@Test()
	public void detectionLanguesNonGeree() throws UrlInvalideException, MalformedURLException {
		Url url = new Url(new URL("https://fy.wikipedia.org/wiki/Espagne"));
		url.estUrlValide();
		assertTrue(!url.estPageWikipedia()); /*les langues gerees par nos pages wikipedia sont uniquement fr ou en */
	}
	
	/**
	 * Verifie qu'il s'agit d'une URL vers une page wikipedia, renvoie une exception sinon
	 * @throws UrlInvalideException si l'url est invalide
	 * @throws MalformedURLException si l'url n'est pas correcte
	 */
	@Test()
	public void pageNonWikipedia() throws UrlInvalideException, MalformedURLException {
		Url url = new Url(new URL("https://www.google.com"));
		url.estUrlValide();
		assertTrue(!url.estPageWikipedia()); /* verifie que la page n'est pas wikipedia */
	}

	/**
	 * La methode renvoie false si le titre est invalide
	 * @throws UrlInvalideException si l'url est invalide
	 * @throws MalformedURLException si l'url est incorrecte
	 */
	@Test
	public void titreInexistant() throws UrlInvalideException, MalformedURLException {
		Url url = new Url(new URL("https://fr.wikipedia.org/wiki/"));
		assertFalse(url.estUrlValide());
	}

	/**
	 * Verifie le titre recupere via l'URL
	 * @throws MalformedURLException si l'url est incorrecte
	 * @throws UrlInvalideException si l'url est invalide
	 */
	@Test
	public void titreUrlEgalEspagne() throws MalformedURLException, UrlInvalideException  {
		Url url = new Url(new URL("https://fr.wikipedia.org/wiki/Espagne"));
		url.estUrlValide();
		assertTrue(url.getTitre().equals("Espagne"));
	}

	/**
	 * La methode doit renvoyer l'exception specifiee en cas de langue inexistante
	 * @throws UrlInvalideException si l'url est invalide
	 * @throws MalformedURLException si l'url est incorrecte
	 */
	@Test()
	public void detectionLlanguesIinexistante() throws UrlInvalideException, MalformedURLException {
		Url url = new Url(new URL("https://.wikipedia.org/wiki/Espagne"));
		url.estUrlValide();
		assertTrue(!url.estPageWikipedia()); /*les langues gerees par nos pages wikipedia sont uniquement fr ou en */
	}
	
	/**
	 * La methode doit renvoyer false si la langue est invalide
	 * @throws UrlInvalideException si l'url est invalide
	 * @throws MalformedURLException si l'url est incorrecte
	 */
	@Test
	public void titreLangueIncomplet() throws UrlInvalideException, MalformedURLException {
		Url url = new Url(new URL("https://.wikipedia.org/wiki/"));
		assertFalse(url.estUrlValide());
	}
	
	/**
	 * Verfie la validite des 336 URLs fournies
	 * @throws UrlInvalideException si l'url est invalide
	 * @throws IOException si l'url est incorrecte
	 */
	@Test
	public void verification336UrlsValides() throws UrlInvalideException, IOException   {
		String BASE_WIKIPEDIA_URL = "output/large_url_test.txt";
		BufferedReader br = new BufferedReader(new FileReader(BASE_WIKIPEDIA_URL));
	    String url;
	    int nurl = 0;
	    while ((url = br.readLine()) != null) {
	    	Url WikiUrl = new Url(new URL(url));
		    WikiUrl.estUrlValide();
		    nurl++;
	    }
	    br.close();
	    assertEquals(336, nurl);
	}
	
	/**
	 * Methode qui test la connexion pour les 336 URLs fournies
	 * @throws UrlInvalideException si l'url est invalide
	 * @throws IOException si l'url est incorrecte
	 */
	@Test
	//@Ignore
	public void testerConnexion336Urls() throws  IOException {
		String BASE_WIKIPEDIA_URL = "output/url_file.txt";
		BufferedReader br = new BufferedReader(new FileReader(BASE_WIKIPEDIA_URL));
	    String url;
	    int articleExistant = 0, articleInexistant = 0;
	    while ((url = br.readLine()) != null) {
	    	Url WikiUrl = new Url(new URL(url));
	    	try {
				WikiUrl.testerConnexionHTTP();
		    	articleExistant++;
			} catch (ArticleInexistantException e) {
				articleInexistant++;
				e.printStackTrace();
			}
	    }
	    br.close();
	    assertEquals(1, articleInexistant);
	    assertEquals(1, articleExistant);
	    log.info("URLs sans article : " + articleInexistant);
	}

	@Test
	public void testerConnexionHTTP(){

	}
}
