package com.wikipediaMatrix;

import com.wikipediaMatrix.exception.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Classe permettant a l'utilisateur de faire une extraction a la carte
 * @author Groupe 5
 *
 */
@Getter
@Setter
@Slf4j
public class WikiExtractMain {

	private static int nbTablesHtml;
	private static int nbColonnesHtml;
	private static int nbLignesHtml;
	private static long tempsExeHtml;
	private static int nbTablesWikitext;
	private static int nbColonnesWikitext;
	private static int nbLignesWikitext;
	private static long tempsExeWikitext;
//	private static final Logger LOG = LogManager.getLogger(WikiExtractMain.class);

	public static void main(String[] args) throws MalformedURLException, IOException, UrlInvalideException, ExtractionInvalideException, ConversionInvalideException, ArticleInexistantException, ResultatEstNullException, InterruptedException {

		//Scanner entree = new Scanner(System.in);
		//String choix = "";
		//log.info("Quel type d'extraction voulez-vous realiser ? Entrez H pour HTML, W pour WIKITEXT ou bien X pour les deux en meme temps.");
		log.info(String.valueOf(args[1].equalsIgnoreCase("H")));
		String url= args[0];
		log.info("Argument: " + args[1]);
		if(args[1].equalsIgnoreCase("H")) {
			log.info("Done H !");
			lancerSimpleExtraction(true, url);
			log.info("Done !");
		}
		else if (args[1].equalsIgnoreCase("W")) {
			log.info("Done W !");
			lancerSimpleExtraction(false, url);
			log.debug("Done !!!!!!");
		}
		/*else if (choix.equals("X")) {
			lancerDoubleExtraction(args[0]);
		}
		else {
			Log.info("Les seules lettres acceptees sont H, W et X !");
		}*/
		//entree.close();
	}


	/**
	 * Methode demarrant le parsing en csv des wikitables de 336 pages wikipedia, a partir du html et du wikitext
	 *
	 * @throws UrlInvalideException si l'url est invalide
	 * @throws IOException si erreur survenue
	 * @throws ResultatEstNullException si le resultat est null
	 * @throws InterruptedException si erreur survenue
	 */
	/*public static void lancerDoubleExtraction(String url) throws UrlInvalideException, IOException,
			ResultatEstNullException, InterruptedException {
		double urlActuelle = 1.0;
		for (Url urlValide : getUrlValides(url)) {
			//Log.info(urlActuelle/336*100 + "% - Extraction de la page " + urlValide.getTitre());
			Donnee_Html donnee_Html = new Donnee_Html();
			donnee_Html.setUrl(urlValide);

			Donnee_Wikitable donnee_Wikitable= new Donnee_Wikitable();
			donnee_Wikitable.setUrl(urlValide);

			//updateComparerCSV(donnee_Html, donnee_Wikitable);
			urlActuelle++;
		}
		//getStatistiques();
	}*/

	public static void lancerSimpleExtraction(boolean isHtml , String url) throws MalformedURLException, IOException, UrlInvalideException, InterruptedException, ResultatEstNullException {
		/*Donnee_Html fakeDonneeHtml = new Donnee_Html();
		Donnee_Wikitable fakeDonneeWikitable = new Donnee_Wikitable();
		double urlActuelle = 1.0;*/
		Url urlx = new Url(new URL(url));
		if (isHtml) {
			Donnee_Html donnee_Html = new Donnee_Html();
			//donnee_Html.setUrl(urlValide);
			try {
				donnee_Html.extraire(urlx);

			}catch (Exception e){
				log.error("exception ");
			}
			/*for (Url urlValide : getUrlValides(url)) {
				log.info("first ==> ", url);
			//Log.info(urlActuelle/336*100 + "% - Extraction de la page " + urlValide.getTitre());

				log.info(urlValide);
				//updateComparerCSV(donnee_Html, fakeDonneeWikitable);
				urlActuelle++;
			}*/
			getStatistiques();
		}
		else {
			//for (Url urlValide : getUrlValides(url)) {
			//Log.info(urlActuelle/336*100 + "% - Extraction de la page " + urlValide.getTitre());

			Donnee_Wikitable donnee_Wikitable= new Donnee_Wikitable();

				try {
					donnee_Wikitable.extraire(urlx);

				}catch (Exception e){
					log.error("exception ");
				}
				//donnee_Wikitable.setUrl(urlValide);

			//	updateComparerCSV(fakeDonneeHtml, donnee_Wikitable);
				//urlActuelle++;
			//}
			//();
		}

	}

	public static Set<Url> getUrlValides(String urlY) throws MalformedURLException, IOException, UrlInvalideException{
		HashSet<Url> lesUrlValides = new HashSet<>();
		//String BASE_WIKIPEDIA_URL = "output/url_file.txt";
		//log.info("Extract/Validate ==> ", urlY);
		//BufferedReader br = new BufferedReader(new FileReader(urlY));
		//String url;
		//log.info("Extract/valid/transf ==> ", br.readLine());
		if(urlY != null) {log.info("second");
			Url wikiUrl = new Url(new URL(urlY));
			if(wikiUrl.estUrlValide()) {
				lesUrlValides.add(wikiUrl);
				log.info(String.valueOf(wikiUrl));
			}
		}
		//br.close();
		return lesUrlValides;
	}

	/*public static void updateComparerCSV(Donnee_Html donnee_Html, Donnee_Wikitable donnee_Wikitable) throws ResultatEstNullException {
		ComparerCSV comparerCsv = new ComparerCSV(donnee_Html, donnee_Wikitable);
		comparerCsv.informationsExtraction();
		nbTablesHtml += comparerCsv.getTablesHtml();
		nbColonnesHtml += comparerCsv.getColonnesHtml();
		nbLignesHtml += comparerCsv.getLignesHtml();
		tempsExeHtml += comparerCsv.getTempsExeHtml();
		nbTablesWikitext += comparerCsv.getTablesWikitable();
		nbColonnesWikitext += comparerCsv.getColonnesWikitable();
		nbLignesWikitext += comparerCsv.getLignesWikitable();
		tempsExeWikitext += comparerCsv.getTempsExeWikitable();
	}*/

	public static void getStatistiques() {
		long tempsExeTotal = (System.currentTimeMillis());
		log.info("Temps d'execution : " + tempsExeTotal/1000 + " secondes");
		log.info("-----------STATISTIQUES-----------");
		log.info("- HTML - Temps d'execution : " + tempsExeHtml/1000 + " secondes.");
		log.info("Nombre de tableaux parsés: " + nbTablesHtml + ", lignes parsées : " + nbLignesHtml + ", colonnes parsées : " + nbColonnesHtml);
		log.info("- WIKITEXT - Temps d'execution : " + tempsExeWikitext/1000 + " secondes.");
		log.info("Nombre de tableaux parsés: " + nbTablesWikitext + ", lignes parsées : " + nbLignesWikitext + ", colonnes parsées : " + nbColonnesWikitext);
	}

//	Logger example
//	=====================================================
//	 	LOG.debug("This Will Be Printed On Debug");
//        LOG.info("This Will Be Printed On Info");
//        LOG.warn("This Will Be Printed On Warn");
//        LOG.error("This Will Be Printed On Error");
//        LOG.fatal("This Will Be Printed On Fatal");
//	=====================================================
}
