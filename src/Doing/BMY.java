package Doing;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/1/16，18:22
 */
@Inherited
@Retention(RUNTIME)
public @interface BMY {
	String primaryKey();
}