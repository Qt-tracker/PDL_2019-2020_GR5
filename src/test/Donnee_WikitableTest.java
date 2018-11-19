package test;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import classes.Donnee_Wikitable;
import classes.Url;
import exceptions.ExtractionInvalideException;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

public class Donnee_WikitableTest {

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
	 * Renvoie un message si le temps d execution depasse un temps maximal
	 * @param nbATest
	 */
	@Test
	void testTempsExec(long nbATest) {
		assertTrue("Temps d'execution de " + nbATest/1000 + " secondes", nbATest < 24000);
	}

	/**
	 * Compare le nombre de tableaux recuperes en HTML et en Wikitext
	 * @param nbATester
	 * @param nbRef
	 */
	@Test
	void testNbTableaux(int nbATester, int nbRef) {
		int diff = nbRef - nbATester;
		assertTrue("Nombre de tableaux different : " + diff, diff == 0);
	}

	/**
	 * Renvoie le nombre de cellule comportant des differences entre recuperation HTML et Wikitext dans celles testees
	 * @param cellulesATest
	 * @param cellulesRef
	 */
	@Test
	void testCellules(String[] cellulesATest, String[] cellulesRef) {
		int diff = 0;
		for (int i = 0; i < cellulesRef.length; i++) {
			if(cellulesATest[i] != cellulesRef[i]) {
				diff++;
			}
		}
		assertTrue("Nombre de cellules differentes", diff == 0);
	}

	/**
	 * Test tout a la wanaghen, en gros c est un main dans les tests
	 * @throws ExtractionInvalideException 
	 * @throws MalformedURLException 
	 */
	@Test
	void testCreationCSV() throws ExtractionInvalideException {
		URL monUrl;
		String contenu, wikitable = "";
		try {
			monUrl = new URL("https://fr.wikipedia.org/wiki/Kevin_Bacon");
			Url newUrl = new Url(monUrl);
			Donnee_Wikitable test = new Donnee_Wikitable();
			try {
			contenu = test.recupContenu(newUrl.url);
			} catch (JSONException e) {
				wikitable = test.jsonVersWikitable(contenu);
			} catch (ExtractionInvalideException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			test.wikitableEnTeteVersCSV(wikitable);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
