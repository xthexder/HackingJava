package com.shopify.examples.injectors;

import java.util.List;

import org.frustra.filament.HookUtil;
import org.frustra.filament.hooking.BadHookException;
import org.frustra.filament.hooking.FilamentClassNode;
import org.frustra.filament.injection.ClassInjector;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import com.shopify.examples.obf.ExampleC;

public class InjectorC extends ClassInjector {
	public boolean match(FilamentClassNode node) {
		// Filter for only the ExampleC class
		return node.equals(ExampleC.class);
	}

	public void inject(FilamentClassNode node) throws BadHookException {
		for (MethodNode method : (List<MethodNode>) node.methods) {
			// Find the method matching the ExampleC.printOne hook
			if (HookUtil.compareMethodNode(method, "ExampleC.printOne")) {

				// Loop through the bytecode instructions inside the printOne function
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
