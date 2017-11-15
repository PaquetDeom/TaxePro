package fr.paquet.framework.ui;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;

import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

import fr.paquet.framework.authentication.JpaAuthorizingRealm;
import fr.paquet.framework.authentication.Permission;
import fr.paquet.framework.authentication.Role;
import fr.paquet.framework.authentication.User;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
	// contient le menu principal de l'application
	private Menu menu;
	
	public MainScreen(UI ui) {
		// définie le style CSS
		setStyleName("main-screen");
		// renseigne la classe chargée de faire les conversions  
		VaadinSession.getCurrent().setConverterFactory(new ProgConverterFactory());
		
		//Container principal
		CssLayout viewContainer = new CssLayout();
		viewContainer.setSizeFull();
		
		//navigateur qui contient : le menu, la vue principale et la fenetre des erreurs
		final Navigator navigator = new Navigator(ui, viewContainer);
		navigator.setErrorView(ErrorView.class);
		menu = new Menu(navigator);

		navigator.addViewChangeListener(viewChangeListener);
		addComponent(menu);

		viewContainer.addStyleName("valo-content");
		addComponent(viewContainer);
		setExpandRatio(viewContainer, 1);
		setSizeFull();
	}

	//Ajoute une vue au menu
	public void addView(ProgView view) {
		menu.addView(view, view.getCaption(), view.getCaption(), view.getIcon());
	}
	// notify the view menu about view changes so that it can display which view
	// is currently active
	ViewChangeListener viewChangeListener = new ViewChangeListener() {
		@Override
		public boolean beforeViewChange(ViewChangeEvent event) {
			return true;
		}

		@Override
		public void afterViewChange(ViewChangeEvent event) {
			menu.setActiveView(event.getViewName());
		}
	};
}
