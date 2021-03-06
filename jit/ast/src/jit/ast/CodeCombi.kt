package jit.ast

import jit.common.Code
import jit.common.Compiler
import jit.common.Inter
import jit.common.Statement

/**
 * Some statements followed by an expression (which produces the return value).
 */
class CodeCombi(vararg statements: Statement<Int>, last: Code<Int>): Code<Int>, Iterable<Code<Int>> {

    var codes: List<Code<Int>>

    /**
     * If any of the statements or ast are itself combinations ({@link CodeCombi}), flatten their content into this one.
     */
    init {
        val flattenedCodes: MutableList<Code<Int>> = mutableListOf()
        for (code in statements) {
//            if (ast is CodeCombi) {
//                for (subcode in ast) {
//                    flattenedCodes.add(subcode)
//                }
//            } else {
                flattenedCodes.add(code)
//            }
        }
        if (last is CodeCombi) {
            for (subcode in last) {
                flattenedCodes.add(subcode)
            }
        } else {
            flattenedCodes.add(last)
        }
        codes = flattenedCodes
    }

    override fun toCompiler(compiler: Compiler): Inter<Int> {
        return compiler.compile(this)
    }

    override fun toText(): CharSequence {
        val text = StringBuilder()
        for (code in codes) {
            text.append(code.toText())
        }
        return text
    }

    override fun iterator(): Iterator<Code<Int>> {
        return codes.iterator()
    }
}

