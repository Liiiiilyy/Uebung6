### 题目要求：

### 任务1：

- 扩展 Field 类，使其具有一种方法 `boolean hasNeighbor(int, int, int)`，其中前两个参数是测试单元格的 `x` 和 `y` 坐标，第三个参数是测试的方向（0 到 3，具有常规含义）。该方法返回在给定位置和方向上是否存在与邻居的连接。这应该通过`位运算`直接从现有方法 getNeighborhood 的返回值确定，而无需循环或条件分支。需要解释实现的原理。
    - `neighborhood`
        - 1: (x+1, y)  ➡     0001
        - 2: (x, y+1)  ⬆     0010
        - 3: 1 + 2    ➡⬆  0011
        - 4: (x-1, y)   ⬅     0100
        - 8: (x, y-1)   ⬇     1000
    - <<左移
        - 1 << `direction` 0 ➡ = 0001
        - 1 << `direction` 1 ⬆= 0010
        - 1 << `direction` 2 ⬅= 0100
        - 1 << `direction` 3 ⬇= 1000
    - & : 按位与(只要有相同bit都为1时结果为1)
        - 1000 & 1000 = 1
        - 1001 & 0110 = 0
        - 1111 & 0010 = 1
    
    ```java
    boolean hasNeighbor(final int x, final int y, final int direction)
        {
            return (getNeighborhood(x * 2, y * 2) & 1 << direction) != 0;
        }
    ```
    
- [位运算相关知识补充](https://www.runoob.com/w3cnote/bit-operation.html)

### 任务2：

- 为 Field 类编写单元测试，可以使用构造函数与 hasNeighbor 方法测试。
- 这是一个测试场类的hasNeighbor()方法的Java测试类。场 "类表示一个二维的单元格场，每个单元格最多可以有四个邻居（上、下、左、右）。
    - 测试类有六个测试方法。
    - `testInside()`测试单元格在场内不同位置的所有四个邻居的情况。
    - `testOutside()`测试字段外不同位置没有邻居的单元。
    - `testNone()`测试在字段顶行没有邻居的单元格。
    - `testSingle()`测试在四个不同方向有一个邻居的单元格。
    - `testStraight()`测试在两个不同方向上有两个相反邻居的单元格。
    - `testL()`测试在L型方向上有两个相邻的单元格。
    - `testT()`。测试在T型方向上有三个邻居的单元。
    每个测试方法都创建了一个具有特定单元配置的Field对象，并针对不同单元调用hasNeighbor()方法。然后使用assertTrue()或assertFalse()方法将预期结果与实际结果进行比较。
- `assertFalse`
    - 将返回结果预设为`false`，如果结果为`false`，测试通过
- `assertTrue`
    - 将返回结果预设为`true`，如果结果为`true`，测试通过

```java
/*
在一个1x1的方格中测试在场外的访问，那里应该没有邻居。
*/
@Test
public void testOutside() {
final Field field = new Field(new String[] {"O"});
assertFalse(field.hasNeighbor(0, 0, 0));
assertFalse(field.hasNeighbor(0, 0, 1));
assertFalse(field.hasNeighbor(0, 0, 2));
assertFalse(field.hasNeighbor(0, 0, 3));
}
```

### 任务3：

- 扩展游戏，使角色和 NPC 只能朝着允许的网格方向移动。
- 在PI1Game类中构建了一个`field` ：[see](https://github.com/Liiiiilyy/Uebung6/blob/e15af90ce1cda583f326d31e59793742dd6af7c9/src/PI1Game.java#L17)
- 这个定义替换了以前单独创建的大部分对象。但是一些对象仍然保留（目标，桥梁，河流），因为它们目前还不能由Field类创建。
- 此外，仅对四个方向测试进行了扩展：[see](https://github.com/Liiiiilyy/Uebung6/blob/e15af90ce1cda583f326d31e59793742dd6af7c9/src/PI1Game.java#L38)
- 游戏人物下一个要走的目标没有`Neighbor` 就掉头。

```java
if (!field.hasNeighbor(avatar.getX(), avatar.getY(), avatar.getRotation())) {
            avatar.setRotation(avatar.getRotation() + 2);
```

- 和npc重合

```java
if (avatar.getX() == player.getX() && avatar.getY() == player.getY()) {
            player.setVisible(false);
            avatar.playSound("go-away");
        }
```

在PI1Game中，构造函数的调用发生了变化：

```java
final Walker walker1 = new Walker(new GameObject(1, 0, 2, "claudius"), field);
        final Walker walker2 = new Walker(new GameObject(0, 1, 0, "laila"), field);
        final Walker walker3 = new Walker(new GameObject(3, 2, 2, "child"), field);
        final GameObject player = new GameObject(0, 3, 0, "woman");

```
