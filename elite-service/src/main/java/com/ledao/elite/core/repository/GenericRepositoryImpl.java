package com.ledao.elite.core.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.genericdao.dao.DAOUtil;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.dao.hibernate.HibernateBaseDAO;
import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

/**
 * all Repository impl should extend this class
 * 
 */
@Component
@SuppressWarnings("unchecked")
public abstract class GenericRepositoryImpl<T, ID extends Serializable> extends HibernateBaseDAO
		implements GenericDAO<T, ID> {

	
	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	/*@Autowired
	protected RedisTemplate<Object, Object> redisTemplate;
	protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }*/

	protected Class<T> persistentClass = (Class<T>) DAOUtil
			.getTypeArguments(GenericRepositoryImpl.class, this.getClass()).get(0);

	public int count(ISearch search) {
		if (search == null)
			search = new Search();
		return _count(persistentClass, search);
	}

	public T find(Serializable id) {
		return _get(persistentClass, id);
	}

	public T[] find(Serializable... ids) {
		return _get(persistentClass, ids);
	}

	public List<T> findAll() {
		return _all(persistentClass);
	}

	public void flush() {
		_flush();
	}
	
	public void clear() {
		getSession().clear();
	}

	public T getReference(Serializable id) {
		return _load(persistentClass, id);
	}

	public T[] getReferences(Serializable... ids) {
		return _load(persistentClass, ids);
	}

	public boolean isAttached(T entity) {
		return _sessionContains(entity);
	}

	public void refresh(T... entities) {
		_refresh(entities);
	}

	public boolean remove(T entity) {
		return _deleteEntity(entity);
	}

	public void remove(T... entities) {
		_deleteEntities(entities);
	}

	public boolean removeById(Serializable id) {
		return _deleteById(persistentClass, id);
	}

	public void removeByIds(Serializable... ids) {
		_deleteById(persistentClass, ids);
	}

	public boolean save(T entity) {
		return _saveOrUpdateIsNew(entity);
	}
	
	public T merge(T entity) {
		return _merge(entity);
	}

	public boolean[] save(T... entities) {
		return _saveOrUpdateIsNew(entities);
	}

	public <RT> List<RT> search(ISearch search) {
		if (search == null)
			return (List<RT>) findAll();
		return _search(persistentClass, search);
	}

	public <RT> SearchResult<RT> searchAndCount(ISearch search) {
		if (search == null) {
			SearchResult<RT> result = new SearchResult<RT>();
			result.setResult((List<RT>) findAll());
			result.setTotalCount(result.getResult().size());
			return result;
		}
		return _searchAndCount(persistentClass, search);
	}

	public <RT> RT searchUnique(ISearch search) {
		return (RT) _searchUnique(persistentClass, search);
	}

	public Filter getFilterFromExample(T example) {
		return _getFilterFromExample(example);
	}

	public Filter getFilterFromExample(T example, ExampleOptions options) {
		return _getFilterFromExample(example, options);
	}

}
