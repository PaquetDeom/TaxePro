package fr.paquet.framework.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import fr.paquet.framework.TaItem;

public class TaBeanFieldGroup extends BeanFieldGroup<TaItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaBeanFieldGroup(Class<? extends TaItem> beanType) {
		super((Class<TaItem>) beanType);
		setFieldFactory(new TaFieldGroupFieldFactory());
	}
}
