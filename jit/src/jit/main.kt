package jit

import jit.code.BinArithmCode
import jit.code.BinaryLogicCode
import jit.code.CodeCombi
import jit.code.ConstCode
import jit.code.DeclarationCode
import jit.code.FunCallCode
import jit.code.FunDefCode
import jit.code.IfCode
import jit.code.PackageCode
import jit.code.UnaryArithmCode
import jit.code.VarCode
import jit.common.BinaryArithmOperation
import jit.common.BinaryNumberLogicOperation
import jit.common.Name
import jit.common.UnaryArithmOperation
import jit.jit.JIT

// todo: rewrite: store the benchmark data outside the preliminary-compiled object (perhaps pass it in)
// todo: how to connect preliminary compiled versions to final ones? is that even necessary? not really if the benchmark data is separate
// todo: mark most AST nodes as Exec except the ones that are different in prelim/opt mode (e.g. If)
// todo: make function definition keep back-references to invoke sites
// todo: make sure invoke sites don't know about optimization level of callee
// todo: make every prelim expression count it's calls (but not necessarily have the same cutoff)
// todo: can I already do recursion? I think so...

fun main(args: Array<String>) {
    val a = VarCode(Name("a"))
    val a2 = VarCode(Name("a2"))
    val b = VarCode(Name("b"))
    val source = PackageCode(listOf(
            FunDefCode(FunCallCode(Name("call_me"), ConstCode(3)), Name("main")),
            FunDefCode(CodeCombi(
                    DeclarationCode(a2, DeclarationCode(a, UnaryArithmCode(UnaryArithmOperation.SQR, ConstCode(4)))),
                    DeclarationCode(b, BinArithmCode(BinaryArithmOperation.SUB, a, ConstCode(7))),
                    last=IfCode(
                            BinaryLogicCode(BinaryNumberLogicOperation.GTE, b, ConstCode(10)),
                            a,
                            UnaryArithmCode(UnaryArithmOperation.NEG, b))
            ), Name("call_me"), listOf(Name("arg_one")))
    ))
    print(source.toText())
    JIT(source).run()
//    Processor(pack).run()
}


