package com.shopify.examples.injectors;

import java.util.List;

import org.frustra.filament.hooking.BadHookException;
import org.frustra.filament.hooking.FilamentClassNode;
import org.frustra.filament.injection.ClassInjector;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import com.shopify.examples.ExampleB;

public class InjectorB extends ClassInjector {
	public boolean match(FilamentClassNode node) {
		// Filter for only the ExampleB class
		return node.equals(ExampleB.class);
	}

	public void inject(FilamentClassNode node) throws BadHookException {
		for (MethodNode method : (List<MethodNode>) node.methods) {
			// Find the main method within ExampleB
			if (method.name.equals("main")) {

				// Loop through the bytecode instructions inside the main function
				AbstractInsnNode insn = method.instructions.getFirst();
				while (insn != null) {
					
					// Find the first Load Constant instruction
					if (insn instanceof LdcInsnNode) {
						LdcInsnNode insn2 = (LdcInsnNode) insn;
						
						// Replace the text inside the string constant
						insn2.cst = ((String) insn2.cst).replace("World", "Shopify");
						return;
					}
					
					insn = insn.getNext();
				}
			}
		}
	}
}
