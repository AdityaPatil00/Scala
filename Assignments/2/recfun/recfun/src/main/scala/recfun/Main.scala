package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = if(c==0 || c==r)1 else pascal(c-1,r-1)+pascal(c,r-1)
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def temp(chars:List[Char], count:Int):Int = if(count>=0){
        if(chars.isEmpty)count
        else if(chars.head=='(')temp(chars.tail,count+1)else if(chars.head == ')')temp(chars.tail,count-1)
        else temp(chars.tail,count)
      }
      else count
      if(temp(chars,0)==0)true else false
    }

  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = if(!coins.isEmpty && money-coins.head == 0)1+countChange(money,coins.tail) else if(money>0 && !coins.isEmpty)
      countChange(money,coins.tail)+countChange(money-coins.head,coins)
    else 0
  }
