package com.wikipediaMatrix;

import com.wikipediaMatrix.exception.ResultatEstNullException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe permettant de recuperer dse statistiques sur les deux methodes de recuperation de table wikipedia,
 * afin de determiner laquelle est la meilleure.
 * @author Groupe 5
 *
 */
@Getter
@Setter
@Slf4j
public class ComparerCSV {

	private Donnee_Html html;
	private Donnee_Wikitable wikitable;
	private long tempsExeHtml;
	private long tempsExeWikitable;
	private int tablesHtml;
	private int tablesWikitext;
	private int lignesHtml;
	private int lignesWikitable;
	private int colonnesHtml;
	private int colonnesWikitable;

	/**
	 * Constructure de ComparerCSV, a instancier pour chaque pgaage wikipedia
	 * @param html l'objet gerant le parsing du html au csv
	 * @param wikitable l'objet gerant le parsing du wikitext au csv
	 */
	public ComparerCSV(Donnee_Html html, Donnee_Wikitable wikitable) {
		this.html = html;
		this.wikitable = wikitable;
	}

	/**
"	 * Recupere des statistiques sur les deux modes d'extraction - Wikitext et HTML -
	 * dans l'optique de determier lequel est le meilleur.
	 */
	public void informationsExtraction() {
		this.tempsExeHtml = html.getTime();
		this.tempsExeWikitable = wikitable.getTime();
		this.lignesHtml = html.getLignesEcrites();
		this.lignesWikitable = wikitable.getLignesEcrites();
		this.colonnesHtml = html.getColonnesEcrites();
		this.colonnesWikitable = wikitable.getColonnesEcrites();
		this.tablesHtml = html.getNbTableaux();
		this.tablesWikitext = wikitable.getNbTableaux();
	}

	public Donnee_Html getHtml() throws ResultatEstNullException {
		if(html == null) throw new ResultatEstNullException("tablesHtml n'est pas de type int");
		return html;
	}

	public Donnee_Wikitable getWikitable() throws ResultatEstNullException {
		if(wikitable == null) throw new ResultatEstNullException("tablesHtml n'est pas de type int");
		return wikitable;
	}
}
