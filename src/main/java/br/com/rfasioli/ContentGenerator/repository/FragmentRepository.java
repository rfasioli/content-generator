package br.com.rfasioli.ContentGenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.rfasioli.ContentGenerator.document.Fragment;

public interface FragmentRepository 
	extends MongoRepository<Fragment, String> 
{
}
