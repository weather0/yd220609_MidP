package co.edu.kanumovie.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	public String exec(HttpServletRequest request, HttpServletResponse response);

}
