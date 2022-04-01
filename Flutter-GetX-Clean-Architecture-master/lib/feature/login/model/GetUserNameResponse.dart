class GetUserNameResponse {
  int? id;
  String? username;
  String? email;
  String? password;
  int? userStatus;

  GetUserNameResponse(
      {this.id, this.username, this.email, this.password, this.userStatus});

  GetUserNameResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    username = json['username'];
    email = json['email'];
    password = json['password'];
    userStatus = json['userStatus'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['username'] = this.username;
    data['email'] = this.email;
    data['password'] = this.password;
    data['userStatus'] = this.userStatus;
    return data;
  }
}
