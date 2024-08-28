package cn.iocoder.yudao.module.system.controller.admin.student.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 学生班级 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StudentGradeRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "388")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "学生编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "12557")
    @ExcelProperty("学生编号")
    private Long studentId;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("名字")
    private String name;

    @Schema(description = "班主任", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("班主任")
    private String teacher;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}