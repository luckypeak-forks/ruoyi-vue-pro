package cn.iocoder.yudao.module.system.service.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.mysql.student.StudentMapper;
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
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.STUDENT_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link StudentServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(StudentServiceImpl.class)
public class StudentServiceImplTest extends BaseDbUnitTest {

    @Resource
    private StudentServiceImpl studentService;

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void testCreateStudent_success() {
        // 准备参数
        StudentSaveReqVO createReqVO = randomPojo(StudentSaveReqVO.class).setId(null);

        // 调用
        Long studentId = studentService.createStudent(createReqVO);
        // 断言
        assertNotNull(studentId);
        // 校验记录的属性是否正确
        StudentDO student = studentMapper.selectById(studentId);
        assertPojoEquals(createReqVO, student, "id");
    }

    @Test
    public void testUpdateStudent_success() {
        // mock 数据
        StudentDO dbStudent = randomPojo(StudentDO.class);
        studentMapper.insert(dbStudent);// @Sql: 先插入出一条存在的数据
        // 准备参数
        StudentSaveReqVO updateReqVO = randomPojo(StudentSaveReqVO.class, o -> {
            o.setId(dbStudent.getId()); // 设置更新的 ID
        });

        // 调用
        studentService.updateStudent(updateReqVO);
        // 校验是否更新正确
        StudentDO student = studentMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, student);
    }

    @Test
    public void testUpdateStudent_notExists() {
        // 准备参数
        StudentSaveReqVO updateReqVO = randomPojo(StudentSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> studentService.updateStudent(updateReqVO), STUDENT_NOT_EXISTS);
    }

    @Test
    public void testDeleteStudent_success() {
        // mock 数据
        StudentDO dbStudent = randomPojo(StudentDO.class);
        studentMapper.insert(dbStudent);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbStudent.getId();

        // 调用
        studentService.deleteStudent(id);
       // 校验数据不存在了
       assertNull(studentMapper.selectById(id));
    }

    @Test
    public void testDeleteStudent_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> studentService.deleteStudent(id), STUDENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetStudentPage() {
       // mock 数据
       StudentDO dbStudent = randomPojo(StudentDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setBirthday(null);
           o.setDescription(null);
           o.setCreateTime(null);
       });
       studentMapper.insert(dbStudent);
       // 测试 name 不匹配
       studentMapper.insert(cloneIgnoreId(dbStudent, o -> o.setName(null)));
       // 测试 birthday 不匹配
       studentMapper.insert(cloneIgnoreId(dbStudent, o -> o.setBirthday(null)));
       // 测试 description 不匹配
       studentMapper.insert(cloneIgnoreId(dbStudent, o -> o.setDescription(null)));
       // 测试 createTime 不匹配
       studentMapper.insert(cloneIgnoreId(dbStudent, o -> o.setCreateTime(null)));
       // 准备参数
       StudentPageReqVO reqVO = new StudentPageReqVO();
       reqVO.setName(null);
       reqVO.setBirthday(null);
       reqVO.setDescription(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<StudentDO> pageResult = studentService.getStudentPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbStudent, pageResult.getList().get(0));
    }

}