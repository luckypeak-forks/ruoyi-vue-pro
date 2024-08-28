package cn.iocoder.yudao.module.system.controller.admin.student;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCoursePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCourseRespVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCourseSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentCourseDO;
import cn.iocoder.yudao.module.system.service.student.StudentCourseService;
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

@Tag(name = "管理后台 - 学生课程")
@RestController
@RequestMapping("/system/student-course")
@Validated
public class StudentCourseController {

    @Resource
    private StudentCourseService studentCourseService;

    @PostMapping("/create")
    @Operation(summary = "创建学生课程")
    @PreAuthorize("@ss.hasPermission('system:student-course:create')")
    public CommonResult<Long> createStudentCourse(@Valid @RequestBody StudentCourseSaveReqVO createReqVO) {
        return success(studentCourseService.createStudentCourse(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新学生课程")
    @PreAuthorize("@ss.hasPermission('system:student-course:update')")
    public CommonResult<Boolean> updateStudentCourse(@Valid @RequestBody StudentCourseSaveReqVO updateReqVO) {
        studentCourseService.updateStudentCourse(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除学生课程")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:student-course:delete')")
    public CommonResult<Boolean> deleteStudentCourse(@RequestParam("id") Long id) {
        studentCourseService.deleteStudentCourse(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得学生课程")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:student-course:query')")
    public CommonResult<StudentCourseRespVO> getStudentCourse(@RequestParam("id") Long id) {
        StudentCourseDO studentCourse = studentCourseService.getStudentCourse(id);
        return success(BeanUtils.toBean(studentCourse, StudentCourseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得学生课程分页")
    @PreAuthorize("@ss.hasPermission('system:student-course:query')")
    public CommonResult<PageResult<StudentCourseRespVO>> getStudentCoursePage(@Valid StudentCoursePageReqVO pageReqVO) {
        PageResult<StudentCourseDO> pageResult = studentCourseService.getStudentCoursePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StudentCourseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出学生课程 Excel")
    @PreAuthorize("@ss.hasPermission('system:student-course:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStudentCourseExcel(@Valid StudentCoursePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StudentCourseDO> list = studentCourseService.getStudentCoursePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "学生课程.xls", "数据", StudentCourseRespVO.class,
                        BeanUtils.toBean(list, StudentCourseRespVO.class));
    }

}