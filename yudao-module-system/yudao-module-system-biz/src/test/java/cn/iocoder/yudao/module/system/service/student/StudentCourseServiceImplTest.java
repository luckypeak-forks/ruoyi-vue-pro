package cn.iocoder.yudao.module.system.service.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCoursePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCourseSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentCourseDO;
import cn.iocoder.yudao.module.system.dal.mysql.student.StudentCourseMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomLongId;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomPojo;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.STUDENT_COURSE_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link StudentCourseServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(StudentCourseServiceImpl.class)
public class StudentCourseServiceImplTest extends BaseDbUnitTest {

    @Resource
    private StudentCourseServiceImpl studentCourseService;

    @Resource
    private StudentCourseMapper studentCourseMapper;

    @Test
    public void testCreateStudentCourse_success() {
        // 准备参数
        StudentCourseSaveReqVO createReqVO = randomPojo(StudentCourseSaveReqVO.class).setId(null);

        // 调用
        Long studentCourseId = studentCourseService.createStudentCourse(createReqVO);
        // 断言
        assertNotNull(studentCourseId);
        // 校验记录的属性是否正确
        StudentCourseDO studentCourse = studentCourseMapper.selectById(studentCourseId);
        assertPojoEquals(createReqVO, studentCourse, "id");
    }

    @Test
    public void testUpdateStudentCourse_success() {
        // mock 数据
        StudentCourseDO dbStudentCourse = randomPojo(StudentCourseDO.class);
        studentCourseMapper.insert(dbStudentCourse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        StudentCourseSaveReqVO updateReqVO = randomPojo(StudentCourseSaveReqVO.class, o -> {
            o.setId(dbStudentCourse.getId()); // 设置更新的 ID
        });

        // 调用
        studentCourseService.updateStudentCourse(updateReqVO);
        // 校验是否更新正确
        StudentCourseDO studentCourse = studentCourseMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, studentCourse);
    }

    @Test
    public void testUpdateStudentCourse_notExists() {
        // 准备参数
        StudentCourseSaveReqVO updateReqVO = randomPojo(StudentCourseSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> studentCourseService.updateStudentCourse(updateReqVO), STUDENT_COURSE_NOT_EXISTS);
    }

    @Test
    public void testDeleteStudentCourse_success() {
        // mock 数据
        StudentCourseDO dbStudentCourse = randomPojo(StudentCourseDO.class);
        studentCourseMapper.insert(dbStudentCourse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbStudentCourse.getId();

        // 调用
        studentCourseService.deleteStudentCourse(id);
       // 校验数据不存在了
       assertNull(studentCourseMapper.selectById(id));
    }

    @Test
    public void testDeleteStudentCourse_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> studentCourseService.deleteStudentCourse(id), STUDENT_COURSE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetStudentCoursePage() {
       // mock 数据
       StudentCourseDO dbStudentCourse = randomPojo(StudentCourseDO.class, o -> { // 等会查询到
           o.setStudentId(null);
           o.setName(null);
           o.setScore(null);
           o.setCreateTime(null);
       });
       studentCourseMapper.insert(dbStudentCourse);
       // 测试 studentId 不匹配
       studentCourseMapper.insert(cloneIgnoreId(dbStudentCourse, o -> o.setStudentId(null)));
       // 测试 name 不匹配
       studentCourseMapper.insert(cloneIgnoreId(dbStudentCourse, o -> o.setName(null)));
       // 测试 score 不匹配
       studentCourseMapper.insert(cloneIgnoreId(dbStudentCourse, o -> o.setScore(null)));
       // 测试 createTime 不匹配
       studentCourseMapper.insert(cloneIgnoreId(dbStudentCourse, o -> o.setCreateTime(null)));
       // 准备参数
       StudentCoursePageReqVO reqVO = new StudentCoursePageReqVO();
       reqVO.setStudentId(null);
       reqVO.setName(null);
       reqVO.setScore(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<StudentCourseDO> pageResult = studentCourseService.getStudentCoursePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbStudentCourse, pageResult.getList().get(0));
    }

}