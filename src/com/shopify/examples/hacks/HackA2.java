package com.shopify.examples.hacks;

import java.lang.reflect.Field;

import com.shopify.examples.obf.ExampleA;

public class HackA2 {
	public static void main(String[] args) throws Exception {
		// Find the subject field with reflection (in the obfuscated code)
		Field f = ExampleA.class.getDeclaredField("subject");
		
		// Field is private, make it accessible
		f.setAccessible(true);
		
		// Set the field's value to "Shopify"
		f.set(null, "Shopify");
		
		// Run the program
		ExampleA.main(args);
	}
}
