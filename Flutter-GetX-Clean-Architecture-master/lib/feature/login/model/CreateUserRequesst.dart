class CreateUserRequesst {
  int? id;
  String? username;
  String? firstName;
  String? lastName;
  String? email;
  String? password;

  CreateUserRequesst(
      {this.id,
      this.username,
      this.firstName,
      this.lastName,
      this.email,
      this.password});

  CreateUserRequesst.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    username = json['username'];
    firstName = json['firstName'];
    lastName = json['lastName'];
    email = json['email'];
    password = json['password'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['username'] = this.username;
    data['firstName'] = this.firstName;
    data['lastName'] = this.lastName;
    data['email'] = this.email;
    data['password'] = this.password;
    return data;
  }
}