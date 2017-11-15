package fr.paquet.ihm;

import java.util.ArrayList;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class AlertWindow extends Window implements Button.ClickListener {

	@SuppressWarnings("unused")
	private String message = null;
	@SuppressWarnings("unused")
	private String[] buttons = null;

	private ArrayList<AlertListener> listenerList = new ArrayList<AlertListener>();

	public void addButtonListener(AlertListener listener) {
		listenerList.add(listener);
	}

	public AlertWindow(String message) {
		this("Message d'erreur", message);
	}

	public AlertWindow(String title, String message) {
		this(title, message, new String[] { "Ok" });
	}

	public AlertWindow(String title, String message, String[] buttons) {
		super(title);
		this.message = message;
		this.buttons = buttons;

		setCaption(title);
		setSizeUndefined();
		setWidth(600.0f, Unit.PIXELS);
		center();
		setModal(true);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		Image image = new Image(null, new ThemeResource("img/cancel.png"));
		image.setStyleName("logo");
		hLayout.addComponent(image);
		hLayout.addComponent(new TextField(message));
		VerticalLayout layout = new VerticalLayout();
		hLayout.addComponent(layout);
		layout.setCaption(message);
		for (String btnName : buttons) {
			Button button = new Button(btnName);
			button.addClickListener(this);
			layout.addComponent(button);
		}

		content.addComponent(layout);
		setContent(content);
	}

	public AlertWindow(String title, String message, String[] buttons, AlertListener listener) {
		this(title, message, buttons);
		addButtonListener(listener);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Button btn = (Button) event.getSource();
		for (AlertListener listener : listenerList) {
			listener.buttonClick(btn.getCaption());
		}
		close();
	}

	public Window show() {
		setVisible(true);
		return this;
	}
}
