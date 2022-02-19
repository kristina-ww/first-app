const val MASTERCARD = "MASTERCARD"
const val MAESTRO = "MAESTRO"
const val VISA = "VISA"
const val MIR = "Мир"
const val VKPAY = "VK Pay"
const val maxTransferWithoutCommissionForMastercardAndMaestro = 75000
const val maxTransferInADay = 150000
const val maxTransferInAMonth = 600000
const val maxTransferInADayForVKPAY = 15000
const val maxTransferInAMonthForVKPAY = 40000
const val commissionForMASTERCARDandMAESTRO = 0.06
const val commissionForVISAandMIR = 0.75
const val minCommissionForVISAandMIR = 35 //RUB

fun main(){
    val typeOfCardOrAccount = MASTERCARD
    val amountOfPreviousTransfersThisMonth = 111
    val amountOfTheTransfer = 11131

    finalAnswer(amountOfPreviousTransfersThisMonth, amountOfTheTransfer, typeOfCardOrAccount)
}

fun finalAnswer(amountOfPreviousTransfersThisMonth:Int,amountOfTheTransfer: Int, typeOfCardOrAccount: String){
    var commission = 0
    var commissionRUB = 0
    var commissionPenny = 0

    when(transferLimitCheck(typeOfCardOrAccount,amountOfPreviousTransfersThisMonth,amountOfTheTransfer)) {
        true -> {commission = cardCommission(typeOfCardOrAccount,amountOfTheTransfer)
            commissionRUB = commission.toInt()
            commissionPenny = ((commission-commissionRUB)*100).toInt()
            print("Комиссия $commissionRUB руб. $commissionPenny коп.")}

        false -> print("Транзакция не может быть совершена, так как превышен лимит")
    }
}

fun cardCommission(typeOfCard:String,amountOfTheTransfer:Int): Int {

    val cardCommission = when (typeOfCard) {
        MAESTRO,MASTERCARD -> {
                if (amountOfTheTransfer<maxTransferWithoutCommissionForMastercardAndMaestro) (amountOfTheTransfer*commissionForMASTERCARDandMAESTRO+20).toInt()
            else 0
        }
        else ->  {
                if ((amountOfTheTransfer*commissionForVISAandMIR)<minCommissionForVISAandMIR) minCommissionForVISAandMIR
                else (amountOfTheTransfer * commissionForVISAandMIR).toInt()
        }

    }
    return cardCommission
}

fun transferLimitCheck(typeOfCardOrAccount:String,amountOfPreviousTransfersThisMonth:Int,amountOfTheTransfer:Int):Boolean{
    val cards = arrayOf(MAESTRO,MASTERCARD,VISA,MIR)

    val transferAccept = when(typeOfCardOrAccount){
         in cards -> {
             ((amountOfTheTransfer+amountOfPreviousTransfersThisMonth)<maxTransferInAMonth) and (amountOfTheTransfer<maxTransferInADay)
         }
        else -> {
            ((amountOfTheTransfer+amountOfPreviousTransfersThisMonth)<maxTransferInADayForVKPAY) and (amountOfTheTransfer<maxTransferInAMonthForVKPAY)
        }
    }
    return transferAccept
}