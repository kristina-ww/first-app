
/** Задание 1 - Денежные переводы */

fun main(){

    val amount = 1332
    val commissionPercentage = 0.75
    val minCommission = 35

    val commission = amount*commissionPercentage
    val commissionRUB = commission.toInt()
    val commissionPenny = ((commission-commissionRUB)*100).toInt()

    println("Задание 1 - Денежные переводы")
    if (commission > minCommission) println("Комиссия $commissionRUB руб. $commissionPenny коп.") else println("Комиссия $minCommission руб.")




/** Задание 2 - "Люди/Человеки" */

    val likes = 61
    var lastDigitOfLikes = 0
    val likesLength = likes.toString().length.toInt()-1
    if (likes>1) lastDigitOfLikes = likes%(10*likesLength)
    val digits:Array<Int> = arrayOf(2,3,4,5,6,7,8,9,0)

    println("""
Задание 2 - "Люди/Человеки" """)

    if(likes==0) println("Запись оценило 0 человек")
    else if (likes==1) println("Понравилось $likes человеку")
    else if(lastDigitOfLikes==1) println("Понравилось $likes человеку")
    else if(lastDigitOfLikes in digits) println("Понравилось $likes людям")




/** Задание 3 - "Меломан" */

    val buysMonthly = true
    val purchaseAmount = 10555
    val maxPurchaseAmountWithStandardDiscount: IntRange = (1001..10000)
    val minPurchaseWithPercentageDiscount = 10001
    val standardDiscount = 100
    val discountPercentage = 0.05
    val bonusDiscount = 0.01
    var finalDiscountFull = 0.00

    if(purchaseAmount in maxPurchaseAmountWithStandardDiscount) finalDiscountFull += standardDiscount
    else if(purchaseAmount >= minPurchaseWithPercentageDiscount) finalDiscountFull = (finalDiscountFull+purchaseAmount*discountPercentage)

    if(buysMonthly) finalDiscountFull += (purchaseAmount * bonusDiscount)
    val finalDiscountRUB = finalDiscountFull.toInt()
    val finalDiscountPenny = ((finalDiscountFull-finalDiscountRUB)*100).toInt()


    println("""
Задание 3 - "Меломан"
Скидка $finalDiscountRUB руб. $finalDiscountPenny коп.""")



}



