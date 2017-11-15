package fr.paquet.io;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Nathanaël
 * 
 *         Class qui permet de lire les noeuds d'un fichier .xml<br/>
 *
 */

public class RecursiveNodes {

	public static void getNodes(Node n, ArrayList<Element> listElement, String node) {

		if (n instanceof Element) {
			Element element = (Element) n;
			if (n.getNodeName().equals(node))
				listElement.add(element);

			// Nous allons maintenant traiter les nœuds enfants du nœud en cours
			// de traitement
			int nbChild = n.getChildNodes().getLength();
			// Nous récupérons la liste des nœuds enfants
			NodeList list = n.getChildNodes();

			// nous parcourons la liste des nœuds
			for (int i = 0; i < nbChild; i++) {
				Node n2 = list.item(i);

				// si le nœud enfant est un Element, nous le traitons
				if (n2 instanceof Element) {
					// appel récursif à la méthode pour le traitement du nœud et
					// de ses enfants
					getNodes(n2, listElement, node);
				}
			}
		}
	}

}
