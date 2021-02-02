package com.github.queebskeleton.schlmgmt.repository;

import java.util.List;

/**
 * Contract for a Repository of objects.
 * 
 * @author queenskeleton
 *
 * @param <T> the type of elements to store in this repository
 * @param <ID> the identifier of the elements
 */
public interface Repository<T, ID> {
	
	/**
	 * Grabs all elements stored in this repository.
	 * @return the list of elements
	 */
	List<T> getAll();
	/**
	 * Grabs an element by its identifier.
	 * @param id the identifier of the element
	 * @return the element
	 */
	T getById(ID id);
	/**
	 * Saves an element in this repository.
	 * @param t the element to store
	 */
	void save(T t);
	/**
	 * Deletes an element from this repository, by its identifier.
	 * @param id the identifier of the element
	 */
	void deleteById(ID id);

}
