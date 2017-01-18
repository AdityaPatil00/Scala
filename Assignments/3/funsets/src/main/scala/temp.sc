import funsets.FunSets.{singletonSet, union}

type Set = Int => Boolean
def contains(s: Set, elem: Int): Boolean = s(elem)
def singletonSet(elem: Int): Set = x => x==elem
def union(s: Set, t: Set): Set = elem => (contains(s,elem)|| contains(t,elem))
def intersection(s: Set, t: Set): Set = elem => (contains(s,elem)&& contains(t,elem))
def diff(s: Set, t: Set): Set = elem => s(elem) && !t(elem)
def filter(s: Set, p: Int => Boolean): Set = elem => s(elem) && p(elem)
def forall(s: Set, p: Int => Boolean): Boolean = {
  def iter(a: Int): Boolean = {
    if (s(a)) p(a) && iter(a+1)
    else if (a>1000) true
    else iter(a+1)
  }
  iter(-1000)
}
def exists(s: Set, p: Int => Boolean): Boolean = if(forall(s,diff(s,p)))false else true
def map(s: Set, f: Int => Int): Set = (elem:Int) => forall(s,f => true)
val bound =1000


val s1 = singletonSet(-1)
val s2 = singletonSet(2)
val s3 = singletonSet(3)
val s4 = singletonSet(1)
val s5 = union(s1,s2)
val s6 = union(union(s2,s3),s4)
contains(intersection(s1,s5),-1)
contains(diff(s1,s2),2)
contains(filter(s2,x=>x>2),2)
forall(s5,x => x>0)
exists(s5,x => x>0)
contains(map(s5, (x:Int) => 2*x),0)