package fr.paquet.ihm.importCsv;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.framework.ui.TaView;



//TODO Listener OkButton
@SuppressWarnings("serial")
public class CSVImportView extends AbsoluteLayout implements TaView {
	/**
	 * 
	 */

	private Panel panel = null;
	private VerticalLayout mainLayout = null;

	/**
	 * Constructeur de la class<br/>
	 */
	public CSVImportView() {
		super();
		BuildView();

	}

	
	/**
	 * efface ecran puis cree un VerticalLayout<br/>
	 */
	public void BuildView() {

		removeAllComponents();
		setPanel(new Panel());
		addStyleName("XMLImport");

		setSizeFull();

		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(getDetail());
		addComponent(layout);

	}

	private void setPanel(Panel panel) {
		this.panel = panel;
	}

	/**
	 * 
	 * @return le Panel de getDetail<br/>
	 */
	private Panel getPanel() {
		return panel;
	}

	/**
	 * 
	 * @return Le layout principal du Panel<br/>
	 */
	public VerticalLayout getCSVImportViewPanelContent() {
		if (mainLayout == null)
			mainLayout = new VerticalLayout();
		return mainLayout;
	}

	/**
	 * 
	 * @return Le component principal de l'ecran<br/>
	 */

	private Component getDetail() {

		// creation des buttons
		Button importXml = new Button("Import");

		// creaton de layout
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);

		// Panel principal
		getPanel().setCaption("Accueil - Import des Fichiers *.xml");

		// Layout principal
		VerticalLayout vLayout = getCSVImportViewPanelContent();

		vLayout.addComponent(importXml);
		getPanel().setContent(vLayout);

		// listener du button de creation des folders
		importXml.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				getCSVImportViewPanelContent().getUI().getUI().addWindow(new WindowImport(CSVImportView.this, new PronoteImport()));
			}
		});

		return getPanel();
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public String getName() {

		return "Import et integration XML";
	}

	@Override
	public String getCaption() {

		return "Import et integration XML";
	}

}
