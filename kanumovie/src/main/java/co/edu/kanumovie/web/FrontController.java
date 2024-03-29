package co.edu.kanumovie.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.kanumovie.admin.command.Admin;
import co.edu.kanumovie.admin.command.AdminMessage;
import co.edu.kanumovie.admin.command.Analytics;
import co.edu.kanumovie.admin.command.AnalyticsWeeklyVisit;
import co.edu.kanumovie.admin.command.Analyticssignupdata;
import co.edu.kanumovie.admin.command.Deletebanner;
import co.edu.kanumovie.admin.command.Insertbanner;
import co.edu.kanumovie.admin.command.SelectUsersPreferredGenre;
import co.edu.kanumovie.admin.command.UpdateReportCheck;
import co.edu.kanumovie.admin.command.Updatebanner;
import co.edu.kanumovie.admin.command.Updateblockcheck;
import co.edu.kanumovie.admin.command.Updateunblockcheck;
import co.edu.kanumovie.admin.command.bannerinputform;
import co.edu.kanumovie.admin.command.bannerupdateform;
//import co.edu.kanumovie.comment.command.Comment;
import co.edu.kanumovie.comment.command.CommentDelete;
import co.edu.kanumovie.comment.command.CommentInsert;
import co.edu.kanumovie.comment.command.CommentSelectList;
import co.edu.kanumovie.comment.command.CommentUpdate;
import co.edu.kanumovie.common.Command;
import co.edu.kanumovie.country.command.CountrySelectList;
import co.edu.kanumovie.genre.command.GenreSelectList;
import co.edu.kanumovie.home.command.Home;
import co.edu.kanumovie.likes.command.Likes;
import co.edu.kanumovie.likes.command.LikesDelete;
import co.edu.kanumovie.likes.command.LikesSelectList;
import co.edu.kanumovie.likes.command.UserLikesSelectList;
import co.edu.kanumovie.movie.command.MovieInfo;
import co.edu.kanumovie.movie.command.MovieInfoghtest;
import co.edu.kanumovie.movie.command.MovieInsert;
import co.edu.kanumovie.movie.command.MovieSearch;
import co.edu.kanumovie.movie.command.MovieSelectCountryList;
import co.edu.kanumovie.movie.command.MovieSelectGenreList;
import co.edu.kanumovie.movie.command.MovieSelectList;
import co.edu.kanumovie.report.command.Report;
import co.edu.kanumovie.report.command.ReportInsert;
import co.edu.kanumovie.report.command.ReportUpdate;
import co.edu.kanumovie.user.command.FindPw;
import co.edu.kanumovie.user.command.FindPwForm;
import co.edu.kanumovie.user.command.IdCheck;
import co.edu.kanumovie.user.command.Login;
import co.edu.kanumovie.user.command.LoginForm;
import co.edu.kanumovie.user.command.LoginGoogle;
import co.edu.kanumovie.user.command.LoginKakao;
import co.edu.kanumovie.user.command.Logout;
import co.edu.kanumovie.user.command.PreferGenreForm;
import co.edu.kanumovie.user.command.ProfileChange;
import co.edu.kanumovie.user.command.PwChange;
import co.edu.kanumovie.user.command.SignUp;
import co.edu.kanumovie.user.command.SignUpForm;
import co.edu.kanumovie.user.command.UserDelete;
import co.edu.kanumovie.user.command.UserManage;
import co.edu.kanumovie.user.command.UserManageForm;
import co.edu.kanumovie.user.command.UserPreference;
import co.edu.kanumovie.user.command.preferGenre;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> map = new HashMap<String, Command>();

	public FrontController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/home.do", new Home()); // 홈페이지
		map.put("/admin.do", new Admin());//관리자 페이지 이동
		map.put("/adminmessage.do", new AdminMessage()); //신고관리페이지이동
		map.put("/updateblockcheck.do", new Updateblockcheck()); //차단 처리
		map.put("/updatereportcheck.do", new UpdateReportCheck()); //신고 처리
		map.put("/updateunblockcheck.do", new Updateunblockcheck());// 차단 해제 처리
		map.put("/analytics.do", new Analytics()); //차트 페이지 이동
		map.put("/analyticssignupdata.do", new Analyticssignupdata()); //주간 회원가입 유저 수 처리
		map.put("/selectUsersPreferredGenre.do", new SelectUsersPreferredGenre()); //유저 선호 장르 데이터처리
		map.put("/bannerinputform.do", new bannerinputform());//배너 인풋폼 이동
		map.put("/bannerupdateform.do", new bannerupdateform());//배너 업데이트폼 이동
		map.put("/updatebanner.do", new Updatebanner());//배너 업데이트
		map.put("/deletebanner.do", new Deletebanner());//배너 딜리트
		map.put("/insertbanner.do", new Insertbanner());//배너 등록
		map.put("/analyticsWeeklyVisit.do", new AnalyticsWeeklyVisit());//주간 방문자 수 데이터 처리
		map.put("/loginForm.do", new LoginForm());
		map.put("/login.do", new Login());
		map.put("/loginKakao.do", new LoginKakao());
		map.put("/loginGoogle.do", new LoginGoogle());
		map.put("/logout.do", new Logout());
		map.put("/userManageForm.do", new UserManageForm());
		map.put("/userManage.do", new UserManage());
		map.put("/profileChange.do", new ProfileChange());
		map.put("/pwChange.do", new PwChange());
		map.put("/userDelete.do", new UserDelete());
		map.put("/preferGenreForm.do", new PreferGenreForm());
		map.put("/preferGenre.do", new preferGenre()); // 선호 영화 장르.
		map.put("/signUpForm.do", new SignUpForm());
		map.put("/signUp.do", new SignUp());
		map.put("/findPwForm.do", new FindPwForm());
		map.put("/findPw.do", new FindPw());
		map.put("/report.do", new Report());  // report 신고창
		map.put("/profileChange.do", new ProfileChange());
		map.put("/movieInfo.do", new MovieInfo());
		map.put("/commentInsert.do", new CommentInsert());
		map.put("/genreSelectList.do", new GenreSelectList());
		map.put("/movieSelectGenreList.do", new MovieSelectGenreList()); // 장르별 영화 페이지
		map.put("/commentDelete.do", new CommentDelete());
		map.put("/movieInfoghtest.do", new MovieInfoghtest());
		map.put("/movieSearch.do", new MovieSearch());
		map.put("/countrySelectList.do", new CountrySelectList()); // 국가 리스트 
		map.put("/movieSelectCountryList.do", new MovieSelectCountryList()); // 국가별 영화 페이지
		map.put("/reportInsert.do", new ReportInsert());   // 신고 등록
		map.put("/commentUpdate.do", new CommentUpdate());  // comment 수정
		map.put("/reportUpdate.do", new ReportUpdate());  // 신고 후 user table update
		map.put("/likes.do", new Likes()); // 좋아요 삽입
		map.put("/userLikesSelectList.do", new UserLikesSelectList()); // 유저의 좋아요 영화 목록
		map.put("/likesSelectList.do", new LikesSelectList()); // 좋아요 전체 목록 
		map.put("/likesDelete.do", new LikesDelete()); // 좋아요 삭제
		map.put("/movieInsert.do", new MovieInsert()); // 영화 정보 삽입
		map.put("/commentSelectList.do", new CommentSelectList()); // 유저가 코멘트 남긴 영화 리스트
		map.put("/userPreference.do", new UserPreference()); // 유저의 선호 장르 리스트
		map.put("/idCheck.do", new IdCheck()); // 아이디 중복 여부 체크)
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());

		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		System.out.println(viewPage);
		
		// .do 호출이 아닐 경우
		if(!viewPage.endsWith(".do")) {
			// ajax 호출일 경우
			if (viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				viewPage = viewPage.substring(5);
				response.getWriter().append(viewPage);
				return;
			} else {
				// ajax 호출이 아닐 경우
				viewPage = viewPage + ".tiles";
			}
	
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else {
			// .do 호출일 경우 리다이렉션
			response.sendRedirect(viewPage);
		}
	}

}
