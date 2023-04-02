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
            child: Row(
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
            ),
          ),
        ),
      ),
      body: Container(),
    );
  }

  Widget getTopBarMenu() {
    List<TopBarMenuProperty> topBarMenuList = [
      TopBarMenuProperty(
        label: "Discover",
      ),
      TopBarMenuProperty(
        label: "Contact Us",
      ),
      TopBarMenuProperty(
        label: "Sign Up",
      ),
      TopBarMenuProperty(
        label: "Login",
      ),
    ];
    return topBarGenerate(topBarMenuList: topBarMenuList);
  }

  Widget topBarGenerate({required List<TopBarMenuProperty> topBarMenuList}) {
    List<Widget> widgetList = [];
    for (TopBarMenuProperty item in topBarMenuList) {
      Widget widget = BasicInkWell(
        onTap: item.onTap,
        child: BasicText(
          text: item.label,
        ),
      );
      widgetList.add(widget);
    }
    return Row(
      children: widgetList.toList(),
    );
  }
}

class TopBarMenuProperty {
  final String? label;
  final Function(BuildContext context)? onTap;

  TopBarMenuProperty({
    this.label,
    this.onTap,
  });
}
