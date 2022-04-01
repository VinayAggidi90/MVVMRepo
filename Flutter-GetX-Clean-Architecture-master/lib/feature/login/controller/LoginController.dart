import 'package:clean_architecture/base/BaseController.dart';
import 'package:clean_architecture/feature/login/model/CreateUserRequesst.dart';
import 'package:clean_architecture/feature/login/model/CreateUserResponse.dart';
import 'package:clean_architecture/feature/login/model/GetUserNameResponse.dart';
import 'package:get/get.dart';
import 'package:dio/dio.dart' as d;
import 'package:get/get_rx/get_rx.dart';
import 'package:clean_architecture/core/network/rest_client.dart';
import 'dart:math';

class LoginController extends BaseController with StateMixin {
  @override
  void onInit() {
    // TODO: implement onInit
    super.onInit();
  }

  var isToLoadMore = true;

  Future<bool> doSignUp(String email, String password, String username) async {
    Random random = new Random();
    int randomNumber = random.nextInt(90) + 10;
    CreateUserRequesst userRequesst = new CreateUserRequesst();
    userRequesst.email = email;
    userRequesst.id = randomNumber;
    userRequesst.password = password;
    userRequesst.username = username;
    userRequesst.firstName = username;
    userRequesst.lastName = username;

    try {
      change(null, status: RxStatus.loading());
      final result = await restClient.createUser("user", userRequesst);

      if (result != null) {
        if (result is d.Response) {
          var data = CreateUserResponse.fromJson(result.data).code;
          if (data != null) {
            if (data == 200) {
              print("Signup response : $data");
              isToLoadMore = true;
              change(data, status: RxStatus.success());
              return true;
            }
          } else {
            isToLoadMore = false;
            return false;
          }
        }
      } else {
        isToLoadMore = false;
        return false;
      }
    } on Exception catch (e) {
      Get.showSnackbar(GetBar(
        message: "$e",
        duration: Duration(milliseconds: 3000),
      ));
    }
    return false;
  }

  Future<String?> doLogin(String email, String password) async {
    //Map<String, String> queryParams = {'username': email, 'password': password};
    try {
      change(null, status: RxStatus.loading());
      final result = await restClient.loginUser("user/" + email);

      if (result != null) {
        if (result is d.Response) {
          var data = GetUserNameResponse.fromJson(result.data).email;
          if (data != null) {
            print("Login response : $data");
            return data;
          } else {
            isToLoadMore = false;
            return null;
          }
        }
      } else {
        isToLoadMore = false;
        return null;
      }
    } on Exception catch (e) {
      Get.showSnackbar(GetBar(
        message: "Login Failed",
        duration: Duration(milliseconds: 3000),
      ));
    }
    return null;
  }

  Future<String> getUserName(String userName) async {
    try {
      change(null, status: RxStatus.loading());
      final result = await restClient.getUserName("user/" + userName);

      if (result != null) {
        if (result is d.Response) {
          var data = GetUserNameResponse.fromJson(result.data).username;
          if (data != null) {
            print("Login response : $data");
            return data;
          } else {
            isToLoadMore = false;
            return "";
          }
        }
      } else {
        isToLoadMore = false;
        return "UserName Not Available";
      }
    } on Exception catch (e) {
      Get.showSnackbar(GetBar(
        message: "$e",
        duration: Duration(milliseconds: 3000),
      ));
    }
    return "";
  }
}
