package cn.youric.exception;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM17 {

	/*public static void main(String[] args) {
		String str1 = new StringBuilder("�����").append("����").toString();
		System.out.println(str1.intern() == str1);
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}*/
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}

	}

}
