package com.shopify.examples.hacks;

import java.lang.reflect.Method;

import org.frustra.filament.FilamentClassLoader;
import org.frustra.filament.Injectors;

import com.shopify.examples.ExampleB;

public class HackB {
	private static FilamentClassLoader loader = new FilamentClassLoader(false);
	
	public static void main(String[] args) throws Exception {
		// Load the examples package so we can modify the bytecode
		loader.loadPackage("com.shopify.examples");
		
		// Register the injector we're going to be using on our example
		Injectors.register("com.shopify.examples.injectors");

		// Run the program with the modified bytecode
		Class<?> cls = loader.loadClass(ExampleB.class.getName());
		Method entryPoint = cls.getDeclaredMethod("main", new Class[] {String[].class});
		entryPoint.invoke(null, new Object[] {args});
	}
}
