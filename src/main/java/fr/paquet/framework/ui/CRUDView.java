package fr.paquet.framework.ui;

import org.vaadin.crudui.crud.impl.GridBasedCrudComponent;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Panel;
import fr.paquet.framework.AnnotationInfo;
import fr.paquet.framework.*;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;

public class CRUDView extends AbsoluteLayout implements Property.ValueChangeListener, Handler, ClickListener, TaView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class<TaItem> entityClass = null;
	private Panel panel;

	/**
	 * créé la vue de gestion de la classe entityClass.
	 * 
	 * @param entityClass
	 */
	@SuppressWarnings("unchecked")
	public CRUDView(Class entityClass) {
		this.entityClass = entityClass;
		buildView();
	}
	
	public String getCaption() {
		return new AnnotationInfo(entityClass).getIhmLibelle();
	}	

	public String getName() {
		return new AnnotationInfo(entityClass).getIhmLibelle();
	}	
	
	/**
	 * construit la vue
	 */
	private void buildView() {
		setSizeFull();
		GridBasedCrudComponent<TaItem> crud = new GridBasedCrudComponent<TaItem>(entityClass);
		FormLayout layout=new FormLayout();
		layout.addComponent(crud);
		
		TaContainer container= new TaContainer(entityClass);
		crud.setFindAllOperation(container);
		crud.setAddOperation(container.add());
		crud.setUpdateOperation(container.update());
		crud.setDeleteOperation(container.delete());
		crud.getGrid().setColumns(new AnnotationInfo(entityClass).getGridNames());
		crud.setCrudFormFactory(new TaCrudFormFactory(entityClass));
		panel = new Panel(getCaption());
		panel.setSizeFull();
		addComponent(panel);
		panel.setContent(layout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public Action[] getActions(Object target, Object sender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
