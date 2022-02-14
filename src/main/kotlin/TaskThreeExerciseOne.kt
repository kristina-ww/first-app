
const val NOW = "right now"
const val HOUR = "minutes ago"
const val DAY = "hours ago"
const val secondDAY = "today"
const val thirdDAY = "yesterday"
const val moreThenThreeDAYS = "long time ago"

fun main(){

    val timeInDigits:Array<String> = arrayOf(HOUR,DAY)
    val timeWithoutDigits:Array<String> = arrayOf(NOW,secondDAY,thirdDAY,moreThenThreeDAYS)

    val howManySecAgoUserWasOnline = 660
    var finalTimeMinOrHours = ""
    var timeDigits = 0
    var timeWord = ""

    when (val timeRule = onlineTimeRule(howManySecAgoUserWasOnline)) {
        in timeInDigits -> {
            timeDigits = ifAnswerWithDigits(timeRule,howManySecAgoUserWasOnline)
            finalTimeMinOrHours = endingsForMinAndHours(timeRule,timeDigits)
            print("Был(а) в сети $timeDigits $finalTimeMinOrHours назад")
        }
        in timeWithoutDigits -> {
            timeWord = ifAnswerWithoutDigits(timeRule)
            println("Был(а) в сети $timeWord ")
        }
    }
}

fun onlineTimeRule(howManySecAgoUserWasOnline:Int): String {
    val untilMin:IntRange = (0..60) //sec
    val fromMinToHour:IntRange = (61..3600)
    val fromHourToADay:IntRange = (3601..86400)
    val fromDayToTwoDays:IntRange = (86401..172800)
    val fromTwoDaysToThreeDays:IntRange = (172801..259200)

    val ruleForTime = when(howManySecAgoUserWasOnline){
        in untilMin -> NOW
        in fromMinToHour -> HOUR
        in fromHourToADay -> DAY
        in fromDayToTwoDays -> secondDAY
        in fromTwoDaysToThreeDays -> thirdDAY
        else -> moreThenThreeDAYS
    }
    return ruleForTime
}

fun ifAnswerWithDigits(timeRule:String,howManySecAgoUserWasOnline:Int): Int{
    var time = 0
    when (timeRule) {
        HOUR -> time = howManySecAgoUserWasOnline/60
        DAY -> time = howManySecAgoUserWasOnline/3600
    }
    return time
}

fun ifAnswerWithoutDigits(timeRule:String):String{

    val phraseEnd = when (timeRule) {
        NOW -> "только что"
        secondDAY -> "сегодня"
        thirdDAY -> "вчера"
        else -> "давно"
    }
    return phraseEnd
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
