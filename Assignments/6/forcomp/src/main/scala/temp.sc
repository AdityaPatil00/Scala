val fruits = List("apple", "pear", "orange", "pineapple")
fruits.groupBy(_.head).map(x => (x._1, x._2.length))
val word = "abcd"
word.toList.groupBy(x => x).map(x => (x._1, x._2.length)).toList.sorted

//dictiona.map(x => x._2).find(y => y == "ate")
val l = List(('a', 2), ('b', 2))
//val k = l.toMap.groupBy(x => x)
val inv = {
  for
  ((chr,digit) <- l) yield (digit -> chr)
}.toList

def op(i : List[(Int,Char)]):List[(Int,Char)] = i match {
  case List() => Nil
  case (k:Int,v:Char)::xs => if(v > 0) (k-1,v)::(k,v)::op(xs) else i
}

val temp = op(inv)
val res = {
  for((digit,chr) <- temp) yield (chr -> digit)
}.toList

List(('a',2))::List(List())::List(('g',5))::List(List())

(for{
  i <- 1 until 10
  j <- 1 until i
  if((i+j)%2 == 0)
} yield(i,j)).toList




