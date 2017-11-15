package fr.paquet.framework.ui;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.ConverterFactory;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TwinColSelect;

import eu.maxschuster.vaadin.colorpickerfield.ColorPickerAreaField;
import eu.maxschuster.vaadin.colorpickerfield.ColorPickerField;
import eu.maxschuster.vaadin.colorpickerfield.converter.ColorToHexConverter;
import eu.maxschuster.vaadin.colorpickerfield.converter.HexToColorConverter;
import fr.paquet.framework.ProgContainer;
import fr.paquet.framework.ProgItem;

public class ProgFieldGroupFieldFactory extends DefaultFieldGroupFieldFactory {

	static {
		VaadinSession.getCurrent().setConverterFactory(new ProgConverterFactory());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
		if (ColorPickerField.class.isAssignableFrom(fieldType)) {
			return (T) createColorField();
		}else if (PasswordField.class.isAssignableFrom(fieldType)) {
				return (T) new PasswordField();
		} else if (List.class.isAssignableFrom(type)) {
			return (T) createListBuilderItem(type);
		} else if (Boolean.class.isAssignableFrom(type)) {
			return (T) createCheckBox();
		} else if (ProgItem.class.isAssignableFrom(type)) {
			return (T) createComboItem(type);
		}
		return createDefaultField(type, fieldType);
	}

	protected ColorPickerField createColorField() {
		ColorPickerField cpf = new ColorPickerField();
		cpf.setImmediate(true);
		return cpf;
	}

	protected TwinColSelect createListBuilderItem(Class type) {
		try {
			ParameterizedType parameterized = (ParameterizedType) List.class.getMethod("iterator").getGenericReturnType();
			Class item = parameterized.getRawType().getClass();
			Collection<ProgItem> values = new ProgContainer(item).findAll();
			return new TwinColSelect(null, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected ComboBox createComboItem(Class<?> type) {
		Collection<ProgItem> values = new ProgContainer(type).findAll();
		ComboBox combo = new ComboBox("", values);
		combo.setNewItemsAllowed(false);
		combo.setWidth(100.0f, Unit.PERCENTAGE);
		return combo;
	}

	protected CheckBox createCheckBox() {
		return new CheckBox();
	}

}
