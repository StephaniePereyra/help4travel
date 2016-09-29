/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib.jeringa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 *
 * @author SCN
 */
public class JeringaInjector {
    
    private static JeringaInjector instance;

	private Properties jeringaProperties;
	private InputStream input = null;
	
	static {
		if (instance == null) {
			try {
				instance = new JeringaInjector();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static JeringaInjector getInstance() {
		return instance;
	}

	private JeringaInjector() throws IOException {
		this.jeringaProperties = new Properties();
		input = this.getClass().getClassLoader().getResourceAsStream("jeringa.properties");
		jeringaProperties.load(input);
	}

	public void inyectar(Object obj) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			Annotation[] annotations = f.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Jeringa) {
					Jeringa jeringa = (Jeringa) annotation;
					String value = jeringa.value();
					String clazzFQN = jeringaProperties.getProperty(value); 
					Class<?> newClazz = Class.forName(clazzFQN);
					Constructor<?> constructor = newClazz.getConstructor();
					Object newObj = constructor.newInstance();
					f.setAccessible(true);
					f.set(obj, newObj);
					f.setAccessible(false);
				}
			}
		}
	}
    
}
