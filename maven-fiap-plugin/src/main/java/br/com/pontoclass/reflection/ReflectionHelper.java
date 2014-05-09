package br.com.pontoclass.reflection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import br.com.pontoclass.reflection.exception.ReflectionException;

public class ReflectionHelper {
	public static <T> List<Class<T>> findClassesImplementing(final Class<T> interfaceClass, final Package fromPackage) throws ReflectionException {
		if (interfaceClass == null) {
			throw new ReflectionException("Unknown subclass.");
		}
		if (fromPackage == null) {
			throw new ReflectionException("Unknown package.");
		}
		final List<Class<T>> rVal = new ArrayList<Class<T>>();
		try {
			final Class<T>[] targets = getAllClassesFromPackage(fromPackage.getName(), interfaceClass);
			if (targets != null) {
				for (Class<T> aTarget : targets) {
					if (aTarget == null || aTarget.equals(interfaceClass) || (!interfaceClass.isAssignableFrom(aTarget))) {
						continue;
					}
					rVal.add(aTarget);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new ReflectionException("Error reading package name.", e);
		}
		catch (IOException e) {
			throw new ReflectionException("Error reading classes in package.", e);
		}

		return rVal;
	}

	/**
	 * Load all classes from a package.
	 * 
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T>[] getAllClassesFromPackage(final String packageName, Class<T> clazz) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = ClassLoader.getSystemResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class<T>> classes = new ArrayList<Class<T>>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName, clazz));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Find file in package.
	 * 
	 * @param directory
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<Class<T>> findClasses(File directory, String packageName, Class<T> clazz) throws ClassNotFoundException {
		List<Class<T>> classes = new ArrayList<Class<T>>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName(), clazz));
			} else if (file.getName().endsWith(".class")) {
				classes.add(clazz.getClass().cast(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6))));
			}
		}
		return classes;
	}
}