/**
 * You can edit, run, and share this code. 
 * play.kotlinlang.org 
 */


 /*여러줄 주석*/
//한줄짜리 주석//
// var: 일반적으로 통용되는 변수, 언제든지 읽기 쓰기 가능.//
// val: 선언시에만 초기화 가능, 중간에 값을 변경할 수 없음.(ex)runtime시 변경되지 않아야 할 것들) //
// class에 선언된 변수: property 속성
// scope내에 선언된 변수: Local Variable 로컬변수

fun main() {
    println("Hello, world!!!")
    var a: Int = 123 // Kotlin에서는 값이 초기화 되지 않았을 시에는 Null값을 허용하지 않는다.
    var b: Int? = Null //nullable변수를 통해 Null값을 표현해줄 수 있다. null pointer exception발생 주의
    println(a)
    var intValue: Int = 1234 //10진수
    var LongValue:Long = 1234L //큰 정수를 쓸때 10진수Long
    var intValueByHex: Int = 0x1af //앞에 0x를 붙임 16진수
    var intValueByBin: Int 0b10110110 //0b를 앞에 붙임. 2진수
    // 코틀린은 8진수의 표기는 지원하지 않음.
    var doubleValue: Double = 123.5
    var doubleValueWithExp: Double = 123.5e10 // 더블형 자연상수
    var floatValue: float = 123.5f //플롯형 (16비트)
    문자형
    유니코드 문자형 중 하나인 UTF-16BE
    var charValue:Char = "a"
    var koreanCharValue:Char = "가"
    // \t tab
    // \b back space
    // \r move curser to the first column
    // \n next line
    // \' 작은 따옴표
    // \'' 큰 따옴표
    // \\ 역 슬래시
    // \$ $
    // \uxxx unicode word

    var booleanValue:Boolean = true
    val stringValue = "hello world"
    val multilineStringValue = """wal wal
    wal wal!!
    """
}

//you can get some result or specific action what you want as using Function

//////////////////////////////////////////// 형 변환

// 다른 언어들과는 달리 '명시적 형변환'을 지원한다.
// 명시적 형변환 : 개발자가 직접 자료형을 변환하는 것.
fun main() {
    var a: Int =123
    var b: Long = a.tolong()
}

///////////////////////////////////////////// 배열
// 배열은 한 번 선언하면 크기를 변경할 수 없다.
// 배열은 한 번 선언하면 입출력이 매우 빠르다.
fun main() {
    var intArr: arrayOf(1,2,3,4,5) //배열을 만든다.
    var nullArr: arrayOfNulls<Int>(5) //빈 공간의 배열을 만든다. <Int>와 같은 형태를 Generic이라고 부른다.
    intArr[2]=8 //assign 8 in the index of 2, intArr
    println(intArr[4])
}

////////////////////////////////////////////// 타입 추론
// 함수나 변수에서 따로 자료형을 할당하지 않아도 타입을 추론하여 코틀린이 자료형을 지정한다.
fun main() {
    var a = 1234 // int
    var b = 1234L //int Long

    var c = 123.5 // double
    var d = 123.5f //float
    
    var intValueByHe = 0x1af // int 
    var intValueByBin= 0b10110110 // int 

    var g = true //boolean
    var g = "c" //Char
}

//////////////////////////////////////////////// 함수

fun add(a: Int,b: Int,c: Int): Int{
    return a+b+c
}

fun add(a: Int,b: Int,c: Int)=a+b+c

//////////////////////////////////////////////// if

fun main() {
    var a = 11

    if(a>10) {
        println("a is bigger than 10")
    }
    else {
        println("a is smaller than 10 or equal with 10")
    }
}

/////////////////////////////////////////////// when
//when은 하나의 변수를 여러개의 값과 비교할 수 있다.

fun main() {


}
fun dowhen (a:Any) {  //Any는 모든 자료형에 상관없이 사용되는 Kotlin에서 최상위 자료형
    // 조건을 반환하는 명령들
    when(a) {
        1 -> println("score is 1")
        "Dimo" -> println("Dimo is skillful coder")
        is Long -> println("The type is Long")
        !is String -> println("Not a String type")
        else -> println("Not satisfied any condition")
    }
    // 값을 초기화하는 명령들
    var result = when(a) { 
        1 -> "score is 1"
        "Dimo" -> "Dimo is skillful coder"
        is Long ->"The type is Long"
        !is String -> "Not a String type"
        else ->"Not satisfied any condition"
    }
    println(result)
}

//////////////////////////////////////////////// 반복문
// 1. 조건형 반복문: 조건이 참인경우 반복을 유지 
    while, do while
    
    //while
    fun main() {
        var a =0
        
        while(a<5) {
            println(a++) // 증감 연산자: 증가나 감소 연산자가 앞에 있으면 해당 루프부터 실행, 연산자가 뒤에 있으면 다음 루프부터 실행.
        }   
    }
    
    //do while
    //조건과 관계없이 반드시 맨 첫 번째 수행을 실행하는 조건형 반복문

// 2. 범위형 반복문: 반복 범위를 정해 반복을 수행
    fun main() {
        
        for(i in 0..9 step 3) {
            print(i) //print는 줄바꿈 없는 출력, println은 실행마다 줄을 바꿔서 출력.
        }
        for(i in 9 donwTo 0 step 3) {
            print(i)
        }
        for(i in 'a'..'e') { // 문자형도 for문으로 가능이누.. 신기방기하누..!
            print(i)
        }
    }

//////////////////////////////////////////////// 코드의 흐름 제어
1. return
2. break
3. continue

fun main() {

    loop @for (i in 1..10) {
        for (j in 1..10) {
            if(i==1 && j==2)
            break @loop // 다중 반복문에서 내부 반복문만 종료되는게 원래 언어의 특징인데, @반복문이름 설정해주면 다중 반복문 한번에 끝낼 수 있음.
            println("i :$i, j:$j") // $j를 통해서 반복하는 변수를 따옴표안에서 나타낼 수 있다.

        }
    }
}

//////////////////////////////////////////////// 논리 연산자
&&
||
!

//////////////////////////////////////////////// Class
// Class의 구성
// 고유의 특징값인 "속성", 기능의 구현인"함수"를 다룸.
fun main() {
    var a = Person("박보영",1999)
    var b = Person("전정국",1997)
    var c = Person("장원영",2004)

    a.introduce()
    b.introduce()
    c.introduce()
}
class Person ( var name:String, val birthYear:Int) {
    // init: 기본 생성자; 생성자를 통해 인스턴스가 만들어질 때 호출되는 함수.
    init {
        println("${this.birthYear}년생 ${this.name}님이 생성되었습니다.") //this는 파이썬의 self와 같은 기능.
    }
    
    //보조 생성자: 인스턴스 생성시 편의를 제공하거나 추가적인 기능 수행을 제공하는 역할을 합니다. 보조 생성자를 사용하려면 반드시 기본 생성자를 통해 속성을 초기화 해주어야 한다.
    constructor(name:String) : this(name,1997) {
        println("보조 생성자가 사용되었습니다.")
    }

    fun introduce() {
        println("안녕하세요. ${birthYear}년생 ${name}입니다.")
    }
}

//클래스의 상속
// Kotlin은 상속 금지가 default

// 클래스 상속의 필요성
// 1. 원래 있는 클래스에 추가적으로 클래스를 만들 때
// 2. 여러 클래스들에서 한 클래스의 공통점을 뽑아낼 때

// super class : 물려주는 쪽
// sub clasee: 물려받는 쪽

// 상속의 규칙
// 1. 수퍼 클래스에 존재하는 속성과 '같은 이름'의 속성을 가질 수 없음.
// 2. 서브 클래스가 생성될 때는 수퍼 클래스의 생성자까지 호출되어야 함.
fun main() {
    var a = Animal("별이",5,"개")
    var b = Dog("별이",5)

    a.introduce()
    b.introduce()

    b.bark()

    var c = Cat("루이",1)
    c.introduce()
    c.meow()

}
open class Animal (var name:String, var age:Int, var type:String) //open으로 클래스를 개방해서 상속에 사용될 수 있게 만들어 준다.
{
    fun introduce() {
        println("저는 ${type} ${name}이고, ${age}살 입니다.")
    }
}

class Dog (name:String,age:Int) : Animal (name,age,"개") //상속을 받고 type은 "개"로 고정 상속을 받는다.
{
    fun bark(){
        println("멍멍")
    }
} 

class Cat (name:String,age:Int) : Animal (name,age,"고양이") // 상속을 받고 type은 "개"로 고정 상속을 받는다.
{
    fun meow(){
        println("야옹야옹")
    }
} 

// 2021.03.04 Kotlin
////////////////////////////////////////////// 오버라이딩
// 수퍼 클래스에서 허용한다면 같은이름,형태의 함수 구현 가능

fun main() {
    var t = Tiger()

    t.eat() // >>> eat food

}

open class Animal {
    fun eat() {
        println("eat food")
    }
}

// What if do you wanna change output from "eat food" to "eat meet"?
// 수퍼 클래스의 함수앞에 open을 붙인다. -> 서브 클래스에 오버라이드 가능(서브 클래스에 함수앞에 override 붙임.) 
class Tiger : Animal() {
    override fun eat() {
        println("eat meet")
    }
} 

////////////////////////////////////////////// 추상화
// 수퍼 클래스에서는 함수에 대한 명확한 동작이 없고, 각 서브 클래스에서 비어있는 내용을 필요에 따라 구현하도록 하는 것.
// 추상화는 추상함수, 추상 클래스로 구현.
// 추상함수: 선언부만 있고 기능구현 없고
// 추상 클래스: 추상함수를 포함하는 클래스

fun main() {
    var r = Rabbit()

    r.eat()
    r.sniff() // eat함수, sniff 함수 출력
}

abstract class Animal { //추상 클래스 구현
    abstract fun eat() // 추상함수는 중괄호 없음 주의
    fun sniff() {
        println("킁킁")
    }
}

//상속 받기
class Rabbit : Animal() {
    override fun eat() {
        println("eat carrot")
    }
}

///////////////////////////////////////// 인터페이스 (추상화를 하는 또 다른 방법)
// kotlin에서는 인터페이스가 속성, 추상함수, 일반함수를 가질 수 있다.
// 추상함수는 생성자 가질 수 있음, 인터페이스는 가질 수 없음.

// 인터페이스에서 구현부 있는 함수 -> open함수
// 인터페이스에서 구현부 없는 함수 -> abstract함수
// 따라서 별도의 키워드 필요 없음.

// 한번에 여러 인터페이스를 상속받을 수 있음.
// 같은 형태,이름의 함수가 있다면 반드시 오버라이딩 하여 혼선이 일어나지 않도록 하자!

fun main() {
    var d = Dog

    d.run()
    d.eat()
}

interface Runner {
    fun run()
}

interface Eater {
    fun eat() {
        println("eat food")
    }
}

//상속

class Dog : Runner, Eater {
    fun run() {
        println("running")
    }
    fun eat() {
        println("eating")
    }
}

/////////////////////////////////////////////Kotlin 기본 프로젝트 구조
// 물리적 구조 단위
//     프로젝트: 우리가 서비스를 만들때 사용하는 모든 내용을 담은 '큰 틀'!
//     모듈: 프로젝트 안에 블록 단위로 구성되어 있는 것으로, 직접 만들수도, 이미 만들어져있는 '라이브러리 모듈'형태를 쓸 수도 있다.
//     모듈 안에는: 여러가지 폴더, 파일, Kotlin관련 리소스, 설정 파일 등

// 논리적 구조 단위
//     패키지: 개발시 소스 코드의'소속'을 지정하기 위한 논리적 단위
//     용도에 따라 서로 다르게 유니크한 패키지 이름을 지어주어야 한다.

// 패키지명 생성 방법
//     youtube.com -> com.youtube(거꾸로) -> com.youtube.dimo(프로젝트 명) -> com.youtube.dimo.base(기능 명,모듈 명?)

// 패키지 파일 만드는 방법
package com.youtube.dimo  // 패키지명 명시 안하면 자동으로 'default'명의 패키지로 묶인다.
import com.youtube.dimo.base //다른 패키지의 변수, 함수, 클래스를 사용할 수 있다. 중복되는 명이 있으면 패키지 풀네임을 명시해야 한다. 
fun main() {
    println("hahaha~~")
}

// Kotlin은 폴더 구조와 패키지명이 달라도 된다, 클래스 명 != 파일명 가능, 하나에 파일에 여러개의 클래스 만들어도 된다(파일 내에 있는 패키지 키워드를 기준으로 구분하기 때문)
// 패키지가 다르면 쓸 수 없다.

/////////////////////////////////////////////// 스코프
// 변수, 함수, 클래스 같은 '멤버'들을 서로 공유하여 사용할 수 있는 범위를 지정해 둔 단위.

// -스코프가 지정되는 범위
//     패키지 내부
//     클래스 내부
//     함수 내부

// -스코프의 세가지 규칙
//     1. 스코프 외부에서는 스코프 내부의 멤버를 '참조연산자'로만 참조 가능하다.
//     import 패키지
//     변수.함수 등
    
//     2. 동일 스코프 내에서는 멤버들을 '공유'할 수 있다.
//     default 패키지라고 가정하면,
    val a = "package scope"

    class B {
        fun print() {
            println(a)
        }
    }

    fun main() {
        B().print()
    }
    // 변수 a 는 동일 스코프 내에 있으므로 '공유'가 가능하다.
    
    // 3.하위 스코프 내에서는 상위 스코프를 재정의 할 수 있다.
    val a = "package scope"

    class B {
        var a = "a"
        fun print() {
            println(a)
        }
    }

    fun main() {
        var a = "b"
        println(a) // >>> b
        B().print() // >>> a 
    } 
    // 하위 스코프의 초기화 값이 출력이 됩니다.
/////////////////////////////////////////////// 접근 제한자
// 스코프 외부에서 내부로 접근할 때 개발자가 직접 제어할 수 있는 접근 제한자!
// 변수, 함수, 클래스 앞에 붙여 사용.
public
internal
private
protected

패키지 스코프
    public(default): 어떤 패키지에서도 접근 가능
    internal: 같은 모듈 내에서만 접근 가능
    private: 같은 파일 내에서만 접근 가능
    protected: 사용하지 않음.

클래스 스코프
    public(default): 클래스 외부에서 늘 접근 가능
    internal: 클래스 내부에서만 접근 가능
    private: 사용하지 않음.
    protected: 클래스 자신, 상속받은 클래스에서 접근 가능.

///////////////////////////////////////////////// 고차함수
// 함수를 '파라미터'로 넘겨줄수 있고, '결과값'으로 반환받을 수도 있다.
// 함수의 형태는 어떻게 자료형으로 나타낼까?

fun main() {
    b(::a) //함수a를 파라미터로 넘겨받은 함수b를 고차함수 형태로 호출한다.
}

fun a (str:String) {
    println("$str 함수 a")
}
fun b (function: (String) -> Unit) { //(자료형(파라미터)) -> 자료형(반환형) a= 문자열을 받고 반환형이 없는 함수이므로 이렇게 표현함.
    function("b가 호출한")
}

///////////////////////////////////////////////// 람다함수
// 파라미터로 넘길 함수를 굳이 이름까지 붙여서 따로 만들 필요가 있을까? -> 간단하게 '람다함수'라는 함수로 표현할 수 있다. 
fun main() {
    val c: (String)->Unit = {str -> println("$str 람다함수")} // {str: String이지만, Kotlin이 알아서 추론해줌.}
    val c = {str(String) -> println("$str 람다함수")} //처럼 타입추론을 이용하여 간단하게 나타낼 수 있다.
    b(c)

fun a (str:String) {
    println("$str 함수 a")
}
fun b (function: (String) -> Unit) {
    function("b가 호출한") // >>> b가 호출한 람다함수
}

////////////////////////////////////////////////// 람다함수를 이용한 스코프 함수
// 람다함수의 특징들
    // 람다함수도 여러 구문을 수행 가능. 
    // 파라미터가 없는 람다함수는 실행할 구문들만 나열하면 된다.
        val a: () -> Unit = {println("파라미터가 없어요")}
    // 파라미터가 1개이면 it으로 대체한다.(파라미터가 여러개이면 각각 파라미터를 써줌)
        var c:(String)-> Unit = {println("$it 람다함수")}

//////////////////////////////////////////////////스코프 함수
// 스코프 함수: 함수형 언어의 특징을 좀더 편리하게 사용할 수 있도록 기본 제공하는 언어들.
// 클래스의 인스턴스를 스코프함수내로 전달하면 스코프함수 내에서 깔끔하게 불러 쓸 수 있다.

//apply: 인스턴스 생성후 변수에 담기전 초기화 할때 쓰는 것.
fun main() {
    var a = Book("디모의 코틀린",10000).apply {
        name = "[초특가]" +name //참조연산자(.)없이 인스턴스 사용가능. 생성되자마자 조작된 인스턴스를 변수에 바로 넣어줄 수 있다.
        discount()
    }

    a.run {
        println("상품명 : ${name}, 가격: ${price}원") // >>> 상품명 : [초특가]디모의 코틀린, 가격 : 8000원
    }

    
}

class Book(var name:String, var price:Int) {
    fun discount() {
        price -= 2000
    }
}
//run: 마지막에 결과값을 반환한다는 것이 apply와 차이점이다.
var b = a.run {
    println(a.price)
    a.name
}
// 이미 만들어진 인스턴스를 스코프내에서 사용해야 할 때 유용하다.

//with: run은 참조연산자로 인스턴스를 받지만, with는 파라미터로 인스턴스를 받는다.protected
    run은 a.run
    with은 with(a)

//apply/ also

//run/ let

    두 함수는 같은 이름의 변수, 함수가 'scope 바깥에 중복'되어 있는 경우에 혼란을 방지하기 위해서.

    fun main() {
        var price = 5000
        var a = Book("디모의 코틀린",10000).apply {
            name = "[초특가]" +name
            discount()
        }
    
        a.run {
            println("상품명 : ${name}, 가격: ${price}원") // >>> 상품명 : [초특가]디모의 코틀린, 가격 : 5000원. main함수의 변수인'price'를 더 먼저 받는다.
        }

        a.let {
            println("상품명 : ${it.name}, 가격: ${it.price}원") // >>> 상품명 : [초특가]디모의 코틀린, 가격 : 8000원. it을 통해 Book클래스의 속성인 'price'를 안전하게 받아올 수 있다. 
        } 
    }
    
    class Book(var name:String, var price:Int) {
        fun discount() {
            price -= 2000
        }
    }