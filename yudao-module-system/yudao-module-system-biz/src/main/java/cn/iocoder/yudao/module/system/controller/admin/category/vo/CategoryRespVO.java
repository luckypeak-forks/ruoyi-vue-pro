package cn.iocoder.yudao.module.system.controller.admin.category.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CategoryRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "19728")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("名字")
    private String name;

    @Schema(description = "父级编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "31401")
    @ExcelProperty("父级编号")
    private Long parentId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}