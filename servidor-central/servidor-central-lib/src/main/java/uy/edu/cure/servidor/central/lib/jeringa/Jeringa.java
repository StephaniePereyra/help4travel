/**
 * 
 */
package uy.edu.cure.servidor.central.lib.jeringa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.lang.model.element.Element;

/**
 * @author fvillegas
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Jeringa {

	String value();
	
}
