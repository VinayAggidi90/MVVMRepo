import 'package:clean_architecture/feature/login/controller/LoginController.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:get/get.dart';

import 'LoginScreen.dart';

class LoginPage extends GetView<LoginController> {
  static final routeName = "/login";

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return LoginScreen();
  }
}
