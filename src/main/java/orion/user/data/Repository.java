package orion.user.data;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class Repository<T> {

    @PersistenceContext(name = "OrionUserDS")
    private EntityManager em;

    public T create(final T obj) {
        this.em.persist(obj);
        return obj;
    }

    public List<T> read() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        final Root<T> root = criteria.from(this.genericClass());
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }

    public void update(T obj) {
        this.em.merge(obj);
    }

    public void delete(Long id) {
        T obj = em.find(this.genericClass(), id);
        this.em.remove(obj);
    }

    public T find(Long id) {
        return em.find(this.genericClass(), id);
    }

    public T find(String hash) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        Root<T> root = criteria.from(this.genericClass());
        criteria.select(root).where(builder.equal(root.get("hash"), hash));
        return em.createQuery(criteria).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    private Class<T> genericClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}