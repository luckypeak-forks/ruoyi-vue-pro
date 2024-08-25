package cn.iocoder.yudao.module.demo.service.user;

import cn.iocoder.yudao.module.demo.controller.app.vo.UserVo;

import java.util.List;

public interface UserService {

	void createUser(UserVo user);

	void updateUser(UserVo user);

	void deleteUser(Long id);

	UserVo getUser(Long id);

	List<UserVo> listUsers();

}
