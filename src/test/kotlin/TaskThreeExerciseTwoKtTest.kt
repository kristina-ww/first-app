import org.junit.Test

import org.junit.Assert.*

class TaskThreeExerciseTwoKtTest {

    @Test
    fun finalAnswer_without_overlimit(){
        val cardType = "MASTERCARD"
        val amountOfPreviousTransfersThisMonth = 1112
        val amountOfTheTransfer = 111313

        val actualResult = finalAnswer(amountOfPreviousTransfersThisMonth,amountOfTheTransfer,cardType)

        assert(actualResult==finalAnswer_without_overlimit_expected_result())

    }
    fun finalAnswer_without_overlimit_expected_result(){
        print("Комиссия 6698 руб. 0 коп.")
    }
    @Test
    fun finalAnswer_with_overlimit() {
        val cardType = "MASTERCARD"
        val amountOfPreviousTransfersThisMonth = 11100002
        val amountOfTheTransfer = 1113100002

        val actualResult = finalAnswer(amountOfPreviousTransfersThisMonth,amountOfTheTransfer,cardType)

        assert(actualResult==finalAnswer_with_overlimit_expected_result())
    }
    fun finalAnswer_with_overlimit_expected_result(){
        print("Транзакция не может быть совершена, так как превышен лимит")
    }

    @Test
    fun cardCommission_minimum_for_visa_and_mir(){
        val typeOfCard = "VISA"
        val amountOfTheTransfer = 32

        val actualCommission = cardCommission(typeOfCard,amountOfTheTransfer)
        val expectedCommission = 35

        assert(actualCommission==expectedCommission)
    }

    @Test
    fun cardCommission_more_then_minimum_for_visa_and_mir(){
        val typeOfCard = "VISA"
        val amountOfTheTransfer = 12

        val actualCommission = cardCommission(typeOfCard,amountOfTheTransfer)
        val expectedCommission = 35

        assert(actualCommission==expectedCommission)
    }

    @Test
    fun cardCommission_for_maestro_and_mastercard(){
        val typeOfCard = "MASTERCARD"
        val amountOfTheTransfer = 75200

        val actualCommission = cardCommission(typeOfCard,amountOfTheTransfer)
        val expectedCommission = 4532

        assert(actualCommission==expectedCommission)
    }

    @Test
    fun cardCommission_zero_for_maestro_and_mastercard() {
        val typeOfCard = "MASTERCARD"
        val amountOfTheTransfer = 10000

        val actualCommission = cardCommission(typeOfCard,amountOfTheTransfer)
        val expectedCommission = 0

        assert(actualCommission==expectedCommission)
    }

    @Test
    fun transfer_Limit_Check_Condition_For_Type_Of_Card_One() {
        val cardType = "MASTERCARD"
        val amountOfPreviousTransfersThisMonth = 111
        val amountOfTheTransfer = 11131


        val actualResult = transferLimitCheck(cardType,amountOfPreviousTransfersThisMonth,amountOfTheTransfer)
        val expectedResult = true

        assert(expectedResult==actualResult)
    }

    @Test
    fun transfer_limit_check_condition_for_type_of_card_two(){
        val cardType = "VK Pay"
        val amountOfPreviousTransfersThisMonth = 111
        val amountOfTheTransfer = 11131

        val actualResult = transferLimitCheck(cardType,amountOfPreviousTransfersThisMonth,amountOfTheTransfer)
        val expectedResult = true

        assert(expectedResult==actualResult)
    }

    @Test
    fun transfer_limit_check_transfer_not_accept_for_type_of_card_one(){
        val cardType = "MASTERCARD"
        val amountOfPreviousTransfersThisMonth = 1110000
        val amountOfTheTransfer = 111310000

        val actualResult = transferLimitCheck(cardType,amountOfPreviousTransfersThisMonth,amountOfTheTransfer)
        val expectedResult = false

        assert(expectedResult==actualResult)
    }

    @Test
    fun transfer_limit_check_transfer_not_accept_for_type_of_card_two(){
        val cardType = "VK Pay"
        val amountOfPreviousTransfersThisMonth = 1110000
        val amountOfTheTransfer = 111310000

        val actualResult = transferLimitCheck(cardType,amountOfPreviousTransfersThisMonth,amountOfTheTransfer)
        val expectedResult = false

        assert(expectedResult==actualResult)
    }
}