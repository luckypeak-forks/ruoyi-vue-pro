package cn.iocoder.yudao.module.system.controller.admin.student;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentGradePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentGradeRespVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentGradeSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentGradeDO;
import cn.iocoder.yudao.module.system.service.student.StudentGradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 学生班级")
@RestController
@RequestMapping("/system/student-grade")
@Validated
public class StudentGradeController {

    @Resource
    private StudentGradeService studentGradeService;

    @PostMapping("/create")
    @Operation(summary = "创建学生班级")
    @PreAuthorize("@ss.hasPermission('system:student-grade:create')")
    public CommonResult<Long> createStudentGrade(@Valid @RequestBody StudentGradeSaveReqVO createReqVO) {
        return success(studentGradeService.createStudentGrade(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新学生班级")
    @PreAuthorize("@ss.hasPermission('system:student-grade:update')")
    public CommonResult<Boolean> updateStudentGrade(@Valid @RequestBody StudentGradeSaveReqVO updateReqVO) {
        studentGradeService.updateStudentGrade(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除学生班级")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:student-grade:delete')")
    public CommonResult<Boolean> deleteStudentGrade(@RequestParam("id") Long id) {
        studentGradeService.deleteStudentGrade(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得学生班级")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:student-grade:query')")
    public CommonResult<StudentGradeRespVO> getStudentGrade(@RequestParam("id") Long id) {
        StudentGradeDO studentGrade = studentGradeService.getStudentGrade(id);
        return success(BeanUtils.toBean(studentGrade, StudentGradeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得学生班级分页")
    @PreAuthorize("@ss.hasPermission('system:student-grade:query')")
    public CommonResult<PageResult<StudentGradeRespVO>> getStudentGradePage(@Valid StudentGradePageReqVO pageReqVO) {
        PageResult<StudentGradeDO> pageResult = studentGradeService.getStudentGradePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StudentGradeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出学生班级 Excel")
    @PreAuthorize("@ss.hasPermission('system:student-grade:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStudentGradeExcel(@Valid StudentGradePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StudentGradeDO> list = studentGradeService.getStudentGradePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "学生班级.xls", "数据", StudentGradeRespVO.class,
                        BeanUtils.toBean(list, StudentGradeRespVO.class));
    }

}