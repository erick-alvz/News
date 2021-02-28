package app.erickalvz.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}
