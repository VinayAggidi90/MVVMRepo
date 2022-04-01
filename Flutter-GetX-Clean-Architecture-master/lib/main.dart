import 'package:clean_architecture/core/database/database.dart';
import 'package:clean_architecture/core/network/rest_client.dart';
import 'package:clean_architecture/feature/home/binding.dart';
import 'package:clean_architecture/feature/home/view/HomePage.dart';
import 'package:clean_architecture/feature/login/loginbinding.dart';
import 'package:clean_architecture/feature/login/view/SignupScreen.dart';
import 'package:clean_architecture/feature/login/view/SuccessfulScreen.dart';
import 'package:clean_architecture/feature/login/view/WelcomeScreen.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'feature/login/view/LoginScreen.dart';

void main() {
  initServices();
  runApp(MyApp());
}

initServices() async {
  await Get.putAsync<RestClient>(() => RestClient().init());
  await Get.putAsync<AppDb>(() => AppDb.init());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'Flutter Clean Architecture Demo',
      initialRoute: WelcomeScreen.routeName,
      routes: {
        WelcomeScreen.routeName: (context) => WelcomeScreen(),
        LoginScreen.routeName: (context) => LoginScreen(),
        SignupScreen.routeName: (context) => SignupScreen(),
        SuccessfulScreen.routeName: (context) => SuccessfulScreen()
      },
      getPages: [
        GetPage(
          name: WelcomeScreen.routeName,
          page: () => WelcomeScreen(),
          binding: LoginBinding(),
        ),
      ],
    );
  }
}
