import 'package:flutter/material.dart';

import '../widget/basic_ink_well.dart';
import '../widget/basic_text.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    Size screenSize = MediaQuery.of(context).size;
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: Size(screenSize.width, 1000),
        child: Container(
          color: Colors.blue,
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: getTopBarMenu(
              context: context,
              screenSize: screenSize,
              title: "EXPLORE",
            ),
            /*child: Row(
              children: [
                const Text("EXPLORE"),
                Expanded(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      InkWell(
                        onTap: () {
                          debugPrint("You clicked on discover");
                        },
                        child: const Text(
                          "Discover",
                          style: TextStyle(
                            color: Colors.black,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),*/
          ),
        ),
      ),
      body: Container(),
    );
  }

  Widget getTopBarMenu({
    required BuildContext context,
    required Size screenSize,
    required String title,
  }) {
    //CustomTrace programInfo = CustomTrace(StackTrace.current);
    List<TopBarMenuProperty> topBarMenuList = [
      TopBarMenuProperty(
        label: "Discover",
        child: SizedBox(width: screenSize.width / 20),
        onTap: (context) {
          debugPrint("alfj");
        },
      ),
      TopBarMenuProperty(
        label: "Contact Us",
      ),
      TopBarMenuProperty(
        label: "Sign Up",
        child: SizedBox(width: screenSize.width / 50),
      ),
      TopBarMenuProperty(
        label: "Login",
      ),
    ];
    debugPrint(
        "DEBUG_LOG_PRINT: topBarMenuList length: ${topBarMenuList.length} ${toString()} Line - 69");
    return topBarGenerate(
      topBarMenuList: topBarMenuList,
      title: title,
    );
    //return const SizedBox.shrink();
  }

  Widget topBarGenerate({
    required List<TopBarMenuProperty> topBarMenuList,
    required String title,
  }) {
    List<Widget> widgetListOne = [];
    List<Widget> widgetListTwo = [];
    /*for (TopBarMenuProperty item in topBarMenuList) {
      Widget widget = BasicInkWell(
        onTap: item.onTap,
        child: BasicText(
          text: item.label,
        ),
      );
      widgetListOne.add(widget);
      item.child != null ? widgetListOne.add(item.child!) : const SizedBox.shrink();
    }*/
    for (int index = 0; index < topBarMenuList.length; index++) {
      Widget widget;
      if (index < 2) {
        widget = BasicInkWell(
          onTap: topBarMenuList[index].onTap,
          child: BasicText(
            text: topBarMenuList[index].label,
          ),
        );
        widgetListOne.add(widget);
        topBarMenuList[index].child != null
            ? widgetListOne.add(topBarMenuList[index].child!)
            : const SizedBox.shrink();
      } else {
        widget = BasicInkWell(
          onTap: topBarMenuList[index].onTap,
          child: BasicText(
            text: topBarMenuList[index].label,
          ),
        );
        widgetListTwo.add(widget);
        topBarMenuList[index].child != null
            ? widgetListTwo.add(topBarMenuList[index].child!)
            : const SizedBox.shrink();
      }
    }
    //
    List<Widget> widgetListFinal = [];
    //
    BasicText text = BasicText(
      label: title,
    );
    //
    Expanded expanded = Expanded(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: widgetListOne.toList(),
      ),
    );
    //
    widgetListFinal.add(text);
    widgetListFinal.add(expanded);
    for (Widget item in widgetListTwo) {
      widgetListFinal.add(item);
    }
    Widget widget = Row(
      children: widgetListFinal.toList(),
    );
    return widget;
  }
}

class TopBarMenuProperty {
  final String? label;
  final Widget? child;
  final bool isHovering;
  final Function(BuildContext context)? onTap;

  TopBarMenuProperty({
    this.label,
    this.child,
    this.onTap,
    this.isHovering = false,
  });
}
