package com.stylefeng.guns.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.common.persistence.dao.DeptMapper;
import com.stylefeng.guns.common.persistence.model.Dept;
import com.stylefeng.guns.modular.system.dao.DeptDao;

/**
 * 字典服务测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class DeptTest extends BaseJunit {

	@Resource
	DeptDao deptDao;

	@Resource
	DeptMapper deptMapper;

	@Test
	public void addDeptTest() {
		Dept dept = new Dept();
		dept.setFullname("测试fullname");
		dept.setNum(5);
		dept.setPid(1);
		dept.setSimplename("测试");
		dept.setTips("测试tips");
		dept.setVersion(1);
		Integer insert = deptMapper.insert(dept);
		assertEquals(insert, new Integer(1));
	}

	@Test
	public void updateTest() {
		Dept dept = this.deptMapper.selectById(24);
		dept.setTips("哈哈");
		boolean flag = dept.updateById();
		assertTrue(flag);
	}

	@Test
	public void deleteTest() {
		Dept dept = this.deptMapper.selectById(24);
		Integer integer = deptMapper.deleteById(dept);
		assertTrue(integer > 0);
	}

	@Test
	public void listTest() {
		List<Map<String, Object>> list = this.deptDao.list("总公司");
		assertTrue(list.size() > 0);
	}

	@Test
	public void testName() throws Exception {
			String msg = "Hello World!";
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			// 更新要计算的内容
			md5Digest.update(msg.getBytes());
			// 完成哈希计算，得到摘要
			byte[] md5Encoded = md5Digest.digest();

			MessageDigest shaDigest = MessageDigest.getInstance("SHA");
			// 更新要计算的内容
			shaDigest.update(msg.getBytes());
			// 完成哈希计算，得到摘要
			byte[] shaEncoded = shaDigest.digest();

			System.out.println("原文: " + msg);
			System.out.println("MD5摘要: " + Base64.encodeBase64URLSafeString(md5Encoded));
			System.out.println("SHA摘要: " + Base64.encodeBase64URLSafeString(shaEncoded));
	}
}
