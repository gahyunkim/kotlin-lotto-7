package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

enum class LottoError(val message: String) {
    INVALID_AMOUNT("[ERROR] 유효한 금액을 입력해주세요."),
    INVALID_UNIT("[ERROR] 로또 금액은 1000원 단위로 입력해야 합니다.");

    fun throwException(): IllegalArgumentException {
        return IllegalArgumentException(this.message)
    }
}

fun main() {
    val amount = readAndValidateAmount()
    val numberOfLottos = calculateNumberOfLottos(amount)
    println("${numberOfLottos}개를 구매했습니다.")
    generateLottos(numberOfLottos)
}

fun readAndValidateAmount(): Int {
    while (true) {
        try {
            val amount = promptUserForAmount()
            Validation.validateAmount(amount) // 이 부분도 try 블록 안에 포함됨
            return amount
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun promptUserForAmount(): Int {
    println("구입금액을 입력해 주세요.")
    val input = Console.readLine()?.trim()
    return Validation.parseAmount(input)
}

fun calculateNumberOfLottos(amount:Int):Int{
    println()
    return amount /1000
}

fun generateLottos(count:Int){
    repeat(count) {
        val lottoNumbers = generateRandomLottoNumbers()
        println(lottoNumbers)
    }
}

fun generateRandomLottoNumbers(): List<Int> {
    return Randoms.pickUniqueNumbersInRange(1, 45, 6).sorted()
}