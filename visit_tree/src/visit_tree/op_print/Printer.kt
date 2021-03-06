package visit_tree.op_print

import visit_tree.data.Leaf
import visit_tree.data.Split
import visit_tree.data.TreeVisitor

class Printer: TreeVisitor<CharSequence> {
    override fun combine(inputs: Iterable<CharSequence>): CharSequence {
        return inputs.joinToString(" ")
    }

    override fun visit(leaf: Leaf): CharSequence {
        return "leaf"
    }

    override fun visit(split: Split): CharSequence {
        return "split"
    }
}
