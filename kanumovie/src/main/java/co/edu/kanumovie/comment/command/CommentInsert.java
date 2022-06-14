package co.edu.kanumovie.comment.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.edu.kanumovie.comment.service.CommentService;
import co.edu.kanumovie.comment.serviceImpl.CommentServiceImpl;
import co.edu.kanumovie.comment.vo.CommentVO;
import co.edu.kanumovie.common.Command;

public class CommentInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 댓글 등록 후 재출력
		HttpSession session = request.getSession();
		CommentService dao = new CommentServiceImpl();
		List<CommentVO> comments = new ArrayList<CommentVO>();
		CommentVO vo = new CommentVO();
		// insert 하기위한 준비
		String email = (String) session.getAttribute("email");
		
		vo.setEmail(email);
		vo.setId(338953);  // id값이 어떻게 유지 되는가?
		vo.setComments(request.getParameter("comments")); // 댓글내용
		dao.commentInsert(vo);
		
		// 업데이트 후 다시 전체 출력하기 위한 작업
		vo = new CommentVO();
		vo.setEmail(email);
		System.out.println(vo.getEmail());
		comments = dao.commentAllList(vo);
		
		// 리스트를 처리한것을 싦어서 보내야함.
		request.setAttribute("comments", comments);
		
		return "comment/comment";
	}

}