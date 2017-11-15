package fr.paquet.framework.ui;

import java.awt.Color;
import java.util.List;

import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.form.FieldProvider;
import org.vaadin.crudui.form.impl.GridLayoutCrudFormFactory;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;

import eu.maxschuster.vaadin.colorpickerfield.ColorPickerAreaField;
import eu.maxschuster.vaadin.colorpickerfield.ColorPickerField;
import fr.paquet.framework.AnnotationInfo;
import fr.paquet.framework.ProgItem;
import fr.paquet.framework.PropertyInfo;

public class ProgCrudFormFactory extends GridLayoutCrudFormFactory<ProgItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AnnotationInfo info = null;

	private static List<PropertyInfo> getColumnsInfo(Class<ProgItem> domainType) {
		return new AnnotationInfo(domainType).getGridProperties();
	}

	private AnnotationInfo getInfo() {
		if (info == null)
			info = new AnnotationInfo(domainType);
		return info;
	}

	public ProgCrudFormFactory(Class<ProgItem> domainType) {
        super(domainType, 1, getColumnsInfo(domainType).size());  
        setVisiblePropertyIds(CrudOperation.READ, getInfo().getFormNames());
        setVisiblePropertyIds(CrudOperation.ADD, getInfo().getFormNames());
        setVisiblePropertyIds(CrudOperation.DELETE, getInfo().getFormNames());
        setVisiblePropertyIds(CrudOperation.UPDATE, getInfo().getFormNames());
        
        for(String passwordField:getInfo().getPasswordNames())
        	setFieldType(passwordField, PasswordField.class);

        for(String colorField:getInfo().getColorNames())
        	setFieldType(colorField, ColorPickerField.class);
	}

	@Override
	public Component buildNewForm(CrudOperation operation, ProgItem domainObject, boolean readOnly,
			Button.ClickListener cancelButtonClickListener, Button.ClickListener operationButtonClickListener) {
		GridLayout gridLayout = new GridLayout(1, getColumnsInfo(domainType).size());
		gridLayout.setWidth("100%");
		gridLayout.setSpacing(true);

		BeanFieldGroup fieldGroup = new ProgBeanFieldGroup(domainObject.getClass());
		List<Field> fields = buildAndBind(operation, domainObject, readOnly, fieldGroup);
		fields.stream().forEach(field -> gridLayout.addComponent(field));

		Layout footerLayout = buildFooter(operation, domainObject, cancelButtonClickListener,
				operationButtonClickListener, fieldGroup);

		VerticalLayout mainLayout = new VerticalLayout(gridLayout, footerLayout);
		mainLayout.setWidth("100%");
		mainLayout.setComponentAlignment(footerLayout, Alignment.BOTTOM_RIGHT);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		return mainLayout;
	}

}
