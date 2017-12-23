package jit.common

interface Code<RT> {
    /**
     * Note that the data structure and the operation cannot be separated well,
     * so the compiler is responsible for dealing with the data structure.
     */
    fun toCompiler(compiler: Compiler): InstructionList
}

interface Statement<RT>: Code<RT> {
}


