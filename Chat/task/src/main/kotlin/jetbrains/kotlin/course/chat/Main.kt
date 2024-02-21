package jetbrains.kotlin.course.chat

fun main() {
    // Write your solution here
    println("Hello! I'm glad to meet you, let me get to know you better! What is your name?")
    val answers : MutableList<String?> = mutableListOf()
    answers.add(readlnOrNull())
    println("Nice to meet you, ${answers[0]}! My name is Kotlin Bot! I am a young programming language created in 2010. How old are you?")
    answers.add(readlnOrNull())
    println("${answers[1]} is great! I hope you successfully complete this course! Anyone can learn programming at any age!")
}
