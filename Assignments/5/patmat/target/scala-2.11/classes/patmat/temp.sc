import patmat.Huffman.{Fork, Leaf}
import common._
//import patmat.Huffman.{Fork, Leaf, combine}

abstract class CodeTree
case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree
case class Leaf(char: Char, weight: Int) extends CodeTree


// Part 1: Basics
def weight(tree: CodeTree): Int = tree match{
  case Fork(l, r, c, w) => w
  case Leaf(c,w) => w
}

def chars(tree: CodeTree): List[Char] = tree match{
  case Fork(l,r,c,w) => c
  case Leaf(c,w) => List(c)
}

def makeCodeTree(left: CodeTree, right: CodeTree) =
  Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right))

def combine(trees: List[CodeTree]): List[CodeTree] = (makeCodeTree(trees.head, trees.tail.head) :: trees.tail.tail).sortWith{(l,r)=> weight(l)<weight(r)}



def encode(tree: CodeTree)(text: List[Char]): List[Bit] = {
  def tempEncode(tr:CodeTree)(cha:Char)(acc:List[Bit]):List[Bit] =
    tr match {
      case Fork(l, r, c, w) => if (chars(l).contains(cha)) tempEncode(l)(cha)(0::acc) else tempEncode(r)(cha)(1::acc)
      case Leaf(c, w) => acc
    }
  text.flatMap(tempEncode(tree)(_)(List()))
}


val leaflist = List(Leaf('e', 1), Leaf('t', 5), Leaf('x', 4))
combine(leaflist)
