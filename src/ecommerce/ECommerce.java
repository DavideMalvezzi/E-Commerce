package ecommerce;


import ecommerce.panel.LoginPanel;
import ecommerce.panel.PanelManager;
import ecommerce.product.ProductManager;
import ecommerce.user.UserManager;

/**
* @mainpage E-Commerce
* @section Introduzione Introduzione
* L'e-commerce sta prendendo sempre pi� piede ed � utilizzato da molti clienti.
* 
* Il presente progetto si propone di descrivere e sviluppare una applicazione Java che abbia le seguenti 
* funzionalit�:
*	- Gestione dei prodotti;
*	- Visualizzazione dei prodotti;
*	- Acquisto dei prodotti;
*	- Pagamento. 
*
* @section Funzionalit� Funzionalit�
* @subsection Login Login
* Tutti gli utenti sono caratterizzati da un nome e da una password memorizzati nel file .e-users salvato nella cartella home.
* Gli utenti si dividono in due categorie:
* 	- @b utenti @b clienti che potranno solamente effettuare l'acquisto di prodotti.
* 	- @b utenti @b amministratori che potranno solamente aggiungere, modificare o rimuovere i prodotti in vendita.
* Alla prima apertura del programma sar� chiesto di creare un utente amministratore.
* 
* @subsection Gestione Gestione dei prodotti 
* Questa funzionalit� � riservata agli utenti amministratori. Tutti i prodotti vengono memorizzati all'interno del file .e-products
* salvato nella cartella home. L'amministratore pu� anche scegliere di utilizzare un file diverso. Se ci� avviene il path del nuovo file
* verr� salvato all'interno delle configurazioni del programma memorizzate nel file .e-config salvato sempre nella home. Cos� facendo
* ad ogni nuovo avvio il programma caricher� sempre il nuovo file dei prodotti. 
* 
* La gestione dei prodotti prevede la possibilit� di inserire, modificare e cancellare i prodotti in vendita, con
* le seguenti informazioni:
*	- Nome
*	- Marca 
*	- Codice
*	- Categoria
*	- Prezzo
*	- Immagine
*
* Un prodotto pu� anche essere in promozione; le promozioni possono essere di due tipi:
*	- 3x2, per cui il cliente pu� acquistare prodotti uguali e pagarne solo 2;
*	- Sconto, che prevede un ribasso di una certa percentuale sul prezzo di acquisto.
*
* Tutti i prodotti sono mostrati all'amministratore tramite una tabella riassuntiva che mostra tutti i dettagli dei singoli prodotti.
*
* @subsection Visualizzazione Visualizzazione dei prodotti 
* Questa funzionalit� � per gli utenti clienti, che potranno visualizzare le informazioni relative ai prodotti in vendita in una
* sorta di bacheca virtuale.
* 
* L'utente ha la possibilit� di filtrare la visualizzazione dei prodotti per categoria e di ricercare
* un prodotto per nome o parte di esso, oppure pu� fare una ricerca avanzata su tutte le informazioni relative ad un prodotto. 
* 
* @subsection Acquisto Acquisto dei prodotti 
* L'utente deve avere la possibilit� di inserire in un carrello i prodotti che vuole acquistare. Di fianco a ogni
* immagine di prodotto devono quindi essere disponibili dei componenti grafici che permettono di scegliere
* il numero di prodotti e di inserirli nel carrello.
* 
* L'utente pu� inserire un prodotto nel carrello premendo il tasto opportuno oppure effettuando il drag'n drop della scheda del prodotto
* sull'icona del carrello.
* 
* @subsection Carrello Carrello
* Questa funzionalit� mostra all'utente tutti i prodotti scelti per l'acquisto e il totale della spesa.
* 
* L'utente potr� rimuovere i singoli prodotti, rimuovere tutti i prodotti dal carrello oppure procedere all'acquisto.
* 
* @subsection Pagamento Pagamento
* Questa funzionalit� mostra una finestra in cui il cliente deve inserire le informazioni di spedizione e 
* la modalit� di pagamento. Nel momento in cui il cliente conferma, il carrello viene svuotato. 
*/


/**
 * Classe contenente il main del programma
 * @author Davide Malvezzi
 */
public class ECommerce {

	public static void main(String[] args) {
		//Load configuration from config file
		Configuration.load();
		
		//Load users from configuration file
		UserManager.loadUsers();
		
		//Load the products
		ProductManager.loadProducts();
		
		//Create the main program window and all the panels
		PanelManager pm = new PanelManager();
		pm.setCurrentPanel(LoginPanel.TAG);
			
	}
	
}
