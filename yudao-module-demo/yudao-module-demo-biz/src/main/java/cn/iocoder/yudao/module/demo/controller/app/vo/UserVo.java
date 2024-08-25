package cn.iocoder.yudao.module.demo.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户 App - Test Response VO")
@Data
public class UserVo {

	@Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long id;

	@Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "rf")
	private String username;

	@Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "rf")
	private String password;

}
