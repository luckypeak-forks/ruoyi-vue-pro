package cn.iocoder.yudao.module.demo.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.demo.controller.app.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - Test")
@RestController
@RequestMapping("/demo/test")
@Validated
@Slf4j
public class AppDemoTestController {

	@GetMapping("/get")
	@Operation(summary = "获取 test 信息")
	public CommonResult<String> get() {
		return success("true");
	}

	@GetMapping("/get/list-cache")
	@Operation(summary = "测试 Redis cache key 和 value 都是 list 会如何操作")
	@Cacheable(cacheNames = "demo-test", key = "#params")
	public CommonResult<List<String>> getListCache(
			@RequestParam("param") List<String> params
	) {
		log.info("param: {}", params.toString());
		return success(params);
	}

	@PostMapping("/get/object-cache")
	@Operation(summary = "测试 Redis cache key 和 value 都是 object 会如何操作")
	@Cacheable(cacheNames = "demo-test", key = "#userVo")
	public CommonResult<UserVo> getObjectCache(
			@RequestBody UserVo userVo
	) {
		log.info("userVo: {}", userVo.toString());
		return success(userVo);
	}


}