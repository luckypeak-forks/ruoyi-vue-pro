package cn.iocoder.yudao.module.demo.dal.dataobject.user;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`t_demo_user`")
public class UserDo extends BaseDO {

	private Long id;

	private String username;

	private String password;

}
