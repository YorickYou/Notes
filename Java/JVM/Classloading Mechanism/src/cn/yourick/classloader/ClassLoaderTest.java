package cn.yourick.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ClassLoader classLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				
				try {
					String filename = name.substring(name.lastIndexOf(".")+1) + ".class";
					System.out.println(filename);
					//getClass() 返回此Object的运行时类
					InputStream is = getClass().getResourceAsStream(filename);
					if(is == null){
						return super.loadClass(name);
					}
					//available()返回从该输入流中可以读取（或跳过）的字节数的估计值，而不会被下一次调用此输入流的方法阻塞。
					byte[] b = new byte[is.available()];
					//从输入流读取一些字节数，并将它们存储到缓冲区 b 。
					is.read(b);
					//defineClass(name, b, 0, b.length); 将字节数组转换为类别 类的实例
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					throw new ClassNotFoundException();
				}
			}
		};
		//loadClass(String name)   用指定的binary name 加载课程 。 
		Object obj = classLoader.loadClass("cn.yourick.classloader.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof cn.yourick.classloader.ClassLoaderTest);
	}
	
}
