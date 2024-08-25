package cn.iocoder.yudao.module.demo.dal.mysql.user;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.demo.dal.dataobject.user.UserDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoUserMapper extends BaseMapperX<UserDo> {
}
