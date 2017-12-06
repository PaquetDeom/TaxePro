package fr.paquet.ihm.importCsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.dataBase.initData.InitDataFactory;
import fr.paquet.ihm.AlertListener;
import fr.paquet.ihm.AlertWindow;

@SuppressWarnings("serial")
public class WindowImport extends Window implements SucceededListener {

	class FileCheckbox extends CheckBox implements PronoteImport.PronoteImportChangedListener {

		CSVFiles file = null;

		public FileCheckbox(CSVFiles file) {
			super(file.fileName());
			try {
				this.file = file;
				getPronoteImport().addChangeListener(this);
				setValue(getPronoteImport().hasDocument(file));
				setEnabled(false);
			} catch (Exception e) {
				e.printStackTrace();
				getCSVVIew().getCSVImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			}
		}

		@Override
		public void PronoteImportChanged() {
			try {
				setValue(getPronoteImport().hasDocument(file));
			} catch (ReadOnlyException | ConversionException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	class ButtonIntegration extends Button implements PronoteImport.PronoteImportChangedListener, Button.ClickListener {
		PronoteImport pronote = null;

		public ButtonIntegration(PronoteImport pronote) {
			super("Integration");
			try {
				this.pronote = pronote;
				pronote.addChangeListener(this);
				setEnabled(pronote.isAllLoaded());
				addClickListener(this);
			} catch (Exception e) {
				e.printStackTrace();
				getCSVVIew().getCSVImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			}
		}

		@Override
		public void buttonClick(ClickEvent event) {

			try {
				pronote.integre();
				pronote.chargeTables(InitDataFactory.getUniqInstance().findDatas());
				pronote.deleteFile(getFile());
				pronote.deleteTable(InitDataFactory.getUniqInstance().findDatas());
				getCSVVIew().getCSVImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Message !!!", "Vos donnes ont correctement integrées").show());
			} catch (Exception e) {
				e.printStackTrace();
				getCSVVIew().getCSVImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow(e.getMessage()).show());
			}

		}

		@Override
		public void PronoteImportChanged() {
			try {
				setEnabled(pronote.isAllLoaded());
			} catch (Exception e) {
				e.printStackTrace();
				getCSVVIew().getCSVImportViewPanelContent().getUI().getUI()
						.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
			}
		}
	}

	private VerticalLayout mainLayout = null;
	private CSVImportView csvView;
	private PronoteImport pronoteImport;

	public WindowImport(CSVImportView csvView, PronoteImport pronoteImport) {
		super();
		setCSVImportView(csvView);
		setPronoteImport(pronoteImport);
		BuildWindow();
	}

	private void setPronoteImport(PronoteImport pronoteImport) {
		this.pronoteImport = pronoteImport;

	}

	private PronoteImport getPronoteImport() {
		return pronoteImport;
	}

	private void setCSVImportView(CSVImportView csvView) {
		this.csvView = csvView;
	}

	private CSVImportView getCSVVIew() {
		return csvView;
	}

	private void setMainLayout(VerticalLayout layout) {

		setSizeFull();
		Layout emptyScreen = new VerticalLayout();
		Upload upload = new Upload(null, new Upload.Receiver() {
			@Override
			public OutputStream receiveUpload(String fileName, String mimeType) {

				try {
					return new FileOutputStream(getPronoteImport().createFile(fileName));
				} catch (FileNotFoundException fE) {
					getCSVVIew().getCSVImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", fE.getMessage()).show());
					fE.printStackTrace();

				} catch (Exception e) {
					getCSVVIew().getCSVImportViewPanelContent().getUI().getUI()
							.addWindow(new AlertWindow("Erreur !!!", e.getMessage()).show());
					e.printStackTrace();
				}
				return null;
			}
		});

		upload.addSucceededListener(this);

		layout.addComponent(upload);
		layout.addComponent(emptyScreen);
		layout.setExpandRatio(emptyScreen, 0.0f);

		Label label0 = new Label();
		Label label7 = new Label();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);

		label0.setCaption("Fichier à télécharger");

		hLayout.addComponent(label7);
		layout.addComponent(label0);

		for (CSVFiles file : EnumSet.allOf(CSVFiles.class)) {
			// on crée le label des documents à télécharger
			FileCheckbox checkboxDoc = new FileCheckbox(file);
			layout.addComponent(checkboxDoc);
		}

		layout.addComponent(new ButtonIntegration(getPronoteImport()));
		this.mainLayout = layout;
	}

	private VerticalLayout getMainLayout() {
		return mainLayout;
	}

	private void BuildWindow() {

		setCaption("integration des fichiers dans base");
		setSizeUndefined();
		setWidth(600.0f, Unit.PIXELS);
		center();
		setModal(true);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		VerticalLayout layout = new VerticalLayout();
		setMainLayout(new VerticalLayout());

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);

		hLayout.addComponent(new ButtonIntegration(getPronoteImport()));
		hLayout.addComponent(getAnnulButton());

		layout.addComponent(getMainLayout());
		layout.addComponent(hLayout);
		content.addComponent(layout);
		setContent(content);

		setVisible(true);

	}

	private Button getAnnulButton() {

		Button button = new Button("Annuler");

		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				close();

			}
		});

		return button;
	}

	private String fileName = null;

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		setFile(event.getFilename());

	}

	private String getFile() {
		return fileName;
	}

	private void setFile(String fileName) {
		this.fileName = fileName;
	}

}
