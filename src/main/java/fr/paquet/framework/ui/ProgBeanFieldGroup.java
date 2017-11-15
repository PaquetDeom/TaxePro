package fr.paquet.framework.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import fr.paquet.framework.ProgItem;

public class ProgBeanFieldGroup extends BeanFieldGroup<ProgItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProgBeanFieldGroup(Class<? extends ProgItem> beanType) {
		super((Class<ProgItem>) beanType);
		setFieldFactory(new ProgFieldGroupFieldFactory());
	}
}
