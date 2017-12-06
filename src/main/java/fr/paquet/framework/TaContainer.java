package fr.paquet.framework;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.vaadin.crudui.crud.AddOperationListener;
import org.vaadin.crudui.crud.DeleteOperationListener;
import org.vaadin.crudui.crud.FindAllCrudOperationListener;
import org.vaadin.crudui.crud.UpdateOperationListener;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;

@SuppressWarnings({ "serial", "rawtypes" })
public class TaContainer extends TaFactory implements FindAllCrudOperationListener {
	private Class<TaItem> entityClass = null;
	private AnnotationInfo info = null;

	/**
	 * @return le nom de la table
	 */
	private String getTablename() {
		return info.getTableName();
	}

	public Property<Object> getProperty(TaItem entity, String mappedBy) {
		return null;
	}

	public TaContainer(Class entityClass) {
		this.entityClass = entityClass;
		info = new AnnotationInfo(entityClass);
	}

	/**
	 * Fait un select * de la table
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<TaItem> cq = (CriteriaQuery<TaItem>) cb.createQuery(entityClass);
		Root<TaItem> rootEntry = (Root<TaItem>) cq.from(entityClass);
		CriteriaQuery<TaItem> all = cq.select(rootEntry);
		TypedQuery<TaItem> allQuery = getEntityManager().createQuery(all);
		return (Collection<TaItem>) allQuery.getResultList();
	}

	/**
	 * Cette methode permet de définir le traitement effectué pour ajouter un
	 * nouvel element
	 * 
	 * @return
	 */
	@SuppressWarnings({ "serial", "rawtypes" })
	public AddOperationListener add() {
		return new AddOperationListener() {

			@Override
			public Object perform(Object domainObject) {
				return create("CRUDVIEW", (TaItem) domainObject);
			}
		};
	}

	@SuppressWarnings("serial")
	public DeleteOperationListener delete() {
		return new DeleteOperationListener() {
			@Override
			public void perform(Object domainObject) {
				softDelete("CRUDVIEW", (TaItem) domainObject);
			}
		};
	}

	@SuppressWarnings("serial")
	public UpdateOperationListener update() {
		return new UpdateOperationListener() {

			@Override
			public Object perform(Object domainObject) {
				return update("CRUDVIEW", (TaItem) domainObject);
			}
		};
	}
}
