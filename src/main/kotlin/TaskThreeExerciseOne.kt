
const val NOW = "right now"
const val HOUR = "minutes ago"
const val DAY = "hours ago"
const val secondDAY = "today"
const val thirdDAY = "yesterday"
const val moreThenThreeDAYS = "long time ago"

fun main(){
    val howManySecAgoUserWasOnline = 66000
    finalAnswer(howManySecAgoUserWasOnline)

}

fun finalAnswer(howManySecAgoUserWasOnline: Int){
    val timeInDigits:Array<String> = arrayOf(HOUR,DAY)
    val timeWithoutDigits:Array<String> = arrayOf(NOW,secondDAY,thirdDAY,moreThenThreeDAYS)
    var finalTimeMinOrHours = ""
    var timeDigits = 0
    var timeWord = ""

    when (onlineTimeRule(howManySecAgoUserWasOnline)) {
        in timeInDigits -> {
            timeDigits = ifAnswerWithDigits(onlineTimeRule(howManySecAgoUserWasOnline),howManySecAgoUserWasOnline)
            finalTimeMinOrHours = endingsForMinAndHours(onlineTimeRule(howManySecAgoUserWasOnline),timeDigits)
            print("Был(а) в сети $timeDigits $finalTimeMinOrHours назад")
        }
        in timeWithoutDigits -> {
            timeWord = ifAnswerWithoutDigits(onlineTimeRule(howManySecAgoUserWasOnline))
            println("Был(а) в сети $timeWord ")
        }
    }
}

fun onlineTimeRule(howManySecAgoUserWasOnline:Int): String {
    return when (howManySecAgoUserWasOnline) {
        in (0..60) -> NOW
        in (61..3600) -> HOUR
        in (3601..86400) -> DAY
        in (86401..172800) -> secondDAY
        in (172801..259200) -> thirdDAY
        else -> moreThenThreeDAYS
    }
}

fun ifAnswerWithDigits(timeRule:String,howManySecAgoUserWasOnline:Int): Int{
    return when (timeRule) {
        HOUR ->  howManySecAgoUserWasOnline/60
        else ->  howManySecAgoUserWasOnline/3600
    }
}

fun ifAnswerWithoutDigits(timeRule:String):String{

    return when (timeRule) {
        NOW -> "только что"
        secondDAY -> "сегодня"
        thirdDAY -> "вчера"
        else -> "давно"
    }
}


fun endingsForMinAndHours(timeRule:String,timeDigits:Int):String{

    val digitsGroupTwo:Array<Int> = arrayOf(2,3,4)
    var lastTwoDigits = if (timeDigits>10) timeDigits/100 else timeDigits
    var endWord = ""


    if (timeRule==HOUR) {
        endWord = when (lastTwoDigits) {
            1 -> "минуту"
            in digitsGroupTwo -> "минуты"
            else -> "минут"
        }
    }
    else if (timeRule==DAY) {
        endWord = when (lastTwoDigits) {
            1 -> "час"
            in digitsGroupTwo -> "часа"
            else -> "часов"
        }
    }
    return endWord
}
