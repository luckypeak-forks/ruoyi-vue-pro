package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 学生班级新增/修改 Request VO")
@Data
public class StudentGradeSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "388")
    private Long id;

    @Schema(description = "学生编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "12557")
    @NotNull(message = "学生编号不能为空")
    private Long studentId;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "名字不能为空")
    private String name;

    @Schema(description = "班主任", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "班主任不能为空")
    private String teacher;

}