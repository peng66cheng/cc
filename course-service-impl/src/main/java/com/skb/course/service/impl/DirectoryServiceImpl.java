package com.skb.course.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.skb.course.common.util.CourseRedisKey;
import com.skb.course.dao.TeacherDirectoryDAO;
import com.skb.course.domain.TeacherDirectoryDO;
import com.skb.course.integration.model.Directory;
import com.skb.course.integration.model.enums.DirectoryTypeEnum;
import com.skb.course.integration.model.enums.PermissionEnum;
import com.skb.course.integration.service.DirectoryService;
import com.skb.util.BeanCopyUtils;
import com.skb.util.ObjectConverter;
import com.skb.util.RedisUtils;
import com.skb.util.sort.SortRange;
import com.skb.util.sort.SortUtil;
import com.skb.util.sort.Sortable;

@Service("directoryService")
public class DirectoryServiceImpl implements DirectoryService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DirectoryServiceImpl.class);

	@Autowired
	private TeacherDirectoryDAO directoryDao;

	@Override
	public int addDirectoryService(Directory directory) {
		if (!StringUtils.hasText(directory.getName()) || directory.getTeacherId() == null
				|| directory.getTeacherId() <= 0) {
			logger.warn("参数错误{}", directory);
			return 0;
		}
		TeacherDirectoryDO teachDir = BeanCopyUtils.convertClass(directory, TeacherDirectoryDO.class,
				new Directory2DO());
		teachDir.setOrderId(getOrderId(directory.getTeacherId(), directory.getDirectoryType()));
		return directoryDao.insertSelective(teachDir);
	}

	/**
	 * 获取到目录的 排序号
	 * 
	 * @param teacherId
	 * @param directoryType
	 * @return
	 */
	private int getOrderId(Integer teacherId, DirectoryTypeEnum directoryType) {
		return RedisUtils.getJedisClient().incr(CourseRedisKey.TEACHER_DIR_ORDERID + directoryType + "_" + teacherId)
				.intValue();
	}

	@Override
	public int changeDirectoryOrder(Integer directoryId, Integer targetOrderId) {

		if (directoryId == null || targetOrderId == null) {
			logger.warn("参数错误directoryId={}, targetOrderId={}", directoryId, targetOrderId);
			return 0;
		}

		TeacherDirectoryDO orgDirectory = directoryDao.selectByPrimaryKey(directoryId);
		int orgOrderId = orgDirectory.getOrderId();
		if (orgOrderId == targetOrderId.intValue()) {// 比较当前序号与目标序号，如果相同，则不移动
			logger.warn("原始顺序与目标顺序一致：directoryId={}, targetOrderId={}", directoryId, targetOrderId);
			return 0;
		}

		// 1 获取到 被移动目录 之前，目标顺序 之后的 所有目录。
		SortRange orderRange = SortUtil.getStartEndOrder(orgOrderId, targetOrderId);
		List<TeacherDirectoryDO> needOrderedList = directoryDao.getBetweenDir(orgDirectory.getTeacherId(),
				orgDirectory.getType(), orderRange.getStartOrderIndex(), orderRange.getEndOrderIndex());
		// 2 将1中获取的目录，更改顺序
		TeacherDirectoryDO orderedObj = new TeacherDirectoryDO();
		orderedObj.setId(orgDirectory.getId());
		orderedObj.setOrderId(orgDirectory.getOrderId());
		List<Sortable> reOrderDirs = SortUtil.getOrderedList(orderedObj, targetOrderId, needOrderedList);
		if (reOrderDirs == null) {// 重排序列表为空，直接返回
			logger.warn("排序列表为空：directoryId={}, targetOrderId={}", directoryId, targetOrderId);
			return 0;
		}
		// 3 设置 被移动的目录 顺序号
		return directoryDao.batchModifyDirOrder(reOrderDirs);
	}

	@Override
	public int modifyDirectory(Integer teacherId, Integer directoryId, String directoryName,
			PermissionEnum permission) {
		if (directoryId == null || (!StringUtils.hasText(directoryName) && permission == null)) {
			logger.warn("参数错误：directoryId＝{}, directoryName={}, permission={}",
					new Object[] { directoryId, directoryName, permission });
			return 0;
		}
		TeacherDirectoryDO teacherDirectoryDO = new TeacherDirectoryDO();
		teacherDirectoryDO.setId(directoryId);
		if (StringUtils.hasText(directoryName)) {
			teacherDirectoryDO.setName(directoryName);
		}
		if (permission != null) {
			teacherDirectoryDO.setPermission(permission.getId());
			RedisUtils.del(CourseRedisKey.public_course_num + ":" + teacherId);
			RedisUtils.del(CourseRedisKey.publi_document_num + ":" + teacherId);
		}
		return directoryDao.updateByPrimaryKeySelective(teacherDirectoryDO);
	}

	class Directory2DO implements ObjectConverter<Directory, TeacherDirectoryDO> {

		@Override
		public void convert(Directory source, TeacherDirectoryDO target) {
			target.setPermission(source.getPermissionEnum() != null ? source.getPermissionEnum().getId() : null);
			target.setType(source.getDirectoryType() != null ? source.getDirectoryType().getId() : null);
		}

	}

	class DO2Directory implements ObjectConverter<TeacherDirectoryDO, Directory> {

		@Override
		public void convert(TeacherDirectoryDO source, Directory target) {
			target.setPermissionEnum(PermissionEnum.getPermission(source.getPermission()));
			target.setDirectoryType(DirectoryTypeEnum.getDirectoryType(source.getType()));
		}

	}

	@Override
	public List<Directory> getTeacherDirectory(Integer teacherId, DirectoryTypeEnum type) {
		return this.getTeacherDirectory(teacherId, type, null);
	}

	@Override
	public List<Directory> getTeacherPublicDirectory(Integer teacherId, DirectoryTypeEnum type) {
		return this.getTeacherDirectory(teacherId, type, PermissionEnum.PUBLIC);
	}

	private List<Directory> getTeacherDirectory(Integer teacherId, DirectoryTypeEnum type,
			PermissionEnum permissionEnum) {

		if (type == null || teacherId == null || teacherId <= 0) {
			logger.warn("参数错误teacherId={}, type={}", teacherId, type);
			return null;
		}
		Byte permission = null;
		if (permissionEnum != null)
			permission = permissionEnum.getId();
		List<TeacherDirectoryDO> resultTemp = directoryDao.selectTeacherDirectory(teacherId, type.getId(), permission);
		return BeanCopyUtils.convertList(resultTemp, Directory.class, new DO2Directory());
	}

	@Override
	public Directory getDirectory(Integer directoryId) {
		if (directoryId == null) {
			return null;
		}
		TeacherDirectoryDO dir = directoryDao.selectByPrimaryKey(directoryId);

		return BeanCopyUtils.convertClass(dir, Directory.class, new DO2Directory());
	}

}
