package com.jimo.ioc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 创建对象的几种方式
 * @author jimo
 * @date 19-2-22 下午4:23
 */
public class CreateInstance {

	public static void main(String[] args) throws Exception {

		// 1. new
		User user1 = new User();

		// 2. reflect
		Class<?> cls = Class.forName("com.jimo.ioc.User");
		User user2 = (User) cls.newInstance();
///		User user3 = User.class.newInstance();

		Constructor<?> c1 = cls.getDeclaredConstructor(String.class);
		c1.setAccessible(true);
		User user4 = (User) c1.newInstance("jimo");

		// 3. clone() 略

		// 4. 序列化/反序列化

		// write
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("User.file"));
		out.writeObject(user4);
		out.close();

		// read
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("User.file"));
		User user5 = (User) in.readObject();
		System.out.println(user5.getName());

	}
}
