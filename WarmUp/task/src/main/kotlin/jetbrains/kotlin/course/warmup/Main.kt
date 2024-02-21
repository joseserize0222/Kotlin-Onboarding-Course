package jetbrains.kotlin.course.warmup

// You will use this function later
fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String) =
    "Welcome to the game! $newLineSymbol" +
            newLineSymbol +
            "Two people play this game: one chooses a word (a sequence of letters), " +
            "the other guesses it. In this version, the computer chooses the word: " +
            "a sequence of $wordLength letters (for example, $secretExample). " +
            "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. $newLineSymbol" +
            newLineSymbol +
            "For example, with $secretExample as the hidden word, the BCDF guess will " +
            "give 1 full match (C) and 1 partial match (B)."

fun generateSecret() : String = "ABCD"
fun countAllMatches(secret : String, guess : String) : Int{
    val map : MutableMap<Char, Int> = mutableMapOf()
    secret.forEach {
        map[it] = map.getOrDefault(it, 0) + 1
    }
    val compare : MutableMap<Char, Int> = mutableMapOf()
    guess.forEach{
        compare[it] = compare.getOrDefault(it, 0) + 1
    }
    var ans = 0
    map.forEach {
        ans += minOf(it.value, compare.getOrDefault(it.key, 0))
    }
    return ans
}

fun countExactMatches(secret : String, guess : String) : Int =
    secret.filterIndexed{
        index : Int, character : Char -> guess[index] == character
    }.length

fun countPartialMatches(secret: String, guess: String) = countAllMatches(secret, guess) - countExactMatches(secret, guess)

fun isComplete(secret : String, guess : String) : Boolean = secret == guess

fun playGame(secret : String, wordLength: Int, maxAttemptsCount: Int){
    var attemps = 0
    do{
        println("Please input your guess. It should be of length $wordLength.")
        val guess = safeReadLine()
        printRoundResults(secret, guess)
        val complete = isComplete(secret, guess)

        if(isWon(complete, attemps, maxAttemptsCount)){
            println("Congratulations! You guessed it!")
            return
        }
        attemps++
    }while(!complete && attemps <= maxAttemptsCount)
    println("Sorry, you lost! :( My word is $secret")
}

fun isWon(complete : Boolean, attemps : Int, maxAttemptsCount: Int) = complete && attemps <= maxAttemptsCount

fun isLost(complete : Boolean, attemps: Int, maxAttemptsCount: Int) = !complete && maxAttemptsCount < attemps
fun printRoundResults(secret : String, guess : String){
    println("Your guess has ${countExactMatches(secret, guess)} full matches and ${countPartialMatches(secret, guess)} partial matches.")
}

fun main() {
    // Write your solution here

    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"

    println( getGameRules(wordLength, maxAttemptsCount, secretExample))
    val secret = generateSecret()
    playGame(secret, wordLength, maxAttemptsCount)
}

