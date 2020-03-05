package io.github.ttallaemideul.config.database;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * h2database mapper annotaion
 * @author TtalLaeMiDeul
 *
 */
@Target({ ElementType.TYPE }) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented 
@Component
public @interface DbH2ConnMapper {
	String value() default "";
}
