package fr.paquet.framework.ui;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import fr.paquet.framework.authentication.*;
import fr.paquet.framework.ui.LoginScreen.*;
import fr.paquet.ihm.Import.XMLImportView;

import fr.paquet.ihm.progression.ProgressionView;
import fr.paquet.ihm.referentiel.ReferentielView;
import fr.paquet.ihm.sequence.SequenceView;
import fr.paquet.progression.Progression;
import fr.paquet.framework.ProgContainer;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("caftheme")
public class PROGRESSUI extends UI {

	public static final String PERSISTENCE_UNIT = "progress";
	Progression prog = null;

	public static String getPersistenceUnit() {
		return PERSISTENCE_UNIT;
	}

	static User root = null;
	static {
		EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
		Realm realm = new JpaAuthorizingRealm();
		SecurityManager securityManager = new DefaultSecurityManager(realm);
		SecurityUtils.setSecurityManager(securityManager);

		// on renseigne les informations du role root
		root = User.findByLogin("root");
		if (root == null) {
			ProgContainer userFactory = new ProgContainer(User.class);
			root = new User("root", "root");
			root.getRoles().add(new Role("manager"));
			userFactory.create("PROGRESSUI", root);
		}
	}

	@Override
	/**
	 * appel initial. renseigne le titre de la page (caf) récupere de shiro
	 * l'utilisateur.<br/>
	 * si l'utilisateur n'est pas authentifié,<br/>
	 * on appelle LoginScreen (qui appelera showMainView)<br/>
	 * sinon <br/>
	 * on appelle showMainView<br/>
	 * finSi<br/>
	 */
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle("progress");
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			setContent(new LoginScreen(new LoginListener() {
				@Override
				public void loginSuccessful() {
					showMainView();
				}
			}));
		} else {
			showMainView();
		}
	}

	private static MainScreen main = null;

	private static void setMainScreen(MainScreen main) {
		PROGRESSUI.main = main;
	}

	public static MainScreen getMainScreen() {
		if (main == null)
			setMainScreen(new MainScreen(PROGRESSUI.getCurrent()));
		return main;
	}

	/**
	 * la fenetre principale devient MainScreen
	 */

	public void showMainView() {
		addStyleName(ValoTheme.UI_WITH_MENU);

		try {
			getMainScreen().addView(new ReferentielView());
			getMainScreen().addView(new ProgressionView());
			getMainScreen().addView(new SequenceView());
			getMainScreen().addView(new XMLImportView());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setContent(getMainScreen());
		getNavigator().navigateTo(getNavigator().getState());
	}

	public static PROGRESSUI get() {
		return (PROGRESSUI) UI.getCurrent();
	}

	@WebServlet(urlPatterns = "/*", name = "PROGRESSUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = PROGRESSUI.class, productionMode = false)
	public static class ProgressUIServlet extends VaadinServlet {
	}

}
