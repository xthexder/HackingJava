package com.shopify.examples.hacks;

import java.lang.reflect.Field;

import com.shopify.examples.obf.ExampleA;

public class HackA3 {
	public static void main(String[] args) throws Exception {
		// Find the first field with reflection (the subject field)
		Field f = ExampleA.class.getDeclaredFields()[0];
		
		// Field is private, make it accessible
		f.setAccessible(true);
		
		// Set the field's value to "Shopify"
		f.set(null, "Shopify");
		
		// Run the program
		ExampleA.main(args);
	}
}
