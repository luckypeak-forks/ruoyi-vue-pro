package cn.iocoder.yudao.module.demo.service.user;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.demo.controller.app.vo.UserVo;
import cn.iocoder.yudao.module.demo.dal.dataobject.user.UserDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final cn.iocoder.yudao.module.demo.dal.mysql.user.DemoUserMapper demoUserMapper;

	@Override
	public void createUser(UserVo user) {
		UserDo userDo = BeanUtils.toBean(user, UserDo.class);
		demoUserMapper.insert(userDo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(cacheNames = "demo-test-user", key = "#user.id")
	public void updateUser(UserVo user) {
		UserDo userDo = BeanUtils.toBean(user, UserDo.class);
		demoUserMapper.updateById(userDo);
	}

	@Override
	public void deleteUser(Long id) {
		demoUserMapper.deleteById(id);
	}

	@Override
	@Cacheable(cacheNames = "demo-test-user", key = "#id")
	public UserVo getUser(Long id) {
		UserDo userDo = demoUserMapper.selectById(id);
		return BeanUtils.toBean(userDo, UserVo.class);
	}

	@Override
	public List<UserVo> listUsers() {
		List<UserDo> userDos = demoUserMapper.selectList();
		return BeanUtils.toBean(userDos, UserVo.class);
	}
}
