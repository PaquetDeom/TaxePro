package fr.paquet.framework.ui;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Ihm {
	int ordre() default 999;
	boolean visible() default true;
	boolean editable() default true;
	String grid() default "hidden";
	String form() default "hidden";
	String libelle() default "";
	String description() default "";
	String type() default "text";
	
}
