import 'package:clean_architecture/feature/login/controller/LoginController.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class SuccessfulScreen extends GetView<LoginController> {
  dynamic argumentData = Get.arguments as String;

  static const routeName = '/success-screen';
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Dashboard", textAlign: TextAlign.center),
        backgroundColor: Colors.grey[900],
      ),
      body: Container(
        color: Colors.grey[900],
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Text("Congratulations :$argumentData",
              style: TextStyle(color: Colors.white, fontSize: 24)),
        ),
      ),
    );
  }
}
