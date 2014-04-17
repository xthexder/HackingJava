package com.shopify.examples.hooks;

import org.frustra.filament.HookUtil;
import org.frustra.filament.Hooks;
import org.frustra.filament.hooking.FilamentClassNode;
import org.frustra.filament.hooking.types.MethodProvider;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import com.shopify.examples.obf.ExampleC;

public class HookC extends MethodProvider {
	public boolean match(FilamentClassNode node) {
		// Filter for only the ExampleC class
		return node.equals(ExampleC.class);
	}
	
	public boolean match(FilamentClassNode node, MethodNode m) {
		// Filter for only the main method
		return m.name.equals("main");
	}

	public void complete(FilamentClassNode node, MethodNode m) {
		// Loop through the bytecode instructions inside the main function
		AbstractInsnNode insn = m.instructions.getFirst();
		while (insn != null) {
			
			// Look for any method call instructions
			if (insn instanceof MethodInsnNode) {
				MethodInsnNode insn2 = (MethodInsnNode) insn;
				
				// Find the first method call pointing to within ExampleC
				if (insn2.owner.equals(Type.getInternalName(ExampleC.class))) {
					// Store the target method as ExampleC.printOne for the injector to use
					Hooks.set("ExampleC.printOne", HookUtil.getMethodNode(insn2.owner, insn2.name, insn2.desc));
					return;
				}
				
			}
			
			insn = insn.getNext();
		}
	}
}
