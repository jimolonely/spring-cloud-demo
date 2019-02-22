package com.jimo.ioc;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jimo
 * @date 19-2-22 上午9:39
 */
public class ApplicationContext {

	private Map<String, Class<?>> beanDefinationFactory = new ConcurrentHashMap<>();

	private Map<String, Object> singletonBeanFactory = new ConcurrentHashMap<>();

	public ApplicationContext(String pkgName) {
		scanPkg(pkgName);
	}

	private void scanPkg(String pkgName) {
		String pkgDir = pkgName.replace(".", "/");
		URL url = getClass().getClassLoader().getResource(pkgDir);
		File file = new File(url.getFile());
		File[] files = file.listFiles(pathname -> {
			String name = pathname.getName();
			if (pathname.isDirectory()) {
				scanPkg(pkgName + "." + name);
			} else {
				return name.endsWith(".class");
			}
			return false;
		});

		for (File f : files) {
			String name = f.getName();
			name = name.substring(0, name.lastIndexOf("."));
			String key = String.valueOf(name.charAt(0)).toLowerCase() + name.substring(1);
			String pkgClass = pkgName + "." + name;
			try {
				Class<?> c = Class.forName(pkgClass);
				if (c.isAnnotationPresent(Component.class)) {
					beanDefinationFactory.put(key, c);
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public Object getBean(String beanId) {
		Class<?> cls = beanDefinationFactory.get(beanId);
		Component component = cls.getAnnotation(Component.class);

		String scope = component.scope();
		try {
			if (Component.SCOPE_SINGLETON.equals(scope)) {
				if (singletonBeanFactory.get(beanId) == null) {
					Object o = cls.newInstance();
					singletonBeanFactory.put(beanId, o);
				}
				return singletonBeanFactory.get(beanId);
			}
			if (Component.SCOPE_PROTOTYPE.equals(scope)) {
				Object o = cls.newInstance();
				return o;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> T getBean(String beanId, Class<T> c) {
		return (T) getBean(beanId);
	}

	public void close() {
		beanDefinationFactory.clear();
		beanDefinationFactory = null;
		singletonBeanFactory.clear();
		singletonBeanFactory = null;
	}
}
