package fr.paquet.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.persistence.Table;
import fr.paquet.framework.ui.Ihm;

public class AnnotationInfo {

	private Class<?> managedClass;
	private List<PropertyInfo> lstProperties = null;

	public AnnotationInfo(Class<?> managedClass) {
		this.managedClass = managedClass;
	}

	/**
	 * @return le nom de la table (s'il est renseigné associé au POJO)
	 */
	public String getIhmLibelle() {
		Ihm annotation = (Ihm) managedClass.getAnnotation(Ihm.class);
		if (annotation != null)
			return annotation.libelle();
		return null;
	}

	/**
	 * @return le nom de la table (s'il est renseigné associé au POJO)
	 */
	public String getTableName() {
		Table annotation = (Table) managedClass.getAnnotation(Table.class);
		if (annotation != null)
			return annotation.name();
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<PropertyInfo> getProperties() {
		if (lstProperties == null || lstProperties.size() == 0) {
			lstProperties = new Vector<PropertyInfo>();
			try {
				for (Field field : managedClass.getDeclaredFields()) {
					Ihm ihmInfo = null;
					Annotation annotation = field.getAnnotation(Ihm.class);
					lstProperties.add(new PropertyInfo(field, (Ihm) annotation));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return lstProperties;
	}

	/**
	 * @return la liste triée des noms de champs a afficher dans la table.
	 */
	@SuppressWarnings("unchecked")
	public List<PropertyInfo> getGridProperties() {
		List<PropertyInfo> list = new ArrayList<PropertyInfo>();
		for (PropertyInfo info : getProperties()) {
			if (info.getIhm() != null && !info.getIhm().grid().equals("hidden"))
				list.add(info);
		}
		java.util.Collections.sort(list);
		return list;
	}

	/**
	 * @return la liste triée des noms de champs a afficher dans la table.
	 */
	@SuppressWarnings("unchecked")
	public List<PropertyInfo> getFormProperties() {
		List<PropertyInfo> list = new ArrayList<PropertyInfo>();
		for (PropertyInfo info : getProperties()) {
			if (info.getIhm() != null && !info.getIhm().form().equals("hidden"))
				list.add(info);
		}
		java.util.Collections.sort(list);
		return list;
	}

	/**
	 * @return la liste triée des colonnes à afficher dans la table.
	 */
	@SuppressWarnings("unchecked")
	public String[] getGridNames() {
		List<PropertyInfo> list = getGridProperties();
		String[] result = new String[list.size()];
		int i = 0;
		for (PropertyInfo info : list) {
			result[i++] = info.getField().getName();
		}
		return result;
	}

	/**
	 * @return la liste triée des colonnes à afficher dans la table.
	 */
	@SuppressWarnings("unchecked")
	public String[] getFormNames() {
		List<PropertyInfo> list = getFormProperties();
		String[] result = new String[list.size()];
		int i = 0;
		for (PropertyInfo info : list) {
			result[i++] = info.getField().getName();
		}
		return result;
	}

	/**
	 * @return la liste des champs de type password
	 */
	@SuppressWarnings("unchecked")
	public String[] getPasswordNames() {
		ArrayList<String> result = new ArrayList<String>();
		List<PropertyInfo> list = getFormProperties();
		for (PropertyInfo info : list) {
			if (info.getIhm().type().equals("password"))
				result.add(info.getField().getName());
		}
		return result.toArray(new String[result.size()]);
	}

	/**
	 * @return la liste des champs de type colorPicker
	 */
	@SuppressWarnings("unchecked")
	public String[] getColorNames() {
		ArrayList<String> result = new ArrayList<String>();
		List<PropertyInfo> list = getFormProperties();
		for (PropertyInfo info : list) {
			if (info.getIhm().type().equals("color"))
				result.add(info.getField().getName());
		}
		return result.toArray(new String[result.size()]);
	}

	/**
	 * @return le nom de la classe sans le packge et sans l'extension.
	 */
	public String getEntityName() {
		return getManagedClass().getName();
	}

	/**
	 * @return the managedClass
	 */
	public Class<?> getManagedClass() {
		return managedClass;
	}

	/**
	 * @param managedClass
	 *            the managedClass to set
	 */
	public void setManagedClass(Class<?> managedClass) {
		this.managedClass = managedClass;
	}
}
