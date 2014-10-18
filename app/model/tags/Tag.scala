package model.tags


case class Tag(id : BigInt, tagName : String) {
  override def hashCode = tagName.hashCode()
  override def equals(that : scala.Any): Boolean = that.isInstanceOf[Tag] && ( id == that.asInstanceOf[Tag].id)
}

object Tag {
  val dummyTag1 = Tag(1, "urgent")
  val dummyTag2 = Tag(2, "rent")
}
