/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.project_model.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation should be used to define the location of ports XML data file.
 * @author uko
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PortsData
{
	/**
	 * Path to a datafile starting from classes directory. Default: "ports.xml"
	 * @return path to ports XML data file.
	 */
	String portsFile() default "ports.xml";
}
