package Doing;

import java.lang.reflect.ParameterizedType;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/1/22，17:49:27
 */
public abstract class Parent<T> {

	private final Class<T> type;
	// private String bmy;

	public Parent() {
		//noinspection unchecked
		type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		System.out.println(type.getName());
		// BMY bmy = this.getClass().getAnnotation(BMY.class);
		// if (bmy != null) {
		// 	System.out.println(bmy.primaryKey());
		// }
	}

}
