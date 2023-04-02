import 'package:flutter/material.dart';

class BasicInkWell extends StatelessWidget {
  final Widget? child;
  final Function(BuildContext context)? onTap;

  const BasicInkWell({this.child, this.onTap, Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        onTap!(context);
      },
      child: child,
    );
  }
}
