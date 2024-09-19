package  com.vobi.demo.vault.entity.service;
import java.util.List;
import java.util.Optional;

import com.vobi.demo.vault.exception.VortexbirdException;

/**
* @author Zathura Code Generator Version 23.10 http://zathuracode.org/
* 
* @generationDate 2024-09-19T17:02:34.813843
*
*/
public interface GenericService <T,ID>{
	
	 	public List<T> findAll();
	 	
	 	public Optional<T> findById(ID id);

	    public T save(T entity) throws VortexbirdException;

	    public T update(T entity) throws VortexbirdException;

	    public void delete(T entity) throws VortexbirdException;

	    public void deleteById(ID id) throws VortexbirdException;	    

	    public void validate(T entity) throws VortexbirdException;
	    
	    public Long count();

}
