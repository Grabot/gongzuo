package jit.comp

import jit.hardware.Processor
import jit.code.VarCode

class VarExec(val varCodeElem: VarCode): PrelimExec<Int>, OptExec<Int> {

    override fun run(proc: Processor): Int {
        throw NotImplementedError()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}