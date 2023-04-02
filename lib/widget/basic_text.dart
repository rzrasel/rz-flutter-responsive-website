import 'package:flutter/material.dart';

class BasicText extends StatelessWidget {
  final String? label;
  final String? text;
  final Color? fontColor;
  final TextAlign? textAlign;

  const BasicText({this.label, this.text, this.fontColor, this.textAlign, Key? key})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    String labelText = (() {
      if (!isNullOrEmpty(label)) {
        return label ?? "";
      } else if (!isNullOrEmpty(text)) {
        return text ?? "";
      }
      return "";
    }());
    return Text(
      labelText,
      textAlign: textAlign ?? TextAlign.left,
      style: TextStyle(
        color: fontColor ?? Colors.black,
      ),
    );
  }

  bool isNullOrEmpty(dynamic data) {
    List nulls = [null, "", "null", {}, [], "Null"];
    return nulls.contains(data);
  }
}
