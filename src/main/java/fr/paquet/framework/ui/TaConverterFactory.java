package fr.paquet.framework.ui;

import java.util.List;
import java.util.Set;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.DefaultConverterFactory;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Table;

import eu.maxschuster.vaadin.colorpickerfield.converter.ColorToHexConverter;

/**
 * cette classe permet de convertir une chaine de caratère en autre chose
 * (booleen)
 * 
 * @author bda
 *
 */
public class TaConverterFactory extends DefaultConverterFactory {

	@SuppressWarnings("unchecked")
	@Override
	protected <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> findConverter(Class<PRESENTATION> presentationType,
			Class<MODEL> modelType) {
		if (presentationType == Color.class && modelType == String.class)
			return (Converter<PRESENTATION, MODEL>) new ColorToHexConverter();
		else
			return super.findConverter(presentationType, modelType);
	}

}
