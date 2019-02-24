package com.jimo.mvc.servlet;

import com.jimo.mvc.annotation.Controller;
import com.jimo.mvc.annotation.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author jimo
 * @date 19-2-22 下午5:20
 */
public class DispatcherServlet extends HttpServlet {

	private Properties properties = new Properties();

	private List<String> classNames = new ArrayList<>();

	private Map<String, Object> ioc = new HashMap<>();

	private Map<String, Method> handlerMapping = new HashMap<>();

	private Map<String, Object> controllerMap = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// load config file
		doLoadConfig(config.getInitParameter("contextConfigLocation"));

		// get all class name
		doScanner(properties.getProperty("scanPackage"));

		// get bean instance
		getBeanInstance();

		// init handler mapping
		initHandlerMapping();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			doDispatch(req, resp);
		} catch (Exception e) {
			resp.getWriter().write("500!---" + e.getMessage());
		}
	}

	private void doLoadConfig(String location) {
		InputStream resource = this.getClass().getClassLoader().getResourceAsStream(location);
		try {
			properties.load(resource);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != resource) {
				try {
					resource.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void doScanner(String pkg) {
		URL url = this.getClass().getClassLoader().getResource("/" + pkg.replaceAll("\\.", "/"));
		File dir = new File(Objects.requireNonNull(url).getFile());
		for (File file : Objects.requireNonNull(dir.listFiles())) {
			if (file.isDirectory()) {
				doScanner(pkg + "." + file.getName());
			} else {
				classNames.add(pkg + "." + file.getName().replace(".class", ""));
			}
		}
	}

	private void getBeanInstance() {
		if (classNames.isEmpty()) {
			return;
		}
		for (String className : classNames) {
			try {
				Class<?> cls = Class.forName(className);
				if (cls.isAnnotationPresent(Controller.class)) {
					String key = cls.getSimpleName().substring(0, 1).toLowerCase() + cls.getSimpleName().substring(1);
					ioc.put(key, cls.newInstance());
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void initHandlerMapping() {
		if (ioc.isEmpty()) {
			return;
		}
		try {
			for (Map.Entry<String, Object> entry : ioc.entrySet()) {
				Class<?> cls = entry.getValue().getClass();

				String baseUrl = "";
				if (cls.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping a = cls.getAnnotation(RequestMapping.class);
					baseUrl = a.value();
				}

				Method[] methods = cls.getMethods();
				for (Method method : methods) {
					if (!method.isAnnotationPresent(RequestMapping.class)) {
						continue;
					}
					RequestMapping a = method.getAnnotation(RequestMapping.class);
					String url = a.value();
					url = (baseUrl + "/" + url).replaceAll("/+", "/");
					handlerMapping.put(url, method);
					controllerMap.put(url, cls.newInstance());
					System.out.println("url=" + url + ",method=" + method);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, InvocationTargetException, IllegalAccessException {
		if (handlerMapping.isEmpty()) {
			return;
		}
		String requestUrl = req.getRequestURI();
		String contextPath = req.getContextPath();
		requestUrl = requestUrl.replace(contextPath, "").replaceAll("/+", "/");
		if (!handlerMapping.containsKey(requestUrl)) {
			resp.getWriter().write("404! 找不到URI：" + requestUrl);
			return;
		}
		Method method = handlerMapping.get(requestUrl);

		Object result = method.invoke(controllerMap.get(requestUrl), getParams(method, req, resp));
		Class<?> returnType = method.getReturnType();
		System.out.println("returnType:" + returnType);
		if (returnType.equals(String.class)) {
			resp.getWriter().write(result.toString());
		}
	}

	private Object[] getParams(Method method, HttpServletRequest req, HttpServletResponse resp) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		Map<String, String[]> parameterMap = req.getParameterMap();
		Object[] params = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			Class<?> type = parameterTypes[i];
			String name = type.getSimpleName();
			if ("HttpServletRequest".equals(name)) {
				params[i] = req;
			} else if ("HttpServletResponse".equals(name)) {
				params[i] = resp;
			} else if ("String".equals(name)) {
				for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
					String s = Arrays.toString(entry.getValue());
					System.out.println("params s:" + s);
					params[i] = s.replaceAll("\\[|\\]", "")
							.replaceAll(",\\s", ",");
				}
			}
		}
		return params;
	}
}
