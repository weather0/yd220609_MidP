package co.edu.kanumovie.admin.serviceimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.edu.kanumovie.admin.service.AdminMapper;
import co.edu.kanumovie.admin.service.AdminService;
import co.edu.kanumovie.common.DataSource;
import co.edu.kanumovie.user.vo.UserVO;

public class AdminServiceImpl implements AdminService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private AdminMapper map = sqlSession.getMapper(AdminMapper.class);
	
	
	@Override
	public List<UserVO> selectAllUserList() {
		return map.selectAllUserList();
	}

	@Override
	public int todayUsersCount() {
		return map.todayUsersCount();
	}

	@Override
	public int selectAllUserCount() {
		
		return map.selectAllUserCount();
	}

	@Override
	public List<UserVO> selectReportUserList() {
		return map.selectReportUserList();
	}

	@Override
	public int updateBlockCheck(String email) {
		
		return map.updateBlockCheck(email);
	}

	@Override
	public int updateReportCheck(String email) {
		
		return map.updateReportCheck(email);
	}

	@Override
	public List<UserVO> selectBlackList() {
		
		return map.selectBlackList();
	}

	@Override
	public int updateUnblockCheck(String email) {
		
		return map.updateUnblockCheck(email);
	}

	@Override
	public int selectAllReportUsers() {
		
		return map.selectAllReportUsers();
	}

	@Override
	public int selectCountWeeklySignUpUsersCount(String weeks) {
		
		return map.selectCountWeeklySignUpUsersCount(weeks);
	}

	


}
