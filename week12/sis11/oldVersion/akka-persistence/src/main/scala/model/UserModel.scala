package model

class UserModel(userId: String, name: String, username:String, password: String, userType:String="user"){
  override def toString: String = {
    "" +
      "name: " + name +
      "username: " + username +
      "userType: " + userType
  }
}
