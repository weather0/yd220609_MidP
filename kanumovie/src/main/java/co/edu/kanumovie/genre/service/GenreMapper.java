package co.edu.kanumovie.genre.service;

import java.util.List;

import co.edu.kanumovie.genre.vo.GenreVO;

public interface GenreMapper {
	
	List<GenreVO> genreSelectList();
	GenreVO genreSelect(); 
	int genreInsert(); 
	int genreUpdate(); 
	int genreDelete(); 

}
