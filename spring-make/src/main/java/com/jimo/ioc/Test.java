package com.jimo.ioc;

/**
 * @author jimo
 * @date 19-2-22 上午10:04
 */
public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ApplicationContext("com.jimo.ioc");
		User user1 = (User) context.getBean("user");
		User user2 = context.getBean("user", User.class);
		System.out.println(user1 == user2);

		SingleBean s1 = (SingleBean) context.getBean("singleBean");
		SingleBean s2 = context.getBean("singleBean", SingleBean.class);
		System.out.println(s1 == s2);
	}
}
